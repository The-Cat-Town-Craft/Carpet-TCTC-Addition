package CarpetTCTCAddition.mixins.net.minecraft.server.dedicated.command;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.settings.SettingsManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.command.OpCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(OpCommand.class)
public class OpCommandMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(
        method = "method_13470",  // lambda method
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z"
        ),
        allow = 1
    )
    private static boolean canUseSeedCommand(ServerCommandSource source, int level)
    {
        return source.getMinecraftServer().isSinglePlayer() || SettingsManager.canUseCommand(source, CarpetTCTCAdditionSettings.commandOp);
    }
}
