/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.utils.FreeCameraUtil;

import java.util.Collection;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.EntityArgument.getPlayers;
import static net.minecraft.commands.arguments.EntityArgument.players;

public class FreecamCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> camera = literal("freecam")
                .requires(commandSourceStack -> SettingsManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandFreecam))
                .executes(context -> executeFreeCamera(context.getSource(), context.getSource().getPlayerOrException()))
                .then(argument("target", players())
                        .requires(commandContext -> commandContext.hasPermission(2))
                        .executes(commandContext -> executeFreeCamera(commandContext.getSource(), getPlayers(commandContext, "target"))));
        dispatcher.register(camera);
    }

    public static int executeFreeCamera(CommandSourceStack source, Collection<ServerPlayer> serverPlayerCollection) {
        for (ServerPlayer serverPlayer : serverPlayerCollection) {
            executeFreeCamera(source, serverPlayer);
        }
        return 1;
    }

    public static int executeFreeCamera(CommandSourceStack source, ServerPlayer serverPlayer) {
        FreeCameraUtil.CameraData cameraData = FreeCameraUtil.freeCameraData == null ? null : FreeCameraUtil.freeCameraData.get(serverPlayer.getUUID());
        boolean isCameraMode;
        FreeCameraUtil.CameraData cameraDataNew = new FreeCameraUtil.CameraData(serverPlayer.gameMode.getGameModeForPlayer(), serverPlayer.dimension, serverPlayer.getX(), serverPlayer.getY(), serverPlayer.getZ(), serverPlayer.yRot, serverPlayer.xRot, true);
        if (cameraData == null || !cameraData.isFreeCamera()) {
            isCameraMode = true;
            serverPlayer.setGameMode(GameType.SPECTATOR);
        } else {
            if (CarpetTCTCAdditionSettings.freecamRestoreLocation && cameraData.isFreeCamera() && serverPlayer.isAlive()) {
                serverPlayer.teleportTo(serverPlayer.server.getLevel(cameraData.getDimensionType()), cameraData.getX(), cameraData.getY(), cameraData.getZ(), cameraData.getYRot(), cameraData.getXRot());
            }
            isCameraMode = false;
            serverPlayer.setGameMode(cameraData.getGameType());
        }
        cameraDataNew.setFreeCamera(isCameraMode);
        FreeCameraUtil.freeCameraData.put(serverPlayer.getUUID(), cameraDataNew);
        FreeCameraUtil.saveFreeCameraData();
        return 1;
    }
}
