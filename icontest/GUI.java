import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final String TITLE = "Just another Java test";

    private Container container;

    private Icon icon;

    private final Color[] rainbow = {
        Color.RED,
        Color.ORANGE,
        Color.YELLOW,
        Color.GREEN,
        Color.CYAN,
        Color.BLUE,
        Color.MAGENTA,
    };

    private final Color bgColor = new Color(0x444240);

    public GUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        icon = new Icon();

        container = getContentPane();
        container.setLayout(new GridLayout(3, 3));
        container.add(icon);
        for (int i=0; i < 8; i++) {
            container.add(new Icon());
        }

        this     .setBackground(bgColor);
        container.setBackground(bgColor);
        //container.add(new JButton("A"));
        //container.add(new JButton("B"));

        pack();

        setVisible(true);

        new RainbowThread().start();
    }

    private class RainbowThread extends Thread {
        public void run() {
            while (true) {
                for (Color color: rainbow) {
                    icon.colorize(color);
                    Util.sleep(500);
                    repaint();
                }
            }
        }
    }
}
