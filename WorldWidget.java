import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WorldWidget extends JComponent implements MouseListener {
    private Color color = new Color(0x00cc00);
    private static final Color innerBg = new Color(0x222222);
    private static final Color selBg = new Color(0xaaaaaa);
    private static final int borderWidth = 3;
    private static final int innerBorderWidth = 3;

    private Dimension dimension = new Dimension(64, 64);

    public Dimension getPreferredSize() { return dimension; }

    private NpcWorld world;
    private WorldSelector selector;
    private int row;
    private int col;

    private boolean selected;

    public WorldWidget(WorldSelector selector, NpcWorld world, int row, int col) {
        this.selector = selector;
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
        final int n    = world.getPopulation().getSize();
        final int femHue = -180;
        final int manHue =    0;
        final int perc = world.percentMale();
        final int comp = 100 - perc;
        final int rgb  = Color.HSBtoRGB(
            ((comp/100f)*femHue)/360f,
            0.75f,
            (float) Math.min(1.00, n/((float)Const.LARGE_POPULATION))
        );
        color = new Color(rgb);
    }

    public void paintComponent(Graphics g) {
        if (selected) selectedPaint(g);
        else          unselectedPaint(g);
    }

    private void selectedPaint(Graphics g) {
        final int x   = 0;
        final int y   = 0;
        final int w   = getWidth();
        final int h   = getHeight();
        final int off = borderWidth;
        final int offf = innerBorderWidth;
        final int xx  = x  + off;
        final int yy  = y  + off;
        final int ww  = w  - off - off;
        final int hh  = h  - off - off;
        final int xxx = xx + offf;
        final int yyy = yy + offf;
        final int www = ww - offf - offf;
        final int hhh = hh - offf - offf;

        calculateColor();

        g.setColor(selBg);
        g.fillRect(x, y, w, h);

        g.setColor(innerBg);
        g.fillRect(xx, yy, ww, hh);

        g.setColor(color);
        g.fillRect(xxx, yyy, www, hhh);
    }

    private void unselectedPaint(Graphics g) {
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
        selector.selectWorldAt(row, col);
        selector.repaint();
        selector.updateGUI();
    }
}
