/**
 * @author Brian Mock (mock.brian@gmail.com)
 */

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class Grid extends JPanel {
    private final Color bgColor = new Color(0x222222);

    private static final int gapH = 3;
    private static final int gapV = 3;

    private static final int borderW = 3;

    private int rows;
    private int cols;
    private int size;

    private LayoutManager layout;

    private List<Icon> icons;

    private GUI gui;

    public Grid(int rows, int cols, GUI gui) {
        this.rows = rows;
        this.cols = cols;
        this.size = rows * cols;
        this.gui  = gui;

        icons = new ArrayList<Icon>(rows * cols);

        layout = new GridLayout(rows, cols, gapH, gapV);
        setLayout(layout);

        setBackground(bgColor);

        setBorder(Util.makeBorder(borderW));

        for (int x=0; x < size; x++) {
            icons.add(new Icon(null));
        }

        for (Icon icon: icons) {
            add(icon);
        }

        populateGrid();
    }

    public void repopulate() {
        populateGrid();
    }

    private synchronized void populateGrid() {
        clearGrid();

        NpcWorld world;
        NpcPopulation pop;
        Collection<NpcIndividual> inds;
        Iterator<NpcIndividual> it;

        world = (NpcWorld) gui.getWorld();
        pop   = world.getPopulation();
        inds  = pop.getIndividuals();
        it    = inds.iterator();

        int x = 0;
        while (x < size && it.hasNext()) {
            NpcIndividual thing;
            int idx;

            thing = it.next();
            idx   = thing.getID();

            if (idx < size) {
                icons.get(idx).setIndividual(thing);
            }

            x++;
        }

        repaint();
    }

    private synchronized void clearGrid() {
        for (Icon icon: icons) {
            icon.setIndividual(null);
        }
    }
}
