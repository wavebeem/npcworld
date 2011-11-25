import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;

public class Icon extends JComponent {
    private static final String FILE = "img/test.png";

    private final Image image;
    private final File  file;

    private final int width;
    private final int height;
    private final Dimension dimension;

    public Icon() {
        file = new File(FILE);

        width  = 32;
        height = 32;

        dimension = new Dimension(width, height);

        image = loadImage();
    }

    private Image loadImage() {
        try {
            return ImageIO.read(file);
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
