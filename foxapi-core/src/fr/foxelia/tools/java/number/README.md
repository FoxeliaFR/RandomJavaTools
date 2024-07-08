# NumericTools

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

The NumericTools is a collection of classes that allows you to manipulate numbers in Java.

## Why I created this class ?

We created this class because the Sonelia Minecraft server needed to convert huge numbers into a readable format, round numbers and check if a number is numeric. 

## License
[<img src="https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg">](https://creativecommons.org/licenses/by-sa/4.0/)<br>
<img src="https://mirrors.creativecommons.org/presskit/buttons/88x31/svg/by-sa.svg" alt="CC BY-SA 4.0" width="200" height="70"><br>
This work is licensed under a
[Creative Commons Attribution-ShareAlike 4.0 International License](https://creativecommons.org/licenses/by-sa/4.0/).<br>
Read the [LICENSE](LICENSE.md) file for license rights and limitations (CC BY-SA 4.0).

## Am I allowed to use this class ?
Sure, you can use this class in your plugins. You can also modify it and redistribute it. But you must respect the credits (do not remove them or change them to your own name) and the license (see section above).

## Used Libraries

Before using this class, you must add the following libraries to your project :

| Library Name                                                         |
|----------------------------------------------------------------------|
| None                                                                 |

## How to use it ?

### Round a number to a defined number of decimal places :

```java
import fr.foxelia.tools.java.number.round.RoundNumber;

public class MyClass {

    public void myMethod() {
        double number = 3.14159265359;

        // Round the number to 3 decimal places
        double roundedNumber = RoundNumber.round(number, 3);
        System.out.println(roundedNumber); // Output : 3.142
        
        // Round the number to 2 decimal places
        double roundedNumber2 = RoundNumber.round(number);
        System.out.println(roundedNumber2); // Output : 3.14
    }
}
```

*N.B. :* The class `RoundNumber` use the `Math.floor()` method to round the number.

### Check if a string is numeric :

```java
public class MyClass {

    public void myMethod() {
        String number = "1234";

        // Check if the string is numeric
        boolean isNumeric = NumericTools.isNumeric(number);
        System.out.println(isNumeric); // Output : true
        
        // Check if the string is an integer
        boolean isInteger = NumericTools.isInteger(number);
        System.out.println(isInteger); // Output : true
        
        // Check if the string is a double
        boolean isDouble = NumericTools.isDouble(number);
        System.out.println(isDouble); // Output : true
        
        // Check if the string is a byte
        boolean isByte = NumericTools.isByte(number);
        System.out.println(isByte); // Output : false
        
        String notNumber = "Hello World !";
        boolean isNotNumeric = NumericTools.isNumeric(notNumber);
        System.out.println(isNotNumeric); // Output : false
    }
}
```

### Convert a number to a readable format :

```java
import fr.foxelia.tools.java.number.display.NumberToString;

public class MyClass {

    public void myMethod() {
        long number = 123456789;

        // Convert the number to a readable format
        String readableNumber = NumberToString.splitNumber(number);
        System.out.println(readableNumber); // Output : 123,45M
    }
}
```

| Number        | Output  |
|---------------|---------|
| 123           | 123     | 
| 123.45        | 123,45  |
| 1234.56       | 1,23K   |
| 1234567       | 1,23M   |
| 1234567890    | 1,23B   |
| 1234567890123 | 1234.56B|

## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit
The NumericTools was created by [ParadoxalUnivers](https://github.com/paradoxalunivers) and updated by [Zarinoow](https://github.com/Zarinoow). By the [Foxelia](https://foxelia.fr/) team.