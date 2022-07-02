/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes.mixin.rule.enderPlatform;

//#if MC >= 11600
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
//#else
//$$ import net.minecraft.server.MinecraftServer;
//#endif
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 11600
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
//#endif
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CFSettings;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

//#if MC >= 11600
@Dependencies(and = @Dependency(value = "carpet-fixes", versionPredicate = ">=1.8.7"))
@Mixin(value = ServerPlayer.class, priority = 1001)
//#else
//$$ @Dependencies(not = @Dependency("minecraft"))
//$$ @Mixin(MinecraftServer.class)
//#endif
public abstract class MixinServerPlayer {
    //#if MC >= 11600
    @Shadow protected abstract void createEndPlatform(ServerLevel serverLevel, BlockPos blockPos);

    @Redirect(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerPlayer;createEndPlatform(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V",
                    ordinal = 0
            )
    )
    private void makeObsidianPlatform(ServerPlayer serverPlayer, ServerLevel serverLevel, BlockPos blockPos) {
        if (CarpetTCTCAdditionSettings.enderPlatform != CarpetTCTCAdditionSettings.EnderPlatformOptions.NONE) {
            if (CFSettings.isObsidianPlatformDestroysBlocksFixEnable()) {
                this.tctc$createEndSpawnObsidian(serverLevel, blockPos);
            } else {
                this.createEndPlatform(serverLevel, blockPos);
            }
        }
    }

    // Modified from Carpet-Fixes
    private void tctc$createEndSpawnObsidian(ServerLevel serverLevel, BlockPos blockPos) {
        BlockPos.MutableBlockPos mutableBlockPos = blockPos.mutable();

        for(int i = -2; i <= 2; ++i) {
            for(int j = -2; j <= 2; ++j) {
                for(int k = -1; k < 3; ++k) {
                    BlockState blockState = k == -1 ? Blocks.OBSIDIAN.defaultBlockState() : Blocks.AIR.defaultBlockState();
                    if (blockState == Blocks.AIR.defaultBlockState() && serverLevel.getBlockState(blockPos.offset(j, k, i)).is(Blocks.END_STONE)) {
                        continue;
                    }
                    serverLevel.setBlockAndUpdate(mutableBlockPos.set(blockPos).move(j, k, i), blockState);
                }
            }
        }
    }
    //#endif
}
