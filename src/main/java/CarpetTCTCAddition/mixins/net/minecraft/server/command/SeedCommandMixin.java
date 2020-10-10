package CarpetTCTCAddition.mixins.net.minecraft.server.command;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.settings.SettingsManager;
import net.minecraft.server.command.SeedCommand;
import net.minecraft.server.command.ServerCommandSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SeedCommand.class)
public class SeedCommandMixin
{
    @SuppressWarnings("UnresolvedMixinReference")
    @Redirect(
            method = "method_13618",  // lambda method
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/command/ServerCommandSource;hasPermissionLevel(I)Z"
            ),
            allow = 1
    )
    private static boolean canUseSeedCommand(ServerCommandSource source, int level)
    {
        return source.getMinecraftServer().isSinglePlayer() || SettingsManager.canUseCommand(source, CarpetTCTCAdditionSettings.commandSeed);
    }
}