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

[English](./README.md)

**警告: 自从 2.0 版本开始，我们使用自己的SettingManager来管理规则，您需要手动迁移carpet.conf中保存的默认配置**

❗在报告问题前，请务必尝试最新[测试版](https://github.com/The-Cat-Town-Craft/Carpet-TCTC-Addition/releases)，检查问题是否依然存在。

一个 [fabric-carpet](https://github.com/gnembon/fabric-carpet) 扩展模组。 它提供了一些有趣的特性。

管理命令: `/carpet-tctc-addition`

## 依赖项

| 依赖         | 类型  | 下载                                                                                                                                                                                 |
|------------|-----|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Carpet     | 必须  | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/carpet) &#124; [Github](https://github.com/gnembon/fabric-carpet)                                                        |
| Fabric API | 必须  | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) &#124; [Github](https://github.com/FabricMC/fabric) &#124; [Modrinth](https://modrinth.com/mod/fabric-api)   |
| MagicLib   | 必须  | [CurseForge](https://www.curseforge.com/minecraft/mc-mods/magiclib) &#124; [GitHub](https://github.com/Hendrix-Shen/MagicLib) &#124; [Modrinth](https://modrinth.com/mod/magiclib) |

## 规则列表
## 阻止非法用户名 (blockIllegalUsername)
使用正版模式规则验证用户名。
- 分类: `杂项`, `协议`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 假人Tab栏名称前缀 (botTabListNamePrefix)
为Tab栏中的假人添加前缀，使用 & 来代替 § 以格式化文本。
- 分类: `杂项`, `协议`
- 类型: `字符串`
- 默认值: #none
- 参考值: `#none`, `[Bot]`
## 假人Tab栏名称后缀 (botTabListNameSuffix)
为Tab栏中的假人添加后缀，使用 & 来代替 § 以格式化文本。
- 分类: `杂项`, `协议`
- 类型: `字符串`
- 默认值: #none
- 参考值: `#none`, `[Fake]`
## 禁止旁观者传送 (cameraModeDisableSpectatePlayers)
禁止旁观者玩家快捷传送。
- 分类: `命令`, `特性`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 区块修复命令 (commandFix)
启用 /fix 命令用于修复区块数据。
- 分类: `命令`
- 类型: `字符串`
- 默认值: ops
- 参考值: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 验证器:
    - 命令(权限等级)
## 灵魂出窍命令 (commandFreecam)
启用 /freecam 允许你切换旁观模式。
- 分类: `命令`
- 类型: `字符串`
- 默认值: true
- 参考值: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 验证器:
    - 命令(权限等级)
## 强制垃圾回收 (commandGC)
启用 /gc 允许你强制jvm垃圾回收。
- 分类: `命令`
- 类型: `字符串`
- 默认值: ops
- 参考值: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 验证器:
    - 命令(权限等级)
## 共享坐标命令 (commandHere)
启用 /here 命令来让你可以与其他玩家分享你的位置。
- 分类: `命令`
- 类型: `字符串`
- 默认值: true
- 参考值: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 验证器:
    - 命令(权限等级)
## 权限控制命令 (commandOperator)
启用 /operator 命令来允许你更改玩家的权限等级。
- 分类: `命令`
- 类型: `字符串`
- 默认值: ops
- 参考值: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 验证器:
    - 命令(权限等级)
## 禁用聊天非法字符检查 (disableIllegalChatCharacterCheck)
允许你在游戏中使用 分节符 等字符。
- 分类: `客户端`, `实验性`, `杂项`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 禁用粒子发包 (disableParticlesPackets)
禁用粒子包发送以降低带宽占用。
- 分类: `客户端`, `特性`, `杂项`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 发射器收集经验 (dispenserCollectExperience)
发射器向玩家发射玻璃瓶将转换为附魔之瓶。
- 分类: `实验性`, `特性`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 末路之地折跃门区块加载器 (endGatewayChunkLoader)
当实体穿越末路之地折跃门时，目标区块会像下界传送门一样使目标区块获得 15 秒的加载。

all - 所有实体穿过末路之地折跃门时，给予目标区块加载票。

except_player - 除玩家外的所有实体穿过末路之地折跃门时，给予目标区块加载票。

item_only - 物品实体穿过末路之地折跃门时，给予目标区块加载票。

off - 原版行为。
- 分类: `特性`
- 类型: `枚举`
- 默认值: off
- 参考值: `all`, `except_player`, `item_only`, `off`
- 验证器:
    - 枚举(区分大小写)
## 末路之地平台 (enderPlatform)
是否在实体进入末路之地时生成黑曜石平台。

all - 任何实体进入末路之地维度时，生成黑曜石平台。

none - 任何实体进入末路之地维度时，都不生成黑曜石平台。

player - 玩家实体进入末路之地维度时，生成黑曜石平台。
- 分类: `特性`, `世界生成`
- 类型: `枚举`
- 默认值: all
- 参考值: `all`, `none`, `player`
- 验证器:
    - 枚举(区分大小写)
## 灵魂回溯 (freecamRestoreLocation)
结束旁观后回到初始位置。
- 分类: `命令`, `杂项`
- 类型: `布尔`
- 默认值: true
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 高亮时间 (hereGlowTime)
使用 here 共享坐标时高亮的时间。
- 分类: `命令`, `杂项`
- 类型: `整型`
- 默认值: 15
- 参考值: `0`, `15`
## 非法活塞动作修复 (illegalPistonActionFix)
修复无头活塞破坏方块。
- 分类: `特性`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 音符盒区块加载器 (noteBlockChunkLoader)
音符盒被激活时, 若上方为黑曜石下方为绿宝石矿石时则赋予所在区块一个与下界传送门等效的加载票。
- 分类: `特性`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## op等级限制 (opLevelBelowSelf)
操作员只能给予其他玩家与自己相同或更低的权限。
- 分类: `命令`
- 类型: `布尔`
- 默认值: true
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 统计信息 (playerStats)
玩家与假人统计信息的控制。

bot - 仅假人可获得统计信息。

both - 玩家和假人均可获得统计信息。

none - 玩家和假人均不可获得统计信息。

player - 仅玩家可获得统计信息。
- 分类: `特性`
- 类型: `枚举`
- 默认值: both
- 参考值: `bot`, `both`, `none`, `player`
- 验证器:
    - 枚举(区分大小写)
## 潜影盒复制修复 (shulkerBoxDupeFix)
在破坏潜影盒的同时取走其中的物品会导致物品复制。
- 分类: `漏洞修复`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 更新抑制崩溃修复 (updateSuppressionCrashFix)
修复更新抑制造成的服务器崩溃。
- 分类: `漏洞修复`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 虚空吞噬行为 (voidDevouringBehavior)
玩家坠入虚空后发生的动作。

instant_death - 进入虚空后立即死亡。

teleport_to_spawn - 进入虚空后传送至玩家出生点，若不存在则传送至世界出生点。

teleport_to_spawn_cost_totem - 进入虚空后传送至玩家出生点，若不存在则传送至世界出生点。此行为消耗一个图腾，若玩家不持有图腾则会受到虚空伤害。

teleport_to_spawn_cost_totem_or_death - 进入虚空后传送至玩家出生点，若不存在则传送至世界出生点。此行为消耗一个图腾，若玩家不持有图腾则会立即死亡。

vanilla - 原版行为。
- 分类: `实验性`, `特性`
- 类型: `枚举`
- 默认值: vanilla
- 参考值: `instant_death`, `teleport_to_spawn`, `teleport_to_spawn_cost_totem`, `teleport_to_spawn_cost_totem_or_death`, `vanilla`
- 验证器:
    - 枚举(区分大小写)
## 虚空交易修复 (voidTradeFix)
一旦村民实体被卸载，将关闭交易界面。
- 分类: `漏洞修复`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 体素地图世界名 (voxelMapWorldName)
向客户端发送体素地图世界信息数据包。
- 分类: `协议`
- 类型: `字符串`
- 默认值: #none
- 参考值: `#none`, `creative`, `mirror`, `survival`
## 湿海绵吸收岩浆 (wetSpongeAbsorbLava)
湿海绵将可以吸收岩浆。
- 分类: `实验性`, `特性`
- 类型: `布尔`
- 默认值: false
- 参考值: `true`, `false`
- 验证器:
    - 严格(不区分大小写)
## 湿海绵吸收岩浆限制 (wetSpongeAbsorbLavaLimit)
湿海绵吸收岩浆最大数量限制。
- 分类: `实验性`, `特性`
- 类型: `整型`
- 默认值: 32
## 湿海绵吸收岩浆距离 (wetSpongeAbsorbLavaRange)
湿海绵吸收岩浆最大偏移限制。
- 分类: `实验性`, `特性`
- 类型: `整型`
- 默认值: 3
## xaero地图世界名 (xaeroMapWorldName)
向客户端发送xaero地图世界信息数据包。
- 分类: `协议`
- 类型: `字符串`
- 默认值: #none
- 参考值: `#none`, `creative`, `mirror`, `survival`

## 开发

### 支持

当前主开发版本：1.20

并且使用 `预处理` 来兼容各版本。

**注意: 我们仅接受以下版本的议题。请注意该信息的时效性，任何不在此列出的版本议题均会被关闭。**

- Minecraft 1.14.4
- Minecraft 1.15.2
- Minecraft 1.16.5
- Minecraft 1.17.1
- Minecraft 1.18.2
- Minecraft 1.19.2
- Minecraft 1.19.3
- Minecraft 1.19.4
- Minecraft 1.20

### 混淆映射表

我们使用 **Mojang 官方** 混淆映射表来反混淆 Minecraft 并插入补丁程序。

### 文档

英文文档与中文文档是逐行对应的。

## 许可

此项目在 LGPLv3许可证 下可用。 从中学习，并将其融入到您自己的项目中。
