/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.xaeroMapWorldName;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.util.WorldMapUtil;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Inject(
            method = "sendLevelInfo",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onSendLevelInfo(ServerPlayer serverPlayer, ServerLevel serverLevel, CallbackInfo ci) {
        WorldMapUtil.xaeroMapPacketHandler(serverPlayer);
    }
}
