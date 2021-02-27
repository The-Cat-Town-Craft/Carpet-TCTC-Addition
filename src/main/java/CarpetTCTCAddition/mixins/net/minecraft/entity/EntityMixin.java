package CarpetTCTCAddition.mixins.net.minecraft.entity;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow @Nullable
    public abstract MinecraftServer getServer();

    @Inject(
        method = "changeDimension",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;getForcedSpawnPoint()Lnet/minecraft/util/math/BlockPos;",
            shift = At.Shift.AFTER
        )
    )
    private void createPlatform(DimensionType newDimension, CallbackInfoReturnable<Entity> cir) {
        if (CarpetTCTCAdditionSettings.obsidianPlatform == CarpetTCTCAdditionSettings.CreatePlatformOptions.ALL) {
            // From Minecraft 1.16
            BlockPos blockPos = new BlockPos(100, 50, 0);
            int i = blockPos.getX();
            int j = blockPos.getY() - 2;
            int k = blockPos.getZ();
            BlockPos.iterate(i - 2, j + 1, k - 2, i + 2, j + 3, k + 2).forEach((blockPosx) -> {
                getServer().getWorld(newDimension).setBlockState(blockPosx, Blocks.AIR.getDefaultState());
            });
            BlockPos.iterate(i - 2, j, k - 2, i + 2, j, k + 2).forEach((blockPosx) -> {
                getServer().getWorld(newDimension).setBlockState(blockPosx, Blocks.OBSIDIAN.getDefaultState());
            });
        }
    }
}
