/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.GameProfileArgument;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.api.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.util.MessageUtil;

import java.util.Collection;
import java.util.Optional;

public class OperatorCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> operator = Commands.literal("operator")
                .requires(commandSourceStack -> CarpetTCTCAdditionSettingManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandOperator))
                .then(Commands.argument("targets", GameProfileArgument.gameProfile())
                        .then(Commands.literal("set")
                                .then(Commands.literal("permissionLevel")
                                        .then(Commands.argument("permissionLevel", IntegerArgumentType.integer(0, 4))
                                                .executes(context -> OperatorCommand.setPermissionLevel(context.getSource(), GameProfileArgument.getGameProfiles(context, "targets"), IntegerArgumentType.getInteger(context, "permissionLevel")))))
                                .then(Commands.literal("canBypassPlayerLimit")
                                        .then(Commands.argument("canBypassPlayerLimit", BoolArgumentType.bool())
                                                .executes(context -> OperatorCommand.setBypassPlayerLimit(context.getSource(), GameProfileArgument.getGameProfiles(context, "targets"), BoolArgumentType.getBool(context, "canBypassPlayerLimit")))))));
        dispatcher.register(operator);
    }

    private static int setPermissionLevel(@NotNull CommandSourceStack commandSourceStack, Collection<GameProfile> gameProfileCollection, int level) {
        if (!commandSourceStack.hasPermission(level) && CarpetTCTCAdditionSettings.opLevelBelowSelf) {
            commandSourceStack.sendFailure(ComponentCompatApi.literal(StringUtil.tr("message.command.operator.setPermissionLevel.lower", level)));
            return 1;
        }

        gameProfileCollection.forEach(gameProfile ->
                Optional.ofNullable(CarpetTCTCAdditionExtension.getServer()).ifPresent(minecraftServer -> {
                    minecraftServer.getPlayerList().tctc$setPermissionLevel(gameProfile, level);
                    MessageUtil.sendMessage(commandSourceStack, StringUtil.tr("message.command.operator.setPermissionLevel.success", gameProfile.getName(), level));
                }));

        return 1;
    }

    private static int setBypassPlayerLimit(CommandSourceStack commandSourceStack, @NotNull Collection<GameProfile> gameProfileCollection, boolean bypass) {
        gameProfileCollection.forEach(gameProfile ->
                Optional.ofNullable(CarpetTCTCAdditionExtension.getServer()).ifPresent(minecraftServer -> {
                    minecraftServer.getPlayerList().tctc$setBypassPlayerLimit(gameProfile, bypass);
                    MessageUtil.sendMessage(commandSourceStack, StringUtil.tr("message.command.operator.setBypassPlayerLimit.success", gameProfile.getName(), bypass));
                }));

        return 1;
    }
}
