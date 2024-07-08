package fr.foxelia.tools.java.number.round;

/**
 * RoundNumber is a class that rounds a number
 * to a specified number of decimal places or to 2 decimal places by default
 * <br><br>License: CC BY-SA 4.0
 * @version 1.1
 * @author Foxelia, ParadoxalUnivers, Zarinoow
 */
public class RoundNumber {

    private static final int DEFAULT_DECIMAL_PLACES = 2;

    /**
     * Round a number to 2 decimal places
     * @param number The number to round
     * @return The rounded number
     */
    public static Double round(Double number) {
        return round(number, DEFAULT_DECIMAL_PLACES);
    }

    /**
     * Round a number to the specified decimal places
     * @param number The number to round
     * @param decimalPlaces The number of decimal places to round to
     * @return The rounded number
     */
    public static Double round(Double number, int decimalPlaces) {
        double pow = Math.pow(10, decimalPlaces);
        return Math.floor(number * pow) / pow;
    }
}
