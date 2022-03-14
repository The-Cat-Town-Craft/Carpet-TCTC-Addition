/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.illegalPistonActionFix;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonBaseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(PistonBaseBlock.class)
public class MixinPistonBaseBlock {
    @Inject(
            method = "triggerEvent",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/Level;removeBlock(Lnet/minecraft/core/BlockPos;Z)Z"
            ),
            cancellable = true
    )
    private void onRemoveBlock(BlockState blockState, Level level, BlockPos blockPos, int i, int j, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetTCTCAdditionSettings.illegalPistonActionFix && level.getBlockState(blockPos.relative(blockState.getValue(PistonBaseBlock.FACING))).getBlock() != Blocks.PISTON_HEAD) {
            cir.cancel();
        }
    }
}
