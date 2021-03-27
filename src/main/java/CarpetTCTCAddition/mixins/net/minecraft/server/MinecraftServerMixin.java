package CarpetTCTCAddition.mixins.net.minecraft.server;

import CarpetTCTCAddition.CarpetTCTCAddition;
import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.utils.Messenger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    @Inject(
        method = "tickWorlds",
        at = @At(
            value = "INVOKE",
            ordinal = 0,
            target = "Lnet/minecraft/util/crash/CrashReport;create(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/util/crash/CrashReport;"
        ),
        cancellable = true
    )
    private void updateSuppressionFix(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
            Messenger.print_server_message(CarpetTCTCAddition.server, "世界循环异常.");
            ci.cancel();
        }
    }
}
