package CarpetTCTCAddition.mixins.net.minecraft.entity;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow @Final private EntityType<?> type;

    @Redirect(
        method = "moveToWorld",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/server/world/ServerWorld;createEndSpawnPlatform(Lnet/minecraft/server/world/ServerWorld;)V"
        )
    )
    private void createEndSpawnPlatformCheck(ServerWorld world) {
        if (CarpetTCTCAdditionSettings.obsidianPlatform == CarpetTCTCAdditionSettings.CreatePlatformOptions.ALL) {
            ServerWorld.createEndSpawnPlatform(world);
        }
        /*
        if (CarpetTCTCAdditionSettings.obsidianPlatform == CarpetTCTCAdditionSettings.CreatePlatformOptions.PLAYER) {
            if (this.type == EntityType.PLAYER) {
                ServerWorld.createEndSpawnPlatform(world);
            }
        }*/
    }
}
