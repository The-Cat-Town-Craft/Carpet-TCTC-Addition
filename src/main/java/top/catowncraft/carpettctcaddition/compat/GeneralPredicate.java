/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat;

import org.objectweb.asm.tree.ClassNode;
import top.catowncraft.carpettctcaddition.util.FabricUtil;
import top.hendrixshen.magiclib.dependency.annotation.MixinDependencyPredicate;

import java.util.regex.Pattern;

public class GeneralPredicate {
    private static final Pattern CARPET_FIXES_VER_PATTERN = Pattern.compile("((?<=(-))[\\d.]+)");

    public static class shouldUseCompatForUpdateSuppressionCrashFix implements MixinDependencyPredicate {
        @Override
        public boolean test(ClassNode classNode) {
            return !GeneralPredicate.shouldCompatForUpdateSuppressionCrashFix();
        }
    }

    private static boolean shouldCompatForUpdateSuppressionCrashFix() {
        return (FabricUtil.isModLoaded("minecraft", ">=1.19") &&
                FabricUtil.isVersionSatisfied("carpet-fixes", CARPET_FIXES_VER_PATTERN, ">=1.9.1")) ||
                FabricUtil.isModLoaded("carpet", ">=1.4.49 <1.4.77") ||
                FabricUtil.isModLoaded("carpet-extra", ">=1.4.14 <=1.4.43");

    }
}
