import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Icon extends JComponent {
    private static final Color bgColor = new Color(0x141210);
    private static final Color fgColor = new Color(0xffffff);

    private int fontSize;

    private ImageFilter filter;

    private Image untainedImage;
    private Image image;
    private Image overlay;
    private Color color;
    private Font  font;
    private int   age;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon(String filename) {
        width  = 64;
        height = 64;

        fontSize = 14;
        font = new Font(Font.MONOSPACED, Font.BOLD, fontSize);

        age = 0;

        color  = Color.RED;
        filter = new ColorizeFilter(color);

        dimension = new Dimension(width, height);

        untainedImage = loadImage(filename);
        image = loadImage(filename);
        image = filterImage(untainedImage);

        overlay = loadImage("img/overlays/eating.png");
    }

    private Image loadImage(String filename) {
        try {
            Image tmp;

            tmp = ImageIO.read(new File(filename));

            return tmp;
        }
        catch (IOException e) {
            System.err.printf("File '%s' could not be read\n", filename);
            return null;
        }
    }

    public void happyBirthday() {
        age++;
    }

    public void colorize(Color newColor) {
        color  = newColor;
        filter = new ColorizeFilter(color);
        image  = filterImage(untainedImage);
    }

    private Image filterImage(Image image) {
        return createImage(new FilteredImageSource(image.getSource(), filter));
    }

    public Dimension getPreferredSize() { return dimension; }

    public void paintComponent(Graphics g) {
        final int w = getWidth();
        final int h = getHeight();

        final int imageX = (int) (0.25 * w);
        final int imageY = (int) (0.25 * h);
        final int imageW = w - imageX;
        final int imageH = h - imageY;

        final int overlayW = w - imageW;
        final int overlayH = h - imageH;
        final int overlayX = 0;
        final int overlayY = h - overlayH;

        g.setColor(bgColor);
        g.fillRect(0, 0, w, h);

        g.drawImage(image,     imageX,   imageY,   imageW,   imageH, null);
        g.drawImage(overlay, overlayX, overlayY, overlayW, overlayH, null);

        g.setColor(fgColor);
        g.setFont(font);
        g.drawString("" + age, 0, fontSize);
    }
}
