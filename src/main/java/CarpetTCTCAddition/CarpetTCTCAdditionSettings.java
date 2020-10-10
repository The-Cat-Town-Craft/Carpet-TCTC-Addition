package CarpetTCTCAddition;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetTCTCAdditionSettings
{
    /**
     * You can define your own catergories. It makes sense to create new category for all settings in your mod.
     */
    /* 命令相关 */
    @Rule(
        desc = "共享玩家坐标",
        category = {"TCTC", RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandHere = "true";

    @Rule(
        desc = "获取服务器管理员(修改该指令权限)",
        category = {"TCTC", RuleCategory.COMMAND, RuleCategory.CREATIVE},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandOp = "3";

    @Rule(
        desc = "查看当前地图种子(修改该指令权限)",
        category = {"TCTC", RuleCategory.COMMAND, RuleCategory.SURVIVAL},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandSeed = "2";

    @Rule
        (desc = "查看当前服务器实际TPS及MSPT",
        category = {"TCTC", RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandTps = "true";

    /* 特性相关*/
    @Rule(
        desc = "禁用旁观者传送其他玩家",
        category = {"TCTC", RuleCategory.SURVIVAL, RuleCategory.FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
        desc = "方块更新",
        category = {"TCTC", RuleCategory.FEATURE}
    )
    public static boolean blockUpdate = true;

    @Rule(
        desc = "光照更新",
        category = {"TCTC", RuleCategory.FEATURE})
    public static boolean lightUpdates = true;


    /* BUG 修复相关 */
    @Rule(desc = "移除更新抑制器", category = {"TCTC", RuleCategory.BUGFIX})
    public static boolean removeUpdateSuppression = false;

    @Rule(desc = "修复更新抑制器崩服", category = {"TCTC", RuleCategory.BUGFIX})
    public static boolean updateSuppressionCrashFix = false;

}
