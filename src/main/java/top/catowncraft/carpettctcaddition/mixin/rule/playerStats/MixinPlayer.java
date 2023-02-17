/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.playerStats;

import carpet.patches.EntityPlayerMPFake;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stat;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity {
    protected MixinPlayer(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @SuppressWarnings("ConstantConditions")
    @Inject(
            method = "awardStat(Lnet/minecraft/stats/Stat;I)V",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onAwardStat(Stat<?> stat, int i, CallbackInfo ci) {
        if ((CarpetTCTCAdditionSettings.playerStats.disableBot && (LivingEntity) this instanceof EntityPlayerMPFake) ||
                (CarpetTCTCAdditionSettings.playerStats.disablePlayer && (LivingEntity) this instanceof ServerPlayer)) {
            ci.cancel();
        }
    }
}
