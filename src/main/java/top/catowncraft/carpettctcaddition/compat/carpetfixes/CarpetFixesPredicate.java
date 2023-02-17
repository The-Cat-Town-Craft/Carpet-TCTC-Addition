/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes;

import org.objectweb.asm.tree.ClassNode;
import top.catowncraft.carpettctcaddition.util.FabricUtil;
import top.hendrixshen.magiclib.dependency.annotation.MixinDependencyPredicate;

import java.util.regex.Pattern;

public class CarpetFixesPredicate {
    private static final Pattern pattern = Pattern.compile("((?<=(-))[\\d.]+)");

    public static class shouldApplyCompatForEnderPlatform implements MixinDependencyPredicate {
        @Override
        public boolean test(ClassNode classNode) {
            return CarpetFixesPredicate.shouldCompatForEnderPlatform();
        }
    }

    public static class shouldUseCompatForEnderPlatform implements MixinDependencyPredicate {
        @Override
        public boolean test(ClassNode classNode) {
            return !CarpetFixesPredicate.shouldCompatForEnderPlatform();
        }
    }

    public static class shouldApplyCompatForUpdateSuppressionCrashFix implements MixinDependencyPredicate {
        @Override
        public boolean test(ClassNode classNode) {
            return CarpetFixesPredicate.shouldCompatForUpdateSuppressionCrashFix();
        }
    }

    private static boolean shouldCompatForEnderPlatform() {
        return (FabricUtil.isModLoaded("minecraft", "1.18.x") &&
                FabricUtil.isVersionSatisfied("carpet-fixes", pattern, ">=1.7.6") ||
                (FabricUtil.isModLoaded("minecraft", ">=1.19") &&
                        FabricUtil.isVersionSatisfied("carpet-fixes", pattern, ">=1.8.7")));
    }

    private static boolean shouldCompatForUpdateSuppressionCrashFix() {
        return (FabricUtil.isModLoaded("minecraft", ">=1.19") &&
                        FabricUtil.isVersionSatisfied("carpet-fixes", pattern, ">=1.9.1"));
    }
}
