package CarpetTCTCAddition.mixins;

import CarpetTCTCAddition.CarpetTCTCAddition;
import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin
{
    // this is here just to load the ExampleExtension class, otherwise noone would load it / need it
    // if you have already you own mixins that use your extension class in any shape or form
    // you don't need this one
    // You need this one to run a server properly
    @Inject(method = "<init>", at = @At("RETURN"))
    private void loadMe(CallbackInfo ci)
    {
        CarpetTCTCAddition.noop();
    }

    @Inject(method = "tickWorlds", at = @At(value = "INVOKE", ordinal = 0, target = "net/minecraft/util/crash/CrashReport.create (Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"), cancellable = true)
    private void updateSuppressionFix(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
            System.out.println("检测到世界循环异常, 尝试避免崩溃");
            ci.cancel();
        }
    }
}
