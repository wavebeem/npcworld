import javax.swing.*;
import java.awt.*;

public class About extends JDialog {
    public static final String TITLE = "About NPC World";

    private JScrollPane scrollPane;
    private JEditorPane editorPane;
    private JPanel      panel;

    private static final int margin = 6;

    private static final Dimension preferredSize = new Dimension(400, 400);

    public About() {
        super((Frame) null, true);

        setTitle(TITLE);

        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setText(Const.ABOUT_TEXT);
        editorPane.setEditable(false);
        editorPane.setCaretPosition(0);

        scrollPane = new JScrollPane(editorPane);
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
