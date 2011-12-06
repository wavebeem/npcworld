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

    private static final Image overlayEating   = Util.loadImage("img/overlays/eating.png");
    private static final Image overlayMating   = Util.loadImage("img/overlays/mating.png");
    private static final Image overlaySleeping = Util.loadImage("img/overlays/sleeping.png");
    private static final Image overlayPlaying  = null;

    private static final Color bgEating   = new Color(0x881111);
    private static final Color bgMating   = new Color(0x118811);
    private static final Color bgSleeping = new Color(0x114488);
    private static final Color bgPlaying  = new Color(0x111111);

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

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon(int gender) {
        width  = 68;
        height = 68;

        setFontSize(16);

        age = 0;

        color  = Color.RED;
        filter = new ColorizeFilter(color);

        dimension = new Dimension(width, height);

        untaintedImage =
            gender == Const.MALE?   imageMale:
            gender == Const.FEMALE? imageFemale:
            null;
        image = untaintedImage;
        image = filterImage(untaintedImage);

        setAction(Const.MATING);
    }

    public void setAction(int state) {
        this.action = state;

        switch (action) {
        case Const.SLEEPING: overlay = overlaySleeping; bgColor = bgSleeping; break;
        case Const.EATING:   overlay = overlayEating;   bgColor = bgEating;   break;
        case Const.MATING:   overlay = overlayMating;   bgColor = bgMating;   break;
        case Const.PLAYING:  overlay = overlayPlaying;  bgColor = bgPlaying;  break;
        }
    }

    private void setFontSize(int size) {
        if (fontSize == size)
            return;

        fontSize = size;
        font     = new Font(Font.MONOSPACED, Font.BOLD, fontSize);
    }

    public void happyBirthday() {
        age++;
    }

    public void colorize(Color newColor) {
        color  = newColor;
        filter = new ColorizeFilter(color);
        image  = filterImage(untaintedImage);
    }

    private Image filterImage(Image image) {
        return createImage(new FilteredImageSource(image.getSource(), filter));
    }

    public Dimension getPreferredSize() { return dimension; }

    public void paintComponent(Graphics g) {
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
        //g.fillRect(0, 0, w, overlayH - 1);

        setFontSize(overlayH - 4);
        g.setFont(font);
        g.setColor(fgShadow); g.drawString("" + age, 3 + shadowOffsetX, fontSize + shadowOffsetY);
        g.setColor(fgColor);  g.drawString("" + age, 3,                 fontSize);
    }
}
