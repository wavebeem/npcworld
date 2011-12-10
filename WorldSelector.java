import javax.swing.*;
import java.awt.*;

public class WorldSelector extends JFrame {
    public static final String TITLE = "Click to select a world";

    private LayoutManager layout;

    private static final int gapH = 3;
    private static final int gapV = 3;

    private WorldWidget[][] worlds;

    public WorldSelector() {
        final int rows = Const.UNIVERSE_ROWS;
        final int cols = Const.UNIVERSE_COLS;

        layout = new GridLayout(rows, cols);
        setLayout(layout);

        setTitle(TITLE);

        layout = new GridLayout(rows, cols, gapH, gapV);
        worlds = new WorldWidget[rows][cols];

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                WorldWidget world = new WorldWidget(new NpcWorld(), r, c);
                worlds[r][c] = world;
                add(world);
            }
        }

        setVisible(true);
    }
}
