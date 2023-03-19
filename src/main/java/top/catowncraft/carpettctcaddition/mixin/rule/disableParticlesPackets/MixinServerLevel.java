/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.disableParticlesPackets;

import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

@Mixin(ServerLevel.class)
public class MixinServerLevel {
    @Inject(
            method = "sendParticles(Lnet/minecraft/server/level/ServerPlayer;ZDDDLnet/minecraft/network/protocol/Packet;)Z",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onSendParticles(ServerPlayer serverPlayer, boolean bl, double d, double e, double f, Packet<?> packet, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetTCTCAdditionSettings.disableParticlesPackets) {
            cir.setReturnValue(false);
        }
    }
}
