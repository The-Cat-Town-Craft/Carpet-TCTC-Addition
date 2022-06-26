/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
//#if MC >= 11600
import net.minecraft.world.level.Level;
//#else
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;

public class MessageUtil {
    public static void sendMessage(CommandSourceStack source, String message) {
        MessageUtil.sendMessage(source, ComponentCompatApi.literal(message));
    }

    public static void sendMessage(CommandSourceStack source, Component component) {
        if (source != null) {
            //#if MC >= 11600
            source.sendSuccess(component, source.getServer() != null && source.getServer().getLevel(Level.OVERWORLD) != null);
            //#else
            //$$ source.sendSuccess(component, source.getServer() != null && source.getServer().getLevel(DimensionType.OVERWORLD) != null);
            //#endif
        }
    }

    public static void sendServerMessage(MinecraftServer server, String message) {
        MessageUtil.sendServerMessage(server, ComponentCompatApi.literal(message));
    }

    public static void sendServerMessage(MinecraftServer server, Component component) {
        if (server != null) {
            CarpetTCTCAddition.getLogger().info(component.getString());
            for (Player player : server.getPlayerList().getPlayers()) {
                player.sendSystemMessageCompat(component);
            }
        } else {
            CarpetTCTCAddition.getLogger().error("Message not delivered: " + component.getString());
        }
    }
}
