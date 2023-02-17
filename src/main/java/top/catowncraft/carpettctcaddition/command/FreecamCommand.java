/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
//#if MC <= 11502
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.FreeCameraData;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;

import java.util.Collection;
import java.util.Optional;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.EntityArgument.getPlayers;
import static net.minecraft.commands.arguments.EntityArgument.players;

public class FreecamCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> camera = literal("freecam")
                .requires(commandSourceStack -> CarpetTCTCAdditionSettingManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandFreecam))
                .executes(context -> executeFreeCamera(context.getSource().getPlayerOrException()))
                .then(argument("target", players())
                        .requires(commandContext -> commandContext.hasPermission(2))
                        .executes(commandContext -> executeFreeCamera(getPlayers(commandContext, "target"))));
        dispatcher.register(camera);
    }

    public static int executeFreeCamera(@NotNull Collection<ServerPlayer> serverPlayerCollection) {
        for (ServerPlayer serverPlayer : serverPlayerCollection) {
            executeFreeCamera(serverPlayer);
        }
        return 1;
    }

    public static int executeFreeCamera(@NotNull ServerPlayer serverPlayer) {
        FreeCameraData data = FreeCameraUtil.get(serverPlayer.getUUID());
        if (data == null || !data.isFreecam) {
            FreecamCommand.enterFreecam(serverPlayer);
        } else {
            FreecamCommand.exitFreecam(serverPlayer, data);
        }
        return 1;
    }

    public static void enterFreecam(@NotNull ServerPlayer player) {
        FreeCameraData cameraDataNew = new FreeCameraData(player, true);
        player.setGameMode(GameType.SPECTATOR);
        FreeCameraUtil.put(player.getUUID(), cameraDataNew);
    }

    public static void exitFreecam(@NotNull ServerPlayer player, @NotNull FreeCameraData data) {
        FreecamCommand.exitFreecam(player, data, false);
    }

    public static void exitFreecam(@NotNull ServerPlayer player, @NotNull FreeCameraData data, boolean ignoreGameType) {
        if (CarpetTCTCAdditionSettings.freecamRestoreLocation && player.isAlive()) {
            ServerLevel serverLevel = Optional.ofNullable(player.server.getLevel(data.dimension))
                    //#if MC > 11502
                    .orElse(player.server.overworld());
                    //#else
                    //$$ .orElse(player.server.getLevel(DimensionType.OVERWORLD));
                    //#endif
            player.teleportTo(serverLevel, data.vec3.x, data.vec3.y, data.vec3.z, data.yRot, data.xRot);
        }
        if (!ignoreGameType) {
            player.setGameMode(data.gameType);
        }
        data.isFreecam = false;
    }
}
