package CarpetTCTCAddition.mixins.net.minecraft.server.network;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.patches.EntityPlayerMPFake;
import com.mojang.authlib.GameProfile;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {

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
        method = "changeDimension",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z"
        )
    )
    private boolean generatePlatform(ServerWorld serverWorld, BlockPos pos, BlockState blockState) {
        if (CarpetTCTCAdditionSettings.obsidianPlatform != CarpetTCTCAdditionSettings.CreatePlatformOptions.PLAYER) {
            return true;
        }
        return serverWorld.setBlockState(pos, blockState);
    }
}
