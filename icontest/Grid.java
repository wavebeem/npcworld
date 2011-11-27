import javax.swing.*;
import java.awt.*;

public class Grid extends JPanel {
    private final Color bgColor = new Color(0x242220);

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

    private Icon[][] icons;

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        icons = new Icon[rows][cols];

        layout = new GridLayout(rows, cols, gapH, gapV);
        setLayout(layout);

        for (int r=0; r < rows; r++) {
            for (int c=0; c < cols; c++) {
                String filename = Math.random() < 0.5
                    ? "img/sprites/m.png"
                    : "img/sprites/f.png";
                Icon icon = new Icon(filename);
                icons[r][c] = icon;
                add(icon);
            }
        }

        setBackground(bgColor);

        setBorder(Util.makeBorder(borderW));

        new RainbowThread().start();
    }

    private class RainbowThread extends Thread {
        public void run() {
            while (true) {
                for (Color color: rainbow) {
                    for (Icon[] row: icons) {
                        for (Icon icon: row) {
                            icon.colorize(color);
                        }
                    }
                    Util.sleep(500);
                    repaint();
                }
            }
        }
    }
}
