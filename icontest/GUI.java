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

    public GUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        icon = new Icon();

        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(icon);
        container.add(new Icon());
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
