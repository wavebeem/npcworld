/**
 * @author Brian Mock (mock.brian@gmail.com)
 */

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Icon extends JComponent {
    private Color bgColor  = new Color(0x111111);
    private Color fgColor  = new Color(0xffffff);
    private Color fgShadow = new Color(0x000000);

    private static final int shadowOffsetX = 1;
    private static final int shadowOffsetY = 1;

    private Color textBg = new Color(0x111111);

    private static final Image overlayEating    = Util.loadImage("img/overlays/eating.png");
    private static final Image overlayMating    = Util.loadImage("img/overlays/mating.png");
    private static final Image overlaySleeping  = Util.loadImage("img/overlays/sleeping.png");
    private static final Image overlayMigrating = Util.loadImage("img/overlays/migrating.png");
    private static final Image overlayPlaying   = null;

    private static final Color bgEating    = new Color(0x115511);
    private static final Color bgMating    = new Color(0x661111);
    private static final Color bgSleeping  = new Color(0x114455);
    private static final Color bgMigrating = new Color(0x777711);
    private static final Color bgPlaying   = new Color(0x111111);

    private static final Image imageMale   = Util.loadImage("img/sprites/m.png");
    private static final Image imageFemale = Util.loadImage("img/sprites/f.png");

    private int fontSize;

    private ImageFilter filter;

    private Image untaintedImage;
    private Image image;
    private Image overlay;
    private Color color;
    private Font  font;
    private int   age;
    private int   action;
    private int   gender;
    private boolean isAlive;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon(NpcIndividual ind) {
        width  = 68;
        height = 68;
        dimension = new Dimension(width, height);

        setFontSize(16);

        setIndividual(ind);
    }

    public void setIndividual(NpcIndividual ind) {
        if (ind == null) {
            isAlive = false;
            bgColor = bgPlaying;
            return;
        }

        isAlive = true;
        setAction(ind.getCurrentAction());
        setGender(ind.getGender());
        setColor(ind.getColor());
        setAge(ind.getAge());
    }

    private void setAge(int age) {
        this.age = age;
    }

    private void setAction(int state) {
        this.action = state;

        switch (action) {
        case Const.SLEEPING:  overlay = overlaySleeping;  bgColor = bgSleeping;  break;
        case Const.EATING:    overlay = overlayEating;    bgColor = bgEating;    break;
        case Const.MATING:    overlay = overlayMating;    bgColor = bgMating;    break;
        case Const.MIGRATING: overlay = overlayMigrating; bgColor = bgMigrating; break;
        case Const.PLAYING:   overlay = overlayPlaying;   bgColor = bgPlaying;   break;
        }
    }

    private void setGender(int gender) {
        this.gender = gender;
        untaintedImage =
            gender == Const.MALE?   imageMale:
            gender == Const.FEMALE? imageFemale:
            null;
    }

    private void setFontSize(int size) {
        if (fontSize == size)
            return;

        fontSize = size;
        font     = new Font(Font.MONOSPACED, Font.BOLD, fontSize);
    }

    private void setColor(Color newColor) {
        color  = newColor;
        filter = new ColorizeFilter(color);
        image  = filterImage(untaintedImage);
    }

    private Image filterImage(Image image) {
        return createImage(new FilteredImageSource(image.getSource(), filter));
    }

    public Dimension getPreferredSize() { return dimension; }

    public void paintComponent(Graphics g) {
        if (isAlive) paintAlive(g);
        else         paintDead(g);
    }

    private void paintDead(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);
    }

    private void paintAlive(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        final int dim = Math.min(w, h);

        final int overlayW = Math.max(16, (int) (dim * 0.25));
        final int overlayH = Math.max(16, (int) (dim * 0.25));
        final int overlayX = 0;
        final int overlayY = h - overlayH;

        final int imageW = dim - overlayW;
        final int imageH = dim - overlayH;
        final int imageX = w - imageW;
        final int imageY = h - imageH;

        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);

        g.drawImage(image,     imageX,   imageY,   imageW,   imageH, null);
        g.drawImage(overlay, overlayX, overlayY, overlayW, overlayH, null);

        g.setColor(textBg);

        setFontSize(overlayH - 4);
        g.setFont(font);
        g.setColor(fgShadow); g.drawString("" + age, 3 + shadowOffsetX, fontSize + shadowOffsetY);
        g.setColor(fgColor);  g.drawString("" + age, 3,                 fontSize);
    }
}
