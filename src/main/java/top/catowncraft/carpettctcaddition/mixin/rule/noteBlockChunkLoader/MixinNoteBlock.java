/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.noteBlockChunkLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.TicketTypeExtra;

@Mixin(NoteBlock.class)
public class MixinNoteBlock {
    @Inject(
            method = "neighborChanged",
            at = @At(
                    value = "INVOKE",
                    //#if MC > 11802
                    target = "Lnet/minecraft/world/level/block/NoteBlock;playNote(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
                    //#else
                    //$$ target = "Lnet/minecraft/world/level/block/NoteBlock;playNote(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)V"
                    //#endif
            )
    )
    private void onActive(BlockState blockState, Level level, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.noteBlockChunkLoader && level instanceof ServerLevel) {
            ServerLevel serverLevel = (ServerLevel) level;
            if (serverLevel.getBlockState(blockPos.below()).getBlock() == Blocks.EMERALD_ORE &&
                    serverLevel.getBlockState(blockPos.above()).getBlock() == Blocks.OBSIDIAN) {
                serverLevel.getChunkSource().addRegionTicket(TicketTypeExtra.NOTE_BLOCK, new ChunkPos(blockPos), 3, blockPos);
            }
        }
    }
}
