/*
 * Copyright (c) 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.carpet.settings;

import carpet.settings.SettingsManager;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.CarpetTCTCAddition.Reference;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;

@Mixin(SettingsManager.class)
public class MixinSettingsManager {
    @Inject(
            method = "listAllSettings",
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = "stringValue= version: ",
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/commands/CommandSourceStack;getPlayerOrException()Lnet/minecraft/server/level/ServerPlayer;",
                    ordinal = 0
            ),
            remap = false
    )
    private void printAdditionVersion(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        MessageUtil.sendMessage(source, Reference.VERSION_MESSAGE);
    }
}
