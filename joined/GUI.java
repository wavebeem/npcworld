import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private static final String TITLE = "Just another Java test";

    private Container     container;
    private LayoutManager layout;

    private static final int gapH = 3;
    private static final int gapV = 3;

    private Grid    grid;
    private Toolbar toolbar;
    private Infobar infobar;
    private World   world;

    private Thread  thread;
    private boolean running;
    private int     runDelay;

    public GUI() {
        Util.tryForNiceTheme();

        world = new NpcWorld();

        thread   = null;
        running  = false;
        runDelay = 500;

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle(TITLE);

        layout = new BorderLayout(gapH, gapV);

        container = getContentPane();

        grid    = new Grid(6, 12);
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
        infobar.fillInfo();
    }

    public World getWorld() {
        return world;
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

    private class RunnerThread extends Thread {
        public void run() {
            while (running) {
                step();
                Util.sleep(runDelay);
            }
        }
    }
}
