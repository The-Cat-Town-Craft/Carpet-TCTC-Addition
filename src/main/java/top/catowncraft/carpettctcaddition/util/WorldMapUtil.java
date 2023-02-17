/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

import java.nio.charset.StandardCharsets;
import java.util.zip.CRC32;

public class WorldMapUtil {
    private static final ResourceLocation VOXEL_MAP_CHANNEL = new ResourceLocation("worldinfo", "world_id");
    private static final ResourceLocation XAERO_WORLD_MAP_CHANNEL = new ResourceLocation("xaeroworldmap", "main");
    private static final ResourceLocation XAERO_MINI_MAP_CHANNEL = new ResourceLocation("xaeroworldmap", "main");

    public static void voxelMapPacketHandler(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl listener, FriendlyByteBuf buf, PacketSender responseSender) {
        if (!CarpetTCTCAdditionSettings.voxelMapWorldName.equals("#none")) {
            byte[] bytes = CarpetTCTCAdditionSettings.voxelMapWorldName.getBytes(StandardCharsets.UTF_8);
            ByteBuf byteBuf = Unpooled.buffer();
            FriendlyByteBuf friendlyByteBuf = new FriendlyByteBuf(byteBuf);
            friendlyByteBuf.writeByte(0);
            friendlyByteBuf.writeByte(bytes.length);
            friendlyByteBuf.writeBytes(bytes);
            //#if MC > 11802
            player.connection.send(new ClientboundCustomPayloadPacket(VOXEL_MAP_CHANNEL, friendlyByteBuf));
            byteBuf.release();
            //#else
            //$$ player.connection.send(new ClientboundCustomPayloadPacket(VOXEL_MAP_CHANNEL, friendlyByteBuf), future -> byteBuf.release());
            //#endif
        }
    }

    public static void xaeroMapPacketHandler(ServerPlayer player) {
        if (!CarpetTCTCAdditionSettings.xaeroMapWorldName.equals("#none")) {
            byte[] bytes = CarpetTCTCAdditionSettings.xaeroMapWorldName.getBytes(StandardCharsets.UTF_8);
            CRC32 crc32 = new CRC32();
            crc32.update(bytes, 0, bytes.length);
            ByteBuf byteBuf = Unpooled.buffer();
            byteBuf.writeByte(0);
            byteBuf.writeInt((int) crc32.getValue());
            player.connection.send(new ClientboundCustomPayloadPacket(XAERO_MINI_MAP_CHANNEL, new FriendlyByteBuf(byteBuf.duplicate())));
            player.connection.send(new ClientboundCustomPayloadPacket(XAERO_WORLD_MAP_CHANNEL, new FriendlyByteBuf(byteBuf.duplicate())));
        }
    }
}
