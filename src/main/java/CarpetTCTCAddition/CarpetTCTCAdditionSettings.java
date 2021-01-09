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
        desc = "You can share your position with other players.",
        category = {"TCTC", RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandHere = "true";

    @Rule(
        desc = "You can check players' position if they are online.",
        category = {"TCTC", RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandFind = "ops";

    @Rule(
        desc = "Get server Operator by yourself.(Modify command permissions)",
        category = {"TCTC", RuleCategory.COMMAND, RuleCategory.CREATIVE},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandOp = "3";

    @Rule(
        desc = "Check seed in this world.(Modify command permissions)",
        category = {"TCTC", RuleCategory.COMMAND, RuleCategory.SURVIVAL},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandSeed = "2";

    @Rule
        (desc = "Check tps and mspt this time.",
        category = {"TCTC", RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandTps = "true";

    /* 特性相关*/
    @Rule(
        desc = "Disables players in /c from spectating other players",
        category = {"TCTC", RuleCategory.SURVIVAL, RuleCategory.FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
        desc = "Block update event.",
        category = {"TCTC", RuleCategory.FEATURE}
    )
    public static boolean blockUpdate = true;

    @Rule(
        desc = "Allows fake players to gain stats.",
        category = {"TCTC", RuleCategory.FEATURE}
    )
    public static boolean fakePlayerStats = true;

    @Rule(
            desc = "All dupe bug for donkey and llmama.",
            category = {"TCTC", RuleCategory.FEATURE, RuleCategory.BUGFIX}
    )
    public static boolean llamaDupeExploit = true;

    @Rule(
        desc = "Light update event, don't set false as default setting or your server can't start again until you set it to true.",
        category = {"TCTC", RuleCategory.FEATURE})
    public static boolean lightUpdates = true;

    /* 优化相关 */
    @Rule(
        desc = "Adjust the parameters of carpet MergeTNT",
        category = {"TCTC", RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL, RuleCategory.TNT})
    public static boolean tweakMergeTNT = false;

    /* BUG 修复相关 */
    @Rule(
        desc = "Limit player command spawn fakeplayer name length.",
        category = {"TCTC", RuleCategory.BUGFIX}
    )
    public static boolean fakePlayerNameLengthLimit = false;

    @Rule(
        desc = "Remove UpdateSuppression in the world",
        category = {"TCTC", RuleCategory.BUGFIX}
    )
    public static boolean removeUpdateSuppression = false;

    @Rule(
        desc = "Fixes updates suppression causing server crashes.",
        category = {"TCTC", RuleCategory.BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;
}
