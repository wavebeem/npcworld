import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
/*
 * @author afisher
 */
public class NpcWorld implements World {
    private NpcPopulation population;

    private ArrayList<NpcIndividual> matingPoolMales;
    private ArrayList<NpcIndividual> matingPoolFemales;

    // current availability
    private int eatingAvailability;
    private int sleepingAvailability;
    private int matingAvailability;

    private int stepNumber;

    private boolean[] availableIDs = new boolean[Settings.maxSize];

    public NpcWorld() {
        population = new NpcPopulation();

        for (int i = 0; i < availableIDs.length; i++) {
            availableIDs[i] = true;
        }

        // initialize the population
        for (int i = 0; i < Settings.populationSize; i++) {
            population.add(new NpcIndividual(i));

            availableIDs[i] = false;
        }

        stepNumber = 0;

        eatingAvailability   = Settings.eatingCapacity;
        sleepingAvailability = Settings.sleepingCapacity;
        matingAvailability   = Settings.matingCapacity;
    }

    // genetic operators
    public Dna crossover(Dna d1, Dna d2) {
        boolean[] n1;
        boolean[] n2;

        // 50/50 chance of which DNA to start out with
        if (Math.random() < 0.5) {
            n1 = d1.getNucleotides();
            n2 = d2.getNucleotides();
        } else {
            n1 = d2.getNucleotides();
            n2 = d1.getNucleotides();
        }

        boolean[] newDNA = new boolean[n1.length];

        boolean crossover = true;

        for (int i = 0; i < newDNA.length; i++) {
            if (Math.random() < Settings.crossoverChance) {
                crossover = !crossover;
            }

            if (crossover) {
                newDNA[i] = n1[i];
            } else {
                newDNA[i] = n2[i];
            }
        }

        return new NpcDna(newDNA);
    }

    public Dna mutate(Dna d) {
        boolean[] nucleotides = d.getNucleotides();
        for (int i = 0; i < nucleotides.length; i++) {
            if (Math.random() < Settings.mutationChance) {
                nucleotides[i] = !nucleotides[i];
            }
        }
        return new NpcDna(nucleotides);
    }

    public void step() {
        System.out.println("Step number " + stepNumber);
        stepNumber++;

        ArrayList<Integer> keys = new ArrayList<Integer>(population.getKeys());
        Collections.shuffle(keys);

        eatingAvailability   = Settings.eatingCapacity;
        sleepingAvailability = Settings.sleepingCapacity;
        matingAvailability   = Settings.matingCapacity;

        // go through all of the individuals and decrement availabilities
        // if the individual is not done with its action
        for (Integer k : keys) {
            NpcIndividual curIndividual = (NpcIndividual)population.get(k);

            if (curIndividual.getStepsRemaining() != 0) {
                if (curIndividual.getCurrentAction() == Const.EATING) {
                    eatingAvailability--;
                } else if (curIndividual.getCurrentAction() == Const.SLEEPING) {
                    sleepingAvailability--;
                } else if (curIndividual.getCurrentAction() == Const.MATING) {
                    matingAvailability--;
                }
            }
        }

        matingPoolMales = new ArrayList<NpcIndividual>();
        matingPoolFemales = new ArrayList<NpcIndividual>();

        for (Integer k : keys) {
            NpcIndividual curIndividual = (NpcIndividual)population.get(k);

            // make the individual choose an action and act on it
            // based on the current state of the population
            if (curIndividual.getStepsRemaining() == 0) {
                ArrayList<Integer> actions = new ArrayList<Integer>();
                if (eatingAvailability > 0) {
                    actions.add(Const.EATING);
                }
                if (sleepingAvailability > 0) {
                    actions.add(Const.SLEEPING);
                }
                if (matingAvailability > 0) {
                    actions.add(Const.MATING);
                }

                int action = curIndividual.chooseAction(actions);

                //TODO update the individual's icon
                if (action == Const.EATING) {
                    eatingAvailability--;
                } else if (action == Const.SLEEPING) {
                    sleepingAvailability--;
                } else if (action == Const.MATING) {
                    matingAvailability--;
                    if (((NpcDna)curIndividual.getDna()).getGender() == Const.MALE) {
                        matingPoolMales.add(curIndividual);
                    } else {
                        matingPoolFemales.add(curIndividual);
                    }
                }
            }
            curIndividual.decreaseStepsRemaining();

            if (curIndividual.getCurrentAction() == Const.EATING) {
                curIndividual.decreaseHunger();
            } else if (curIndividual.getCurrentAction() == Const.SLEEPING) {
                curIndividual.decreaseSleepiness();
            }

            curIndividual.increaseHunger();
            curIndividual.increaseSleepiness();
            curIndividual.increaseAge();

            if (curIndividual.getHunger() > Settings.maxHunger ||
                curIndividual.getSleepiness() > Settings.maxSleepiness ||
                Math.random() < Settings.deathChance) {
                population.remove(curIndividual);

                availableIDs[curIndividual.getID()] = true;
            }

            if (Math.random() < chanceOfDeath(curIndividual.getAge())) {
                population.remove(curIndividual);

                availableIDs[curIndividual.getID()] = true;
            }
        }

        reproduce(); // mate the individuals who chose to mate
    }

    public void reproduce() {
        for (int i = 0; i < matingPoolMales.size(); i++) {
            if (i < matingPoolMales.size() && i < matingPoolFemales.size()) {
                NpcIndividual male   = matingPoolMales.get(i);
                NpcIndividual female = matingPoolFemales.get(i);

                Individual child = mate(male, female);
                if (child != null) {
                    population.add(child);

                    availableIDs[((NpcIndividual)child).getID()] = false;

                    male.mated();
                    female.mated();
                }
            }
        }
    }

    public NpcPopulation getPopulation() {
        return population;
    }

    public Individual mate(Individual i1, Individual i2) {
        Dna dna = mutate(crossover(i1.getDna(), i2.getDna()));

        for (int i = 0; i < availableIDs.length; i++) {
            if (availableIDs[i]) {
                return new NpcIndividual(i, (NpcDna)dna);
            }
        }

        return null;
    }

    public double chanceOfDeath(int age) {
        if (age < Settings.oldAge) return Settings.deathChance;

        int ageDiff = age - Settings.oldAge;
        double curDeathChance = Settings.deathChance + Settings.deathChanceChange * ageDiff;

        return curDeathChance < Settings.deathChanceMax ? curDeathChance : Settings.deathChanceMax;
    }
}
