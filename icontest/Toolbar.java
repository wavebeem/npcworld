import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {
    private LayoutManager layout;

    private JButton buttonStep;
    private JButton buttonPause;
    private JButton buttonPrefs;
    private JButton buttonQuit;

    public Toolbar() {
        buttonStep  = new JButton("Step");
        buttonPause = new JButton("Pause");
        buttonPrefs = new JButton("Preferences");
        buttonQuit  = new JButton("Quit");

        add(buttonStep);
        add(buttonPause);
        add(buttonPrefs);
        add(buttonQuit);
    }
}
