import javax.swing.*;
import java.awt.*;

public class Infobar extends JPanel {
    private static final int gapV = 3;
    private static final int gapH = 3;

    private static final int borderW = 3;

    private LayoutManager layout;

    private GUI gui;

    private static final String[][] fields = {
        {"Population size:", "<null>"},
        {"Oldest age:",      "<null>"},
        {"Average age:",     "<null>"},
        {"Youngest age:",    "<null>"},
    };

    private JLabel[] valueLabels;

    public Infobar(GUI gui) {
        this.gui = gui;

        layout = new GridLayout(0, 4, gapH, gapV);

        setLayout(layout);

        valueLabels = new JLabel[fields.length];

        int i = 0;
        for (String[] pair: fields) {
            final String a = pair[0];
            final String b = pair[1];

            JLabel c = Util.rightLabel(a);
            JLabel d = Util.leftLabel(b);

            valueLabels[i] = d;

            add(c);
            add(d);

            i++;
        }

        setBorder(Util.makeBorder(borderW));
    }

    public void fillInfo() {
        Population pop = gui.getWorld().getPopulation();

        // Change the label values here... TODO
        valueLabels[0].setText("" + pop.getSize());
        valueLabels[1].setText("" + pop.getMaxAge());
        valueLabels[2].setText("" + pop.getAverageAge());
        valueLabels[3].setText("" + pop.getMinAge());
    }
}
