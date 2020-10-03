package CarpetTCTCAddition.mixins;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(
        method = "onBlockAdded",
        at = @At(value = "HEAD")
    )
    private void cancelBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify, CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
    @Inject(
        method = "onStateReplaced",
        at = @At(value = "HEAD")
    )
    private void cancelBlockRemove(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved, CallbackInfo ci) {
        if(!CarpetTCTCAdditionSettings.blockUpdate) ci.cancel();
    }
}
