package CarpetTCTCAddition.mixins.net.minecraft.client.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin {
    @Redirect(
        method = "processPlayerActionResponse",
        at = @At(
            value = "INVOKE",
            target = "Lorg/apache/logging/log4j/Logger;error(Ljava/lang/String;)V"
        )
    )
    private void consoleSpamFix(Logger logger, String message) {
        if(!CarpetTCTCAdditionSettings.consoleSpamFix) {
            logger.error(message);
        }
    }
}
