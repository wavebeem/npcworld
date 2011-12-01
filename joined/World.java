/*
 * @author afisher
 */
public interface World {
    public Dna crossover(Dna d1, Dna d2);
    public Dna mutate(Dna d);

    public Population merge(Population p1, Population p2);

    public Population getPopulation();

    public void step();
    public void reproduce(ArrayList<Individual> males, ArrayList<Individual> females);

    public Individual mate(Individual i1, Individual i2);
}
