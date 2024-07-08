package fr.foxelia.tools.java.number.check;

/**
 * NumericChecker is a class that checks
 * if a string is a number.
 * <br><br>License: CC BY-SA 4.0
 * @version 1.1
 * @author Foxelia, ParadoxalUnivers, Zarinoow
 */
public class NumericChecker {

    /**
     * Check if a string is a number
     * @param strNum The string to check
     * @return True if the string is a number, false otherwise
     * @see #isDouble(String)
     */
    public static boolean isNumeric(String strNum) {
        return isDouble(strNum);
    }

    /**
     * Check if a string is an integer
     * @param strNum The string to check
     * @return True if the string is an {@link Integer}, false otherwise
     */
    public static boolean isInteger(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if a string is a long
     * @param strNum The string to check
     * @return True if the string is a {@link Long}, false otherwise
     */
    public static boolean isLong(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Long.parseLong(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if a string is a float
     * @param strNum The string to check
     * @return True if the string is a {@link Float}, false otherwise
     */
    public static boolean isFloat(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Float.parseFloat(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if a string is a double
     * @param strNum The string to check
     * @return True if the string is a {@link Double}, false otherwise
     */
    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if a string is a short
     * @param strNum The string to check
     * @return True if the string is a {@link Short}, false otherwise
     */
    public static boolean isShort(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Short.parseShort(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Check if a string is a byte
     * @param strNum The string to check
     * @return True if the string is a {@link Byte}, false otherwise
     */
    public static boolean isByte(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Byte.parseByte(strNum);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
