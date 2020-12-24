package CarpetTCTCAddition.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Collection;
import java.util.Iterator;

public class HereCommand {

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> here = CommandManager.literal("here")
            .requires((player) -> SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandHere))
            .executes((c) -> printPos(c.getSource(), c.getSource().getPlayer()))
            .then(CommandManager.argument("目标选择器", EntityArgumentType.players())
                .requires((c) -> c.hasPermissionLevel(3))
                .executes((c) -> execute(c.getSource(), EntityArgumentType.getPlayers(c, "目标选择器"))))
                .then(CommandManager.argument("玩家", EntityArgumentType.player())
                    .executes((c) -> printPos(c.getSource(), EntityArgumentType.getPlayer(c, "玩家"))));
        dispatcher.register(here);
    }
    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
        Iterator var3 = targets.iterator();
        while(var3.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var3.next();
            printPos(source, serverPlayerEntity);
        }
        return 1;
    }
    public static int printPos(ServerCommandSource source, ServerPlayerEntity player) {
        Messenger.print_server_message(source.getMinecraftServer(), showPos(player));
        return 1;
    }
    public static String showPos(ServerPlayerEntity player) {
        int playerX = (int)player.prevX;
        int playerY = (int)player.prevY;
        int playerZ = (int)player.prevZ;
        String playerWorld = player.getServerWorld().getRegistryKey().getValue().toString();
        String Message = String.format("玩家 %s 位于 %s §b[x:%d, y:%d, z:%d]", player.getDisplayName().getString(), getDimensionName(playerWorld), playerX, playerY, playerZ);
        switch (playerWorld) {
            case "minecraft:overworld":
                Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName("minecraft:the_nether"), playerX / 8, playerY, playerZ / 8);
                break;
            case "minecraft:the_nether":
                Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName("minecraft:overworld"), playerX * 8, playerY, playerZ * 8);
                break;
        }
        return Message;
    }
    public static String getDimensionName(String world) {
        switch (world) {
            case "minecraft:overworld":
                return "§a主世界";
            case "minecraft:the_nether":
                return "§c下界";
            case "minecraft:the_end":
                return "§e末路之地";
            default:
                return world;
        }
    }
}
