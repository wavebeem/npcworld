import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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

        populateGrid();

        setBackground(bgColor);

        setBorder(Util.makeBorder(borderW));
    }

    public void repopulate() {
        clearGrid();
        populateGrid();
    }

    private void populateGrid() {
        clearGrid();

        World world;
        NpcPopulation pop;
        Collection<NpcIndividual> inds;
        List<NpcIndividual> linds;
        Iterator<NpcIndividual> it;

        world = gui.getWorld();
        pop   = (NpcPopulation) world.getPopulation();
        inds  = pop.getIndividuals();
        linds = new ArrayList(inds);
        Collections.sort(linds);
        it    = linds.iterator();
        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                if (it.hasNext()) {
                    JComponent icon = it.next().getWidget();
                    icons[r][c] = icon;
                    add(icon);
                }
            }
        }
    }

    private void clearGrid() {
        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                JComponent icon = icons[r][c];
                icons[r][c] = null;
                if (icon != null) {
                    remove(icon);
                }
            }
        }
    }
}
