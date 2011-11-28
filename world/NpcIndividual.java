import java.util.ArrayList;
import java.awt.*;

/*
 * @author tgriswol
 */
public class NpcIndividual implements Individual {
	private NpcDna dna;
	private Image sprite;
	private static Image[] icons;
	private int ID, age, currentAction, stepsRemaining, hunger, sleepiness;
	
	public NpcIndividual(){
		dna = new NpcDna();
	}
	
	public Dna getDna(){
		return dna;
	}
	public Image getImage(){
		return null; //Brian?
	}
	public int getAge(){
		return age;
	}
	public int getID(){
		return ID;
	}
	public int getCurrentAction(){
		return currentAction;
	}
	public int getStepsRemaining(){
		return stepsRemaining;
	}
	public int getHunger(){
		return hunger;
	}
	public int getSleepiness(){
		return sleepiness;
	}
	
	public void decreaseStepsRemaining(){
		stepsRemaining--;
	}
	public void increaseHunger(){
		hunger++;
	}
	public void decreaseHunger(int change){
		hunger -= change;
	}
	public void increaseSleepiness(){
		sleepiness++;
	}
	public void decreaseSleepiness(int change){
		sleepiness -= change;
	}
	public int chooseAction(ArrayList<Integer> availableActions){
		System.out.println("Returning a chosen action");
		return 0;
	}
}
