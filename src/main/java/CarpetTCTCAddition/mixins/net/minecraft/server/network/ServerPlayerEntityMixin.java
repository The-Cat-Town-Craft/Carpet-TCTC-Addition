package CarpetTCTCAddition.mixins.net.minecraft.server.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.patches.EntityPlayerMPFake;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TeleportTarget;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Shadow protected abstract void createEndSpawnPlatform(ServerWorld world, BlockPos centerPos);

    @Shadow @Nullable protected abstract TeleportTarget getTeleportTarget(ServerWorld destination);

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
    @Redirect(
        method = "moveToWorld",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/network/ServerPlayerEntity;createEndSpawnPlatform(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)V"
        )
    )
    private void createEndSpawnPlatformCheck(ServerPlayerEntity serverPlayerEntity, ServerWorld world, BlockPos centerPos)
    {
        if(CarpetTCTCAdditionSettings.obsidianPlatform != CarpetTCTCAdditionSettings.CreatePlatformOptions.NONE) {
            this.createEndSpawnPlatform(world, new BlockPos(Objects.requireNonNull(this.getTeleportTarget(world)).position));
        }
    }
}
