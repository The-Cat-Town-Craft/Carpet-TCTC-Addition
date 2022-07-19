/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes;

import java.lang.reflect.Field;

public class CarpetFixesSettings {
    private static boolean isPresent = false;
    private static Class<?> CFSettings;

    private static Field obsidianPlatformDestroysBlocksFix;
    private static Field updateSuppressionCrashFix;

    static {
        try {
            CFSettings = Class.forName("carpetfixes.CFSettings");
            isPresent = true;
        } catch (ClassNotFoundException ignore) {
        }

        if (isPresent) {
            try {
                updateSuppressionCrashFix = CFSettings.getDeclaredField("updateSuppressionCrashFix");
                updateSuppressionCrashFix.setAccessible(true);
            } catch (NoSuchFieldException ignore) {
            }

            try {
                obsidianPlatformDestroysBlocksFix = CFSettings.getDeclaredField("obsidianPlatformDestroysBlocksFix");
                obsidianPlatformDestroysBlocksFix.setAccessible(true);
            } catch (NoSuchFieldException ignore) {
            }
        }
    }

    public static boolean isObsidianPlatformDestroysBlocksFixEnable() {
        try {
            return obsidianPlatformDestroysBlocksFix.getBoolean(null);
        } catch (IllegalAccessException | NullPointerException e) {
            return false;
        }
    }

    public static boolean isUpdateSuppressionCrashFixEnable() {
        try {
            return updateSuppressionCrashFix.getBoolean(null);
        } catch (IllegalAccessException | NullPointerException e) {
            return false;
        }
    }
}
