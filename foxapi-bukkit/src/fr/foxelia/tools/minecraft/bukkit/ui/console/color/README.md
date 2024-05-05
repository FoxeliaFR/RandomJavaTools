# Colored Console for Bukkit, Spigot, Paper, etc.

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

ColoredConsole is a class that allows you to bring back colors to your console logs messages on Minecraft plugins servers.

## Why I created this class ?

When the log4shell vulnerability was discovered, some patches were made to the Java Virtual Machine. Now, when you use colors in your console it will be displayed as `§c` instead of the color red. This class allows you to display colors in your console logs without any problem. This class uses the ANSI escape codes to display colors in the console.

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
Simply add the library to your project and enjoy it.

Before when you wanted to display a message in the console, you did it like this :
```java
getLogger().info(Level.INFO, ChatColor.GREEN + "Hello World !");
```

Now you can do it like this :
```java
getLogger().info(Level.INFO, ColoredConsole.GREEN + "Hello World !" + ColoredConsole.RESET);
```

If before you was preffering using the § or & symbol to display colors, you can do it like this :
```java
getLogger().info(Level.INFO, ColoredConsole.translateAlternateColorCodes("§aHello World !"));
```

or

```java
getLogger().info(Level.INFO, ColoredConsole.translateAlternateColorCodes('&', "&aHello &eWorld !"));
```

You can also retrieve a color by its bukkit color or its name :
```java
getLogger().info(Level.INFO, ColoredConsole.getByBukkitColor(ChatColor.GREEN) + "Hello World !" + ColoredConsole.RESET);
```

## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit
The ColoredConsole class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team. For a smooth transition, the class was inspired by the class [ChatColor](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/ChatColor.html) of the Bukkit API.