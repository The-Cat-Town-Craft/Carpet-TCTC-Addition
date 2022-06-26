/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.FreeCameraData;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
        Map<UUID, FreeCameraData> freeCameraUtilMap = FreeCameraUtil.getCameraData();
        FreeCameraData cameraData = freeCameraUtilMap.get(serverPlayer.getUUID());
        boolean isCameraMode;
        FreeCameraData cameraDataNew = new FreeCameraData(serverPlayer, true);
        if (cameraData == null || !cameraData.isFreecam) {
            isCameraMode = true;
            serverPlayer.setGameMode(GameType.SPECTATOR);
        } else {
            if (CarpetTCTCAdditionSettings.freecamRestoreLocation && serverPlayer.isAlive()) {
                serverPlayer.teleportTo(Objects.requireNonNull(serverPlayer.server.getLevel(cameraData.dimension)), cameraData.vec3.x, cameraData.vec3.y, cameraData.vec3.z, cameraData.yRot, cameraData.xRot);
            }
            isCameraMode = false;
            serverPlayer.setGameMode(cameraData.gameType);
        }
        cameraDataNew.isFreecam = isCameraMode;
        freeCameraUtilMap.put(serverPlayer.getUUID(), cameraDataNew);
        return 1;
    }
}
