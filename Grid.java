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
    private int size;

    private LayoutManager layout;

    private List<JComponent> icons;

    private GUI gui;

    public Grid(int rows, int cols, GUI gui) {
        this.rows = rows;
        this.cols = cols;
        this.size = Settings.MAX_SIZE;
        this.gui  = gui;

        icons = new ArrayList<JComponent>(size);

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

        NpcWorld world;
        NpcPopulation pop;
        Collection<NpcIndividual> inds;
        Iterator<NpcIndividual> it;

        world = (NpcWorld) gui.getWorld();
        pop   = world.getPopulation();
        inds  = pop.getIndividuals();
        it    = inds.iterator();

        for (int x=0; x < size; x++) {
            icons.add(new NullIcon());
        }

        for (int x=0; x < size; x++) {
            if (it.hasNext()) {
                NpcIndividual thing;
                JComponent icon;
                int idx;

                thing = it.next();
                idx   = thing.getID();
                icon  = thing.getWidget();

                icons.set(idx, icon);
            }
        }

        Iterator<JComponent> iconIt = icons.iterator();
        for (int i=0; i < (rows * cols); i++) {
            if (iconIt.hasNext()) {
                add(iconIt.next());
            }
            else {
                break;
            }
        }
    }

    private void clearGrid() {
        Iterator<JComponent> it = icons.iterator();
        while (it.hasNext()) {
            JComponent icon = it.next();
            remove(icon);
            it.remove();
        }
    }
}
