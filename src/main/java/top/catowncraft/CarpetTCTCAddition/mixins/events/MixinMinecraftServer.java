/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */

package top.catowncraft.CarpetTCTCAddition.mixins.events;

import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(
            method = "saveAllChunks",
            at = @At(
                    value = "HEAD"
            )
    )
    private void onTickingWorld(boolean bl, boolean bl2, boolean bl3, CallbackInfoReturnable<Boolean> cir) {
        CarpetTCTCAddition.getInstance().onLevelSave();
    }
}
