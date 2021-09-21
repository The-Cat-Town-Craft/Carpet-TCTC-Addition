/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.commands;

import carpet.settings.SettingsManager;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.GameProfileArgument;
import net.minecraft.network.chat.TextComponent;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.fakes.PlayerListInterface;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;

import java.util.Collection;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class OperatorCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> operator = literal("operator")
                .requires(commandSourceStack -> SettingsManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandOperator))
                .then(argument("targets", GameProfileArgument.gameProfile())
                        .then(literal("set")
                                .then(literal("permissionLevel")
                                        .then(argument("permissionLevel", IntegerArgumentType.integer(0, 4))
                                                .executes(context -> setPermissionLevel(context.getSource(), GameProfileArgument.getGameProfiles(context, "targets"), IntegerArgumentType.getInteger(context, "permissionLevel")))))
                                .then(literal("canBypassPlayerLimit")
                                        .then(argument("canBypassPlayerLimit", BoolArgumentType.bool())
                                                .executes(context -> setBypassPlayerLimit(context.getSource(), GameProfileArgument.getGameProfiles(context, "targets"), BoolArgumentType.getBool(context, "canBypassPlayerLimit")))))));
        dispatcher.register(operator);
    }

    private static int setPermissionLevel(CommandSourceStack commandSourceStack, Collection<GameProfile> gameProfileCollection, int level) {
        if (!commandSourceStack.hasPermission(level)) {
            commandSourceStack.sendFailure(new TextComponent(String.format("You cannot assign a higher level of permission to a target than you have(level: %d).", level)));
            return 1;
        }
        for (GameProfile gameProfile : gameProfileCollection) {
            ((PlayerListInterface) CarpetTCTCAddition.getServer().getPlayerList()).setPermissionLevel(gameProfile, level);
            MessageUtil.sendMessage(commandSourceStack, String.format("Player %s's permissions have been updated to level %d.", gameProfile.getName(), level));
        }
        return 1;
    }

    private static int setBypassPlayerLimit(CommandSourceStack commandSourceStack, Collection<GameProfile> gameProfileCollection, boolean bypass) {
        for (GameProfile gameProfile : gameProfileCollection) {
            ((PlayerListInterface) CarpetTCTCAddition.getServer().getPlayerList()).setBypassPlayerLimit(gameProfile, bypass);
            MessageUtil.sendMessage(commandSourceStack, String.format("Player %s bypassed the player count limit has been updated to %s.", gameProfile.getName(), bypass));
        }
        return 1;
    }
}
