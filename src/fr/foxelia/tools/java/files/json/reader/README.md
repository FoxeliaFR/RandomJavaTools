# JSON File Reader

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

JSON File Reader is a class that allows you to read a [File](https://docs.oracle.com/javase/7/docs/api/java/io/File.html) or an [InputStream](https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html) and convert it to a [JsonObject](https://javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com/google/gson/JsonObject.html).

## Why I created this class ?

I created this class because I needed to read a JSON file to load an embed for my Discord bot. You can see the result of this work in the [JsonEmbedBuilder](src/fr/foxelia/tools/java/discord/JsonEmbedBuilder.java) class.

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
| [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) | 

## How to use it ?
You can use this class in your projects in two different ways :
- Add the dependency to your project using Maven, Gradle, or any other dependency manager.
- You can also import the class directly into your project.

Simply pass the file or input stream :

```java
String json = JsonFileReader.getJsonAsString(new File("file.json"));
```

You can also use the `getJsonObject` method to get a `JsonObject` :

```java
JsonObject jsonObject = JsonFileReader.getJsonAsObject(new File("file.json"));
```


## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit

The JsonFileReader class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team.