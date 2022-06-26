/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
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

    @Inject(
            method = "awardStat(Lnet/minecraft/stats/Stat;)V",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onAwardStat(Stat<?> stat, CallbackInfo ci) {
        if (CarpetTCTCAdditionSettings.playerStats == CarpetTCTCAdditionSettings.PlayerStatsOptions.PLAYER && (LivingEntity) this instanceof EntityPlayerMPFake) {
            ci.cancel();
        }
        if (CarpetTCTCAdditionSettings.playerStats == CarpetTCTCAdditionSettings.PlayerStatsOptions.BOT && (LivingEntity) this instanceof ServerPlayer && !((LivingEntity) this instanceof EntityPlayerMPFake)) {
            ci.cancel();
        }
        if (CarpetTCTCAdditionSettings.playerStats == CarpetTCTCAdditionSettings.PlayerStatsOptions.NONE) {
            ci.cancel();
        }
    }
}
