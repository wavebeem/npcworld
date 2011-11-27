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

    private final ImageFilter filter;
    private final Image       image;
    private final File        file;
    private final Color       color;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon() {
        file = new File("img/sprites/paula.png");

        width  = 32;
        height = 32;

        color  = Color.GREEN;
        filter = new ColorizeFilter(color);

        dimension = new Dimension(width, height);

        image = loadImage();
    }

    private Image loadImage() {
        try {
            Image tmp;

            tmp = ImageIO.read(file);
            tmp = createImage(new FilteredImageSource(tmp.getSource(), filter));

            return tmp;
        }
        catch (IOException e) {
            System.err.printf("File '%s' could not be read\n", file);
            return null;
        }
    }

    public Dimension getPreferredSize() { return dimension; }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
}
