package CarpetTCTCAddition;

import CarpetTCTCAddition.commands.TPSCommand;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.ServerCommandSource;

import static net.minecraft.server.command.CommandManager.literal;

public class Command
{
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        dispatcher.register(literal("carpetTCTCAddition"));
    }

}
