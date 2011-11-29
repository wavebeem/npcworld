import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Toolbar extends JPanel {
    private LayoutManager layout;

    private JButton buttonStep;
    private JButton buttonPause;
    private JButton buttonPrefs;
    private JButton buttonQuit;

    private GUI gui;

    public Toolbar(GUI gui) {
        this.gui = gui;

        buttonStep  = Util.clickableButton("Step",        new StepAction());
        buttonPause = Util.clickableButton("Pause",       new PauseAction());
        buttonPrefs = Util.clickableButton("Preferences", new PreferencesAction());
        buttonQuit  = Util.clickableButton("Quit",        new QuitAction());

        add(buttonStep);
        add(buttonPause);
        add(buttonPrefs);
        add(buttonQuit);
    }

    private class StepAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("stepping...");
        }
    }

    private class PauseAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("pausing...");
        }
    }

    private class PreferencesAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("preferences...");
            new Preferences(gui);
        }
    }

    private class QuitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
