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

public class FindCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> find = CommandManager.literal("find")
            .requires((player) -> SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandFind))
            .then(CommandManager.argument("目标选择器", EntityArgumentType.players())
                .executes((c) -> execute(c.getSource(), EntityArgumentType.getPlayers(c, "目标选择器"))))
                    .then(CommandManager.argument("玩家", EntityArgumentType.player())
                        .executes((c) -> showPos((ServerCommandSource)c.getSource(), EntityArgumentType.getPlayer(c, "玩家"))));
        dispatcher.register(find);
    }

    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
        Iterator var3 = targets.iterator();
        while(var3.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var3.next();
            showPos(source, serverPlayerEntity);
        }
        return 1;
    }
    public static int showPos(ServerCommandSource source, ServerPlayerEntity player) {
        int playerX = (int)player.prevX;
        int playerY = (int)player.prevY;
        int playerZ = (int)player.prevZ;
        String playerWorld = player.getServerWorld().getRegistryKey().getValue().toString();
        String Message = String.format("玩家 %s 位于 %s §b[x:%d, y:%d, z:%d]", player.getDisplayName().getString(), getDimensionName(playerWorld), playerX, playerY, playerZ);
        if(playerWorld.equals("minecraft:overworld")) {
            Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName("minecraft:the_nether"), playerX / 8, playerY, playerZ / 8);
        } else if (playerWorld.equals("minecraft:the_nether")) {
            Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName("minecraft:overworld"), playerX * 8, playerY, playerZ * 8);
        }
        Messenger.m(source, Message);
        return 1;
    }
    public static String getDimensionName(String world) {
        if (world.equals("minecraft:overworld")) {
            return "§a主世界";
        } else if (world.equals("minecraft:the_nether")) {
            return "§c下界";
        } else if (world == "minecraft:the_end") {
            return "§e末路之地";
        } else {
            return world;
        }
    }
}
