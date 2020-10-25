package CarpetTCTCAddition.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.dimension.DimensionType;

import java.util.Collection;
import java.util.Iterator;

public class FindCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
            LiteralArgumentBuilder<ServerCommandSource> find = CommandManager.literal("find").requires((player) -> {
            return SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandFind);
        }).then(CommandManager.argument("目标选择器", EntityArgumentType.players())
            .executes((c) -> {
            return execute(c.getSource(), EntityArgumentType.getPlayers(c, "目标选择器"));
        })).then(CommandManager.argument("玩家", EntityArgumentType.player()).executes((c) -> {
            return showPos((ServerCommandSource)c.getSource(), EntityArgumentType.getPlayer(c, "玩家"));
        }));
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
        DimensionType playerDimension =  player.dimension;
        String Message = String.format("玩家 %s 位于 %s §b[x:%d, y:%d, z:%d]", player.getDisplayName().getString(), getDimensionName(playerDimension), playerX, playerY, playerZ);
        if(playerDimension == DimensionType.OVERWORLD) {
            Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName(DimensionType.THE_NETHER), playerX / 8, playerY, playerZ / 8);
        } else if (playerDimension == DimensionType.THE_NETHER) {
            Message += String.format(" §f-> %s §b[x:%d, y:%d, z:%d]", getDimensionName(DimensionType.OVERWORLD), playerX * 8, playerY, playerZ * 8);
        }
        Messenger.m(source, Message);
        return 1;
    }
    public static String getDimensionName(DimensionType dimension) {
        if (dimension == DimensionType.OVERWORLD) {
            return "§a主世界";
        } else if (dimension == DimensionType.THE_NETHER) {
            return "§c下界";
        } else if (dimension == DimensionType.THE_END) {
            return "§e末路之地";
        } else {
            return "§f未知";
        }
    }
}
