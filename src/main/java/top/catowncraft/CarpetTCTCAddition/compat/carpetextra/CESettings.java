/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetextra;

import java.lang.reflect.Field;

public class CESettings {
    private static boolean isPresent = false;
    private static Class<?> CESettings;
    private static Field updateSuppressionCrashFix;

    static {
        try {
            CESettings = Class.forName("carpetextra.CarpetExtraSettings");
            isPresent = true;
        } catch (ClassNotFoundException ignore) {
        }

        if (isPresent) {
            try {
                updateSuppressionCrashFix = CESettings.getDeclaredField("updateSuppressionCrashFix");
                updateSuppressionCrashFix.setAccessible(true);
            } catch (NoSuchFieldException ignore) {
            }
        }
    }

    public static boolean isUpdateSuppressionCrashFixEnable() {
        try {
            return updateSuppressionCrashFix.getBoolean(null);
        } catch (IllegalAccessException e) {
            return false;
        }
    }
}
