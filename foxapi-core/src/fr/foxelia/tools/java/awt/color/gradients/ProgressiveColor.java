package fr.foxelia.tools.java.awt.color.gradients;

import java.awt.*;

/**
 * A class to create a progressive color gradient between 3 colors.
 * <br><br>License: CC BY-SA 4.0
 * @author Foxelia, Zarinoow
 * @version 1.0
 */
public class ProgressiveColor {

    private final Color startColor;
    private final Color middleColor;
    private final Color endColor;

    private final int middleIndex;
    private final int endIndex;

    public ProgressiveColor(Color startColor, Color middleColor, Color endColor, int middleIndex, int endIndex) {
        this.startColor = startColor;
        this.middleColor = middleColor;
        this.endColor = endColor;

        this.middleIndex = middleIndex;
        this.endIndex = endIndex;
    }

    public Color getColorByPlacement(int index) {
        return getColorByPlacement(index, endIndex);
    }

    public Color getColorByPlacement(int index, int max) {
        float ratio = (float) index / max;

        if (index <= middleIndex) {
            return new Color(
                    (int) (startColor.getRed() + ratio * (middleColor.getRed() - startColor.getRed())),
                    (int) (startColor.getGreen() + ratio * (middleColor.getGreen() - startColor.getGreen())),
                    (int) (startColor.getBlue() + ratio * (middleColor.getBlue() - startColor.getBlue()))
            );
        } else if (index <= endIndex) {
            float subRatio = (float) (index - middleIndex) / (endIndex - middleIndex);
            return new Color(
                    (int) (middleColor.getRed() + subRatio * (endColor.getRed() - middleColor.getRed())),
                    (int) (middleColor.getGreen() + subRatio * (endColor.getGreen() - middleColor.getGreen())),
                    (int) (middleColor.getBlue() + subRatio * (endColor.getBlue() - middleColor.getBlue()))
            );
        } else {
            return endColor;
        }
    }
}
