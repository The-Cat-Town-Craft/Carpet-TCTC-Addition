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

import static CarpetTCTCAddition.commands.HereCommand.showPos;

public class FindCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> find = CommandManager.literal("find")
            .requires((player) -> SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandFind))
            .then(CommandManager.argument("目标选择器", EntityArgumentType.players())
                .executes((c) -> execute(c.getSource(), EntityArgumentType.getPlayers(c, "目标选择器"))))
                    .then(CommandManager.argument("玩家", EntityArgumentType.player())
                        .executes((c) -> printPos(c.getSource(), EntityArgumentType.getPlayer(c, "玩家"))));
        dispatcher.register(find);
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
        Messenger.m(source, showPos(player));
        return 1;
    }
}
