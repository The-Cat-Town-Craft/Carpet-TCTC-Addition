package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.AbstractBlockState.class)
public class AbstractBlockStateMixin {
    @Inject(
        method = "onBlockAdded",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockAdded(World world, BlockPos pos, BlockState state, boolean notify, CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
    @Inject(
        method = "onStateReplaced",
        at = @At(value = "HEAD"),
        cancellable = true
    )
    private void cancelBlockRemove(World world, BlockPos pos, BlockState state, boolean moved, CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
}
