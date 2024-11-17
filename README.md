# FoxeliaAPI (RandomJavaTools)

[![License](https://img.shields.io/badge/License-CC%20BY--SA%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-sa/4.0/)
[![Discord](https://img.shields.io/discord/341897164642975756?color=blue&label=Discord)](https://discord.foxelia.fr/)
[![GitHub](https://img.shields.io/github/stars/FoxeliaFR/RandomJavaTools?style=social)](https://github.com/FoxeliaFR/RandomJavaTools)

This is a collection of random Java tools made by the Foxelia team. You can use them in your projects following the according license.

## [Java](foxapi-core/src/fr/foxelia/tools/java)

This package contains tools to help you develop your Java applications.

* 📂 [Files](foxapi-core/src/fr/foxelia/tools/java/files) → You can find here some tools to help you manage files.
    * 🍵 [FilesDeleter](foxapi-core/src/fr/foxelia/tools/java/files/deleter) → A tool to delete files and directories recursively.
    * 🍵 [JsonFileReader](foxapi-core/src/fr/foxelia/tools/java/files/json/reader) → A tool to read JSON files.
* 📂 [AbstractWindowToolkit (AWT)](foxapi-core/src/fr/foxelia/tools/java/awt) → Tools for the Abstract Window Toolkit (AWT).
    * 📂 [Color](foxapi-core/src/fr/foxelia/tools/java/awt/color) → Tools related to colors.
        * 🍵 [ProgressiveColor](foxapi-core/src/fr/foxelia/tools/java/awt/color/gradients) → A tool to create a progressive color gradient between 3 colors.
* 📂 [NumericTools](foxapi-core/src/fr/foxelia/tools/java/number) → You can find here some tools to help you manage numbers.
    * 🍵 [NumericChecker](foxapi-core/src/fr/foxelia/tools/java/number/check/NumericChecker.java) → A tool to check if a string is a number.
    * 🍵 [NummberToString](foxapi-core/src/fr/foxelia/tools/java/number/display/NumberToString.java) → Convert a number to a short string (K, M, B).
    * 🍵 [RoundNumber](foxapi-core/src/fr/foxelia/tools/java/number/round/RoundNumber.java) → A tool to round a number to a specific number of decimal places.
* 🍵 [Pair](foxapi-core/src/fr/foxelia/tools/java/pair) → A tool to store a two objects in one.

## [Minecraft](foxapi-core/src/fr/foxelia/tools/minecraft)

This package contains tools to help you develop your Minecraft plugins, mods, or anything else related to Minecraft.

* 📂 [Bukkit](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit) → You can find here some tools to help you develop your Minecraft plugins.
    * 📂 [NMS](foxapi-nms/api/src/main/java/fr/foxelia/tools/minecraft/bukkit/nms) → Our tools which use NMS to interact with Minecraft.
        * 🍵 [Toaster](foxapi-nms/api/src/main/java/fr/foxelia/tools/minecraft/bukkit/nms/toast) → A tool to send toast notifications to players.
    * 📂 [UI](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui) → You can find here some tools to help you develop your Minecraft plugins UI.
        * 📂 [Console](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui/console) → Tools related to the console UI.
            * 🍵 [ColoredConsole](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui/console/color) → A tool to bring back colors to your console logs messages inspired by the Bukkit API.
        * 📂 [GUI](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui/gui) → Collection of classes to create inventory based GUI
          * 🍵 [GUI](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui/gui/GUI.java) → Create a GUI with basic features.
          * 🍵 [NavigableGUI](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/ui/gui/NavigableGUI.java) → Create a navigable GUI with pages.
    * 🍵 [Cooldown](foxapi-bukkit/src/fr/foxelia/tools/minecraft/bukkit/cooldown) → Check if a player is has does a specific action in a specific time.

## [Discord](foxapi-core/src/fr/foxelia/tools/discord)

This package contains tools to help you develop your Discord bots or anything else related to Discord.

* 📂 [Java Discord API (JDA)](foxapi-core/src/fr/foxelia/tools/discord/jda) → You can find here some tools to help you develop your Discord bots using JDA.
    * 📂 [Embed](foxapi-core/src/fr/foxelia/tools/discord/jda/embed) → Tools related to Discord embeds.
        * 🍵 [JsonEmbedBuilder](foxapi-core/src/fr/foxelia/tools/discord/jda/embed/json) → A tool to build embeds using JSON.