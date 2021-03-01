package CarpetTCTCAddition.mixins.net.minecraft.server.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.network.packet.c2s.play.SpectatorTeleportC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @Inject(
        method = "onSpectatorTeleport",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void disableSpectatorTeleport(SpectatorTeleportC2SPacket packet, CallbackInfo ci) {
        if(CarpetTCTCAdditionSettings.cameraModeDisableSpectatePlayers) {
            ci.cancel();
        }
    }
}
