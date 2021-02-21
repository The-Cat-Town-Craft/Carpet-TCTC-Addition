package CarpetTCTCAddition.mixins.net.minecraft.server.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
    @Redirect(
        method = "processBlockBreakingAction",
        at = @At(
            value = "INVOKE",
            target = "Lorg/apache/logging/log4j/Logger;warn(Ljava/lang/String;)V"
        )
    )
    private void consoleSpamFix(Logger logger, String message) {
        if (!CarpetTCTCAdditionSettings.consoleSpamFix) {
            logger.warn(message);
        }
    }
}
