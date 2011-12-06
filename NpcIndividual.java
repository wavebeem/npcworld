import java.util.ArrayList;
import java.awt.*;
import javax.swing.JComponent;

/*
 * @author tgriswol
 */
public class NpcIndividual implements Individual {
    private static int youngAge = Settings.YOUNG_AGE;
    private static int maxSleepiness = Settings.MAX_SLEEPINESS;
    private static int maxHunger = Settings.MAX_HUNGER;
    private static int healthinessPercent = Settings.HEALTHINESS_PERCENT;
    private static int matingFrequency = Settings.MATING_FREQUENCY;
    private static int hungerChange = Settings.HUNGER_CHANGE;
    private static int sleepinessChange = Settings.SLEEPINESS_CHANGE;
    private NpcDna dna;
    private int ID, age, currentAction, stepsRemaining, hunger, sleepiness, stepsUntilMating;
    private Icon icon;

    public NpcIndividual(int ID){
        this.ID = ID;
        dna = new NpcDna();
        icon = new Icon(dna.getGender());
        icon.colorize(dna.getColor());
        age = 0;
        hunger = 0;
        sleepiness = 0;
        stepsUntilMating = 0;
        stepsRemaining = 0;
    }

    public NpcIndividual(int ID, NpcDna dna) {
        this.ID = ID;
        this.dna = dna;
        icon = new Icon(dna.getGender());
        icon.colorize(dna.getColor());
        age = 0;
        hunger = 0;
        sleepiness = 0;
        stepsUntilMating = 0;
        stepsRemaining = 0;
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

    public void setHungerChange(int change){
        hungerChange = change;
    }
    public void setSleepinessChange(int change){
        sleepinessChange = change;
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
        hunger -= hungerChange;
    }
    public void increaseSleepiness(){
        sleepiness++;
    }
    public void decreaseSleepiness(){
        sleepiness -= sleepinessChange;
    }
    public void mated(){
        stepsUntilMating = matingFrequency;
    }
    public int chooseAction(ArrayList<Integer> availableActions){
        if (availableActions.contains(Const.EATING) && hunger >= (dna.getEatingDuration() * hungerChange)){ //If eating is available, and you are hungry enough to eat
            stepsRemaining = dna.getEatingDuration();
            currentAction = Const.EATING;
        } else if (availableActions.contains(Const.SLEEPING) && sleepiness >= (dna.getSleepingDuration() * sleepinessChange)){ //If sleeping is available, and you are sleepy enough to sleep
            stepsRemaining = dna.getSleepingDuration();
            currentAction = Const.SLEEPING;
        } else if ( availableActions.contains(Const.MATING) &&                          //If mating is available,
                    stepsUntilMating == 0 &&                                            //and you haven't mated recently
                    age > youngAge &&                                                   //and you are atlease a certain age
                    hunger < ((0.01)*maxHunger*(100 - healthinessPercent)) &&           //and you are not healthynessPercent hungry
                    sleepiness < ((0.01)*maxSleepiness*(100 - healthinessPercent))) {   //and you are not healthynessPercent sleepy
            stepsRemaining = 1;
            currentAction = Const.MATING;
        } else { //Otherwise, just play
            stepsRemaining = 1;
            currentAction = Const.PLAYING;
        }
        
        Debug.echo("Returning a chosen action of "+currentAction+" with "+stepsRemaining+" steps remaining.");
        icon.setAction(currentAction);
        return currentAction;
    }
}
