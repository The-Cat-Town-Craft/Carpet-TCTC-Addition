/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.voidTradeFix;

//#if MC < 11700
//$$ import net.minecraft.server.level.ServerLevel;
//#endif
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

//#if MC >= 11700
@Mixin(targets = "net/minecraft/server/level/ServerLevel$EntityCallbacks")
//#else
//$$ @Mixin(ServerLevel.class)
//#endif
public class MixinServerLevel {
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
            //#if MC >= 11700
            method = "onTrackingEnd(Lnet/minecraft/world/entity/Entity;)V",
            //#else
            //$$ method = "onEntityRemoved",
            //#endif
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
