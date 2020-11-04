package CarpetTCTCAddition.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.helpers.TickSpeed;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.MathHelper;

public class TPSCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> tps = CommandManager.literal("tps")
            .requires((player) -> {return SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandTps);})
            .executes((c) -> queryTps(c.getSource()));
        dispatcher.register(tps);
    }

    private static int queryTps(ServerCommandSource source) {
        MinecraftServer server = source.getMinecraftServer();
        double MSPT = MathHelper.average(server.lastTickLengths) * 1.0E-6D;
        double TPS = 1000.0D / Math.max(TickSpeed.time_warp_start_time != 0L ? 0.0D : (double)TickSpeed.mspt, MSPT);
        Messenger.m(source, new Object[]{"w 当前TPS: ", String.format("wb %.1f ", TPS), " MSPT: ", String.format("wb %.1f", MSPT)});
        return 1;
    }
}
