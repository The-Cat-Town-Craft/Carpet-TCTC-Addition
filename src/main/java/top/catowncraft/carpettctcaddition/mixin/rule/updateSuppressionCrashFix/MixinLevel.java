/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.updateSuppressionCrashFix;

//#if MC < 11900
//$$ import net.minecraft.ChatFormatting;
//$$ import net.minecraft.core.BlockPos;
//#endif
import net.minecraft.world.level.Level;
//#if MC < 11900
//$$ import net.minecraft.world.level.block.Block;
//$$ import net.minecraft.world.level.block.state.BlockState;
//#endif
import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 11900
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;
//#else
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$ import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
//$$ import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
//$$ import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
//$$ import top.catowncraft.carpettctcaddition.helper.UpdateSuppressionException;
//$$ import top.catowncraft.carpettctcaddition.util.StringUtil;
//$$ import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;
//$$ import top.hendrixshen.magiclib.util.MessageUtil;
//#endif

//#if MC >= 11900
@Dependencies(not = @Dependency(value = "minecraft"))
//#endif
@Mixin(Level.class)
public class MixinLevel {
    //#if MC <= 11900
    //$$ @Inject(
    //$$         method = "neighborChanged",
    //$$         at = @At(
    //$$                 value = "INVOKE",
    //$$                 target = "Lnet/minecraft/CrashReport;forThrowable(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/CrashReport;"
    //$$         ),
    //$$         locals = LocalCapture.CAPTURE_FAILHARD
    //$$ )
    //$$ private void onNeighborChanged(BlockPos blockPos, Block block, BlockPos blockPos2, CallbackInfo ci, BlockState blockState, Throwable throwable) {
    //$$     if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
    //$$         if (throwable instanceof StackOverflowError) {
    //$$             MessageUtil.sendServerMessage(CarpetTCTCAdditionExtension.getServer(), ComponentCompatApi.literal(StringUtil.tr("message.server.updateSuppression.processed")).withStyle(ChatFormatting.GRAY, ChatFormatting.ITALIC));
    //$$         }
    //$$         throw new UpdateSuppressionException("Update suppression");
    //$$     }
    //$$ }
    //#endif
}
