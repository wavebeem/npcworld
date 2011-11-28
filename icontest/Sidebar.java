import javax.swing.*;
import java.awt.*;

public class Sidebar extends JPanel {
    private static final int gapV = 3;
    private static final int gapH = 3;

    private static final int borderW = 3;

    private LayoutManager layout;

    public Sidebar() {
        layout = new GridLayout(0, 2, gapH, gapV);

        setLayout(layout);

        add(new JButton("1"));
        add(new JButton("2"));
        add(new JButton("3"));
        add(new JButton("4"));
        add(new JButton("5"));
        add(new JButton("6"));
        add(new JButton("7"));
        add(new JButton("8"));

        setBorder(Util.makeBorder(borderW));
    }
}
