package CarpetTCTCAddition.mixins.net.minecraft.server;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin
{
    @Inject(method = "tickWorlds", at = @At(value = "INVOKE", ordinal = 0, target = "net/minecraft/util/crash/CrashReport.create (Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"), cancellable = true)
    private void updateSuppressionFix(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
            ci.cancel();
        }
    }
}
