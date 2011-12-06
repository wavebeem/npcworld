import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Grid extends JPanel {
    private final Color bgColor = new Color(0x222222);

    private final Color[] rainbow = {
        Color.RED,
        Color.ORANGE,
        Color.YELLOW,
        Color.GREEN,
        Color.CYAN,
        Color.BLUE,
        Color.MAGENTA,
    };

    private static final int gapH = 3;
    private static final int gapV = 3;

    private static final int borderW = 3;

    private int rows;
    private int cols;

    private LayoutManager layout;

    private JComponent[][] icons;

    private GUI gui;

    public Grid(int rows, int cols, GUI gui) {
        this.rows = rows;
        this.cols = cols;
        this.gui  = gui;

        icons = new Icon[rows][cols];

        layout = new GridLayout(rows, cols, gapH, gapV);
        setLayout(layout);

        /*
        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                int gender = Math.random() < 0.5 ? 0 : 1;
                Icon icon = new Icon(gender);
                icons[r][c] = icon;
                add(icon);
            }
        }
        */

        Iterator<NpcIndividual> it = ((NpcPopulation) gui.getWorld().getPopulation()).getIndividuals().iterator();
        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                if (it.hasNext()) {
                    JComponent icon = it.next().getWidget();
                    icons[r][c] = icon;
                    add(icon);
                }
            }
        }

        setBackground(bgColor);

        setBorder(Util.makeBorder(borderW));
    }
}
