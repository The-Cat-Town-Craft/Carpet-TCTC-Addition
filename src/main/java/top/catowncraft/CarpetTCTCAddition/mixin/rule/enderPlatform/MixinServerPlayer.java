/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.enderPlatform;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
//#if MC < 11600
//$$ import net.minecraft.world.level.block.state.BlockState;
//#endif
import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 11600
import org.spongepowered.asm.mixin.Shadow;
//#endif
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer {
    //#if MC >= 11600
    @Shadow protected abstract void createEndPlatform(ServerLevel serverLevel, BlockPos blockPos);
    //#endif

    @Redirect(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    //#if MC >= 11600
                    target = "Lnet/minecraft/server/level/ServerPlayer;createEndPlatform(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V"
                    //#else
                    //$$ target = "Lnet/minecraft/server/level/ServerLevel;setBlockAndUpdate(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z"
                    //#endif
            )
    )
    //#if MC >= 11600
    private void makeObsidianPlatform(ServerPlayer serverPlayer, ServerLevel serverLevel, BlockPos blockPos) {
    //#else
    //$$ private boolean makeObsidianPlatform(ServerLevel serverLevel, BlockPos blockPos, BlockState blockState) {
    //#endif
        if (CarpetTCTCAdditionSettings.enderPlatform != CarpetTCTCAdditionSettings.EnderPlatformOptions.NONE) {
            //#if MC >= 11600
            this.createEndPlatform(serverLevel, blockPos);
            //#else
            //$$ return serverLevel.setBlockAndUpdate(blockPos, blockState);
            //#endif
        }
        //#if MC < 11600
        //$$ return false;
        //#endif
    }
}
