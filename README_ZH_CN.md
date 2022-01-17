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

[English](./README.md)

**警告: 此项目仍然处于早期开发阶段。**

一个 [fabric-carpet](https://github.com/gnembon/fabric-carpet) 扩展模组。 它提供了一些有趣的特性。

## 依赖项

| 依赖       | 类型 | 下载                                                                                                                                                                             |
| ---------- | ---- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Carpet     | 必须 | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/carpet) &#124; [Github](https://github.com/gnembon/fabric-carpet)                                                      |
| Fabric API | 必须 | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) &#124; [Github](https://github.com/FabricMC/fabric) &#124; [Modrinth](https://modrinth.com/mod/fabric-api) |

## 规则列表

### 阻止非法用户名(blockIllegalUsername)

使用正版模式规则验证用户名。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-杂项`, `喵镇附属-协议`

### 区块修复命令(commandFix)

启用 /fix 命令用于修复区块数据。

- 类型: `字符串`
- 默认值: `ops`
- 参考数据: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- 类别: `喵镇附属`, `喵镇附属-命令`

### 强制垃圾回收(commandGC)

启用 /freecam 允许你切换旁观模式。

- 类型: `字符串`
- 默认值: `ops`
- 参考数据: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- 类别: `喵镇附属`, `喵镇附属-命令`

### 灵魂出窍命令(commandFreecam)

启用 /freecam 允许你切换旁观模式。

- 类型: `字符串`
- 默认值: `true`
- 参考数据: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- 类别: `喵镇附属`, `喵镇附属-命令`

### 共享坐标命令(commandHere)

启用 /here 命令来让你可以与其他玩家分享你的位置。

- 类型: `字符串`
- 默认值: `true`
- 参考数据: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- 类别: `喵镇附属`, `喵镇附属-命令`

### 权限控制命令(commandOperator)

启用 /operator 命令来允许你更改玩家的权限等级。

- 类型: `字符串`
- 默认值: `ops`
- 参考数据: `false`, `true`, `ops`, `0`, `1`, `2`, `3`, `4`
- 类别: `喵镇附属`, `喵镇附属-命令`

### 禁止旁观者传送(cameraModeDisableSpectatePlayers)

禁止旁观者玩家快捷传送。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-特性`

### 禁用聊天非法字符检查(disableIllegalChatCharacterCheck)

允许你在游戏中使用 分节符 等字符。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-客户端`, `喵镇附属-实验性`, `喵镇附属-杂项`

### 末路之地平台(enderPlatform)

是否在实体进入末路之地时生成黑曜石平台。

all - 任何实体进入末路之地维度时，生成黑曜石平台。

none - 任何实体进入末路之地维度时，都不生成黑曜石平台。

player - 玩家实体进入末路之地维度时，生成黑曜石平台。

- 类型: `枚举`
- 默认值: `player`
- 参考数据: `all`, `none`, `player`
- 类别: `喵镇附属`, `喵镇附属-特性`, `喵镇附属-世界生成`

*对于 Minecraft 1.16.5 及更高版本的分支，默认值为 `all`*

### 末路之地折跃门区块加载器(endGatewayChunkLoader)

当实体穿越末路之地折跃门时，目标区块会像下界传送门一样使目标区块获得 15 秒的加载。

all - 所有实体穿过末路之地折跃门时，给予目标区块加载票。

except_player - 除玩家外的所有实体穿过末路之地折跃门时，给予目标区块加载票。

item_only - 物品实体穿过末路之地折跃门时，给予目标区块加载票。

off - 原版行为。

- 类型: `枚举`
- 默认值: `off`
- 参考数据: `all`, `except_player`, `item_only`, `off`
- 类别: `喵镇附属`, `喵镇附属-特性`

### 烟花加速系数(fireworkSpeedupCoefficient)

控制烟花火箭的加速系数。

- 类型: `双精度浮点`
- 默认值: `1.5`
- 参考数据: `1.25`, `1.5`
- 类别: `喵镇附属`, `喵镇附属-客户端`, `喵镇附属-特性`

### 灵魂回溯(freecamRestoreLocation)

结束旁观后回到初始位置。

- 类型: `布尔值`
- 默认值: `true`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-杂项`

### 高亮时间(hereGlowTime)

使用 here 共享坐标时高亮的时间。

- 类型: `整型`
- 默认值: `15`
- 参考数据: `0`, `15`
- 类别: `喵镇附属`, `喵镇附属-杂项`

### 非法活塞动作修复(illegalPistonActionFix)

修复无头活塞破坏方块。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-特性`

### 统计信息(playerStats)

玩家与假人统计信息的控制。

bot - 仅假人可获得统计信息。

both - 玩家和假人均可获得统计信息。

none - 玩家和假人均不可获得统计信息。

player - 仅玩家可获得统计信息。

- 类型: `枚举`
- 默认值: `both`
- 参考数据: `bot`, `both`, `none`, `player`
- 类别: `喵镇附属`, `喵镇附属-特性`

### 潜影盒复制修复(shulkerBoxDupeFix)

在破坏潜影盒的同时取走其中的物品会导致物品复制。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-漏洞修复`

### 更新抑制崩溃修复(updateSuppressionCrashFix)

修复更新抑制造成的服务器崩溃。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-漏洞修复`

### 虚空交易修复(voidTradeFix)

一旦村民实体被卸载，将关闭交易界面。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-漏洞修复`

### 体素地图世界名(voxelMapWorldName)

向客户端发送体素地图世界信息数据包。

- 类型: `字符串`
- 默认值: `#none`
- 参考数据: `#none`, `creative`, `mirror`, `survival`
- 类别: `喵镇附属`, `喵镇附属-协议`

### 湿海绵吸收岩浆(wetSpongeAbsorbLava)

湿海绵将可以吸收岩浆。

- 类型: `布尔值`
- 默认值: `false`
- 参考数据: `false`, `true`
- 类别: `喵镇附属`, `喵镇附属-实验性`, `喵镇附属-特性`

### 湿海绵吸收岩浆限制(wetSpongeAbsorbLavaLimit)

湿海绵吸收岩浆最大数量限制。

- 类型: `整型`
- 默认值: `32`
- 参考数据: `32`, `64`
- 类别: `喵镇附属`, `喵镇附属-实验性`, `喵镇附属-特性`

### 湿海绵吸收岩浆距离(wetSpongeAbsorbLavaRange)

湿海绵吸收岩浆最大偏移限制。

- 类型: `整型`
- 默认值: `3`
- 参考数据: `3`, `6`
- 类别: `喵镇附属`, `喵镇附属-实验性`, `喵镇附属-特性`

### xaero地图世界名(voxelMapWorldName)

向客户端发送xaero地图世界信息数据包。

- 类型: `字符串`
- 默认值: `#none`
- 参考数据: `#none`, `creative`, `mirror`, `survival`
- 类别: `喵镇附属`, `喵镇附属-协议`

## 开发

### 分支

当前主开发分支：1.15.2

目前维护的分支：

- `1.14.4` 适用于 Minecraft 1.14.4
- `1.15.2` 适用于 Minecraft 1.15.2
- `1.16.5` 适用于 Minecraft 1.16.5
- `1.17.1` 适用于 Minecraft 1.17.1
- `1.18` 适用于 Minecraft 1.18-快照

对于通用的新特性，在 `dev` 分支中实现，再将其合并至其他分支。

分支合并顺序：

- `dev` -> `1.14.4`
- `dev` -> `1.15.2`
- `dev` -> `1.16.5`
- `dev` -> `1.17.1`
- `dev` -> `1.18`
- `1.15.2` -> `master` (当发布**公共发行版**时)

对于版本专用的修复/补丁，在对应的分支上实现即可。

`master` 永远与主开发分支版本一致。

除非必要，尽量不要影响版本兼容性。

### 混淆映射表

我们使用 **Mojang 官方** 混淆映射表来反混淆 Minecraft 并插入补丁程序。

### 文档

英文文档与中文文档是逐行对应的。

## 许可

此项目在 GPLv3许可证 下可用。 从中学习，并将其融入到您自己的项目中。