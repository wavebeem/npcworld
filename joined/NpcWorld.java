import java.util.ArrayList;
import java.util.Set;
import java.util.Collections;
/*
 * @author afisher
 */
public class NpcWorld implements World {
    private NpcPopulation population;

    private int oldAge;

    private double mutationChance;
    private double crossoverChance;
    private double deathChance;

    // max capacities
    private int eatingCapacity;
    private int sleepingCapacity;
    private int matingCapacity;

    // current availability
    private int eatingAvailability;
    private int sleepingAvailability;
    private int matingAvailability;

    private int stepNumber;

    public NpcWorld() {
        population = new NpcPopulation();

        oldAge = 100;

        stepNumber = 0;

        mutationChance  = 0.1;
        crossoverChance = 0.1;
        deathChance     = 0.01;

        eatingCapacity   = 10;
        sleepingCapacity = 10;
        matingCapacity   = 10;

        eatingAvailability   = eatingCapacity;
        sleepingAvailability = sleepingCapacity;
        matingAvailability   = matingCapacity;
    }

    // setters for capacities
    public void setEatingCapacity(int c) {
        eatingCapacity = c;
    }

    public void setSleepingCapacity(int c) {
        sleepingCapacity = c;
    }

    public void setMatingCapacity(int c) {
        matingCapacity = c;
    }

    // genetic operators
    public Dna crossover(Dna d1, Dna d2) {
        System.out.println("Crossing over!");
        return null;
    }

    public Dna mutate(Dna d) {
        System.out.println("Mutating some DNA!");
        return null;
    }

    public Population merge(Population p1, Population p2) {
        System.out.println("Merging two populations");
        return null;
    }

    public void step() {
        System.out.println("Step number " + stepNumber);
        stepNumber++;

        ArrayList<Integer> keys = new ArrayList<Integer>(population.getKeys());
        Collections.shuffle(keys);

        for (Integer k : keys) {
            Individual curIndividual = population.get(k);

            // make the individual choose an action and act on it
            // based on the current state of the population
        }
    }

    public void reproduce() {
        System.out.println("Reproducing");
    }

    public Population getPopulation() {
        return population;
    }

    public Individual mate(Individual i1, Individual i2) {
        System.out.println("Mating two individuals");
        return null;
    }
}
