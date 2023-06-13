# Carpet TCTC Addition
[![Minecraft](http://cf.way2muchnoise.eu/versions/Minecraft_513524_all.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition/files)
[![License](https://img.shields.io/github/license/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=License&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/blob/main/LICENSE)
![Languages](https://img.shields.io/github/languages/top/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)
![Java-8~18](https://img.shields.io/badge/Java-8%20%7C%209%20%7C%2010%20%7C%2011%20%7C%2012%20%7C%2013%20%7C%2014%20%7C%2015%20%7C%2016%20%7C%2017%20%7C%2018-orange?style=flat-square)
[![Codacy Grade](https://img.shields.io/codacy/grade/f55de957650840f0be367cedb027aeba?label=Codacy%20Grade&style=flat-square)](https://app.codacy.com/gh/The-Cat-Town-Craft/Carpet-TCTC-Addition/dashboard)
[![Issues](https://img.shields.io/github/issues/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Issues&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Pull%20Requests&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/pulls)
[![Last build](https://img.shields.io/github/actions/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/CI.yml?label=Last%20build&style=flat-square&branch=dev)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/CI.yml)
[![Stable Release](https://img.shields.io/github/v/release/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Stable%20Release&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Development Release](https://img.shields.io/github/v/release/The-Cat-Town-Craft/Carpet-TCTC-Addition?include_prereleases&label=Development%20Release&style=flat-square)](https://github.com/Carpet-TCTC-Addition/releases)
[![Github Release Downloads](https://img.shields.io/github/downloads/The-Cat-Town-Craft/Carpet-TCTC-Addition/total?label=Github%20Release%20Downloads&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/vbBQ6dVH?label=Modrinth%20Downloads&logo=Modrinth%20Downloads&style=flat-square)](https://modrinth.com/mod/carpet-tctc-addition)
[![CurseForge Downloads](http://cf.way2muchnoise.eu/513524.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition)

[中文](./README_ZH_CN.md)

**Warning: we use our own SettingManager to manage the rules and you need to manually migrate the default configuration saved in carpet.conf.**

❗Before reporting a problem, be sure to try the latest [beta version](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases) to check if the problem still exists.

A [fabric-carpet](https://github.com/gnembon/fabric-carpet) extension mod. It offers some interesting features.

Operation command: `/carpet-tctc-addition`

## Dependencies

| Dependency | Type     | Download                                                                                                                                                                            |
|------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Carpet     | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/carpet) &#124; [GitHub](https://github.com/gnembon/fabric-carpet)                                                         |
| Fabric API | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) &#124; [GitHub](https://github.com/FabricMC/fabric) &#124; [Modrinth](https://modrinth.com/mod/fabric-api)    |
| MagicLib   | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/magiclib) &#124; [GitHub](https://github.com/Hendrix-Shen/MagicLib) &#124; [Modrinth](https://modrinth.com/mod/magiclib)  |

## Rule List
## blockIllegalUsername
Verify username using online mode rules.
- Categories: `Misc`, `Protocol`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## botTabListNamePrefix
Add a prefix to the Bot in the TabList, using & instead of § to format the text.
- Categories: `Misc`, `Protocol`
- Type: `String`
- Default value: #none
- Options: `#none`, `[Bot]`
## botTabListNameSuffix
Add a suffix to the Bot in the TabList, using & instead of § to format the text.
- Categories: `Misc`, `Protocol`
- Type: `String`
- Default value: #none
- Options: `#none`, `[Fake]`
## cameraModeDisableSpectatePlayers
Disables players in /c from spectating other players.
- Categories: `Command`, `feature`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## commandFix
Enables /fix command to fix chunk data.
- Categories: `Command`
- Type: `String`
- Default value: ops
- Options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Validators:
    - Command(Permission level)
## commandFreecam
Enables /freecam command to toggle your camera mode.
- Categories: `Command`
- Type: `String`
- Default value: true
- Options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Validators:
    - Command(Permission level)
## commandGC
Enables /gc command to allow you to force gc with jvm.
- Categories: `Command`
- Type: `String`
- Default value: ops
- Options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Validators:
    - Command(Permission level)
## commandHere
Enables /here command to allow you to share your location with other players.
- Categories: `Command`
- Type: `String`
- Default value: true
- Options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Validators:
    - Command(Permission level)
## commandOperator
Enables /operator command to allow you to change the player's permission level.
- Categories: `Command`
- Type: `String`
- Default value: ops
- Options: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- Validators:
    - Command(Permission level)
## disableIllegalChatCharacterCheck
Allows you to use characters such as subsections in the game.
- Categories: `Client`, `Experimental`, `Misc`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## disableParticlesPackets
Disable particle packet sending to reduce bandwidth usage.
- Categories: `Client`, `feature`, `Misc`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## dispenserCollectExperience
Dispenser firing glass bottles at players will convert to experience bottles.
- Categories: `Experimental`, `feature`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## endGatewayChunkLoader
When the entity passes through the ender gateway, the target chunk will be loaded for 15 seconds like nether portal.

all - Give the target chunk a load ticket when all entities through the ender gateway.

except_player - Give the target chunk a load ticket when all entities except the player through the ender gateway.

item_only - Give the target chunk a load ticket when the player entities except the player through the ender gateway.

off - Vanilla action.
- Categories: `feature`
- Type: `Enum`
- Default value: off
- Options: `all`, `except_player`, `item_only`, `off`
- Validators:
    - Enum(Case-sensitive)
## enderPlatform
Is obsidian platform generated in the end.

all - Generate ender platform when all entities are transferred to the_end dimension.。

none - Ender platform will not be generated anyway.

player - Ender platform is generated only when the player entity teleports to the_end dimension.
- Categories: `feature`, `WorldGenerate`
- Type: `Enum`
- Default value: all
- Options: `all`, `none`, `player`
- Validators:
    - Enum(Case-sensitive)
## freecamRestoreLocation
Return to the starting position when you have finished spectating.
- Categories: `Command`, `Misc`
- Type: `Boolean`
- Default value: true
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## hereGlowTime
Time highlighted when using here command shared coordinates.
- Categories: `Command`, `Misc`
- Type: `Integer`
- Default value: 15
- Options: `0`, `15`
## illegalPistonActionFix
Fix headless pistons to destroy blocks.
- Categories: `feature`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## noteBlockChunkLoader
When the note block is activated, if the top is obsidian and the bottom is emerald ore, it gives the block a load ticket equivalent to the nether portal.
- Categories: `feature`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## opLevelBelowSelf
Operators can only grant the same or lower permissions to other players than themselves.
- Categories: `Command`
- Type: `Boolean`
- Default value: true
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## playerStats
Control of player and bot statistics.

bot - Only bots are allowed to gain stats.

both - Both bots and players are allowed to gain stats.

none - No one allowed to gain stats.

player - Only players are allowed to gain stats.
- Categories: `feature`
- Type: `Enum`
- Default value: both
- Options: `bot`, `both`, `none`, `player`
- Validators:
    - Enum(Case-sensitive)
## shulkerBoxDupeFix
Taking items from a shulker box while destroying it will result in item duplication.
- Categories: `Bugfix`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## updateSuppressionCrashFix
Fix updates suppression causing server crashes.
- Categories: `Bugfix`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## voidDevouringBehavior
The action that will occur when the player falls into the void.

instant_death - Dies immediately when entering the void.

teleport_to_spawn - Teleport to the player's spawn point when entering the void, or to the world spawn point if it does not exist.

teleport_to_spawn_cost_totem - Teleport to the player's spawn point when entering the void, or to the world spawn point if it does not exist. This action consumes a totem, and if the player does not hold a totem they will receive void damage.

teleport_to_spawn_cost_totem_or_death - Teleport to the player's spawn point when entering the void, or to the world spawn point if it does not exist. This action consumes a totem, and if the player does not hold a totem they will die immediately.

vanilla - Vanilla behaviour.
- Categories: `Experimental`, `feature`
- Type: `Enum`
- Default value: vanilla
- Options: `instant_death`, `teleport_to_spawn`, `teleport_to_spawn_cost_totem`, `teleport_to_spawn_cost_totem_or_death`, `vanilla`
- Validators:
    - Enum(Case-sensitive)
## voidTradeFix
Once the villager entity is unloaded, the trading interface will be closed.
- Categories: `Bugfix`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## voxelMapWorldName
Send VoxelMap world information packets to client.
- Categories: `Protocol`
- Type: `String`
- Default value: #none
- Options: `#none`, `creative`, `mirror`, `survival`
## wetSpongeAbsorbLava
Wet sponges will be able to absorb lava.
- Categories: `Experimental`, `feature`
- Type: `Boolean`
- Default value: false
- Options: `true`, `false`
- Validators:
    - Strict(Case-insensitive)
## wetSpongeAbsorbLavaLimit
Maximum offset limit for wet sponge.
- Categories: `Experimental`, `feature`
- Type: `Integer`
- Default value: 32
## wetSpongeAbsorbLavaRange
Maximum lava sucking for wet sponge.
- Categories: `Experimental`, `feature`
- Type: `Integer`
- Default value: 3
## xaeroMapWorldName
Send XaeroMap world information packets to client.
- Categories: `Protocol`
- Type: `String`
- Default value: #none
- Options: `#none`, `creative`, `mirror`, `survival`

## Development

### Support

Current main development for Minecraft version: 1.20

And use `preprocess` to be compatible with all versions.

**Note: We only accept the following versions of issues. Please note that this information is time-sensitive and any version of the issue not listed here will be closed**

- Minecraft 1.14.4
- Minecraft 1.15.2
- Minecraft 1.16.5
- Minecraft 1.17.1
- Minecraft 1.18.2
- Minecraft 1.19.2
- Minecraft 1.19.3
- Minecraft 1.19.4
- Minecraft 1.20

### Mappings

We are using the **Mojang official** mappings to de-obfuscate Minecraft and insert patches.

### Document

The English doc and the Chinese doc are aligned line by line.

## License

This project is available under the LGPLv3 license. Feel free to learn from it and incorporate it in your own projects.
