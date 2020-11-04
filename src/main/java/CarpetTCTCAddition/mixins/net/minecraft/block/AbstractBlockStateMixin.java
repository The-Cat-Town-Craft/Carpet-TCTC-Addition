package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.AbstractBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
        method = "method_26182", //onBlockAdded
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockAdded(CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
        method = "method_26197", //onStateReplaced
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockRemove(CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
        method = "method_26181", //neighborUpdate
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelNeighborUpdate(CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
        method = "method_26183", //updateNeighbors
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelUpdateNeighbors(CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
}
