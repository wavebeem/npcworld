import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    public static final String TITLE = "Just another Java test";

    private Container container;

    public GUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.LINE_AXIS));
        container.add(new Icon());
        container.add(new Icon());
        //container.add(new JButton("A"));
        //container.add(new JButton("B"));

        pack();

        setVisible(true);
    }
}
