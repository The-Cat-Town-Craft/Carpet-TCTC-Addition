/*
 * Copyright (c) 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition;

import carpet.settings.Rule;

public class CarpetTCTCAdditionSettings {
    private static final String TCTC = "TCTC";
    private static final String TCTC_BUGFIX = "TCTC-BUGFIX";

    @Rule(
            name = "updateSuppressionCrashFix",
            desc = "Fixes updates suppression causing server crashes.",
            category = {TCTC, TCTC_BUGFIX}
    )
    public static boolean updateSuppressionCrashFix = false;
}
