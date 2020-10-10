package CarpetTCTCAddition.mixins.net.minecraft.world;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {

    @Inject(method = "updateNeighbor", at = @At(value = "INVOKE", target = "net/minecraft/util/crash/CrashException.<init> (Lnet/minecraft/util/crash/CrashReport;)V"), cancellable = true)
    private void SupressorFix(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.removeUpdateSuppression) {
            ci.cancel();
        }
    }
}
