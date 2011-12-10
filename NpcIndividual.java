import java.util.ArrayList;
import java.awt.*;
import javax.swing.JComponent;

/*
 * @author tgriswol
 */
public class NpcIndividual implements Individual, Comparable {
    private NpcDna dna;
    private int ID, age, currentAction, stepsRemaining, hunger, sleepiness, stepsUntilMating;
    private Icon icon;

    public NpcIndividual(int ID){
        this.ID = ID;
        dna = new NpcDna();
        init();
    }
    public NpcIndividual(int ID, NpcDna dna) {
        this.ID = ID;
        this.dna = dna;
        init();
    }
    
    private void init(){
        icon = new Icon(dna.getGender());
        icon.colorize(dna.getColor());
        age = 0;
        hunger = 0;
        sleepiness = 0;
        stepsUntilMating = 0;
        stepsRemaining = 0;
    }
    public int compareTo(Object o){ //Compares individuals by ID
        int otherID = ((NpcIndividual) o).getID();
        return ((Integer) ID).compareTo(otherID);
    }

    public Dna getDna(){
        return dna;
    }
    public JComponent getWidget() {
        return icon;
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

    public void increaseAge(){
        age++;
        icon.happyBirthday();
        if(stepsUntilMating > 0)
            stepsUntilMating--;
    }
    public void decreaseStepsRemaining(){
        stepsRemaining--;
    }
    public void increaseHunger(){
        hunger++;
    }
    public void decreaseHunger(){
        hunger -= Settings.hungerChange;
    }
    public void increaseSleepiness(){
        sleepiness++;
    }
    public void decreaseSleepiness(){
        sleepiness -= Settings.sleepinessChange;
    }
    public void mated(){
        stepsUntilMating = Settings.matingFrequency;
    }
    
    public int chooseAction(ArrayList<Integer> actions){
        //If it doesn't choose to do anything, then play
        if (!chooseEating(actions)
        &&  !chooseSleeping(actions)
        &&  !chooseMating(actions)
        &&  !chooseMigration(actions))
        {
            stepsRemaining = 1;
            currentAction = Const.PLAYING;
        }
        
        Debug.echo("Returning a chosen action of "+currentAction+" with "+stepsRemaining+" steps remaining.");
        icon.setAction(currentAction);
        return currentAction;
    }
    public boolean chooseEating(ArrayList<Integer> actions){
        if (actions.contains(Const.EATING) 
        &&  hunger >= (dna.getEatingDuration() * Settings.hungerChange))
        { 
            stepsRemaining = dna.getEatingDuration();
            currentAction = Const.EATING;
            return true;
        }
        return false;
    }
    public boolean chooseSleeping(ArrayList<Integer> actions){
        if (actions.contains(Const.SLEEPING)  
        &&  sleepiness >= (dna.getSleepingDuration() * Settings.sleepinessChange))
        {
            stepsRemaining = dna.getSleepingDuration();
            currentAction = Const.SLEEPING;
            return true;
        }
        return false;
    }
    public boolean chooseMating(ArrayList<Integer> actions){
        if (actions.contains(Const.MATING)                                          
        &&  stepsUntilMating == 0
        &&  age > Settings.youngAge
        &&  hunger < ((0.01)*Settings.maxHunger*(100 - Settings.healthinessPercent))
        &&  sleepiness < ((0.01)*Settings.maxSleepiness*(100 - Settings.healthinessPercent))) 
        {
            stepsRemaining = 1;
            currentAction = Const.MATING;
            return true;
        }
        return false;
    }
    public boolean chooseMigration(ArrayList<Integer> actions) {
        if (false) {
            return true;
        }
        return false;
    }
}
