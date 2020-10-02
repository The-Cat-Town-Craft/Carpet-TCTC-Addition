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
    @Rule(desc="移除更新抑制器", category = {"TCTC", RuleCategory.BUGFIX})
    public static boolean removeUpdateSuppression = false;

    @Rule(desc="光照更新", category = {"TCTC"})
    public static boolean lightUpdates = true;

    @Rule(desc="修复更新抑制器崩服", category = {"TCTC", RuleCategory.BUGFIX})
    public static boolean updateSuppressionCrashFix = false;

    @Rule(desc="方块更新", category = {"TCTC", RuleCategory.BUGFIX})
    public static boolean blockUpdate = true;

    @Rule(desc="查看当前服务器实际TPS及MSPT", category = {"TCTC", RuleCategory.COMMAND})
    public static String commandTps = "true";

}
