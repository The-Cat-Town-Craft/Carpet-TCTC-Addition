package CarpetTCTCAddition.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.world.chunk.ChunkStatus;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class LoadChunkCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        LiteralArgumentBuilder<ServerCommandSource> loadChunk = literal("loadchunk")
            .requires((player) -> SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandLoadChunk))
            .then(argument("chunkX", IntegerArgumentType.integer())
                .then(argument("chunkZ", IntegerArgumentType.integer()).executes((c) -> loadChunk(c.getSource(), IntegerArgumentType.getInteger(c, "chunkX"), IntegerArgumentType.getInteger(c, "chunkZ")))));
        dispatcher.register(loadChunk);
    }
    private static int loadChunk(ServerCommandSource source, int ChunkX, int ChunkZ)
    {
        source.getWorld().getChunk(ChunkX, ChunkZ);
        Messenger.print_server_message(source.getMinecraftServer(), String.format("加载区块 %d, %d", ChunkX, ChunkZ));
        return 1;
    }
}
