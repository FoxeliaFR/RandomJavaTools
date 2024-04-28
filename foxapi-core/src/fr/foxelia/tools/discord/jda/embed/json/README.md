# JSON Embed Builder for JDA

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

Embeds are a great way to make your bot look professional. They can be used to display information in a nice and organized way. This class allows you to create embeds easily and quickly. You can use it to create embeds for your bot using JDA.

## Why I created this class ?

Because I'm a big fan of configuration files. I like to have a configuration file for everything. So I wanted to create a class that allows me to create embeds using a json file. This way, I can easily create embeds and modify them without having to modify the code.

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
| [JavaDiscordAPI](https://github.com/discord-jda/JDA)                 |
| [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson) | 

## How to use it ?
You can use this class in your projects in two different ways :
- Add the dependency to your project using Maven, Gradle, or any other dependency manager.
- You can also import the class directly into your project.

First, you need to create a new JSONEmbedBuilder object. Then you can use the following methods to create your embed :

```java
String jsonEmbed = "{\"title\": \"My Title\", \"description\": \"My Description\", \"color\": \"#ff0000\"}";

JsonEmbedBuilder builder = new JsonEmbedBuilder(jsonEmbed);
```

To build the embed, simply use the build() method :

```java
MessageEmbed embed = builder.build();
```

You can also build it in one line :

```java
MessageEmbed embed = new JsonEmbedBuilder("{\"title\": \"My Title\", \"description\": \"My Description\", \"color\": \"#ff0000\"}").build();
```

To build the embed with the good fields, you can use the following tools created by [cubedhuang](https://github.com/cubedhuang) :
- https://embed.dan.onl/

If you need to read the embed from a file, refer to [JsonFileReader](../../../../java/files/json/reader/).

## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit

The JsonEmbedBuilder class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team.