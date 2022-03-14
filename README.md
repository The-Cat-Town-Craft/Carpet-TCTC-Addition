# Carpet TCTC Addition
[![Minecraft](http://cf.way2muchnoise.eu/versions/Minecraft_513524_all.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition/files)
[![License](https://img.shields.io/github/license/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=License&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/blob/main/LICENSE)
![Java-8](https://img.shields.io/github/languages/top/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)
![Languages](https://img.shields.io/badge/Java-8-orange?style=flat-square)
[![Codacy Grade](https://img.shields.io/codacy/grade/f55de957650840f0be367cedb027aeba?label=Codacy%20Grade&style=flat-square)](https://app.codacy.com/gh/The-Cat-Town-Craft/Carpet-TCTC-Addition/dashboard)
[![Issues](https://img.shields.io/github/issues/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Issues&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Pull%20Requests&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/pulls)
[![CI](https://img.shields.io/github/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/CI/dev?label=Public%20Beta&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/CI.yml?query=branch%3Adev)
[![CI](https://img.shields.io/github/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/CI/master?label=Public%20Release&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/CI.yml?query=branch%3Amaster)
[![Github Release](https://img.shields.io/github/v/release/The-Cat-Town-Craft/Carpet-TCTC-Addition?label=Release&include_prereleases&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Github Release Downloads](https://img.shields.io/github/downloads/The-Cat-Town-Craft/Carpet-TCTC-Addition/total?label=Github%20Release%20Downloads&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![CurseForge Downloads](http://cf.way2muchnoise.eu/513524.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition)

[中文](./README_ZH_CN.md)

**Warning: The project is still in the early development stage.**

A [fabric-carpet](https://github.com/gnembon/fabric-carpet) extension mod. It offers some interesting features.

## Dependencies

| Dependency | Type     | Download                                                                                                                                                                         |
| ---------- | -------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Carpet     | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/carpet) &#124; [Github](https://github.com/gnembon/fabric-carpet)                                                      |
| Fabric API | Required | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) &#124; [Github](https://github.com/FabricMC/fabric) &#124; [Modrinth](https://modrinth.com/mod/fabric-api) |

## Rule List

### blockIllegalUsername

Verify username using online mode rules.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-MISC`, `TCTC-PROTOCOL`

### commandFix

Enables /fix command to fix chunk data.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `TCTC`, `TCTC-COMMAND`

### commandFreecam

Enables /freecam command to toggle your camera mode.

- Type: `string`
- Default value: `true`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `TCTC`, `TCTC-COMMAND`

### commandGC

Enables /gc command to allow you to force gc with jvm.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `TCTC`, `TCTC-COMMAND`

### commandHere

Enables /here command to allow you to share your location with other players.

- Type: `string`
- Default value: `true`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `TCTC`, `TCTC-COMMAND`

### commandOperator

Enables /operator command to allow you to change the player's permission level.

- Type: `string`
- Default value: `ops`
- Suggested options: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- Categories: `TCTC`, `TCTC-COMMAND`

### cameraModeDisableSpectatePlayers

Disables players in /c from spectating other players.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-FEATURE`

### disableIllegalChatCharacterCheck

Allows you to use characters such as subsections in the game.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-CLIENT`, `TCTC-EXPERIMENTAL`, `TCTC-MISC`

### enderPlatform

Is obsidian platform generated in the end.

all - Generate ender platform when all entities are transferred to the_end dimension.

none - Ender platform will not be generated anyway.

player - Ender platform is generated only when the player entity teleports to the_end dimension.

- Type: `enum`
- Default value: `player`
- Suggested options: `all`, `none`, `player`
- Categories: `TCTC`, `TCTC-FEATURE`, `TCTC-WORLD-GENERATE`

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
- Categories: `TCTC`, `TCTC-FEATURE`

### fireworkSpeedupCoefficient

Controlling the acceleration coefficient of a firework rocket.

- Type: `double`
- Default value: `1.5`
- Suggested options: `1.25`, `1.5`
- Categories: `TCTC`, `TCTC-CLIENT`, `TCTC-FEATURE`

### freecamRestoreLocation

Return to the starting position when you have finished spectating.

- Type: `boolean`
- Default value: `true`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-MISC`

### hereGlowTime

Time highlighted when using here command shared coordinates.

- Type: `int`
- Default value: `15`
- Suggested options: `0`, `15`
- Categories: `TCTC`, `TCTC-MISC`

### illegalPistonActionFix

Fix headless pistons to destroy blocks.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-FEATURE`

### playerStats

Control of player and bot statistics.

bot - Only bots are allowed to gain stats.

both - Both bots and players are allowed to gain stats.

none - No one allowed to gain stats.

player - Only players are allowed to gain stats.

- Type: `enum`
- Default value: `both`
- Suggested options: `bot`, `both`, `none`, `player`
- Categories: `TCTC`, `TCTC-FEATURE`

### updateSuppressionCrashFix

Fix updates suppression causing server crashes.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-BUGFIX`

### shulkerBoxDupeFix

Taking items from a shulker box while destroying it will result in item duplication.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-BUGFIX`

### voidTradeFix

Once the villager entity is unloaded, the trading interface will be closed.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-BUGFIX`

### voxelMapWorldName

Send VoxelMap world information packets to client.

- Type: `string`
- Default value: `#none`
- Suggested options: `#none`, `creative`, `mirror`, `survival`
- Categories: `TCTC`, `TCTC-PROTOCOL`

### wetSpongeAbsorbLava

Wet sponges will be able to absorb lava.

- Type: `boolean`
- Default value: `false`
- Suggested options: `false`, `true`
- Categories: `TCTC`, `TCTC-EXPERIMENTAL`, `TCTC-FEATURE`

### wetSpongeAbsorbLavaLimit

Maximum offset limit for wet sponge.

- Type: `int`
- Default value: `32`
- Suggested options: `32`, `64`
- Categories: `TCTC`, `TCTC-EXPERIMENTAL`, `TCTC-FEATURE`

### wetSpongeAbsorbLavaRange

Maximum lava sucking for wet sponge.

- Type: `int`
- Default value: `3`
- Suggested options: `3`, `6`
- Categories: `TCTC`, `TCTC-EXPERIMENTAL`, `TCTC-FEATURE`

### xaeroMapWorldName

Send XaeroMap world information packets to client.

- Type: `string`
- Default value: `#none`
- Suggested options: `#none`, `creative`, `mirror`, `survival`
- Categories: `TCTC`, `TCTC-PROTOCOL`

## Development

### Branches

Current main development for Minecraft version: 1.15.2.

Current maintaining branches:

- `1.14.4` for Minecraft 1.14.4
- `1.15.2` for Minecraft 1.15.2
- `1.16.5` for Minecraft 1.16.5
- `1.17.1` for Minecraft 1.17.1
- `1.18.1` for Minecraft 1.18.1

For general new features, implement them in `dev` branch first then merge it into other branches.

Branches merge order:

- `dev` -> `1.14.4`
- `dev` -> `1.15.2`
- `dev` -> `1.16.5`
- `dev` -> `1.17.1`
- `dev` -> `1.18.1`
- `1.15.2` -> `master` (When publishing **public releases**)

For version specific fixes / patches, implement them in relevant branches.

`master` branch is always consistent with the main development branch version.

Try not to affect version compatibility unless it's necessary.

### Mappings

We are using the **Mojang official** mappings to de-obfuscate Minecraft and insert patches.

### Document

The English doc and the Chinese doc are aligned line by line.

## License

This project is available under the GPLv3 license. Feel free to learn from it and incorporate it in your own projects.