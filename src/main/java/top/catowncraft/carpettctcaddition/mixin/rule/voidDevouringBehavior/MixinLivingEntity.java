/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.voidDevouringBehavior;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

import java.util.Optional;

//#if MC > 11802
//#if MC > 11902
import net.minecraft.core.Holder;
//#endif
import net.minecraft.network.protocol.game.ClientboundSoundPacket;
//#else
//$$ import net.minecraft.network.protocol.game.ClientboundCustomSoundPacket;
//#endif

//#if MC > 11502
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
//#endif

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity extends Entity {
    @Shadow
    public abstract void kill();

    @Shadow
    protected abstract boolean checkTotemDeathProtection(DamageSource damageSource);

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
        CarpetTCTCAdditionSettings.VoidDevouringBehaviorOptions behavior = CarpetTCTCAdditionSettings.voidDevouringBehavior;

        if (behavior == CarpetTCTCAdditionSettings.VoidDevouringBehaviorOptions.VANILLA) {
            return;
        }

        if ((Entity) this instanceof ServerPlayer) {
            if (behavior.teleportToSpawn) {
                if (!behavior.totemBypass) {
                    this.cta$teleportToSpawn();
                    ci.cancel();
                    return;
                //#if MC > 11903
                } else if (this.checkTotemDeathProtection(this.damageSources().fall())) {
                //#else
                //$$ } else if (this.checkTotemDeathProtection(DamageSource.FALL)) {
                //#endif
                    this.cta$teleportToSpawn();
                    ci.cancel();
                    return;
                }
            }

            if (behavior.immediateDeath) {
                this.kill();
                ci.cancel();
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void cta$teleportToSpawn() {
        ServerPlayer serverPlayer = (ServerPlayer) (Entity) this;
        BlockPos pos = serverPlayer.getRespawnPosition();
        //#if MC > 11502
        float respawnAngle = serverPlayer.getRespawnAngle();
        //#endif
        boolean isRespawnForced = serverPlayer.isRespawnForced();

        //#if MC > 11502
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
            //#if MC > 11502
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
            //#if MC > 11502
            BlockPos sharedSpawnPos = serverLevel2.getSharedSpawnPos();
            serverPlayer.teleportTo(serverLevel2, sharedSpawnPos.getX(), sharedSpawnPos.getY(), sharedSpawnPos.getZ(),
                    serverLevel2.getSharedSpawnAngle(), 0.0F);
            //#else
            //$$ BlockPos sharedSpawnPos = serverLevel.getSharedSpawnPos();
            //$$ serverPlayer.teleportTo(serverLevel, sharedSpawnPos.getX(), sharedSpawnPos.getY(), sharedSpawnPos.getZ(),
            //$$ 0.0F, 0.0F);
            //#endif
        }
        //#if MC > 11802
        serverPlayer.connection.send(new ClientboundSoundPacket(
                //#if MC > 11902
                Holder.direct(SoundEvent.createVariableRangeEvent(new ResourceLocation("entity.enderman.teleport"))),
                //#else
                //$$ new SoundEvent (new ResourceLocation("entity.enderman.teleport")),
                //#endif
                serverPlayer.getSoundSource(),
                serverPlayer.getXCompat(), serverPlayer.getYCompat(), serverPlayer.getZCompat(),
                1.0F, 1.0F, serverPlayer.level.getRandom().nextLong()

        ));
        //#else
        //$$ serverPlayer.connection.send(new ClientboundCustomSoundPacket(
        //$$         new ResourceLocation("entity.enderman.teleport"),
        //$$         serverPlayer.getSoundSource(),
        //$$         new Vec3(serverPlayer.getXCompat(), serverPlayer.getYCompat(), serverPlayer.getZCompat()),
        //$$         1.0F, 1.0F));
        //#endif
        serverPlayer.fallDistance = 0.0F;
    }
}
