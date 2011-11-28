import javax.swing.border.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;

public class Util {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    public static Image loadImage(String filename) {
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

    public static Border makeBorder(int size) {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }
}
