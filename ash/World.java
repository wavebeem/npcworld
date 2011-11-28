/*
 * @author afisher
 */
public interface World {
    private Dna crossover(Dna d1, Dna d2);
    private Dna mutate(Dna d);

    private Population merge(Population p1, Population p2);

    private void step();
    private void run();
    private void reproduce();

    private Individual mate(Individual i1, Individual i2);
}
