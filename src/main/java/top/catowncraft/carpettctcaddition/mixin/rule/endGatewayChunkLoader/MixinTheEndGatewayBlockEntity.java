/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.endGatewayChunkLoader;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ChunkPos;
//#if MC >= 11600
import net.minecraft.world.level.Level;
//#endif
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
//#if MC >= 11600
import net.minecraft.world.level.block.state.BlockState;
//#endif
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.TicketTypeExtra;

import java.util.Optional;

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
    //#if MC >= 11700
    private static void addRegionTicket(Level level, BlockPos blockPos, BlockState blockState, Entity entity, TheEndGatewayBlockEntity theEndGatewayBlockEntity, CallbackInfo ci) {
    //#else
    //$$ private void addRegionTicket(Entity entity, CallbackInfo ci) {
    //#endif
        if ((CarpetTCTCAdditionSettings.endGatewayChunkLoader == CarpetTCTCAdditionSettings.EndGatewayChunkLoaderOptions.OFF)) {
            return;
        }

        if ((CarpetTCTCAdditionSettings.endGatewayChunkLoader == CarpetTCTCAdditionSettings.EndGatewayChunkLoaderOptions.ALL) ||
                (CarpetTCTCAdditionSettings.endGatewayChunkLoader == CarpetTCTCAdditionSettings.EndGatewayChunkLoaderOptions.ITEM_ONLY && entity instanceof ItemEntity) ||
                (CarpetTCTCAdditionSettings.endGatewayChunkLoader == CarpetTCTCAdditionSettings.EndGatewayChunkLoaderOptions.EXCEPT_PLAYER && !(entity instanceof Player))) {
            BlockPos targetBlockPos = new BlockPos(entity.position());
            Optional.ofNullable(CarpetTCTCAdditionExtension.getServer())
                    //#if MC > 11502
                    .flatMap(server -> Optional.ofNullable(server.getLevel(entity.level.dimension())))
                    //#else
                    //$$ .flatMap(server -> Optional.ofNullable(server.getLevel(entity.dimension)))
                    //#endif
                    .ifPresent(targetLevel -> targetLevel.getChunkSource().addRegionTicket(TicketTypeExtra.THE_END_GATEWAY_BLOCK_ENTITY, new ChunkPos(targetBlockPos), 3, targetBlockPos));
        }
    }
}
