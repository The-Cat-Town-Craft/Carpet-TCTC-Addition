/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.TicketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(TheEndGatewayBlockEntity.class)
public class MixinTheEndGatewayBlockEntity {
    @Inject(
            method = "teleportEntity",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/Entity;teleportToWithTicket(DDD)V",
                    shift = At.Shift.AFTER
            )
    )
    private static void addRegionTicket(Level level, BlockPos blockPos, BlockState blockState, Entity entity, TheEndGatewayBlockEntity theEndGatewayBlockEntity, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.endGatewayChunkLoader) {
            BlockPos blockPos1 = new BlockPos(entity.position());
            CarpetTCTCAddition.getServer().getLevel(entity.level.dimension()).getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockPos1), 3, blockPos1);
        }
    }
}
