/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

import java.nio.charset.StandardCharsets;

public class WorldMapUtil {
    private static final ResourceLocation VOXEL_MAP_CHANNEL = new ResourceLocation("worldinfo:world_id");

    public static void VoxelMapPacketHandler(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, FriendlyByteBuf buf, PacketSender responseSender) {
        if (!CarpetTCTCAdditionSettings.voxelMapWorldName.equals("#none")) {
            byte[] bytes = CarpetTCTCAdditionSettings.voxelMapWorldName.getBytes(StandardCharsets.UTF_8);
            ByteBuf byteBuf = Unpooled.buffer();
            FriendlyByteBuf friendlyByteBuf = new FriendlyByteBuf(byteBuf);
            friendlyByteBuf.writeByte(0);
            friendlyByteBuf.writeByte(bytes.length);
            friendlyByteBuf.writeBytes(bytes);
            player.connection.send(new ClientboundCustomPayloadPacket(VOXEL_MAP_CHANNEL, friendlyByteBuf), future -> {
                byteBuf.release();
            });
        }
    }
}
