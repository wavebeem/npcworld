import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Toolbar extends JPanel {
    private LayoutManager layout;

    private JButton buttonStep;
    private JButton buttonPlay;
    private JButton buttonPrefs;
    private JButton buttonQuit;

    private GUI   gui;
    private World world;

    public Toolbar(GUI gui) {
        this.gui   = gui;
        this.world = gui.getWorld();

        buttonStep  = Util.clickableButton("Step",        new StepAction());
        buttonPlay  = Util.clickableButton("Play/Pause",  new PlayAction());
        buttonPrefs = Util.clickableButton("Preferences", new PreferencesAction());
        buttonQuit  = Util.clickableButton("Quit",        new QuitAction());

        add(buttonStep);
        add(buttonPlay);
        add(buttonPrefs);
        add(buttonQuit);
    }

    private class StepAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("stepping...");
            gui.pause();
            gui.step();
        }
    }

    private class PlayAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gui.toggleRunning();

            boolean running = gui.isRunning();
            Debug.echo(running? "pausing...": "playing...");

            if (running) disableOtherButtons();
            else          enableOtherButtons();
        }
    }

    private void disableOtherButtons() {
        buttonStep .setEnabled(false);
        buttonPrefs.setEnabled(false);
    }

    private void enableOtherButtons() {
        buttonStep .setEnabled(true);
        buttonPrefs.setEnabled(true);
    }

    private class PreferencesAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            Debug.echo("preferences...");
            gui.pause();
            new Preferences(gui);
        }
    }

    private class QuitAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
