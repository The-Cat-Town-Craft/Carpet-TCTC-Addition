/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.misc.printCarpetVersion;

//#if MC > 11802
import carpet.api.settings.SettingsManager;
//#else
//$$ import carpet.settings.SettingsManager;
//#endif
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.hendrixshen.magiclib.api.rule.WrapperSettingManager;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
import top.hendrixshen.magiclib.util.MessageUtil;

@Mixin(value = SettingsManager.class, remap = false)
public abstract class MixinSettingsManager {
    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    //#if MC > 11802
                    target = "Lcarpet/api/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"
                    //#else
                    //$$ target = "Lcarpet/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"
                    //#endif
            )
    )
    private void printAdditionVersion(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        WrapperSettingManager sm = WrapperSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier());
        MessageUtil.sendMessage(source, ComponentCompatApi.literal(sm.trUI("version", sm.trFancyName(), sm.getVersion()))
                .withStyle(ChatFormatting.GRAY));
    }
}
