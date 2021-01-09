package CarpetTCTCAddition.mixins.net.minecraft.server.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.patches.EntityPlayerMPFake;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {
    @Inject(
        method = "Lnet/minecraft/server/network/ServerPlayerEntity;increaseStat(Lnet/minecraft/stat/Stat;I)V",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void increaseStat(CallbackInfo ci)
    {
        if(!CarpetTCTCAdditionSettings.fakePlayerStats && (Object)this instanceof EntityPlayerMPFake) {
            ci.cancel();
        }
    }
}
