/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition;

import carpet.settings.Rule;

public class CarpetTCTCAdditionSettings {
    private static final String TCTC = "TCTC";
    private static final String TCTC_BUGFIX = "TCTC-BUGFIX";
    private static final String TCTC_CLIENT = "TCTC-CLIENT";
    private static final String TCTC_COMMAND = "TCTC-COMMAND";
    private static final String TCTC_EXPERIMENTAL = "TCTC-EXPERIMENTAL";
    private static final String TCTC_FEATURE = "TCTC-FEATURE";
    private static final String TCTC_MISC = "TCTC-MISC";
    private static final String TCTC_PROTOCOL = "TCTC-PROTOCOL";
    private static final String TCTC_WORLD_GENERATE = "TCTC-WORLD_GENERATE";

    @Rule(
            name = "blockIllegalUsername",
            desc = "Verify username using online mode rules.",
            category = {TCTC, TCTC_MISC, TCTC_PROTOCOL}
    )
    public static boolean blockIllegalUsername = false;

    @Rule(
            name = "commandFix",
            desc = "Enables /fix command to fix chunk data.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandFix = "ops";

    @Rule(
            name = "commandFreecam",
            desc = "Enables /freecam command to toggle your camera mode.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandFreecam = "true";

    @Rule(
            name = "commandGC",
            desc = "Enables /gc command to allow you to force gc with jvm.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandGC = "ops";

    @Rule(
            name = "commandHere",
            desc = "Enables /here command to allow you to share your location with other players.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandHere = "true";

    @Rule(
            name = "commandOperator",
            desc = "Enables /operator command to allow you to change the player's permission level.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandOperator = "ops";

    @Rule(
            name = "cameraModeDisableSpectatePlayers",
            desc = "Disables players in /c from spectating other players.",
            category = {TCTC, TCTC_FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
            name = "disableIllegalChatCharacterCheck",
            desc = "Allows you to use characters such as subsections in the game.",
            category = {TCTC, TCTC_CLIENT, TCTC_EXPERIMENTAL, TCTC_MISC}
    )
    public static boolean disableIllegalChatCharacterCheck = false;

    @Rule(
            name = "enderPlatform",
            desc = "Is obsidian platform generated in the end.",
            extra = {
                    "all - Generate ender platform when all entities are transferred to the_end dimension.",
                    "none - Ender platform will not be generated anyway.",
                    "player - Ender platform is generated only when the player entity teleports to the_end dimension.",
            },
            category = {TCTC, TCTC_FEATURE, TCTC_WORLD_GENERATE}
    )
    public static EnderPlatformOptions enderPlatform = EnderPlatformOptions.ALL;

    public enum EnderPlatformOptions {
        ALL,
        NONE,
        PLAYER
    }

    @Rule(
            name = "endGatewayChunkLoader",
            desc = "When the entity passes through the ender gateway, the target chunk will be loaded for 15 seconds like nether portal.",
            extra = {
                    "all - Give the target chunk a load ticket when all entities through the ender gateway.",
                    "except_player - Give the target chunk a load ticket when all entities except the player through the ender gateway.",
                    "item_only - Give the target chunk a load ticket when the player entities except the player through the ender gateway.",
                    "off - Vanilla action.",
            },
            category = {TCTC, TCTC_FEATURE}
    )
    public static EndGatewayChunkLoaderOptions endGatewayChunkLoader = EndGatewayChunkLoaderOptions.OFF;

    public enum EndGatewayChunkLoaderOptions {
        ALL,
        EXCEPT_PLAYER,
        ITEM_ONLY,
        OFF
    }

    @Rule(
            name = "fireworkSpeedupCoefficient",
            desc = "Controlling the acceleration coefficient of a firework rocket.",
            category = {TCTC, TCTC_CLIENT, TCTC_FEATURE},
            options = {"1.25", "1.5"},
            strict = false
    )
    public static double fireworkSpeedupCoefficient = 1.5D;

    @Rule(
            name = "freecamRestoreLocation",
            desc = "Return to the starting position when you have finished spectating.",
            category = {TCTC, TCTC_MISC}
    )
    public static boolean freecamRestoreLocation = true;

    @Rule(
            name = "hereGlowTime",
            desc = "Time highlighted when using here command shared coordinates.",
            category = {TCTC, TCTC_MISC},
            options = {"0", "15"},
            strict = false
    )
    public static int hereGlowTime = 15;

    @Rule(
            name = "illegalPistonActionFix",
            desc = "Fix headless pistons to destroy blocks.",
            category = {TCTC, TCTC_FEATURE}
    )
    public static boolean illegalPistonActionFix = false;

    @Rule(
            name = "playerStats",
            desc = "Control of player and bot statistics.",
            extra = {
                    "bot - Only bots are allowed to gain stats.",
                    "both - Both bots and players are allowed to gain stats.",
                    "none - No one allowed to gain stats.",
                    "player - Only players are allowed to gain stats."
            },
            category = {TCTC, TCTC_FEATURE}
    )
    public static PlayerStatsOptions playerStats = PlayerStatsOptions.BOTH;

    public enum PlayerStatsOptions {
        BOT,
        BOTH,
        NONE,
        PLAYER
    }

    @Rule(
            name = "shulkerBoxDupeFix",
            desc = "Taking items from a shulker box while destroying it will result in item duplication.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean shulkerBoxDupeFix = false;

    @Rule(
            name = "updateSuppressionCrashFix",
            desc = "Fix updates suppression causing server crashes.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;

    @Rule(
            name = "voidTradeFix",
            desc = "Once the villager entity is unloaded, the trading interface will be closed.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean voidTradeFix = false;

    @Rule(
            name = "voxelMapWorldName",
            desc = "Send VoxelMap world information packets to client.",
            category = {TCTC, TCTC_PROTOCOL},
            options = {"#none", "creative", "mirror", "survival"},
            strict = false
    )
    public static String voxelMapWorldName = "#none";

    @Rule(
            name = "wetSpongeAbsorbLava",
            desc = "Wet sponges will be able to absorb lava.",
            category = {TCTC, TCTC_EXPERIMENTAL, TCTC_FEATURE}
    )
    public static boolean wetSpongeAbsorbLava = false;

    @Rule(
            name = "wetSpongeAbsorbLavaLimit",
            desc = "Maximum offset limit for wet sponge.",
            category = {TCTC, TCTC_EXPERIMENTAL, TCTC_FEATURE}
    )
    public static int wetSpongeAbsorbLavaLimit = 32;

    @Rule(
            name = "wetSpongeAbsorbLavaRange",
            desc = "Maximum lava sucking for wet sponge.",
            category = {TCTC, TCTC_EXPERIMENTAL, TCTC_FEATURE}
    )
    public static int wetSpongeAbsorbLavaRange = 3;

    @Rule(
            name = "xaeroMapWorldName",
            desc = "Send XaeroMap world information packets to client.",
            category = {TCTC, TCTC_PROTOCOL},
            options = {"#none", "creative", "mirror", "survival"},
            strict = false
    )
    public static String xaeroMapWorldName = "#none";
}
