package CarpetTCTCAddition;

import carpet.settings.Rule;
import carpet.settings.RuleCategory;

/**
 * Here is your example Settings class you can plug to use carpetmod /carpet settings command
 */
public class CarpetTCTCAdditionSettings
{
    public static final String TCTC = "TCTC";
    public static final String WORLDGENERATION = "WorldGeneration";
    /**
     * You can define your own catergories. It makes sense to create new category for all settings in your mod.
     */
    /* 命令相关 */
    @Rule(
        desc = "Send some packets to players in order to crash their client.",
        category =  {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandCrash = "ops";

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

    @Rule
        (desc = "Check tps and mspt this time.",
        category = {TCTC, RuleCategory.COMMAND},
        options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandTps = "true";

    /* 特性相关*/
    @Rule(
            desc = "Block update event.",
            category = {TCTC, RuleCategory.FEATURE}
    )
    public static boolean blockUpdate = true;

    @Rule(
        desc = "Disables players in /c from spectating other players",
        category = {TCTC, RuleCategory.SURVIVAL, RuleCategory.FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
        desc = "When the entity passes through the ender gateway, the target chunk will be loaded for 15 seconds like nether portal.",
        category = {TCTC, RuleCategory.CREATIVE, RuleCategory.FEATURE}
    )
    public static boolean endGateWayChunkLoader = false;

    @Rule(
        desc = "Allows fake players to gain stats.",
        category = {TCTC, RuleCategory.FEATURE}
    )
    public static boolean fakePlayerStats = true;

    @Rule(
        desc = "Light update event, don't set false as default setting or your server can't start again until you set it to true.",
        category = {TCTC, RuleCategory.FEATURE})
    public static boolean lightUpdates = true;

    @Rule(
        desc = "Emerald ore receiving a block update will throw a StackOverflowError, simulating an update suppressor.",
        category = {TCTC, RuleCategory.CREATIVE, RuleCategory.FEATURE}
    )
    public static boolean oreUpdateSuppressor = false;

    /* 优化相关 */
    @Rule(
        desc = "Adjust the parameters of carpet MergeTNT",
        category = {TCTC, RuleCategory.OPTIMIZATION, RuleCategory.EXPERIMENTAL, RuleCategory.TNT})
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
        desc = "[MC-161754]All dupe bug for donkey and llmama.",
        category = {TCTC, RuleCategory.FEATURE, RuleCategory.BUGFIX}
    )
    public static boolean llamaDupe = true;

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

    @Rule(
        desc = "[MC-113809]Fixes zero tick make Bamboo, Cactus, ChorusFlower, SugarCane grow up.",
        category = {TCTC, RuleCategory.BUGFIX}
    )
    public static boolean zeroTickFarm = true;

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
