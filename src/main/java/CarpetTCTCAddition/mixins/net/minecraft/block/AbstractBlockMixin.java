package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractBlock.class)
public class AbstractBlockMixin {
    @Inject(
        method = "neighborUpdate",
        at = @At(
            value = "HEAD"
        )
    )
    private void updateSuppressor(BlockState state, World world, BlockPos pos, Block block, BlockPos neighborPos, boolean moved, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.oreUpdateSuppressor && block == Blocks.EMERALD_ORE) {
            throw new StackOverflowError("Carpet-triggered update suppression.");
        }
    }
}
