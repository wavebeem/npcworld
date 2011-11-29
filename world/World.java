/*
 * @author afisher
 */
public interface World {
    public Population getPopulation();

    public Dna crossover(Dna d1, Dna d2);
    public Dna mutate(Dna d);

    public Population merge(Population p1, Population p2);

    public void step();
    public void reproduce();

    public Individual mate(Individual i1, Individual i2);
}
