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
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
//#endif
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CarpetFixesSettings;
import top.catowncraft.carpettctcaddition.util.mixin.MixinType;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicAttack;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicInterruption;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

//#if MC >= 11600
@MagicInterruption(targets = "carpetfixes.mixins.playerFixes.ServerPlayerEntity_spawnPlatformMixin")
@Dependencies(and = @Dependency(value = "carpet-fixes", versionPredicate = ">=1.8.7"))
@Mixin(value = ServerPlayer.class, priority = 1100)
//#else
//$$ @Dependencies(not = @Dependency("minecraft"))
//$$ @Mixin(MinecraftServer.class)
//#endif
public abstract class MixinServerPlayer {
    //#if MC >= 11600
    @Shadow protected abstract void createEndPlatform(ServerLevel serverLevel, BlockPos blockPos);
    @SuppressWarnings("unused")
    @MagicAttack(
            type = MixinType.REDIRECT,
            name = "dontRecreateObsidianPlatform",
            owner = "class_1297",
            method = "method_5731",
            desc = "(Lnet/minecraft/class_3218;)Lnet/minecraft/class_1297;"
    )
    private void tctc$makeObsidianPlatform(ServerPlayer serverPlayer, ServerLevel serverLevel, BlockPos blockPos) {
        if (CarpetTCTCAdditionSettings.enderPlatform != CarpetTCTCAdditionSettings.EnderPlatformOptions.NONE) {
            if (CarpetFixesSettings.isObsidianPlatformDestroysBlocksFixEnable()) {
                // Modified from Carpet-Fixes
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
            } else {
                this.createEndPlatform(serverLevel, blockPos);
            }
        }
    }
    //#endif
}
