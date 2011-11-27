import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Icon extends JComponent {
    /*
     * Image src = getImage("doc:///demo/images/duke/T1.gif");
     * ImageFilter colorfilter = new RedBlueSwapFilter();
     * Image img = createImage(new FilteredImageSource(src.getSource(), colorfilter));
     */

    private ImageFilter filter;

    private Image image;
    private Image overlay;
    private Color color;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon() {
        width  = 64;
        height = 64;

        color  = Color.BLACK;
        filter = new ColorizeFilter(color);

        dimension = new Dimension(width, height);

        image = loadImage("img/sprites/paula.png");
        image = filterImage(image);

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

    public void colorize(Color newColor) {
        color  = newColor;
        filter = new ColorizeFilter(color);
        image  = filterImage(image);
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

        final int overlayX = 0;
        final int overlayY = 0;
        final int overlayW = w - imageW;
        final int overlayH = h - imageH;

        g.drawImage(image,     imageX,   imageY,   imageW,   imageH, null);
        g.drawImage(overlay, overlayX, overlayY, overlayW, overlayH, null);
    }
}
