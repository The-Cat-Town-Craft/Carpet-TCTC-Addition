/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes.mixin.rule.updateSuppressionCrashFix;

import net.minecraft.ChatFormatting;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.compat.carpetextra.CarpetExtraSettings;
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CarpetFixesHelper;
import top.catowncraft.carpettctcaddition.helper.UpdateSuppressionException;
import top.catowncraft.carpettctcaddition.util.MessageUtil;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.catowncraft.carpettctcaddition.util.mixin.MixinType;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicAttack;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicInterruption;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

import java.util.function.BooleanSupplier;

@MagicInterruption(targets = "carpetfixes.mixins.coreSystemFixes.updateSuppression.MinecraftServer_crashFixMixin")
@Dependencies(and = @Dependency(value = "carpet-fixes", versionPredicate = ">=1.9.1"))
@Mixin(value = MinecraftServer.class, priority = 1100)
public class MixinMinecraftServer {
    @SuppressWarnings("unused")
    @MagicAttack(type = MixinType.REDIRECT, name = "catchUpdateSuppression")
    private void onTickingWorld(ServerLevel instance, BooleanSupplier booleanSupplier) {
        try {
            instance.tick(booleanSupplier);
        } catch (Throwable throwable) {
            if ((CarpetTCTCAdditionSettings.updateSuppressionCrashFix && throwable.getCause() instanceof UpdateSuppressionException)
                    || (CarpetExtraSettings.isUpdateSuppressionCrashFixEnable() && throwable.getCause().getClass() == CarpetFixesHelper.UpdateSuppressionException())) {
                MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(),
                        ComponentCompatApi.literal(StringUtil.tr("message.server.updateSuppression.exception")).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
            } else {
                throw throwable;
            }
        }
    }
}
