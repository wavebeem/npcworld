/*
 * @author afisher
 */
public interface Population {
    public int getMaxAge();
    public int getMinAge();
    public int getAverageAge();
    public int getSize();

    public void add   (Individual i);
    public void remove(Individual i);
    public void remove(int id);
}
