import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final String TITLE = "Just another Java test";

    private Container container;

    private Grid grid;

    public GUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        grid = new Grid(9, 9);

        container = getContentPane();

        container.add(grid);

        pack();

        setVisible(true);
    }
}
