package fr.foxelia.tools.java.number.display;

import fr.foxelia.tools.java.number.round.RoundNumber;

/**
 * NumberToString is a class that converts a number to a string
 * <br><br>License: CC BY-SA 4.0
 * @version 1.0
 * @author Foxelia, ParadoxalUnivers
 */
public class NumberToString {

     /**
      * Returns a string from a given number
      * @param split The number to split
      *              <br>Can be an {@link Integer} or a {@link Double}
      *              <br>Can be a number with a maximum of 9 digits
      * @return The split number as a {@link String}
      */
    public static String splitNumber(Double split) {
        if (split > 999999999) {
            return RoundNumber.round(split / 1000000000) + "B";
        } else if (split > 999999) {
            return RoundNumber.round(split / 1000000) + "M";
        } else if (split > 999) {
            return RoundNumber.round(split / 1000) + "K";
        } else {
            return split.toString();
        }
    }

    /**
     * Returns a string from a given number
     * @param split The number to split
     *              <br>Can be an {@link Integer}
     *              <br>Can be a number with a maximum of 9 digits
     * @return The split number as a {@link String}
     * @see #splitNumber(Double)
     */
    public static String splitNumber(Integer split) {
        if (split > 999) {
            return splitNumber(split * 1.0);
        } else {
            return split.toString();
        }
    }
}
