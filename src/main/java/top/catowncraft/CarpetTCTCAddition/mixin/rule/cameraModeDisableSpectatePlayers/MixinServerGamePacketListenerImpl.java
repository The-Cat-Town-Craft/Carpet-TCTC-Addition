/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.cameraModeDisableSpectatePlayers;

import net.minecraft.network.protocol.game.ServerboundTeleportToEntityPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.FreeCameraData;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;

@Mixin(ServerGamePacketListenerImpl.class)
public abstract class MixinServerGamePacketListenerImpl {
    @Shadow
    public ServerPlayer player;

    @Inject(
            method = "handleTeleportToEntityPacket",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onHandleTeleportToEntityPacket(ServerboundTeleportToEntityPacket serverboundTeleportToEntityPacket, CallbackInfo ci) {
        FreeCameraData cameraData = FreeCameraUtil.getCameraData().get(this.player.getUUID());
        if (cameraData != null && !cameraData.isFreecam) {
            return;
        }

        if (CarpetTCTCAdditionSettings.cameraModeDisableSpectatePlayers) {
            ci.cancel();
        }
    }
}
