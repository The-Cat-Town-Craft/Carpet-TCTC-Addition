# Carpet TCTC Addition

依赖于 [fabric-carpet](https://github.com/gnembon/fabric-carpet) 的附属模组

# 新增的功能
### 光照更新(lightUpdates) (感谢 [HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet))
用于关闭服务器光照更新
* 默认值: `true`
* 可选参数: `true`, `false`
* 关闭方法: `/carpet lightUpdates false`

**警告：** 请勿设置默认关闭，否则服务器将无法启动

**警告：** 关闭光照抑制时无法加载新区块，请提前加载区块，请勿在未加载区块上线

### 移除更新抑制器(removeUpdateSuppression)
关闭后更新抑制器将不会抑制更新, 同时控制台不会抛出异常
* 默认值: `true`
* 可选参数: `true`, `false`
* 关闭方法: `/carpet removeUpdateSuppression false`

### 修复更新抑制器崩服(updateSuppressionCrashFix) (感谢 [Xcom6000](https://github.com/X-com))
在保证更新抑制器可以正常工作的情况下, 防止非玩家更新崩服
* 默认值: `true`
* 可选参数: `true`, `false`
* 关闭方法: `/carpet updateSuppressionCrashFix false`


# 感谢
- [gnembon](https://github.com/gnembon/) - [fabric-carpet](https://github.com/gnembon/fabric-carpet)
- [Xcom6000](https://github.com/X-com) - updateSuppressionCrashFix
- [ten-miles-away](https://github.com/ten-miles-away) - [HIT-Carpet](https://github.com/HIT-Craft/HIT-Carpet)