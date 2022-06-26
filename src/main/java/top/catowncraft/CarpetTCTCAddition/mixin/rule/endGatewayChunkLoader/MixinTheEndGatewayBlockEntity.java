/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.endGatewayChunkLoader;

import net.minecraft.core.BlockPos;
//#if MC < 11500
//$$ import net.minecraft.server.level.ColumnPos;
//#endif
import net.minecraft.server.level.TicketType;
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
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

import java.util.Objects;

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
            BlockPos blockPos1 = new BlockPos(entity.position());
            //#if MC >= 11600
            Objects.requireNonNull(CarpetTCTCAddition.getServer().getLevel(entity.level.dimension())).getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockPos1), 3, blockPos1);
            //#elseif MC >= 11500
            //$$ Objects.requireNonNull(CarpetTCTCAddition.getServer().getLevel(entity.dimension)).getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockPos1), 3, blockPos1);
            //#else
            //$$ Objects.requireNonNull(CarpetTCTCAddition.getServer().getLevel(entity.dimension)).getChunkSource().addRegionTicket(TicketType.PORTAL, new ChunkPos(blockPos1), 3, new ColumnPos(blockPos1));
            //#endif
        }
    }
}
