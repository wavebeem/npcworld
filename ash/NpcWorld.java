/*
 * @author afisher
 */
public class NpcWorld implements World {
    private Population population;

    private boolean running;
    private int     runSpeed;

    private int oldAge;

    private double mutationPercent;
    private double crossoverPercent;
    private double deathChance;

    private Dna crossover(Dna d1, Dna d2) {
        System.out.println("Crossing over!");
        return null;
    }

    private Dna mutate(Dna d) {
        System.out.println("Mutating some DNA!");
        return null;
    }

    private Population merge(Population p1, Population p2) {
        System.out.println("Merging two populations");
        return null;
    }

    private void step() {
        System.out.println("One step");
    }

    private void run() {
        System.out.println("Running the simulation");
    }

    private void reproduce() {
        System.out.println("Reproducing");
    }

    private void toggleRunning() {
        running = !running;
    }

    private Individual mate(Individual i1, Individual i2) {
        System.out.println("Mating two individuals);
        return null;
    }
}
