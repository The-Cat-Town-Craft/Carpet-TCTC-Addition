/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.server.level.ColumnPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ThreadedLevelLightEngine;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
//#if MC < 11500
//$$ import net.minecraft.world.level.chunk.ChunkStatus;
//#endif
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.util.MessageUtil;

import java.util.EnumSet;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.coordinates.ColumnPosArgument.columnPos;
import static net.minecraft.commands.arguments.coordinates.ColumnPosArgument.getColumnPos;

public class FixCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> fix = literal("fix")
                .requires(s -> CarpetTCTCAdditionSettingManager.canUseCommand(s, CarpetTCTCAdditionSettings.commandFix))
                .then(argument("location", columnPos())
                        .executes(c -> fixChunk(c.getSource(), getColumnPos(c, "location"))));
        dispatcher.register(fix);
    }

    private static int fixChunk(@NotNull CommandSourceStack source, @NotNull ColumnPos pos) throws CommandSyntaxException {
        ServerLevel level = source.getLevel();
        //#if MC >= 11900
        ChunkPos chunkPos = new ChunkPos(pos.x() >> 4, pos.z() >> 4);
        //#else
        //$$ ChunkPos chunkPos = new ChunkPos(pos.x >> 4, pos.z >> 4);
        //#endif
        //#if MC >= 11500
        BlockGetter blockGetter = level.getChunkForCollisions(chunkPos.x, chunkPos.z);
        //#else
        //$$ BlockGetter blockGetter = level.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.FULL, false);
        //#endif
        if (!(blockGetter instanceof LevelChunk)) {
            throw BlockPosArgument.ERROR_NOT_LOADED.create();
        }
        LevelChunk levelChunk = (LevelChunk) blockGetter;
        Heightmap.primeHeightmaps(levelChunk, EnumSet.allOf(Heightmap.Types.class));
        MessageUtil.sendMessage(source, ComponentCompatApi.literal(StringUtil.tr("message.command.fix.fixing", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        //#if MC >= 11500
        ((ThreadedLevelLightEngine) level.getLightEngine()).lightChunk(levelChunk, false).thenRun(
        //#else
        //$$ level.getChunkSource().getLightEngine().lightChunk(levelChunk, false).thenRun(
        //#endif
                () -> MessageUtil.sendMessage(source, ComponentCompatApi.literal(StringUtil.tr("message.command.fix.fixed", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)));
        return 1;
    }
}
