# Carpet TCTC Addition
[![Minecraft](http://cf.way2muchnoise.eu/versions/Minecraft_513524_all.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition/files)
[![License](https://img.shields.io/github/license/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/blob/main/LICENSE)
[![Issues](https://img.shields.io/github/issues/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/issues)
[![Pull Requests](https://img.shields.io/github/issues-pr/The-Cat-Town-Craft/Carpet-TCTC-Addition?style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/pulls)
[![Public Beta](https://img.shields.io/github/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/Public%20Beta?label=Public%20Beta&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/Public%20Beta.yml)
[![Public Release](https://img.shields.io/github/workflow/status/The-Cat-Town-Craft/Carpet-TCTC-Addition/Public%20Release?label=Public%20Release&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/actions/workflows/Public%20Release.yml)
[![Github Release](https://img.shields.io/github/v/release/The-Cat-Town-Craft/Carpet-TCTC-Addition?include_prereleases&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![Github Release Downloads](https://img.shields.io/github/downloads/The-Cat-Town-Craft/Carpet-TCTC-Addition/total?label=Github%20Release%20Downloads&style=flat-square)](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)
[![CurseForge Downloads](http://cf.way2muchnoise.eu/513524.svg?badge_style=flat)](https://www.curseforge.com/minecraft/mc-mods/carpet-tctc-addition)

[English](./README.md)

**警告: 此项目仍然处于早期开发阶段。**

一个 [fabric-carpet](https://github.com/gnembon/fabric-carpet) 扩展模组。 它提供了一些有趣的特性。

## 规则列表

### 统计信息(playerStats)

玩家与假人统计信息的控制。

bot - 仅假人可获得统计信息。

both - 玩家和假人均可获得统计信息。

none - 玩家和假人均不可获得统计信息。

player - 仅玩家可获得统计信息。

- 类型: `enum`

- 默认值: `both`

- 参考数据: `bot`, `both`, `none`, `player`

- 类别: `TCTC`, `TCTC_FEATURE`

### 更新抑制崩溃修复(updateSuppressionCrashFix)

修复更新抑制造成的服务器崩溃。

- 类型: `boolean`

- 默认值: `false`

- 参考数据: `false`, `true`

- 类别: `TCTC`, `TCTC_BUGFIX`

## 开发

当前主开发分支：1.15.2

目前维护的分支：

- `1.15.2` 适用于 Minecraft 1.15.2

- `1.16.5` 适用于 Minecraft 1.16.5

- `1.17.1` 适用于 Minecraft 1.17.1

对于通用的新特性，在 `master` 分支中实现，再将其合并至其他分支。

分支合并顺序：

- `master` -> `1.15.2`

- `master` -> `1.16.5`

- `master` -> `1.17.1`

对于版本专用的修复/补丁，在对应的分支上实现即可。

`master` 永远与主开发分支版本一致。

除非必要，尽量不要影响版本兼容性。

英文文档与中文文档是逐行对应的。

## 许可

此项目在 GPLv3许可证 下可用. 从中学习, 并将其融入到您自己的项目中.