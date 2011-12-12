/**
 * @author Brian Mock (mock.brian@gmail.com)
 */

import java.awt.image.*;
import java.awt.*;

public class ColorizeFilter extends RGBImageFilter {
    private Color color;

    public ColorizeFilter(Color color) {
        // The filter's operation does not depend on the
        // pixel's location, so IndexColorModels can be
        // filtered directly.
        canFilterIndexColorModel = true;

        this.color = color;
    }

    public int filterRGB(int x, int y, int rgb) {
        int filterR = color.getRed();
        int filterG = color.getGreen();
        int filterB = color.getBlue();

        Color input = new Color(rgb, true);

        int inputR = input.getRed();
        int inputG = input.getGreen();
        int inputB = input.getBlue();
        int inputA = input.getAlpha();

        float[] filterHSV = Color.RGBtoHSB(filterR, filterG, filterB, null);
        float[] inputHSV  = Color.RGBtoHSB(inputR,  inputG,  inputB,  null);
        float[] outputHSV = {0, 0, 0};

        outputHSV[0] = filterHSV[0];
        outputHSV[1] = (filterHSV[1] + inputHSV[1]) / 2;
        outputHSV[2] = inputHSV[2];

        Color output = Color.getHSBColor(outputHSV[0], outputHSV[1], outputHSV[2]);

        int outputR = output.getRed();
        int outputG = output.getGreen();
        int outputB = output.getBlue();
        int outputA = inputA;

        output = new Color(outputR, outputG, outputB, outputA);

        return output.getRGB();
    }
}
