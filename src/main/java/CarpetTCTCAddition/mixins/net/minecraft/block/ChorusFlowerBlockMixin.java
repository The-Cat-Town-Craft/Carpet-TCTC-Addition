package CarpetTCTCAddition.mixins.net.minecraft.block;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.Random;

@Mixin(ChorusFlowerBlock.class)
public abstract class ChorusFlowerBlockMixin extends Block {
    @Shadow @Final public static IntProperty AGE;

    @Shadow @Final private ChorusPlantBlock plantBlock;

    @Shadow protected abstract void grow(World world, BlockPos pos, int age);

    @Shadow protected abstract void die(World world, BlockPos pos);

    private static boolean isSurroundedByAir(WorldView world, BlockPos pos, @Nullable Direction exceptDirection) {
        Iterator var3 = Direction.Type.HORIZONTAL.iterator();

        Direction direction;
        do {
            if (!var3.hasNext()) {
                return true;
            }

            direction = (Direction)var3.next();
        } while(direction == exceptDirection || world.isAir(pos.offset(direction)));

        return false;
    }

    public ChorusFlowerBlockMixin(Settings settings) {
        super(settings);
    }

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
        } else if (CarpetTCTCAdditionSettings.zeroTickFarm){
            BlockPos blockPos = pos.up();
            if (world.isAir(blockPos) && blockPos.getY() < 256) {
                int i = state.get(AGE);
                if (i < 5) {
                    boolean bl = false;
                    boolean bl2 = false;
                    BlockState blockState = world.getBlockState(pos.down());
                    Block block = blockState.getBlock();
                    int l;
                    if (block == Blocks.END_STONE) {
                        bl = true;
                    } else if (block == this.plantBlock) {
                        l = 1;

                        for(int k = 0; k < 4; ++k) {
                            Block block2 = world.getBlockState(pos.down(l + 1)).getBlock();
                            if (block2 != this.plantBlock) {
                                if (block2 == Blocks.END_STONE) {
                                    bl2 = true;
                                }
                                break;
                            }

                            ++l;
                        }

                        if (l < 2 || l <= random.nextInt(bl2 ? 5 : 4)) {
                            bl = true;
                        }
                    } else if (blockState.isAir()) {
                        bl = true;
                    }

                    if (bl && isSurroundedByAir(world, blockPos, null) && world.isAir(pos.up(2))) {
                        world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                        this.grow(world, blockPos, i);
                    } else if (i < 4) {
                        l = random.nextInt(4);
                        if (bl2) {
                            ++l;
                        }

                        boolean bl3 = false;

                        for(int m = 0; m < l; ++m) {
                            Direction direction = Direction.Type.HORIZONTAL.random(random);
                            BlockPos blockPos2 = pos.offset(direction);
                            if (world.isAir(blockPos2) && world.isAir(blockPos2.down()) && isSurroundedByAir(world, blockPos2, direction.getOpposite())) {
                                this.grow(world, blockPos2, i + 1);
                                bl3 = true;
                            }
                        }

                        if (bl3) {
                            world.setBlockState(pos, this.plantBlock.withConnectionProperties(world, pos), 2);
                        } else {
                            this.die(world, pos);
                        }
                    } else {
                        this.die(world, pos);
                    }
                }
            }
        }
        ci.cancel();
    }
}
