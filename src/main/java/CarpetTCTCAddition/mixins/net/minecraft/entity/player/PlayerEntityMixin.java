package CarpetTCTCAddition.mixins.net.minecraft.entity.player;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.patches.EntityPlayerMPFake;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(
        method = "increaseStat(Lnet/minecraft/util/Identifier;I)V",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void increaseStat(CallbackInfo ci)
    {
        if(!CarpetTCTCAdditionSettings.fakePlayerStats && (ServerPlayerEntity)(Object)this instanceof EntityPlayerMPFake) {
            ci.cancel();
        }
    }
    @Inject(
        method = "incrementStat(Lnet/minecraft/util/Identifier;)V",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void incrementStat(CallbackInfo ci)
    {
        if(!CarpetTCTCAdditionSettings.fakePlayerStats && (ServerPlayerEntity)(Object)this instanceof EntityPlayerMPFake) {
            ci.cancel();
        }
    }
}
