# Carpet TCTC Addition
[![Minecraft](http://cf.way2muchnoise.eu/versions/Minecraft_513524_all.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition/files)
[![License](https://img.shields.io/github/license/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=License&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/blob/main/LICENSE)
![Languages](https://img.shields.io/github/languages/top/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)
![Java-8~18](https://img.shields.io/badge/Java-8%20%7C%209%20%7C%2010%20%7C%2011%20%7C%2012%20%7C%2013%20%7C%2014%20%7C%2015%20%7C%2016%20%7C%2017%20%7C%2018-orange?style=flat-square)
[![Codacy Grade](https://img.shields.io/codacy/grade/f55de957650840f0be367cedb027aeba?label=Codacy%20Grade&style=flat-square)](https://app.codacy.com/gh/The-Cat-Town-Craft/Carpet-TCTC-Addition/dashboard)
[![Issues](https://img.shields.io/github/issues/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Issues&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Pull%20Requests&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/pulls)
[![Last build](https://img.shields.io/github/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/CI/dev?label=Last%20build&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/CI.yml)
[![Github Release](https://img.shields.io/github/v/release/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Release&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Github Release Downloads](https://img.shields.io/github/downloads/The-Cat-Town-Craft/Carpet-TCTC-Addition/total?label=Github%20Release%20Downloads&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Modrinth Downloads](https://img.shields.io/modrinth/dt/vbBQ6dVH?label=Modrinth%20Downloads&logo=Modrinth%20Downloads&style=flat-square)](https://modrinth.com/mod/carpet-tctc-addition)
[![CurseForge Downloads](http://cf.way2muchnoise.eu/513524.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition)

[中文](./README_ZH_CN.md)

**Warning: we use our own SettingManager to manage the rules and you need to manually migrate the default configuration saved in carpet.conf.**

A [fabric-carpet](https://github.com/gnembon/fabric-carpet) extension mod. It offers some interesting features.

Operation command: `/carpettctcaddition`

## Dependencies

| Dependency | Type     | Download                                                                                                                                                                            |
|------------|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Carpet     | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/carpet) &#124; [GitHub](https://github.com/gnembon/fabric-carpet)                                                         |
| Fabric API | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) &#124; [GitHub](https://github.com/FabricMC/fabric) &#124; [Modrinth](https://modrinth.com/mod/fabric-api)    |
| MagicLib   | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/magiclib) &#124; [GitHub](https://github.com/Hendrix-Shen/MagicLib) &#124; [Modrinth](https://modrinth.com/mod/magiclib)  |

## Rule List

### blockIllegalUsername

Verify username using online mode rules.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `MISC`, `PROTOCOL`

### commandFix

Enables /fix command to fix chunk data.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `COMMAND`

### commandFreecam

Enables /freecam command to toggle your camera mode.

- Type: `string`
- Default value: `true`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `COMMAND`

### commandGC

Enables /gc command to allow you to force gc with jvm.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `COMMAND`

### commandHere

Enables /here command to allow you to share your location with other players.

- Type: `string`
- Default value: `true`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `COMMAND`

### commandOperator

Enables /operator command to allow you to change the player's permission level.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `COMMAND`

### cameraModeDisableSpectatePlayers

Disables players in /c from spectating other players.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `COMMAND`, `FEATURE`

### disableIllegalChatCharacterCheck

Allows you to use characters such as subsections in the game.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `CLIENT`, `EXPERIMENTAL`, `MISC`

### disableParticlesPackets

Disable particle packet sending to reduce bandwidth usage.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `CLIENT`, `FEATURE`, `MISC`

### dispenserCollectExperience

Dispenser firing glass bottles at players will convert to experience bottles.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `EXPERIMENTAL`, `FEATURE`

### enderPlatform

Is obsidian platform generated in the end.

all - Generate ender platform when all entities are transferred to the_end dimension.

none - Ender platform will not be generated anyway.

player - Ender platform is generated only when the player entity teleports to the_end dimension.

- Type: `enum`
- Default value: `player`
- Suggested options: `all`, `none`, `player`
- Categories: `FEATURE`, `WORLD-GENERATE`

*For Minecraft 1.16.5 and later branches, the default value is `all`*

### endGatewayChunkLoader

When the entity passes through the ender gateway, the target chunk will be loaded for 15 seconds like nether portal.

all - Give the target chunk a load ticket when all entities through the ender gateway.

except_player - Give the target chunk a load ticket when all entities except the player through the ender gateway.

item_only - Give the target chunk a load ticket when the player entities except the player through the ender gateway.

off - Vanilla action.

- Type: `enum`
- Default value: `off`
- Suggested options: `all`, `except_player`, `item_only`, `off`
- Categories: `FEATURE`

### freecamRestoreLocation

Return to the starting position when you have finished spectating.

- Type: `boolean`
- Default value: `true`
- Suggested options: `false`, `true`
- Categories: `COMMAND`, `MISC`

### hereGlowTime

Time highlighted when using here command shared coordinates.

- Type: `int`
- Default value: `15`
- Suggested options: `0`, `15`
- Categories: `COMMAND`, `MISC`

### illegalPistonActionFix

Fix headless pistons to destroy blocks.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `FEATURE`

### opLevelBelowSelf

Operators can only grant the same or lower permissions to other players than themselves.

- Type: `boolean`
- Default value: `true`
- Suggested options: `false`, `true`
- Categories: `COMMAND`

### playerStats

Control of player and bot statistics.

bot - Only bots are allowed to gain stats.

both - Both bots and players are allowed to gain stats.

none - No one allowed to gain stats.

player - Only players are allowed to gain stats.

- Type: `enum`
- Default value: `both`
- Suggested options: `bot`, `both`, `none`, `player`
- Categories: `FEATURE`

### shulkerBoxDupeFix

Taking items from a shulker box while destroying it will result in item duplication.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `BUGFIX`

### updateSuppressionCrashFix

Fix updates suppression causing server crashes.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `BUGFIX`

### voidDevouringBehavior

The action that will occur when the player falls into the void.

instant_death - Dies immediately when entering the void.

teleport_to_spawn - Teleport to the player's spawn point when entering the void, or to the world spawn point if it does not exist.

vanilla - Vanilla behaviour.

- Type: `enum`
- Default value: `false`
- Suggested options: `instant_death`, `teleport_to_spawn`, `vanilla`
- Categories: `EXPERIMENTAL`, `FEATURE`

### voidTradeFix

Once the villager entity is unloaded, the trading interface will be closed.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `BUGFIX`

### voxelMapWorldName

Send VoxelMap world information packets to client.

- Type: `string`
- Default value: `#none`
- Suggested options: `#none`, `creative`, `mirror`, `survival`
- Categories: `PROTOCOL`

### wetSpongeAbsorbLava

Wet sponges will be able to absorb lava.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `EXPERIMENTAL`, `FEATURE`

### wetSpongeAbsorbLavaLimit

Maximum offset limit for wet sponge.

- Type: `int`
- Default value: `32`
- Suggested options: `32`, `64`
- Categories: `EXPERIMENTAL`, `FEATURE`

### wetSpongeAbsorbLavaRange

Maximum lava sucking for wet sponge.

- Type: `int`
- Default value: `3`
- Suggested options: `3`, `6`
- Categories: `EXPERIMENTAL`, `FEATURE`

### xaeroMapWorldName

Send XaeroMap world information packets to client.

- Type: `string`
- Default value: `#none`
- Suggested options: `#none`, `creative`, `mirror`, `survival`
- Categories: `PROTOCOL`

## Development

### Support

Current main development for Minecraft version: 1.19 

And use `preprocess` to be compatible with all versions.

**Note: We only accept the following versions of issues, which are the last updates of each MC major version. Please note that this information is time-sensitive and any version of the issue not listed here will be closed**

- Minecraft 1.14.4
- Minecraft 1.15.2
- Minecraft 1.16.5
- Minecraft 1.17.1
- Minecraft 1.18.2
- Minecraft 1.19

### Mappings

We are using the **Mojang official** mappings to de-obfuscate Minecraft and insert patches.

### Document

The English doc and the Chinese doc are aligned line by line.

## License

This project is available under the LGPLv3 license. Feel free to learn from it and incorporate it in your own projects.