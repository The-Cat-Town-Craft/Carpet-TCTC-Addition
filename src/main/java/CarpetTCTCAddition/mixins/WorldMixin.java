package CarpetTCTCAddition.mixins;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldMixin {

    @Inject(method = "updateNeighbor", at = @At(value = "INVOKE", target = "net/minecraft/util/crash/CrashException.<init> (Lnet/minecraft/util/crash/CrashReport;)V"), cancellable = true)
    private void SupressorFix(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.removeUpdateSuppression) {
            ci.cancel();
        }
    }
}
