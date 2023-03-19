/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes.mixin.rule.enderPlatform;

import org.spongepowered.asm.mixin.Mixin;
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CarpetFixesPredicate;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicInterruption;

import top.hendrixshen.magiclib.dependency.api.annotation.Dependencies;

//#if MC > 11502
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Shadow;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.compat.carpetfixes.CarpetFixesSettings;
import top.catowncraft.carpettctcaddition.util.mixin.MixinType;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicAttack;
//#else
//$$ import top.hendrixshen.magiclib.compat.preprocess.api.DummyClass;
//#endif

@MagicInterruption(targets = "carpetfixes.mixins.playerFixes.ServerPlayerEntity_spawnPlatformMixin")
@Dependencies(predicate = CarpetFixesPredicate.shouldApplyCompatForEnderPlatform.class)
//#if MC > 11502
@Mixin(value = ServerPlayer.class, priority = 1100)
//#else
//$$ @Mixin(DummyClass.class)
//#endif
public abstract class MixinServerPlayer {
    //#if MC > 11502
    @Shadow
    protected abstract void createEndPlatform(ServerLevel serverLevel, BlockPos blockPos);

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
