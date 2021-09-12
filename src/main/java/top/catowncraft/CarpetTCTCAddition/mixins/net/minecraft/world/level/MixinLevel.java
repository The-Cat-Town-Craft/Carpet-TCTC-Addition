/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.net.minecraft.world.level;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;
import top.catowncraft.CarpetTCTCAddition.utils.ThrowableSuppression;

@Mixin(Level.class)
public class MixinLevel {
    @Inject(
            method = "neighborChanged",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/CrashReport;forThrowable(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/CrashReport;",
                    shift = At.Shift.BEFORE
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void OnPopulateBlockDetails(BlockPos blockPos, Block block, BlockPos blockPos2, CallbackInfo ci, BlockState state, Throwable throwable) {
        if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix && (throwable.getCause() instanceof StackOverflowError || throwable.getCause() instanceof ThrowableSuppression)) {
            if (throwable.getCause() instanceof StackOverflowError) {
                MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(), (BaseComponent) new TextComponent("Update suppression.").withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
                throw new ThrowableSuppression("updateSuppression");
            }
        }
    }
}
