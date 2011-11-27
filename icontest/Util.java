import javax.swing.border.*;
import javax.swing.*;

public class Util {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
        }
    }

    public static Border makeBorder(int size) {
        return BorderFactory.createEmptyBorder(size, size, size, size);
    }
}
