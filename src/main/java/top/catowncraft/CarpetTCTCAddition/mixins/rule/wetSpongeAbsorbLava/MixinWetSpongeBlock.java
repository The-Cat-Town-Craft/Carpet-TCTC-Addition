/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.wetSpongeAbsorbLava;

import com.google.common.collect.Lists;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Tuple;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

import java.util.Queue;

@Mixin(WetSpongeBlock.class)
public class MixinWetSpongeBlock extends Block {
    public MixinWetSpongeBlock(Properties properties) {
        super(properties);
    }

    @Inject(
            method = "onPlace",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState2, boolean bl, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.wetSpongeAbsorbLava) {
            if (blockState2.getBlock() != blockState.getBlock()) {
                this.tryAbsorbLava(level, blockPos);
            }
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void neighborChanged(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl) {
        if (CarpetTCTCAdditionSettings.wetSpongeAbsorbLava) {
            this.tryAbsorbLava(level, blockPos);
        }
        super.neighborChanged(blockState, level, blockPos, block, blockPos2, bl);
    }

    protected void tryAbsorbLava(Level level, BlockPos blockPos) {
        if (this.removeLavaBreadthFirstSearch(level, blockPos)) {
            level.setBlock(blockPos, Blocks.SPONGE.defaultBlockState(), 2);
            level.levelEvent(2009, blockPos, Block.getId(Blocks.WATER.defaultBlockState()));
            level.playSound(null, blockPos, SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 1.0F, (1.0F + level.getRandom().nextFloat() * 0.2F) * 0.7F);
        }
    }


    private boolean removeLavaBreadthFirstSearch(Level level, BlockPos blockPos) {
        Queue<Tuple<BlockPos, Integer>> queue = Lists.newLinkedList();
        queue.add(new Tuple<>(blockPos, 0));
        int i = 0;

        while (!queue.isEmpty()) {
            Tuple<BlockPos, Integer> tuple = queue.poll();
            BlockPos tupleA = tuple.getA();
            int tupleB = tuple.getB();
            Direction[] directions = Direction.values();

            for (Direction direction : directions) {
                BlockPos relative = tupleA.relative(direction);
                BlockState blockState = level.getBlockState(relative);
                FluidState fluidState = level.getFluidState(relative);
                if (fluidState.is(FluidTags.LAVA)) {
                    if (blockState.getBlock() instanceof BucketPickup && !((BucketPickup) blockState.getBlock()).pickupBlock(level, relative, blockState).isEmpty()) {
                        i++;
                        if (tupleB < CarpetTCTCAdditionSettings.wetSpongeAbsorbLavaRange) {
                            queue.add(new Tuple<>(relative, tupleB + 1));
                        }
                    } else if (blockState.getBlock() instanceof LiquidBlock) {
                        level.setBlock(relative, Blocks.AIR.defaultBlockState(), 3);
                        i++;
                        if (tupleB < CarpetTCTCAdditionSettings.wetSpongeAbsorbLavaRange) {
                            queue.add(new Tuple<>(relative, tupleB + 1));
                        }
                    }
                }
            }

            if (CarpetTCTCAdditionSettings.wetSpongeAbsorbLavaLimit > 64) {
                break;
            }
        }

        return i > 0;
    }
}
