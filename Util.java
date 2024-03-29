/**
 * @author Brian Mock (mock.brian@gmail.com)
 */

import javax.swing.border.*;
import javax.swing.UIManager.*;
import javax.swing.*;
import java.awt.image.*;
import java.awt.geom.*;
import java.awt.*;
import java.awt.event.*;
import javax.imageio.*;
import java.io.*;
import java.net.*;
import java.util.Random;

public class Util {
    public static final Random random = new Random();

    public static int intFromSpinner(JSpinner spinner) {
        return new Integer((Integer) spinner.getValue());
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    public static void joinThread(Thread thread) {
        try {
            thread.join();
        }
        catch (InterruptedException e) {
        }
    }

    public static Image loadImage(String filename) {
        try {
            Image tmp = ImageIO.read(Util.class.getResource(filename));

            return tmp;
        }
        catch (IOException e) {
            System.err.printf("File '%s' could not be read\n", filename);

            return null;
        }
    }

    public static URL getURL(String filename) {
        return Util.class.getResource(filename);
    }

    public static JEditorPane getEditorPane(String filename) {
        try {
            return new JEditorPane(Util.class.getResource(filename));
        }
        catch (IOException e) {
            System.err.printf("File '%s' could not be read\n", filename);

            return null;
        }
    }

    public static Border makeBorder(int size) {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }

    public static void tryForNiceTheme() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }
    }

    public static JLabel leftLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.LEFT);

        return label;
    }

    public static JLabel rightLabel(String text) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.RIGHT);

        return label;
    }

    public static JButton clickableButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);

        return button;
    }
}
