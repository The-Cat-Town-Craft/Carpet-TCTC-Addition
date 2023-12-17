/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.commandFreecam;

import net.minecraft.network.Connection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.command.FreecamCommand;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;

import java.util.Optional;

//#if MC > 12001
import net.minecraft.server.network.CommonListenerCookie;
//#endif

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Shadow public abstract MinecraftServer getServer();

    @Inject(
            method = "placeNewPlayer",
            at = @At(
                    value = "INVOKE",
                    //#if MC > 12001
                    target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/network/Connection;Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/server/network/CommonListenerCookie;)V",
                    //#else
                    //$$ target = "Lnet/minecraft/server/network/ServerGamePacketListenerImpl;<init>(Lnet/minecraft/server/MinecraftServer;Lnet/minecraft/network/Connection;Lnet/minecraft/server/level/ServerPlayer;)V",
                    //#endif
                    shift = At.Shift.AFTER
            )
    )
    private void onPlaceNewPlayer(
            Connection connection,
            ServerPlayer serverPlayer,
            //#if MC > 12001
            CommonListenerCookie commonListenerCookie,
            //#endif
            CallbackInfo ci
    ) {
        if (CarpetTCTCAdditionSettings.freecamRestoreLocation &&
                this.getServer() instanceof DedicatedServer &&
                //#if MC > 11605
                this.getServer().getForcedGameType() != null
                //#else
                //$$ this.getServer().getForceGameType()
                //#endif
        ) {
            Optional.ofNullable(FreeCameraUtil.get(serverPlayer.getUUID()))
                    .ifPresent(data -> {
                        if (data.isFreecam) {
                            FreecamCommand.exitFreecam(serverPlayer, data, true);
                        }
                    });
        }
    }
}
