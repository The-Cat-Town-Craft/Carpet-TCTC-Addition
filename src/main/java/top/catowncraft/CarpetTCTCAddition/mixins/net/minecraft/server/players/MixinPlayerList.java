/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.net.minecraft.server.players;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.server.players.ServerOpList;
import net.minecraft.server.players.ServerOpListEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.fakes.PlayerListInterface;
import top.catowncraft.CarpetTCTCAddition.utils.WorldMapUtil;

import java.util.UUID;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList implements PlayerListInterface {
    @Shadow
    @Final
    private ServerOpList ops;

    @Shadow
    public abstract boolean canBypassPlayerLimit(GameProfile gameProfile);

    @Shadow
    public abstract void sendPlayerPermissionLevel(ServerPlayer serverPlayer);

    @Shadow
    @Nullable
    public abstract ServerPlayer getPlayer(UUID uUID);

    @Override
    public void setPermissionLevel(GameProfile gameProfile, int level) {
        ops.add(new ServerOpListEntry(gameProfile, level, canBypassPlayerLimit(gameProfile)));
        ServerPlayer serverPlayer = getPlayer(gameProfile.getId());
        if (serverPlayer != null) {
            sendPlayerPermissionLevel(serverPlayer);
        }
    }

    @Override
    public void setBypassPlayerLimit(GameProfile gameProfile, boolean bypass) {
        ops.add(new ServerOpListEntry(gameProfile, CarpetTCTCAddition.getServer().getProfilePermissions(gameProfile), bypass));
    }

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
