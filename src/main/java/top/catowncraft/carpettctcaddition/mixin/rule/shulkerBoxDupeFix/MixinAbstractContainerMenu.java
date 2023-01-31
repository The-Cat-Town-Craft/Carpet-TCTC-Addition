/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.shulkerBoxDupeFix;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
//#if MC < 11700
//$$ import net.minecraft.world.item.ItemStack;
//#endif
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
//#if MC >= 11700
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//#else
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#endif
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

@Mixin(AbstractContainerMenu.class)
public abstract class MixinAbstractContainerMenu {
    @Shadow
    public abstract boolean stillValid(Player var1);

    @Shadow
    public abstract void removed(Player player);

    @Inject(
            method = "clicked",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    //#if MC >= 11700
    private void onClick(int i, int j, ClickType clickType, Player player, CallbackInfo ci) {
    //#else
    //$$ private void onClick(int i, int j, ClickType clickType, Player player, CallbackInfoReturnable<ItemStack> ci) {
    //#endif
        if (CarpetTCTCAdditionSettings.shulkerBoxDupeFix && !stillValid(player)) {
            this.removed(player); // Maybe useless, but still works.
            ci.cancel();
        }
    }
}
