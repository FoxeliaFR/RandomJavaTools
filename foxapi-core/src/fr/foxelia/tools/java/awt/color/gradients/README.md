# Progressive Tricolor

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

A useful class to easily create color gradients with 3 different color.

## Why I created this class ?

I created this class because I needed to create a color gradient with 3 different colors to display a colored progress bar in Minecraft.

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

You can use the following code to delete a directory and all its content :

In this example, we will create a color gradient with 3 colors : red, yellow and green.
We will generate 100 colors range with a change of the color in the middle of the gradient.
We will get the color at the index 69.

```java
int nbColors = 100;

ProgressiveColor gradient = new ProgressiveColor(Color.RED, Color.YELLOW, Color.GREEN, nbColors/2, nbColors);

// Get the color at the index 69
Color color = gradient.getColorByPlacement(69);
```

Exemple of use in Minecraft :

```java
Player player = ...;
String message = "&aProgress : %color%%progress% %";
int progress = 50;

ProgressiveColor gradient = new ProgressiveColor(Color.RED, Color.YELLOW, Color.GREEN, 50, 100);
player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message
        .replace("%color%", net.md_5.bungee.api.ChatColor.of(gradient.getColorByPlacement(progress) + ""))
        .replace("%progress%", String.valueOf(progress))));
```




## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit
The ProgressiveColor class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team.