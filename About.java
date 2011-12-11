import javax.swing.*;
import java.awt.*;

public class About extends JDialog {
    public static final String TITLE = "About NPCWorld";

    private JScrollPane scrollPane;
    private JTextArea   textArea;
    private JPanel      panel;

    private static final int margin = 6;

    private static final Dimension preferredSize = new Dimension(400, 100);

    public About() {
        super((Frame) null, true);

        setTitle(TITLE);

        textArea = new JTextArea(Const.ABOUT_TEXT);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(preferredSize);

        panel = new JPanel();
        panel.setBorder(Util.makeBorder(margin));
        panel.add(scrollPane);
        panel.setLayout(new GridLayout(1, 1));

        add(panel);
        pack();

        setVisible(true);
    }
}
