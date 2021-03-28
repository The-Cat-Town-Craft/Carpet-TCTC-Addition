package CarpetTCTCAddition;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetTCTCAdditionSettings
{
    public final static String EXPLOIT = "Exploit";
    public final static String TCTC = "TCTC";
    public final static String WORLDGENERATION = "WorldGeneration";
    /* 命令相关 */
    @Rule(
            desc = "Send some packets to players in order to crash their client.",
            category =  {TCTC, RuleCategory.COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandCrash = "ops";

    @Rule(
        desc = "Enables /c and /s commands to quickly switch between camera and survival modes",
        extra = "/c and /s commands are available to all players regardless of their permission levels",
        category =  {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandCameramode = "false";

    @Rule(
        desc = "You can share your position with other players.",
        category = {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandHere = "true";

    @Rule(
        desc = "You can check players' position if they are online.",
        category = {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandFind = "ops";
    @Rule(
        desc = "Get server Operator by yourself.(Modify command permissions)",
        category = {TCTC, RuleCategory.COMMAND, RuleCategory.CREATIVE},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandOp = "3";

    @Rule(
        desc = "Check seed in this world.(Modify command permissions)",
        category = {TCTC, RuleCategory.COMMAND, RuleCategory.SURVIVAL},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandSeed = "2";

    @Rule(desc = "Check tps and mspt this time.",
        category = {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandTps = "true";

    /* 特性相关*/
    @Rule(
        desc = "Disables players in /c from spectating other players",
        category = {TCTC, RuleCategory.SURVIVAL, RuleCategory.FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
        desc = "Block update event.",
        category = {TCTC, RuleCategory.FEATURE}
    )
    public static boolean blockUpdate = true;

    @Rule(
        desc = "Allows fake players to gain stats.",
        category = {TCTC, RuleCategory.FEATURE}
    )
    public static boolean fakePlayerStats = true;

    @Rule(
        desc = "Light update event, don't set false as default setting or your server can't start again until you set it to true.",
        category = {TCTC, RuleCategory.FEATURE}
    )
    public static boolean lightUpdates = true;

    @Rule(
        desc = "Emerald ore receiving a block update will throw a StackOverflowError, simulating an update suppressor.",
        category = {TCTC, RuleCategory.CREATIVE, RuleCategory.FEATURE}
    )
    public static boolean oreUpdateSuppressor = false;

    /* 优化相关 */
    @Rule(
        desc = "Adjust the parameters of carpet MergeTNT",
        category = {TCTC, RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL, RuleCategory.TNT}
    )
    public static boolean tweakMergeTNT = false;

    /* BUG 修复相关 */
    @Rule(
        desc = "Hide something console spam.",
        category = {TCTC, RuleCategory.BUGFIX, RuleCategory.CLIENT}
    )
    public static boolean consoleSpamFix = false;

    @Rule(
        desc = "Limit player command spawn fakeplayer name length.",
        category = {TCTC, RuleCategory.BUGFIX}
    )
    public static boolean fakePlayerNameLengthLimit = false;

    @Rule(
        desc = "Remove UpdateSuppression in the world",
        category = {TCTC, RuleCategory.BUGFIX}
    )
    public static boolean removeUpdateSuppression = false;

    @Rule(
        desc = "Fixes updates suppression causing server crashes.",
        category = {TCTC, RuleCategory.BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;

    /* 漏洞 */
    @Rule(
        desc = "[MC-161754]All dupe bug for donkey and llmama.",
        category = {TCTC, EXPLOIT}
    )
    public static boolean llamaDupe = false;

    @Rule(
        desc = "[MC-113809]Fixes zero tick make Bamboo, Cactus, ChorusFlower, SugarCane grow up.",
        category = {TCTC, EXPLOIT}
    )
    public static boolean zeroTickFarm = false;

    /* 世界生成 相关 */
    @Rule(
            desc = "Is end spike generated in the end.",
            category = {TCTC, WORLDGENERATION, RuleCategory.FEATURE}
    )
    public static boolean endSpike = true;

    @Rule(
        desc = "Is obsidian platform generated in the end.",
        category = {TCTC, WORLDGENERATION, RuleCategory.FEATURE}
    )
    public static CreatePlatformOptions obsidianPlatform = CreatePlatformOptions.PLAYER;
    public enum CreatePlatformOptions {
        ALL(false, true),
        NONE(false, false),
        PLAYER(true, false);

        private final boolean createForPlayer;
        private final boolean createForAll;

        CreatePlatformOptions(boolean createForPlayer, boolean createForAll)
        {
            this.createForPlayer = createForPlayer;
            this.createForAll = createForAll;
        }
        public boolean shouldCreateForPlayer() {
            return this.createForPlayer;
        }
        public boolean shouldCreateForAll() {
            return this.createForAll;
        }
    }
}
