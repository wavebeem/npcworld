import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final String TITLE = "NPC World";

    private Container     container;
    private LayoutManager layout;

    private static final int gapH = 3;
    private static final int gapV = 3;

    private Grid    grid;
    private Toolbar toolbar;
    private Infobar infobar;
    private World   world;

    private WorldSelector selector;

    private Thread  thread;
    private boolean running;

    public GUI() {
        Util.tryForNiceTheme();

        world    = new NpcWorld();
        selector = new WorldSelector(this);

        thread   = null;
        running  = false;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        layout = new BorderLayout(gapH, gapV);

        container = getContentPane();

        grid    = new Grid(6, 12, this);
        toolbar = new Toolbar(this);
        infobar = new Infobar(this);

        container.add(grid,    BorderLayout.CENTER);
        container.add(toolbar, BorderLayout.NORTH);
        container.add(infobar, BorderLayout.SOUTH);

        pack();

        setVisible(true);
    }

    public void step() {
        world.step();
        redraw();
    }

    public void redraw() {
        grid.repopulate();
        infobar.fillInfo();
        selector.repaint();
        repaint();
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void toggleRunning() {
        if (! running) startRun();
        else           endRun();
    }

    public void pause() {
        endRun();
    }

    public void play() {
        startRun();
    }

    private void startRun() {
        running = true;
        thread  = new RunnerThread();

        thread.start();
    }

    private void endRun() {
        running = false;
        if (thread != null) {
            Util.joinThread(thread);
            thread = null;
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void selectWorldAt(int row, int col) {
        selector.selectWorldAt(row, col);
        redraw();
    }

    private class RunnerThread extends Thread {
        public void run() {
            while (running) {
                step();
                Util.sleep(Settings.runDelay);
            }
        }
    }
}
