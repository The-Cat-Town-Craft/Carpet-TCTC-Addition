package CarpetTCTCAddition.mixins.net.minecraft.block.entity;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(EndGatewayBlockEntity.class)
public class EndGatewayBlockEntityMixin {
    @Inject(
        method = "tryTeleportingEntity",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/entity/Entity;teleport(DDD)V",
            shift = At.Shift.AFTER
        )
    )
    private void tryAddTicket(Entity entity, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.endGateWayChunkLoader) {
            Objects.requireNonNull(Objects.requireNonNull(entity.getServer()).getWorld(entity.world.getRegistryKey())).getChunkManager().addTicket(ChunkTicketType.PORTAL, new ChunkPos(entity.chunkX, entity.chunkZ), 3, new BlockPos(entity.prevX, entity.prevY, entity.prevZ));
        }
    }
}
