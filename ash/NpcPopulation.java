/*
 * @author afisher
 */
public class NpcPopulation implements Population {
    private HashMap<Integer, Individual> individuals; // key is the ID#

    public int getMaxAge() {
        int max = 0;
        for (Individual i : individuals) {
            int cur = i.getAge();
            if (cur > max) max = cur;
        }
        return max;
    }

    public int getMinAge() {
        int min = Integer.MAX_VALUE;
        for (Individual i : individuals) {
            int cur = i.getAge();
            if (cur < min) min = cur;
        }
        return min;
    }

    public int getAverageAge() {
        int total = 0;
        for (Individual i : individuals) {
            total += i.getAge();
        }
        return total / individuals.size();
    }

    public int getSize() { return individuals.size(); }

    public void add(Individual i) {
        individuals.put(i.getID(), i);
    }

    public void remove(Individual i) {
        individuals.remove(i.getID());
    }

    public void remove(int id) {
        individuals.remove(id);
    }
}
