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
    private File  file;
    private Color color;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon() {
        file = new File("img/sprites/paula.png");

        width  = 64;
        height = 64;

        color  = Color.BLACK;
        filter = new ColorizeFilter(color);

        dimension = new Dimension(width, height);

        image = loadImage();
        image = filterImage();
    }

    private Image loadImage() {
        try {
            Image tmp;

            tmp = ImageIO.read(file);

            return tmp;
        }
        catch (IOException e) {
            System.err.printf("File '%s' could not be read\n", file);
            return null;
        }
    }

    public void colorize(Color newColor) {
        color  = newColor;
        filter = new ColorizeFilter(color);
        image  = filterImage();
    }

    private Image filterImage() {
        return createImage(new FilteredImageSource(image.getSource(), filter));
    }

    public Dimension getPreferredSize() { return dimension; }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 12, 12, 48, 48, null);
    }
}
