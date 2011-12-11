import javax.swing.*;
import java.awt.*;

public class WorldWidget extends JComponent {
    private Color color = new Color(0x00cc00);

    private Dimension dimension = new Dimension(32, 32);

    public Dimension getPreferredSize() { return dimension; }

    public NpcWorld world;
    public int row;
    public int col;

    public WorldWidget(NpcWorld world, int row, int col) {
        this.world = world;
        this.row   = row;
        this.col   = col;
    }

    private void calculateColor() {
        color = new Color(
            Util.random.nextInt(256),
            Util.random.nextInt(256),
            Util.random.nextInt(256)
        );
    }

    public void paintComponent(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        calculateColor();

        g.setColor(color);
        g.fillRect(0, 0, w, h);
    }
}
