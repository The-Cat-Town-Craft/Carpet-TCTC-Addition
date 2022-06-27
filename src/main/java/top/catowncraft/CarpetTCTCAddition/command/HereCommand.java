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
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.*;
//#if MC >= 11600
import net.minecraft.resources.ResourceKey;
//#endif
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
//#if MC < 11600
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.util.MessageUtil;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;

import java.util.Collection;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class HereCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> here = literal("here")
                .requires(commandSourceStack -> SettingsManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandHere))
                .executes(context -> print(context.getSource(), context.getSource().getPlayerOrException()))
                .then(argument("target", EntityArgument.players())
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .executes(context -> print(context.getSource(), EntityArgument.getPlayers(context, "target"))));
        dispatcher.register(here);
    }

    public static int print(CommandSourceStack commandSourceStack, Collection<ServerPlayer> serverPlayerCollection) {
        for (ServerPlayer serverPlayer : serverPlayerCollection) {
            print(commandSourceStack, serverPlayer);
        }
        return 1;
    }

    public static int print(CommandSourceStack commandSourceStack, ServerPlayer serverPlayer) {
        //#if MC >= 11600
        if (serverPlayer.level.dimension() == Level.OVERWORLD || serverPlayer.level.dimension() == Level.NETHER) {
        //#else
        //$$ if (serverPlayer.level.dimension.getType() == DimensionType.OVERWORLD || serverPlayer.level.dimension.getType() == DimensionType.NETHER) {
        //#endif
            MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(),
                    ComponentCompatApi.literal(StringUtil.tr("message.command.here.withTransformed",
                            ComponentCompatApi.literal(serverPlayer.getName().getString()).withStyle(ChatFormatting.GRAY),
                            //#if MC >= 11600
                            HereCommand.getDimension(serverPlayer.level.dimension()),
                            ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.level.dimension() == Level.OVERWORLD ? ChatFormatting.DARK_GREEN : ChatFormatting.DARK_RED),
                            //#else
                            //$$ HereCommand.getDimension(serverPlayer.level.dimension.getType()),
                            //$$ ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.level.dimension.getType() == DimensionType.OVERWORLD ? ChatFormatting.DARK_GREEN : ChatFormatting.DARK_RED),
                            //#endif
                            HereCommand.getWorldMapAdderVM(serverPlayer),
                            HereCommand.getWorldMapAdderXM(serverPlayer),
                            HereCommand.getTeleportLocationAction(serverPlayer),
                            HereCommand.getTeleportPlayerAction(serverPlayer),
                            //#if MC >= 11600
                            ComponentCompatApi.literal(serverPlayer.level.dimension() == Level.OVERWORLD ? getDividedPosition(serverPlayer) : getMultipliedPosition(serverPlayer)).withStyle(serverPlayer.level.dimension() == Level.OVERWORLD ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN)
                            //#else
                            //$$ ComponentCompatApi.literal(serverPlayer.level.dimension.getType() == DimensionType.OVERWORLD ? getDividedPosition(serverPlayer) : getMultipliedPosition(serverPlayer)).withStyle(serverPlayer.level.dimension.getType() == DimensionType.OVERWORLD ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN)
                            //#endif
                    )));
        } else {
            MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(),
                    ComponentCompatApi.literal(StringUtil.tr("message.command.here.withoutTransformed",
                            ComponentCompatApi.literal(serverPlayer.getName().getString()).withStyle(ChatFormatting.GRAY),
                            //#if MC >= 11600
                            HereCommand.getDimension(serverPlayer.level.dimension()),
                            ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.level.dimension() == Level.END ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA),
                            //#else
                            //$$ HereCommand.getDimension(serverPlayer.level.dimension.getType()),
                            //$$ ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.level.dimension.getType() == DimensionType.THE_END ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA),
                            //#endif
                            HereCommand.getWorldMapAdderVM(serverPlayer),
                            HereCommand.getWorldMapAdderXM(serverPlayer),
                            HereCommand.getTeleportLocationAction(serverPlayer),
                            HereCommand.getTeleportPlayerAction(serverPlayer))));
        }
        if (CarpetTCTCAdditionSettings.hereGlowTime > 0) {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, CarpetTCTCAdditionSettings.hereGlowTime * 20, 0, false, false, false));
        }
        return 1;
    }

    public static String getOriginalPosition(Entity entity) {
        return String.format("[%d, %d, %d]", (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat());
    }

    public static String getMultipliedPosition(Entity entity) {
        return String.format("[%d, %d, %d]", (int) (entity.getXCompat() * 8), (int) (entity.getYCompat() * 8), (int) (entity.getZCompat() * 8));
    }

    public static String getDividedPosition(Entity entity) {
        return String.format("[%d, %d, %d]", (int) (entity.getXCompat() / 8), (int) (entity.getYCompat() / 8), (int) (entity.getZCompat() / 8));
    }

    //#if MC >= 11600
    public static Component getDimension(ResourceKey<Level> resourceKey) {
        switch (resourceKey.location().toString()) {
    //#else
    //$$ public static Component getDimension(DimensionType dimensionType) {
    //$$     switch (dimensionType.toString()) {
    //#endif
            case "minecraft:overworld":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.overworld")).withStyle(ChatFormatting.GREEN);
            case "minecraft:the_end":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.end")).withStyle(ChatFormatting.YELLOW);
            case "minecraft:the_nether":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.nether")).withStyle(ChatFormatting.RED);
            default:
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.unknown")).withStyle(ChatFormatting.AQUA);
        }
    }

    public static Component getWorldMapAdderVM(Entity entity) {
        return ComponentCompatApi.literal("[V]")
                .withStyle(ChatFormatting.DARK_BLUE)
                .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/newWaypoint x:%d, y:%d, z:%d", (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.voxelMap")))));
    }

    public static Component getWorldMapAdderXM(Entity entity) {
        return ComponentCompatApi.literal("[V]").withStyle(ChatFormatting.DARK_GREEN).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("xaero_waypoint_add:%s's Location:%s:%d:%d:%d:8:false:0",  entity.getDisplayName().getString(), entity.getDisplayName().getString(1), (int) entity.getZCompat(), (int) entity.getYCompat(), (int) entity.getZ()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.xaeroMap")))));
    }

    public static Component getTeleportLocationAction(Entity entity) {
        //#if MC >= 11600
        return ComponentCompatApi.literal("[T]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/execute in %s run tp %d %d %d", entity.level.dimension().location().toString(), (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.location", (int) entity.getX(), (int) entity.getY(), (int) entity.getZ())))));
        //#else
        //$$ return ComponentCompatApi.literal("[T]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/execute in %s run tp %d %d %d", entity.level.dimension.toString(), (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.location", (int) entity.getX(), (int) entity.getY(), (int) entity.getZ())))));
        //#endif
    }

    public static Component getTeleportPlayerAction(Entity entity) {
        return ComponentCompatApi.literal("[P]").withStyle(ChatFormatting.DARK_AQUA).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/tp %s", entity.getName().getString()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.player", entity.getName().getString())))));
    }
}