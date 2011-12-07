import java.awt.*;
import javax.swing.*;

public class NullIcon extends JComponent {
    private Color bgColor = new Color(0x111111);

    public void paintComponent(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);
    }
}
