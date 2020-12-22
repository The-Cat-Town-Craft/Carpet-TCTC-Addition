package CarpetTCTCAddition.mixins.carpet.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.commands.PlayerCommand;
import carpet.utils.Messenger;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PlayerCommand.class)
public abstract class PlayerCommandMixin {
    @Inject(
        method = "spawn",
        at = @At(value = "HEAD"),
        cancellable = true,
        remap = false
    )
    private static void fakePlayerNameLengthLimit(CommandContext<ServerCommandSource> context, CallbackInfoReturnable<Integer> cir)
    {
        if (CarpetTCTCAdditionSettings.fakePlayerNameLengthLimit) {
            String playerName = StringArgumentType.getString(context, "player");
            if (StringArgumentType.getString(context, "player").length() > 16) {
                Messenger.m(context.getSource(), new Object[]{"rb Player name: " + playerName + " is too long"});
                cir.cancel();
            }
        }
    }
}