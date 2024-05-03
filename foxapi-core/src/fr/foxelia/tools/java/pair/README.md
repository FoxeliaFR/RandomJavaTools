# Pair

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

Pair is a class that allows you to store a two objects in a single object.

## Why I created this class ?

Sometimes you need to store two objects in a single object without creating a new class. This prevents the usage of a Map to store just two objects.

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

| Library Name |
|--------------|
| None         | 

## How to use it ?
You can use this class in your projects in two different ways :
- Add the dependency to your project using Maven, Gradle, or any other dependency manager.
- You can also import the class directly into your project.

To create a new Pair object, you can use the following code :
```java
Pair<String, Integer> pair = new Pair<>("Hello World !", 42);
```

To get the first object of the Pair object, you can use the following code :
```java
String first = pair.getFirst();
```

To get the second object of the Pair object, you can use the following code :
```java
Integer second = pair.getSecond();
```


## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit

The Pair class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team. The class is inspired by the [Pair class](https://docs.oracle.com/javase/8/javafx/api/javafx/util/Pair.html) from JavaFX.