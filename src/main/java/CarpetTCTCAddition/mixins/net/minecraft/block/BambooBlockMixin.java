package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.BambooBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(BambooBlock.class)
public abstract class BambooBlockMixin extends Block {
    public BambooBlockMixin(Settings settings) {
        super(settings);
    }
    @Shadow
    @Final
    protected abstract int countBambooBelow(BlockView world, BlockPos pos);

    @Shadow
    @Final
    public static IntProperty STAGE;

    @Shadow
    @Final
    protected abstract void updateLeaves(BlockState state, World world, BlockPos pos, Random random, int height);

    @Inject(
        method = "scheduledTick",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (!state.canPlaceAt(world, pos)) {
            world.breakBlock(pos, true);
        } else if (CarpetTCTCAdditionSettings.zeroTickFarm && state.get(STAGE) == 0) {
            if (random.nextInt(3) == 0 && world.isAir(pos.up()) && world.getBaseLightLevel(pos.up(), 0) >= 9) {
                int i = this.countBambooBelow(world, pos) + 1;
                if (i < 16) {
                    this.updateLeaves(state, world, pos, random, i);
                }
            }
        }
        ci.cancel();
    }
}
