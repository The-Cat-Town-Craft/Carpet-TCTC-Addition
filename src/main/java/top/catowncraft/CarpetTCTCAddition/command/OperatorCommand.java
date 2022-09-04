/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.GameProfileArgument;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.util.MessageUtil;

import java.util.Collection;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class OperatorCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> operator = literal("operator")
                .requires(commandSourceStack -> CarpetTCTCAdditionSettingManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandOperator))
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

    private static int setPermissionLevel(@NotNull CommandSourceStack commandSourceStack, Collection<GameProfile> gameProfileCollection, int level) {
        if (!commandSourceStack.hasPermission(level) && CarpetTCTCAdditionSettings.opLevelBelowSelf) {
            commandSourceStack.sendFailure(ComponentCompatApi.literal(StringUtil.tr("message.command.operator.setPermissionLevel.lower", level)));
            return 1;
        }

        for (GameProfile gameProfile : gameProfileCollection) {
            CarpetTCTCAddition.getServer().getPlayerList().tctc$setPermissionLevel(gameProfile, level);
            MessageUtil.sendMessage(commandSourceStack, StringUtil.tr("message.command.operator.setPermissionLevel.success", gameProfile.getName(), level));
        }

        return 1;
    }

    private static int setBypassPlayerLimit(CommandSourceStack commandSourceStack, @NotNull Collection<GameProfile> gameProfileCollection, boolean bypass) {
        for (GameProfile gameProfile : gameProfileCollection) {
            CarpetTCTCAddition.getServer().getPlayerList().tctc$setBypassPlayerLimit(gameProfile, bypass);
            MessageUtil.sendMessage(commandSourceStack, StringUtil.tr("message.command.operator.setBypassPlayerLimit.success", gameProfile.getName(), bypass));
        }
        return 1;
    }
}
