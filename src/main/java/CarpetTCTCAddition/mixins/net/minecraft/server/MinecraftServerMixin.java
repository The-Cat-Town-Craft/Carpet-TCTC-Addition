package CarpetTCTCAddition.mixins.net.minecraft.server;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import CarpetTCTCAddition.helps.ThrowableSuppression;
import carpet.utils.Messenger;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.crash.CrashException;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.function.BooleanSupplier;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin
{
    @Redirect(method = "tickWorlds", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;tick(Ljava/util/function/BooleanSupplier;)V"))
    private void ficUpdateSuppressionCrashTick(ServerWorld serverWorld, BooleanSupplier shouldKeepTicking){
        if (!CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
            serverWorld.tick(shouldKeepTicking);
            return;
        }
        try {
            serverWorld.tick(shouldKeepTicking);
        } catch (CrashException e) {
            if (!(e.getCause() instanceof ThrowableSuppression)) throw e;
            Messenger.print_server_message((MinecraftServer) (Object) this, "世界运算异常.");
        } catch (ThrowableSuppression ignored) {
            Messenger.print_server_message((MinecraftServer) (Object) this, "世界运算异常.");
        }
    }
}
