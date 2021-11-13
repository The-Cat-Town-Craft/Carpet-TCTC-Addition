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
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ColumnPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkStatus;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.levelgen.Heightmap;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;

import java.util.EnumSet;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.arguments.coordinates.ColumnPosArgument.columnPos;
import static net.minecraft.commands.arguments.coordinates.ColumnPosArgument.getColumnPos;

public class FixCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> fix = literal("fix")
                .requires(s -> SettingsManager.canUseCommand(s, CarpetTCTCAdditionSettings.commandFix))
                .then(argument("location", columnPos())
                        .executes(c -> fixChunk(c.getSource(), getColumnPos(c, "location"))));
        dispatcher.register(fix);
    }

    private static int fixChunk(CommandSourceStack source, ColumnPos pos) throws CommandSyntaxException {
        ServerLevel level = source.getLevel();
        ChunkPos chunkPos = new ChunkPos(pos.x >> 4, pos.z >> 4);
        BlockGetter blockGetter = level.getChunk(chunkPos.x, chunkPos.z, ChunkStatus.FULL, false);
        if (!(blockGetter instanceof LevelChunk)) {
            throw BlockPosArgument.ERROR_NOT_LOADED.create();
        }
        LevelChunk levelChunk = (LevelChunk) blockGetter;
        Heightmap.primeHeightmaps(levelChunk, EnumSet.allOf(Heightmap.Types.class));
        MessageUtil.sendMessage(source, (BaseComponent) new TextComponent(String.format("Fixing chunk [%s, %s]...", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
        level.getChunkSource().getLightEngine().lightChunk(levelChunk, false).thenRun(() -> MessageUtil.sendMessage(source, (BaseComponent) new TextComponent(String.format("Fixed chunk [%s, %s]", chunkPos.x, chunkPos.z)).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC)));
        return 1;
    }
}
