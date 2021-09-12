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
    private static final String TCTC_COMMAND = "TCTC-COMMAND";
    private static final String TCTC_FEATURE = "TCTC-FEATURE";
    private static final String TCTC_MISC = "TCTC-MISC";
    private static final String TCTC_WORLD_GENERATE = "TCTC-WORLD_GENERATE";

    @Rule(
            name = "commandFix",
            desc = "Enables /fix command to fix chunk data.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandFix = "ops";

    @Rule(
            name = "commandHere",
            desc = "Enables /here command to allow you to share your location with other players.",
            category = {TCTC, TCTC_COMMAND},
            options = {"true", "false", "ops", "0", "1", "2", "3", "4"}
    )
    public static String commandHere = "true";

    @Rule(
            name = "cameraModeDisableSpectatePlayers",
            desc = "Disables players in /c from spectating other players.",
            category = {TCTC, TCTC_FEATURE}
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

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
            name = "hereGlowTime",
            desc = "Time highlighted when using here command shared coordinates.",
            category = {TCTC, TCTC_MISC}
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
            name = "updateSuppressionCrashFix",
            desc = "Fix updates suppression causing server crashes.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;
}
