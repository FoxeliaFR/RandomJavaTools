# Toast Notification System

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

A toast notification is a small popup that appears in the top right corner of the screen. It is used to display a message to the user without interrupting their current activity. In minecraft, you receive a toast when you receive an advancement, unlock a recipe or complete a challenge.


## Why I created this class ?

After a lot of research on the web, I only found unanswered threads or fuzzy answers. I searched how to do that with ProtocolLib, but I didn't find anything. I also searched how to do that with NMS, but I didn't find anything either. So I decided to download some plugins who implements this feature and I reverse engineered them. Now I found the way to do that and __I want to share it with you to spare you this suffering__. 

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

| Library Name                                                                                    |
|-------------------------------------------------------------------------------------------------|
| [Spigot](https://www.spigotmc.org/wiki/spigot-nms-and-minecraft-versions-1-16/) (Including CraftBukkit) |
| [Gson](https://mvnrepository.com/artifact/com.google.code.gson/gson)                            | 

## How to use it ?
Firstly, add the class to your project and adapt the package name to your project. Now, adapt the imports to match the wanted minecraft version.<br><br>
You can use the following code to create a toast object :

```java
Toaster toast = new Toaster(icon, text, frame);
```

`icon` is an [ItemStack](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/inventory/ItemStack.html) object.<br>
`text` is a [String](https://docs.oracle.com/javase/8/docs/api/java/lang/String.html) object. It can be colored with [ChatColor](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/ChatColor.html) objects. I recommend you to not use more than 64 characters including spaces.<br>
`frame` is a AdvancementFrameType object. You can use the following values : `Toaster.TASK`, `Toaster.CHALLENGE` and `Toaster.GOAL`.<br><br>
AdvancementFrameType has the following attributes :<br>

| Type      | Title color | Title text           | Play sound |
|-----------|-------------|----------------------|------------|
| TASK      | YELLOW      | Advancement made!    | false      |
| CHALLENGE | PINK        | Challenge complete!  | true       |
| GOAL      | YELLOW      | Goal reached!        | false      |

<br>When you have created your toast object, you can send it to a player with the following code :

```java
toast.sendTo(player);
```

## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit

The Toaster class was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team. A huge thanks to [ZockerAxel](https://github.com/ZockerAxel/) who allowed me to reverse engineer his plugin [CrazyAdvancementsAPI](https://github.com/ZockerAxel/CrazyAdvancementsAPI) to create this class and accepted to share it with the community. 