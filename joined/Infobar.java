import javax.swing.*;
import java.awt.*;

public class Infobar extends JPanel {
    private static final int gapV = 3;
    private static final int gapH = 3;

    private static final int borderW = 3;

    private LayoutManager layout;

    private static final String[] items = {
        "Population size:",
        "Oldest age:",
        "Average age:",
        "Youngest age:",
    };

    public Infobar() {
        layout = new GridLayout(0, 4, gapH, gapV);

        setLayout(layout);

        for (String item: items) {
            add(Util.rightLabel(item));
            add(Util.leftLabel("<null>"));
        }

        setBorder(Util.makeBorder(borderW));
    }
}
