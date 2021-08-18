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
    private static final String TCTC_FEATURE = "TCTC-FEATURE";

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
        PLAYER;
    }

    @Rule(
            name = "updateSuppressionCrashFix",
            desc = "Fix updates suppression causing server crashes.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;
}
