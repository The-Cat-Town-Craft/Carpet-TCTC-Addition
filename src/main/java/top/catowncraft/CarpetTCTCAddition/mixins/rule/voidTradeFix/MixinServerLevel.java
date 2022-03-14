/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.voidTradeFix;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(ServerLevel.class)
public class MixinServerLevel {
    @Inject(
            method = "onEntityRemoved",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onEntityRemoved(Entity entity, CallbackInfo ci) {
        if (entity instanceof AbstractVillager) {
            Player player = ((AbstractVillager) entity).getTradingPlayer();
            if (CarpetTCTCAdditionSettings.voidTradeFix && player != null) {
                player.containerMenu.removed(player);
            }
        }
    }
}
