/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
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
            FriendlyByteBuf bufs = PacketByteBufs.create();
            bufs.writeByte(0);
            bufs.writeByte(bytes.length);
            bufs.writeBytes(bytes);
            responseSender.sendPacket(VOXEL_MAP_CHANNEL, bufs);
            bufs.release();
        }
    }

    public static void xaeroMapPacketHandler(ServerPlayer player) {
        if (!CarpetTCTCAdditionSettings.xaeroMapWorldName.equals("#none")) {
            byte[] bytes = CarpetTCTCAdditionSettings.xaeroMapWorldName.getBytes(StandardCharsets.UTF_8);
            CRC32 crc32 = new CRC32();
            crc32.update(bytes, 0, bytes.length);
            FriendlyByteBuf buf = PacketByteBufs.create();
            buf.writeByte(0);
            buf.writeInt((int) crc32.getValue());
            ServerPlayNetworking.send(player, XAERO_MINI_MAP_CHANNEL, buf);
            ServerPlayNetworking.send(player, XAERO_WORLD_MAP_CHANNEL, buf);
            buf.release();
        }
    }
}
