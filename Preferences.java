import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Preferences extends JDialog {
    private static final String TITLE = "Preferences | NPC World";

    private LayoutManager layout;
    private Container     container;

    private GUI gui;

    private JPanel panel;
    private JPanel okPanel;

    private static final int gapH = 3;
    private static final int gapV = 3;
    private static final int pad  = 3;

    private JSpinner _runDelay;

    private JSpinner _deathChance;
    private JSpinner _deathChanceMax;
    private JSpinner _deathChanceChange;

    private JSpinner _mutationChance;
    private JSpinner _crossoverChance;

    private JSpinner _oldAge;
    private JSpinner _youngAge;

    private JSpinner _eatingCapacity;
    private JSpinner _sleepingCapacity;

    private JSpinner _maxHunger;
    private JSpinner _maxExhaustion;

    private JSpinner _hungerChange;
    private JSpinner _exhaustionChange;

    private JSpinner _healthinessPercent;

    private JSpinner _matingFrequency;
    private JSpinner _migratingFrequency;

    private JCheckBox _migrationEnabled;

    public Preferences(GUI gui) {
        // true means make it modal
        super((Frame) null, true);

        this.gui = gui;

        _runDelay = new JSpinner();

        _deathChance        = new JSpinner();
        _deathChanceMax     = new JSpinner();
        _deathChanceChange  = new JSpinner();

        _mutationChance     = new JSpinner();
        _crossoverChance    = new JSpinner();

        _oldAge   = new JSpinner();
        _youngAge = new JSpinner();

        _eatingCapacity   = new JSpinner();
        _sleepingCapacity = new JSpinner();

        _maxHunger     = new JSpinner();
        _maxExhaustion = new JSpinner();

        _hungerChange     = new JSpinner();
        _exhaustionChange = new JSpinner();

        _healthinessPercent = new JSpinner();

        _matingFrequency = new JSpinner();
        _migratingFrequency = new JSpinner();

        _migrationEnabled = new JCheckBox();

        setTitle(TITLE);

        container = getContentPane();
        panel     = new JPanel();
        okPanel   = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel);
        container.add(okPanel);
        panel.setLayout(new GridLayout(0, 4, gapH, gapV));
        panel.setBorder(Util.makeBorder(pad));

        addPair("Run Delay:", _runDelay);

        addPair("Death Chance:", _deathChance);
        addPair("Death Chance Max:", _deathChanceMax);
        addPair("Death Chance Change:", _deathChanceChange);

        addPair("Mutation Chance:", _mutationChance);
        addPair("Crossover Chance:", _crossoverChance);

        addPair("Old Age:", _oldAge);
        addPair("Young Age:", _youngAge);

        addPair("Eating Capacity:", _eatingCapacity);
        addPair("Sleeping Capacity:", _sleepingCapacity);

        addPair("Max Hunger:", _maxHunger);
        addPair("Max Exhaustion:", _maxExhaustion);

        addPair("Hunger Change:", _hungerChange);
        addPair("Exhaustion Change:", _exhaustionChange);

        addPair("Healthiness Percent:", _healthinessPercent);

        addPair("Mating Frequency:", _matingFrequency);
        addPair("Migrating Frequency:", _migratingFrequency);
        addPair("Allow Migration:", _migrationEnabled);

        okPanel.add(Util.clickableButton("OK",     new OkHandler()));
        okPanel.add(Util.clickableButton("Cancel", new CancelHandler()));

        setResizable(false);

        pack();

        loadCurrentValues();

        setVisible(true);
    }

    private void addPair(String label, JComponent component) {
        panel.add(Util.rightLabel(label));
        panel.add(component);
    }

    private void loadCurrentValues() {
        _runDelay.setValue(Settings.runDelay);

        _deathChance.setValue((int) (Settings.deathChance * 100));
        _deathChanceMax.setValue((int) (Settings.deathChanceMax * 100));
        _deathChanceChange.setValue((int) (Settings.deathChanceChange * 100));

        _mutationChance.setValue((int) (Settings.mutationChance * 100));
        _crossoverChance.setValue((int) (Settings.crossoverChance * 100));

        _oldAge.setValue(Settings.oldAge);
        _youngAge.setValue(Settings.youngAge);

        _eatingCapacity.setValue(Settings.eatingCapacity);
        _sleepingCapacity.setValue(Settings.sleepingCapacity);

        _maxHunger.setValue(Settings.maxHunger);
        _maxExhaustion.setValue(Settings.maxExhaustion);

        _hungerChange.setValue(Settings.hungerChange);
        _exhaustionChange.setValue(Settings.exhaustionChange);

        _healthinessPercent.setValue(Settings.healthinessPercent);

        _matingFrequency.setValue(Settings.matingFrequency);
        _migratingFrequency.setValue(Settings.migratingFrequency);

        _migrationEnabled.setSelected(Settings.migrationEnabled);
    }

    private void saveCurrentValues() {
        Settings.runDelay = Util.intFromSpinner(_runDelay);

        Settings.deathChance = Util.intFromSpinner(_deathChance) / 100.0;
        Settings.deathChanceMax = Util.intFromSpinner(_deathChanceMax) / 100.0;
        Settings.deathChanceChange = Util.intFromSpinner(_deathChanceChange) / 100.0;

        Settings.mutationChance = Util.intFromSpinner(_mutationChance) / 100.0;
        Settings.crossoverChance = Util.intFromSpinner(_crossoverChance) / 100.0;

        Settings.oldAge = Util.intFromSpinner(_oldAge);
        Settings.youngAge = Util.intFromSpinner(_youngAge);

        Settings.eatingCapacity = Util.intFromSpinner(_eatingCapacity);
        Settings.sleepingCapacity = Util.intFromSpinner(_sleepingCapacity);

        Settings.maxHunger = Util.intFromSpinner(_maxHunger);
        Settings.maxExhaustion = Util.intFromSpinner(_maxExhaustion);

        Settings.hungerChange = Util.intFromSpinner(_hungerChange);
        Settings.exhaustionChange = Util.intFromSpinner(_exhaustionChange);

        Settings.healthinessPercent = Util.intFromSpinner(_healthinessPercent);

        Settings.matingFrequency = Util.intFromSpinner(_matingFrequency);
        Settings.migratingFrequency = Util.intFromSpinner(_migratingFrequency);

        Settings.migrationEnabled = _migrationEnabled.isSelected();
    }

    private class OkHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            saveCurrentValues();
            Debug.echo("ok...");
            dispose();
        }
    }

    private class CancelHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("cancel...");
            dispose();
        }
    }
}
