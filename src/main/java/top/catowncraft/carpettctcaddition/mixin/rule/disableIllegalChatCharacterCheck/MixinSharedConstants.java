/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.disableIllegalChatCharacterCheck;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

@Mixin(SharedConstants.class)
public class MixinSharedConstants {
    @Inject(
            method = "isAllowedChatCharacter",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private static void onIllegalChatCharacterCheck(char c, CallbackInfoReturnable<Boolean> cir) {
        if (CarpetTCTCAdditionSettings.disableIllegalChatCharacterCheck) {
            cir.setReturnValue(true);
        }
    }
}
