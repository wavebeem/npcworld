import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Preferences extends JFrame {
    private static final String TITLE = "Preferences | NPC World";

    private LayoutManager layout;
    private Container     container;

    private GUI gui;

    private JPanel panel;

    private static final int gapH = 3;
    private static final int gapV = 3;
    private static final int pad  = 3;

    private JSpinner runDelaySpinner;
    private JSpinner fooSpinner;
    private JSpinner gooSpinner;
    private JSpinner pooSpinner;
    private JSpinner mooSpinner;

    public Preferences(GUI gui) {
        this.gui = gui;

        runDelaySpinner = new JSpinner();
        fooSpinner      = new JSpinner();
        gooSpinner      = new JSpinner();
        mooSpinner      = new JSpinner();
        pooSpinner      = new JSpinner();

        setTitle(TITLE);

        container = getContentPane();
        panel     = new JPanel();
        //container.setLayout(new GridLayout(0, 1, gapH, gapV));
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel);
        panel.setLayout(new GridLayout(0, 4, gapH, gapV));
        panel.setBorder(Util.makeBorder(pad));

        addPair("Run delay:", runDelaySpinner);
        addPair("Foo you:",   fooSpinner);

        addPair("Goo balls:", gooSpinner);
        addPair("Poop:",      pooSpinner);

        addPair("Moo moo:",   mooSpinner);
        addPair("",           new ZeroPaddingComponent());

        panel.add(new ZeroPaddingComponent());
        panel.add(Util.clickableButton("OK",     new OkHandler()));
        panel.add(Util.clickableButton("Cancel", new CancelHandler()));
        panel.add(new ZeroPaddingComponent());

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
        runDelaySpinner.setValue(gui.getRunDelay());
    }

    private void saveCurrentValues() {
        gui.setRunDelay(Util.intFromSpinner(runDelaySpinner));
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
