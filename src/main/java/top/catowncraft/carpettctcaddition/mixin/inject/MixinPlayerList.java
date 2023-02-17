/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.inject;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.server.players.ServerOpList;
import net.minecraft.server.players.ServerOpListEntry;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.mixininterface.PlayerListApi;

import java.util.UUID;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList implements PlayerListApi {
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
    public void tctc$setPermissionLevel(GameProfile gameProfile, int level) {
        ops.add(new ServerOpListEntry(gameProfile, level, canBypassPlayerLimit(gameProfile)));
        ServerPlayer serverPlayer = getPlayer(gameProfile.getId());
        if (serverPlayer != null) {
            sendPlayerPermissionLevel(serverPlayer);
        }
    }

    @Override
    public void tctc$setBypassPlayerLimit(GameProfile gameProfile, boolean bypass) {
        ops.add(new ServerOpListEntry(gameProfile, CarpetTCTCAdditionExtension.getServer().getProfilePermissions(gameProfile), bypass));
    }
}
