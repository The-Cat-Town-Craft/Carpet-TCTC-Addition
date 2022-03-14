/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.rule.enderPlatform;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(Entity.class)
public abstract class MixinEntity {
    @Redirect(
            method = "changeDimension",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerLevel;makeObsidianPlatform(Lnet/minecraft/server/level/ServerLevel;)V"
            )
    )
    private void makeObsidianPlatform(ServerLevel serverLevel) {
        if (CarpetTCTCAdditionSettings.enderPlatform == CarpetTCTCAdditionSettings.EnderPlatformOptions.ALL) {
            ServerLevel.makeObsidianPlatform(serverLevel);
        }
    }
}
