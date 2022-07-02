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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CFHelper;
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CFSettings;
import top.catowncraft.carpettctcaddition.helper.UpdateSuppressionException;
import top.catowncraft.carpettctcaddition.util.MessageUtil;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

import java.util.function.BooleanSupplier;

@Dependencies(and = @Dependency(value = "carpet-fixes", versionPredicate = ">=1.9.1"))
@Mixin(value = MinecraftServer.class, priority = 1001)
public class MixinMinecraftServer {
    @Redirect(
            method = "tickChildren",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;tick(Ljava/util/function/BooleanSupplier;)V",
                    ordinal = 0
            )
    )
    private void onTickingWorld(ServerLevel instance, BooleanSupplier booleanSupplier) {
        try {
            instance.tick(booleanSupplier);
        } catch (Throwable throwable) {
            if (!CarpetTCTCAdditionSettings.updateSuppressionCrashFix || !(throwable.getCause() instanceof UpdateSuppressionException)) {
                throw throwable;
            }

            if (!CFSettings.isUpdateSuppressionCrashFixEnable() || !(throwable.getCause().getClass() == CFHelper.UpdateSuppressionException())) {
                throw throwable;
            }

            MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(), ComponentCompatApi.literal(StringUtil.tr("message.server.updateSuppression.exception")).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        }
    }
}
