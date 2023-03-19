/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.blockIllegalUsername;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.hendrixshen.magiclib.compat.minecraft.api.network.chat.ComponentCompatApi;

import java.net.SocketAddress;

@Mixin(PlayerList.class)
public class MixinPlayerList {
    @Inject(
            method = "canPlayerLogin",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onCheckCanPlayerLogin(SocketAddress socketAddress, GameProfile gameProfile, CallbackInfoReturnable<Component> cir) {
        if (CarpetTCTCAdditionSettings.blockIllegalUsername && (gameProfile.getName().matches("[^a-zA-Z0-9_]+$") || gameProfile.getName().length() > 16)) {
            cir.setReturnValue(ComponentCompatApi.translatable("disconnect.loginFailedInfo", ComponentCompatApi.translatable("disconnect.loginFailedInfo.invalidSession")));
        }
    }
}
