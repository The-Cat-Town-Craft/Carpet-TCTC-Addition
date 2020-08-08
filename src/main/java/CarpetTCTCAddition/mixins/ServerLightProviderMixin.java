package CarpetTCTCAddition.mixins;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.server.world.ServerLightingProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLightingProvider.class)
public abstract class ServerLightProviderMixin {
    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    private void cancelcall1(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.lightUpdates){
            ci.cancel();
        }
    }

    @Inject(method = "enqueue(IILjava/util/function/IntSupplier;Lnet/minecraft/server/world/ServerLightingProvider$Stage;Ljava/lang/Runnable;)V", at = @At("HEAD"), cancellable = true)
    private void cancelcall2(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.lightUpdates){
            ci.cancel();
        }
    }
}