/**
 * @author Brian Mock (mock.brian@gmail.com)
 */

import javax.swing.*;
import java.awt.*;

public class WorldSelector extends JFrame {
    public static final String TITLE = "Click to select a world";

    public static Color bgColor = new Color(0x222222);

    private LayoutManager layout;

    private static final int gapH    = 3;
    private static final int gapV    = 3;
    private static final int borderW = 3;

    private NpcUniverse universe;
    private WorldWidget[][] worlds;

    private GUI gui;

    private int selRow;
    private int selCol;

    public WorldSelector(GUI gui) {
        this.gui = gui;

        selCol = 0;
        selRow = 0;

        final int rows = Const.UNIVERSE_ROWS;
        final int cols = Const.UNIVERSE_COLS;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel bg = new JPanel();

        bg.setBackground(bgColor);
        bg.setBorder(Util.makeBorder(borderW));

        layout = new GridLayout(rows, cols, gapH, gapV);
        bg.setLayout(layout);

        setTitle(TITLE);

        universe = gui.getUniverse();
        worlds   = new WorldWidget[rows][cols];

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                WorldWidget world = new WorldWidget(this, universe.getWorld(r, c), r, c);
                worlds[r][c] = world;
                bg.add(world);
            }
        }

        add(bg);

        pack();

        setVisible(true);
    }

    public void selectWorldAt(int row, int col) {
        Debug.echo("Select world at (", row, ",", col, ")");
        worlds[selRow][selCol].deselect();
        selRow = row;
        selCol = col;
        worlds[selRow][selCol].select();
        gui.selectWorldAt(row, col);
    }

    public void deselect() {
        worlds[selRow][selCol].deselect();
    }

    public void updateGUI() {
        gui.redraw();
    }
}
