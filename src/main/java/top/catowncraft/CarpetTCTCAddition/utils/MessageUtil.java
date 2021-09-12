/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import net.minecraft.Util;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;

public class MessageUtil {
    public static void sendMessage(CommandSourceStack source, String message) {
        if (source != null)
            source.sendSuccess(new TextComponent(message), source.getServer() != null && source.getServer().getLevel(Level.OVERWORLD) != null);
    }

    public static void sendMessage(CommandSourceStack source, Component component) {
        if (source != null)
            source.sendSuccess(component, source.getServer() != null && source.getServer().getLevel(Level.OVERWORLD) != null);
    }

    public static void sendServerMessage(MinecraftServer server, String message) {
        if (server != null) {
            CarpetTCTCAddition.logger.info(message);
            for (Player player : server.getPlayerList().getPlayers()) {
                player.sendMessage(new TextComponent(message), Util.NIL_UUID);
            }
        } else {
            CarpetTCTCAddition.logger.error("Message not delivered: " + message);
        }
    }

    public static void sendServerMessage(MinecraftServer server, Component component) {
        if (server != null) {
            CarpetTCTCAddition.logger.info(component.getString());
            for (Player player : server.getPlayerList().getPlayers()) {
                player.sendMessage(component, Util.NIL_UUID);
            }
        } else {
            CarpetTCTCAddition.logger.error("Message not delivered: " + component.getString());
        }
    }
}
