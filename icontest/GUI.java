import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final String TITLE = "Just another Java test";

    private Container     container;
    private LayoutManager layout;

    private static final int gapH = 3;
    private static final int gapV = 3;

    private Grid    grid;
    private Toolbar toolbar;
    private Sidebar sidebar;

    public GUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        layout = new BorderLayout(gapH, gapV);

        container = getContentPane();

        grid    = new Grid(9, 9);
        toolbar = new Toolbar();
        sidebar = new Sidebar();

        container.add(grid,    BorderLayout.CENTER);
        container.add(toolbar, BorderLayout.SOUTH);
        container.add(sidebar, BorderLayout.EAST);

        pack();

        setVisible(true);
    }
}
