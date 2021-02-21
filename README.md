# Carpet TCTC Addition

依赖于 [fabric-carpet](https://github.com/gnembon/fabric-carpet) 的附属模组.

## 新增的功能

### 命令
 - [客户端崩溃](#客户端崩溃-commandCrash)
 - [查询玩家坐标](#查询玩家坐标-commandFind)
 - [共享玩家坐标](#共享玩家坐标-commandHere)
 - [管理员命令](#管理员命令-commandOp)
 - [获取当前世界种子](#获取当前世界种子-commandSeed)
 - [获取当前服务器运行速度](#获取当前服务器运行速度-commandTps)
### 特性
 - [禁用旁观者传送其他玩家](#禁用旁观者传送其他玩家-cameraModeDisableSpectatePlayers)
 - [方块更新](#方块更新-blockUpdate)
 - [假人计入统计信息](#假人计入统计信息-fakePlayerStats)
 - [光照更新](#光照更新-lightUpdates)
### 优化
 - [TNT合并调整](#TNT合并调整-tweakMergeTNT)
### BUG 修复
 - [羊驼复制](#羊驼复制-llamaDupe)
 - [假人玩家名长度限制](#假人玩家名长度限制-fakePlayerNameLengthLimit)
 - [移除更新抑制器](#移除更新抑制器-removeUpdateSuppression)
 - [修复更新抑制器崩服](#修复更新抑制器崩服-updateSuppressionCrashFix)
 - [0gt作物强制催熟](#0gt作物强制催熟-zeroTickFarm)

## 列表

### 方块更新 (blockUpdate)

切换玩家放置/破坏方块的更新

- 类型: `boolean`  
- 默认值: `true`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `FEATURE`

### 客户端崩溃 (commandCrash)

发送一个巨大的爆炸数据包交由客户端运算.

注意: 这会使玩家的客户端进入长时间的未响应状态.

- 类型: `string`  
- 默认值: `ops`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 分类: `TCTC`, `COMMAND` 

### 查询玩家坐标 (commandFind)

查询玩家坐标 

使用 `/find <玩家/目标选择器>` 来查询玩家坐标, 位于主世界/下界的玩家还会展示其对应另一维度的坐标.

- 类型: `string`  
- 默认值: `ops`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 分类: `TCTC`, `COMMAND` 

### 共享玩家坐标 (commandHere)

共享玩家坐标 

使用 `/here [玩家/目标选择器]` 来共享玩家坐标, 位于主世界/下界的玩家还会展示其对应另一维度的坐标.

对于 `权限等级 > 3` 的玩家可以展示其他玩家的坐标.

- 类型: `string`  
- 默认值: `true`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 分类: `TCTC`, `COMMAND` 

### 管理员命令 (commandOp)

修改管理员命令权限

调整 `/op` 命令的权限等级, 方便 创造/镜像 服玩家快速调整自己的管理员状态.

- 类型: `string`  
- 默认值: `3`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 分类: `TCTC`, `COMMAND`, `CREATIVE` 

### 获取当前世界种子 (commandSeed)

修改查询世界种子命令权限

调整 `/seed` 命令的权限等级, 方便在生存服快速获取当前世界种子.

- 类型: `string`  
- 默认值: `2`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4`
- 分类: `TCTC`, `COMMAND`, `SURVIVAL` 

### 获取当前服务器运行速度 (commandTps)

获取当前服务器实际 TPS 和 MSPT 信息

- 类型: `string`  
- 默认值: `true`  
- 参考选项: `true`, `false`, `ops`, `0`, `1`, `2`, `3`, `4```
- 分类: `TCTC`, `COMMAND` 

### 禁用旁观者传送其他玩家 (cameraModeDisableSpectatePlayers)

处于旁观者模式的玩家将不能通过玩家头像进行快捷传送

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `FEATURE` 

### 假人玩家名长度限制 (fakePlayerNameLengthLimit)

限制 carpet 召唤假人玩家名长度

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `BUGFIX`

### 假人计入统计信息 (fakePlayerStats)

假人玩家是否产生统计信息, 通常与计分板相关

- 类型: `boolean`  
- 默认值: `true`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `FEATURE`

### 羊驼复制 (llamaDupe)

[MC-161754]允许使用羊驼和驴子的复制机制.

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `FEATURE`, `BUGFIX` 

### 光照更新 (lightUpdates)

切换世界光照更新状态.

此代码来自由 @ten-miles-away 改写的 [HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet).

#### <b>警告:</b> 请勿设置默认关闭，否则服务器将无法启动

#### <b>警告:</b> 关闭光照更新时无法加载新区块，请提前加载区块，请勿在未加载区块上线

- 类型: `boolean`  
- 默认值: `true`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `FEATURE` 

### 移除更新抑制器 (removeUpdateSuppression)

启用后, 更新抑制器将无法抑制更新, 同时也不会造成崩溃.

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `BUGFIX` 

### 合并TNT调整(tweakMergeTNT)

对 carpet 的 TNT 合并参数进行调整, 以获得更好的优化.

此代码来自由 @ten-miles-away 改写的 [HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet).

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `BUGFIX` 

### 修复更新抑制器崩服 (updateSuppressionCrashFix)

防止更新抑制器造成崩溃.

此原理为抑制世界循环崩溃, 或许可防止某些其他原因造成崩溃.

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `BUGFIX` 

### 0gt作物强制催熟 (zeroTickFarm)

[MC-113809]修复 0t 方块移动使 柱子, 仙人掌, 紫颂花, 甘蔗生长.

- 类型: `boolean`  
- 默认值: `false`  
- 参考选项: `true`, `false`
- 分类: `TCTC`, `BUGFIX` 

# 感谢
- [gnembon](https://github.com/gnembon/) - [fabric-carpet](https://github.com/gnembon/fabric-carpet)
- [Xcom6000](https://github.com/X-com) - updateSuppressionCrashFix(Carpet112)
- [ten-miles-away](https://github.com/ten-miles-away) - [HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet)