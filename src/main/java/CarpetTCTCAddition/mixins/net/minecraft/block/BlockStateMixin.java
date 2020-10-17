package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockState.class)
public abstract class BlockStateMixin {
    @Inject(
        method = "onBlockAdded",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockAdded(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.blockUpdate) {
            ci.cancel();
        }
    }
    @Inject(
        method = "onBlockRemoved",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockRemove(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.blockUpdate) {
            ci.cancel();
        }
    }
    @Inject(
        method = "updateNeighborStates",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelUpdateNeighborStates(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.blockUpdate) {
            ci.cancel();
        }
    }
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
        method = "method_11622", // neighborUpdate
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockAction(CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.blockUpdate) {
            ci.cancel();
        }
    }
}
