# Cooldown System

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

The Cooldown System is used to define a cooldown for a player and check if the player has done the action before the end of the cooldown.

## Why I created this class ?

I created this class because the Sonelia server needed a cooldown system to prevent players from moving before teleporting them to a location.

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

Before using this class, you need to install the plugin version of the FoxAPI on your server.

Firstly, you need to hook the Cooldowns you want to use in your plugin :

```java
import fr.foxelia.foxapi.FoxAPI;
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownType;

public class MyPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        FoxAPI.getInstance().hookCooldown(CooldownType.class);
    }
}
```

You can use the following values for the CooldownType :

| Cooldown Type | What it does                                         |
|---------------|------------------------------------------------------|
| GLOBAL        | Hook this type will hook every cooldowns types       |
| MOVE          | This type will detect if the player has moved        |
| TAKE_DAMAGE   | This type will detect if the player has taken damage |

Then you can now check if a player has done an action before the end of the cooldown :

```java
import fr.foxelia.tools.minecraft.bukkit.cooldown.CooldownManager;

public class MyClass {
    public void myMethod(Player player) {
        long cooldown = 1000; // 1 second
        // Check if the player has moved before the end of the cooldown
        if (CooldownManager.hasTriggered(player, CooldownType.MOVE, cooldown)) {
            // Do something
        }
        
        // Check if the player has done any action before the end of the cooldown
        if (CooldownManager.hasTriggered(player, CooldownType.GLOBAL, cooldown)) {
            // Do something
        }
    }
}
```


## Support
If you have any questions, you can contact us on our discord server : [https://discord.foxelia.fr/](https://discord.foxelia.fr/)

## Credit
The Cooldown System was created by [Zarinoow](https://github.com/Zarinoow/) of the [Foxelia](https://foxelia.fr/) team. Inspired by the class Cooldown class made by [ParadoxalUnivers](https://github.com/paradoxalunivers)