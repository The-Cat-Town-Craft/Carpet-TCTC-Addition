/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.blockIllegalUsername;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

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
            cir.setReturnValue(new TranslatableComponent("disconnect.loginFailedInfo", new TranslatableComponent("disconnect.loginFailedInfo.invalidSession")));
        }
    }
}
