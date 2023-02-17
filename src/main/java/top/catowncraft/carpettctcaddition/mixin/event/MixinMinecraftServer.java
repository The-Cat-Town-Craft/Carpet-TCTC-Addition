/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.event;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(
            method = "saveAllChunks",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onSaveAllChunks(boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<Boolean> cir) {
        FreeCameraUtil.save();
    }
}
