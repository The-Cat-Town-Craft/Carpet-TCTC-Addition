/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.voidFallBehavior;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundCustomSoundPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
//#if MC >= 11600
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
//#endif
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
//#if MC >= 11600
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
//#endif
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

import java.util.Optional;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    @Shadow
    public abstract void kill();

    public MixinLivingEntity(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(
            method = "outOfWorld",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onOutOfWorld(CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.voidDevouringBehavior == CarpetTCTCAdditionSettings.VoidDevouringBehaviorOptions.VANILLA) {
            return;
        }

        if ((Entity) this instanceof ServerPlayer) {
            if (CarpetTCTCAdditionSettings.voidDevouringBehavior == CarpetTCTCAdditionSettings.VoidDevouringBehaviorOptions.INSTANT_DEATH) {
                this.kill();
            }

            if (CarpetTCTCAdditionSettings.voidDevouringBehavior == CarpetTCTCAdditionSettings.VoidDevouringBehaviorOptions.TELEPORT_TO_SPAWN) {
                ServerPlayer serverPlayer = (ServerPlayer) (Entity) this;
                BlockPos pos = serverPlayer.getRespawnPosition();
                //#if MC >= 11600
                float respawnAngle = serverPlayer.getRespawnAngle();
                //#endif
                boolean isRespawnForced = serverPlayer.isRespawnForced();


                //#if MC >= 11600
                ServerLevel serverLevel = this.getServer().getLevel(serverPlayer.getRespawnDimension());
                Optional<Vec3> optional = serverLevel != null && pos != null ?
                        Player.findRespawnPositionAndUseSpawnBlock(serverLevel, pos, respawnAngle, isRespawnForced, false) :
                        Optional.empty();
                ServerLevel serverLevel2 = serverLevel != null && optional.isPresent() ? serverLevel : this.getServer().overworld();
                //#else
                //$$ ServerLevel serverLevel = this.getServer().getLevel(serverPlayer.dimension);
                //$$ Optional<Vec3> optional = Player.checkBedValidRespawnPosition(this.getServer().getLevel(serverPlayer.dimension), pos, false);
                //#endif

                if (optional.isPresent()) {
                    //#if MC >= 11600
                    BlockState blockState = serverLevel2.getBlockState(pos);
                    boolean isRespawnAnchor = blockState.is(Blocks.RESPAWN_ANCHOR);
                    Vec3 vec3 = optional.get();
                    float teleportAngle;
                    if (!blockState.is(BlockTags.BEDS) && !isRespawnAnchor) {
                        teleportAngle = respawnAngle;
                    } else {
                        Vec3 vec32 = Vec3.atBottomCenterOf(pos).subtract(vec3).normalize();
                        teleportAngle = (float) Mth.wrapDegrees(Mth.atan2(vec32.z, vec32.x) * 180.0F / (float)Math.PI - 90.0);
                    }

                    serverPlayer.teleportTo(serverLevel2, vec3.x(), vec3.y(), vec3.z(), teleportAngle, 0.0F);
                    //#else
                    //$$ serverPlayer.teleportTo(serverLevel, optional.get().x(), optional.get().y(), optional.get().z(), 0.0F, 0.0F);
                    //#endif
                } else {
                    //#if MC >= 11600
                    BlockPos sharedSpawnPos = serverLevel2.getSharedSpawnPos();
                    serverPlayer.teleportTo(serverLevel2, sharedSpawnPos.getX(), sharedSpawnPos.getY(), sharedSpawnPos.getZ(),
                            serverLevel2.getSharedSpawnAngle(), 0.0F);
                    //#else
                    //$$ BlockPos sharedSpawnPos = serverLevel.getSharedSpawnPos();
                    //$$ serverPlayer.teleportTo(serverLevel, sharedSpawnPos.getX(), sharedSpawnPos.getY(), sharedSpawnPos.getZ(),
                    //$$ 0.0F, 0.0F);
                    //#endif
                }
                serverPlayer.connection.send(new ClientboundCustomSoundPacket(
                        new ResourceLocation("item.totem.use"),
                        serverPlayer.getSoundSource(),
                        new Vec3(serverPlayer.getXCompat(), serverPlayer.getYCompat(), serverPlayer.getZCompat()),
                        //#if MC >= 11900
                        1.0F, 1.0F, serverPlayer.level.getRandom().nextLong()));
                        //#else
                        //$$ 1.0F, 1.0F));
                        //#endif
                serverPlayer.fallDistance = 0.0F;
            }
            ci.cancel();
        }
    }
}
