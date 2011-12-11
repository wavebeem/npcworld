public class Const {
    public static final int MALE   = 0;
    public static final int FEMALE = 1;

    public static final int PLAYING   = 0;
    public static final int EATING    = 1;
    public static final int SLEEPING  = 2;
    public static final int MATING    = 3;
    public static final int MIGRATING = 4;

    public static final int NUCLEOTIDES_SIZE                  = 14;
    public static final int GENDER_IDX                        = 0;
    public static final int EATING_DURATION_IDX               = 1;
    public static final int SLEEPING_DURATION_IDX             = 3;
    public static final int COLORIDX_IDX                      = 5;
    public static final int EATING_OR_SLEEPING_PREFERENCE_IDX = 8;
    public static final int MATING_OR_NEEDS_PREFERENCE_IDX    = 9;
    public static final int MIGRATION_CHANCE_IDX              = 10;

    public static final int UNIVERSE_ROWS = 4;
    public static final int UNIVERSE_COLS = 4;

    public static int LARGE_POPULATION = 100;

    public static final int INITIAL_POPULATION_SIZE =   50;
    public static final int MAX_POPULATION_SIZE     = 1000;

    public static final String ABOUT_TEXT =
    "<html>" + 
    "<b>Run Delay</b>: The amount of time between steps (in milliseconds)<br />" + 
    "<b>Death Chance</b>: The chance that an individual will die if it is younger than the \"old age\"<br />" + 
    "<b>Death Chance Change</b>: The amount by which an individual's death chance increases each step after it reaches \"old age\"<br />" + 
    "<b>Death Chance Max</b>: The maximum chance of death that an individual can ever reach<br />" + 
    "<b>Mutation Chance</b>: The chance of flipping each bit of the DNA during mutation<br />" + 
    "<b>Crossover Chance</b>: The chance of crossing over on each bit during crossover<br />" + 
    "<b>Old Age</b>: The age at which an individual's death chance starts increasing each step, and the age at which an individual will no longer migrate<br />" + 
    "<b>Young Age</b>: The age at which an individual will be allowed to mate and migrate<br />" + 
    "<b>Eating Capacity</b>: The maximum number of individuals that can be eating at one time<br />" + 
    "<b>Sleeping Capacity</b>: The maximum number of individuals that can be sleeping at one time<br />" + 
    "<b>Max Hunger</b>: The maximum hunger that an individual can endure before dying<br />" + 
    "<b>Max Sleepiness</b>: The maximum sleepiness that an individual can endure before dying<br />" + 
    "<b>Hunger Change</b>: The amount an individual's hunger is decreased for each step they spend eating<br />" + 
    "<b>Sleepiness Change</b>:  The amount an individual's sleepiness is decreased for each step they spend sleeping<br />" + 
    "<b>Healthiness Percent</b>: Based on an individual's hunger and sleepiness, the healthiness percent that an individual must meet in order to successfully mate<br />" + 
    "<b>Mating Frequency</b>: The number of steps an individual must wait between successfully mating and attempting to mate again<br />" + 
    "<b>Mating Frequency</b>: The number of steps an individual must wait between successfully migrating and attempting to migrate again<br />" + 
    "<b>Allow Migration</b>: Turns migration on/off<br />" + 
    "<br /><b>Copyright 2011 Brian Mock, Ashley Fisher, Trevor Griswold</b><br />" + 
    "</html>";
    }
