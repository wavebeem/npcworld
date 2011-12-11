import javax.swing.*;
import java.awt.*;

public class WorldSelector extends JFrame {
    public static final String TITLE = "Click to select a world";

    public static Color bgColor = new Color(0x222222);

    private LayoutManager layout;

    private static final int gapH    = 3;
    private static final int gapV    = 3;
    private static final int borderW = 3;

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

        JPanel bg = new JPanel();

        bg.setBackground(bgColor);
        bg.setBorder(Util.makeBorder(borderW));

        layout = new GridLayout(rows, cols, gapH, gapV);
        bg.setLayout(layout);

        setTitle(TITLE);

        worlds = new WorldWidget[rows][cols];

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                WorldWidget world = new WorldWidget(gui, new NpcWorld(), r, c);
                worlds[r][c] = world;
                bg.add(world);
            }
        }

        selectWorldAt(0, 0);

        add(bg);

        pack();

        setVisible(true);
    }

    public void selectWorldAt(int row, int col) {
        Debug.echo("Select world at (", row, ",", col, ")");
        worlds[row][col].deselect();
        selRow = row;
        selCol = col;
        worlds[row][col].select();
        gui.setWorld(worlds[row][col].getWorld());
    }
}
