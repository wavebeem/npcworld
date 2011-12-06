import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Preferences extends JFrame {
    private LayoutManager layout;
    private Container     container;

    private GUI gui;

    private JPanel panel;

    private static final int gapH = 3;
    private static final int gapV = 3;
    private static final int pad  = 3;

    public Preferences(GUI gui) {
        this.gui = gui;

        container = getContentPane();
        panel     = new JPanel();
        //container.setLayout(new GridLayout(0, 1, gapH, gapV));
        //container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(panel);
        panel.setLayout(new GridLayout(0, 2, gapH, gapV));
        panel.setBorder(Util.makeBorder(pad));

        panel.add(Util.rightLabel("Click here:"));
        panel.add(new JSpinner());
        panel.add(Util.rightLabel("And here:"));
        panel.add(new JSpinner());

        panel.add(Util.clickableButton("OK",     new OkHandler()));
        panel.add(Util.clickableButton("Cancel", new CancelHandler()));

        setResizable(false);

        pack();

        setVisible(true);
    }

    private class OkHandler implements ActionListener {
        public void actionPerformed(ActionEvent e) {
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
