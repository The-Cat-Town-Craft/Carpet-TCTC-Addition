/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.enderPlatform;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    @Shadow
    protected abstract void createEndPlatform(ServerLevel serverLevel, BlockPos blockPos);

    @Redirect(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;createEndPlatform(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V"
            )
    )
    private void makeObsidianPlatform(ServerPlayer serverPlayer, ServerLevel serverLevel, BlockPos blockPos) {
        if (CarpetTCTCAdditionSettings.enderPlatform != CarpetTCTCAdditionSettings.EnderPlatformOptions.NONE) {
            createEndPlatform(serverLevel, blockPos);
        }
    }
}
