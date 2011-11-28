/*
 * @author tgriswol
 */
public class NpcDna implements Dna {
	private boolean[] nucleotides;
	private int eatingDuration, sleepDuration, gender;
	
	public NpcDna(){
		nucleotides = new boolean[1];
		initEatingDuration();
		initSleepDuration();
		initGender();
	}
	
	private void initEatingDuration(){
		System.out.println("Initializing the EatingDuration base of nucleotides.");
		eatingDuration = 0;
	}
	private void initSleepDuration(){
		System.out.println("Initializing the SleepDuration base of nucleotides.");
		sleepDuration = 0;
	}
	private void initGender(){
		System.out.println("Initializing the Gender base of nucleotides.");
		gender = 0;
	}
	
	public int getEatingDuration(){
		return eatingDuration;
	}
	public int getSleepDuration(){
		return sleepDuration;
	}
	public int getGender(){
		return gender;
	}
    public boolean[] getNucleotides(){
		boolean[] nucleotidesCopy = new boolean[nucleotides.length];
		System.arraycopy(nucleotides, 0, nucleotidesCopy, 0, nucleotides.length);
		return nucleotidesCopy;
	}
}
