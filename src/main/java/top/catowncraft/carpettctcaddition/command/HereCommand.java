/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.*;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.api.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.util.MessageUtil;

import java.util.Collection;

//#if MC > 11502
import net.minecraft.resources.ResourceKey;
//#else
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif

public class HereCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> here = Commands.literal("here")
                .requires(commandSourceStack -> CarpetTCTCAdditionSettingManager.canUseCommand(commandSourceStack, CarpetTCTCAdditionSettings.commandHere))
                .executes(context -> HereCommand.print(context.getSource(), context.getSource().getPlayerOrException()))
                .then(Commands.argument("target", EntityArgument.players())
                        .requires(commandSourceStack -> commandSourceStack.hasPermission(2))
                        .executes(context -> HereCommand.print(context.getSource(), EntityArgument.getPlayers(context, "target"))));
        dispatcher.register(here);
    }

    public static int print(CommandSourceStack commandSourceStack, @NotNull Collection<ServerPlayer> serverPlayerCollection) {
        serverPlayerCollection.forEach(serverPlayer -> HereCommand.print(commandSourceStack, serverPlayer));

        return 1;
    }

    public static int print(CommandSourceStack commandSourceStack, @NotNull ServerPlayer serverPlayer) {
        //#if MC > 11502
        if (serverPlayer.getLevelCompat().dimension() == Level.OVERWORLD || serverPlayer.getLevelCompat().dimension() == Level.NETHER) {
        //#else
        //$$ if (serverPlayer.getLevelCompat().dimension.getType() == DimensionType.OVERWORLD || serverPlayer.getLevelCompat().dimension.getType() == DimensionType.NETHER) {
        //#endif
            MessageUtil.sendServerMessage(CarpetTCTCAdditionExtension.getServer(),
                    ComponentCompatApi.translatable(StringUtil.tr("message.command.here.withTransformed"),
                            ComponentCompatApi.literal(serverPlayer.getName().getString()).withStyle(ChatFormatting.GRAY),
                            //#if MC > 11502
                            HereCommand.getDimension(serverPlayer.getLevelCompat().dimension()),
                            ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension() == Level.OVERWORLD ? ChatFormatting.DARK_GREEN : ChatFormatting.DARK_RED),
                            //#else
                            //$$ HereCommand.getDimension(serverPlayer.getLevelCompat().dimension.getType()),
                            //$$ ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension.getType() == DimensionType.OVERWORLD ? ChatFormatting.DARK_GREEN : ChatFormatting.DARK_RED),
                            //#endif
                            HereCommand.getWorldMapAdderVM(serverPlayer),
                            HereCommand.getWorldMapAdderXM(serverPlayer),
                            HereCommand.getTeleportLocationAction(serverPlayer),
                            HereCommand.getTeleportPlayerAction(serverPlayer),
                            //#if MC > 11502
                            ComponentCompatApi.literal(serverPlayer.getLevelCompat().dimension() == Level.OVERWORLD ? getDividedPosition(serverPlayer) : getMultipliedPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension() == Level.OVERWORLD ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN)
                            //#else
                            //$$ ComponentCompatApi.literal(serverPlayer.getLevelCompat().dimension.getType() == DimensionType.OVERWORLD ? getDividedPosition(serverPlayer) : getMultipliedPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension.getType() == DimensionType.OVERWORLD ? ChatFormatting.DARK_RED : ChatFormatting.DARK_GREEN)
                            //#endif
                    ));
        } else {
            MessageUtil.sendServerMessage(CarpetTCTCAdditionExtension.getServer(),
                    ComponentCompatApi.translatable(StringUtil.tr("message.command.here.withoutTransformed"),
                            ComponentCompatApi.literal(serverPlayer.getName().getString()).withStyle(ChatFormatting.GRAY),
                            //#if MC > 11502
                            HereCommand.getDimension(serverPlayer.getLevelCompat().dimension()),
                            ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension() == Level.END ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA),
                            //#else
                            //$$ HereCommand.getDimension(serverPlayer.getLevelCompat().dimension.getType()),
                            //$$ ComponentCompatApi.literal(getOriginalPosition(serverPlayer)).withStyle(serverPlayer.getLevelCompat().dimension.getType() == DimensionType.THE_END ? ChatFormatting.GOLD : ChatFormatting.DARK_AQUA),
                            //#endif
                            HereCommand.getWorldMapAdderVM(serverPlayer),
                            HereCommand.getWorldMapAdderXM(serverPlayer),
                            HereCommand.getTeleportLocationAction(serverPlayer),
                            HereCommand.getTeleportPlayerAction(serverPlayer)));
        }

        if (CarpetTCTCAdditionSettings.hereGlowTime > 0) {
            serverPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING, CarpetTCTCAdditionSettings.hereGlowTime * 20, 0, false, false, false));
        }

        return 1;
    }

    public static String getOriginalPosition(@NotNull Entity entity) {
        return String.format("[%d, %d, %d]", (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat());
    }

    public static String getMultipliedPosition(@NotNull Entity entity) {
        return String.format("[%d, %d, %d]", (int) (entity.getXCompat() * 8), (int) (entity.getYCompat() * 8), (int) (entity.getZCompat() * 8));
    }

    public static String getDividedPosition(@NotNull Entity entity) {
        return String.format("[%d, %d, %d]", (int) (entity.getXCompat() / 8), (int) (entity.getYCompat() / 8), (int) (entity.getZCompat() / 8));
    }

    //#if MC >= 11600
    public static @NotNull Component getDimension(@NotNull ResourceKey<Level> resourceKey) {
        switch (resourceKey.location().toString()) {
    //#else
    //$$ public static Component getDimension(DimensionType dimensionType) {
    //$$     switch (dimensionType.toString()) {
    //#endif
            case "minecraft:overworld":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.minecraft:overworld")).withStyle(ChatFormatting.GREEN);
            case "minecraft:the_end":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.minecraft:the_end")).withStyle(ChatFormatting.YELLOW);
            case "minecraft:the_nether":
                return ComponentCompatApi.literal(StringUtil.tr("label.dimensionType.minecraft:the_nether")).withStyle(ChatFormatting.RED);
            default:
                return ComponentCompatApi.literal(StringUtil.tr(String.format("label.dimensionType.%s",
                        //#if MC > 11502
                        resourceKey.location().toString()))).withStyle(ChatFormatting.AQUA);
                        //#else
                        //$$ dimensionType.toString()))).withStyle(ChatFormatting.AQUA);
                        //#endif
        }
    }

    public static @NotNull Component getWorldMapAdderVM(Entity entity) {
        return ComponentCompatApi.literal("[V]")
                .withStyle(ChatFormatting.DARK_BLUE)
                .withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/newWaypoint x:%d, y:%d, z:%d", (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.voxelMap")))));
    }

    public static @NotNull Component getWorldMapAdderXM(Entity entity) {
        return ComponentCompatApi.literal("[V]").withStyle(ChatFormatting.DARK_GREEN).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("xaero_waypoint_add:%s's Location:%s:%d:%d:%d:8:false:0",  entity.getDisplayName().getString(), entity.getDisplayName().getString(1), (int) entity.getZCompat(), (int) entity.getYCompat(), (int) entity.getZ()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.xaeroMap")))));
    }

    public static @NotNull Component getTeleportLocationAction(Entity entity) {
        //#if MC >= 11600
        return ComponentCompatApi.literal("[T]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/execute in %s run tp %d %d %d", entity.getLevelCompat().dimension().location().toString(), (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.location", (int) entity.getX(), (int) entity.getY(), (int) entity.getZ())))));
        //#else
        //$$ return ComponentCompatApi.literal("[T]").withStyle(ChatFormatting.DARK_PURPLE).withStyle(style -> style.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/execute in %s run tp %d %d %d", entity.getLevelCompat().dimension.toString(), (int) entity.getXCompat(), (int) entity.getYCompat(), (int) entity.getZCompat()))).setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.location", (int) entity.getX(), (int) entity.getY(), (int) entity.getZ())))));
        //#endif
    }

    public static @NotNull Component getTeleportPlayerAction(Entity entity) {
        return ComponentCompatApi.literal("[P]").withStyle(ChatFormatting.DARK_AQUA).withStyle(style -> style.withClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, String.format("/tp %s", entity.getName().getString()))).withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, ComponentCompatApi.literal(StringUtil.tr("message.command.here.hover.teleport.player", entity.getName().getString())))));
    }
}