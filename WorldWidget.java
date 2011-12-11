import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldWidget extends JComponent implements MouseListener {
    private Color color = new Color(0x00cc00);

    private Dimension dimension = new Dimension(64, 64);

    public Dimension getPreferredSize() { return dimension; }

    private NpcWorld world;
    private GUI gui;
    private int row;
    private int col;

    private boolean selected;

    public WorldWidget(GUI gui, NpcWorld world, int row, int col) {
        this.gui   = gui;
        this.world = world;
        this.row   = row;
        this.col   = col;

        selected = false;

        addMouseListener(this);
    }

    public World getWorld() {
        return world;
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

    public void   select() { this.selected = true;  }
    public void deselect() { this.selected = false; }

    public void mousePressed(MouseEvent  e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent  e) {}
    public void mouseExited(MouseEvent   e) {}

    public void mouseClicked(MouseEvent e) {
        Debug.echo("You clicked me!", row, col);
        gui.setWorld(world);
        gui.redraw();
    }
}
