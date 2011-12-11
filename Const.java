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
    "<b>Copyright 2011 Brian Mock, Ashley Fisher, Trevor Griswold</b><br />" +   
    "<br/>" + 
    "<b><u>How to use the GUI:</b></u><br/>" + 
    "<b>Step Button:</b> Advances the simulation one timestep.<br/>" + 
    "<b>Run / Pause Button:</b> Repeatededly advances the simulation by one timestep or pauses the simulation if it is already running.<br/>" + 
    "<b>Preferences:</b> Opens up a window to change nearly every value related to the simulation. Descriptions of each modifiable preference are listed below.<br/>" + 
    "<b>About Button:</b> Opens this window.<br/>" + 
    "<b>Quit Button:</b> Closes the program.<br/>" + 
    "<b>World Selection Grid:</b> Click any square in the grid to display the population of that world. The currently selected world is designated by the white border around the square.<br/>" + 
    "<br/>" + 
    "<b><u>Description of the GUI:</b></u> (Description of the simulation is below)<br/>" + 
    "The main window displays the population (as many as will fit onscreen) of the world. Each individual is represented by one rectangle in the main window. The mage with a hood represents a female, and the mage with pointy hat represents a male, and the number in the top left of each rectangle is the individual's age. The icon in the bottom left of the rectangle is the action the individual is performing, and the action is also represented by the background color of the rectangle: green for eating, blue for sleeping, and red for looking to mate.<br/>" + 
    "The smaller window with the smaller grid displays all the current worlds, with one square for each world. The color of the square changes depending on how many individuals are in the world, and what the ratio between males and females in the population are. The color tends towards red if there are more males and towards blue if there are more females, and gets darker the smaller the population is.<br/>" + 
    "<br/>" + 
    "<b><u>Description of the Simuation:</b></u><br/>" + 
    "NPC World is a simulaton of simple individuals. The simulation is composed of a collection of worlds, each which has a population of individuals. Individuals have three main attributes: hunger, exhaustion, and age. The simulation works in timesteps: each step an individual's hunger, age, and exhaustion increase, and each individual chooses an action to perform. Each world has its own eating capacity and sleeping capacity which specify how many individuals can eat and sleep each timestep. Individuals can choose (given that there is availability for these actions in the individual's current world) to  eat, sleep, look for a mate, migrate to an adjacent world, or play (do nothing) each timestep. Individuals have a DNA string which determines there tendencies for certain actions, such as how long they sleep, how long they spend eating each meal, the order of importance of their actions, and how likely they are migrate to another world. When an individual chooses to look for a mate, they must successfully find another individual of the opposite gender who is also looking for a mate, and if they are successful a newborn will be created with a crossover of the two parent's DNA strings with mutation. Individuals have a set chance to die each step that increases (to a maximum %) after a certain age. Nearly every value related to the simulation is modifiable in the preferences window.<br />" +
    "<br/>" + 
    "<b><u>Descriptions of modifiable preferences:</b></u><br />" + 
    "<b>Run Delay</b>: The amount of time between steps (in milliseconds)<br />" + 
    "<b>Allow Migration</b>: Turns migration on/off<br />" + 
    "<b>Death Chance</b>: The chance that an individual will die if it is younger than the \"old age\"<br />" + 
    "<b>Death Chance Max</b>: The maximum chance of death that an individual can ever reach<br />" + 
    "<b>Death Chance Change</b>: The amount by which an individual's death chance increases each step after it reaches \"old age\"<br />" + 
    "<b>Mutation Chance</b>: The chance of flipping each bit of the DNA during mutation<br />" + 
    "<b>Crossover Chance</b>: The chance of crossing over on each bit during crossover<br />" + 
    "<b>Eating Capacity</b>: The maximum number of individuals that can be eating at one time<br />" + 
    "<b>Sleeping Capacity</b>: The maximum number of individuals that can be sleeping at one time<br />" + 
    "<b>Old Age</b>: The age at which an individual's death chance starts increasing each step, and the age at which an individual will no longer migrate<br />" + 
    "<b>Young Age</b>: The age at which an individual will be allowed to mate and migrate<br />" + 
    "<b>Max Hunger</b>: The maximum hunger that an individual can endure before dying<br />" + 
    "<b>Max Exhaustion</b>: The maximum exhaustion that an individual can endure before dying<br />" + 
    "<b>Hunger Change</b>: The amount an individual's hunger is decreased for each step they spend eating<br />" + 
    "<b>Exhaustion Change</b>:  The amount an individual's exhaustion is decreased for each step they spend sleeping<br />" + 
    "<b>Healthiness Percent</b>: Based on an individual's hunger and exhaustion, the healthiness percent that an individual must meet in order to successfully mate<br />" + 
    "<b>Mating Frequency</b>: The number of steps an individual must wait between successfully mating and attempting to mate again<br />" + 
    "<b>Migrating Frequency</b>: The number of steps an individual must wait between successfully migrating and attempting to migrate again<br />" + 
    "</html>";
    }
