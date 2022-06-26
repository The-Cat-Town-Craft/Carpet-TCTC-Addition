/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.misc.printCarpetVersion;

import carpet.CarpetSettings;
import carpet.settings.ParsedRule;
import carpet.settings.SettingsManager;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
//#if MC < 11600
//$$ import net.minecraft.network.chat.BaseComponent;
//#endif
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.HoverEvent;
//#if MC >= 11600
import net.minecraft.network.chat.MutableComponent;
//#endif
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingsManager;
import top.catowncraft.carpettctcaddition.util.MessageUtil;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;

import java.util.Collection;

@Mixin(value = SettingsManager.class, remap = false)
public abstract class MixinSettingsManager {
    @Shadow
    protected abstract int listSettings(CommandSourceStack source, String title, Collection<ParsedRule<?>> settings_list);

    @Shadow
    @Final
    private String fancyName;

    @Shadow
    public abstract Collection<ParsedRule<?>> getNonDefault();

    @Shadow
    @Final
    private String version;

    @Shadow
    public abstract Iterable<String> getCategories();

    @Shadow
    @Final
    private String identifier;

    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void controlTCTCSettingsManager(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        // Only overwrite CarpetTCTCAddition SettingsManager
        if (!((Object) this instanceof CarpetTCTCAdditionSettingsManager)) {
            return;
        }

        int count = this.listSettings(source, StringUtil.tr("message.command.carpettctcaddition.header", this.fancyName), this.getNonDefault());

        if (this.version != null) {
            MessageUtil.sendMessage(source, ComponentCompatApi.literal(StringUtil.tr(
                    "message.command.carpettctcaddition.version",
                    this.fancyName,
                    this.version,
                    StringUtil.tr(String.format("label.versionType.%s", CarpetTCTCAdditionReference.getModVersionType()))))
                    .withStyle(ChatFormatting.BOLD));
        }

        MessageUtil.sendMessage(source, StringUtil.tr("message.command.carpettctcaddition.categories"));

        //#if MC >= 11600
        MutableComponent component = ComponentCompatApi.literal("");
        //#else
        //$$ BaseComponent component = ComponentCompatApi.literal("");
        //#endif
        for (String category: this.getCategories()) {
            //#if MC >= 11500
            String translatedCategory = !CarpetSettings.language.equals("none") ?
                    String.format("%s (%s)", StringUtil.tr(String.format("label.categories.%s", category)), category) : category;
            //#endif

            component.append(ComponentCompatApi.literal(String.format("[%s]", StringUtil.tr(String.format("label.categories.%s", category))))
                    .withStyle(ChatFormatting.AQUA)
                    .withStyle(style -> style
                            .withHoverEvent(new HoverEvent(
                                    HoverEvent.Action.SHOW_TEXT,
                                    ComponentCompatApi.literal(
                                            //#if MC >= 11500
                                            StringUtil.tr("message.command.carpettctcaddition.tip", translatedCategory))))
                                            //#else
                                            //$$ StringUtil.tr("message.command.carpettctcaddition.tip", category))))
                                            //#endif
                            .withClickEvent(new ClickEvent(
                                    ClickEvent.Action.RUN_COMMAND,
                                    String.format("/%s list %s", this.identifier, category)))));
            component.append(" ");
        }

        MessageUtil.sendMessage(source, component);
        cir.setReturnValue(count);
    }

    @Inject(
            method = "listAllSettings",
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/settings/SettingsManager;getCategories()Ljava/lang/Iterable;"
            )
    )
    private void printAdditionVersion(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        MessageUtil.sendMessage(source, ComponentCompatApi.literal(StringUtil.tr(
                "message.command.carpet.version",
                        CarpetTCTCAdditionReference.getModName(),
                        CarpetTCTCAdditionReference.getModVersion(),
                        StringUtil.tr(String.format("label.versionType.%s", CarpetTCTCAdditionReference.getModVersionType()))))
                .withStyle(ChatFormatting.GRAY));
    }
}
