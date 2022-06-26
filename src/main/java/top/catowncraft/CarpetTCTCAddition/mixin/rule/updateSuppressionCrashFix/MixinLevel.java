/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.updateSuppressionCrashFix;

//#if MC < 11900
//$$ import net.minecraft.core.BlockPos;
//#endif
import net.minecraft.world.level.Level;
//#if MC < 11900
//$$ import net.minecraft.world.level.block.Block;
//#endif
import org.spongepowered.asm.mixin.Mixin;
//#if MC >= 11900
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;
//#else
//$$ import org.spongepowered.asm.mixin.injection.At;
//$$ import org.spongepowered.asm.mixin.injection.Inject;
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//$$ import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;
//$$ import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
//$$ import top.catowncraft.carpettctcaddition.helper.UpdateSuppressionException;
//$$ import top.catowncraft.carpettctcaddition.util.MessageUtil;
//$$ import top.catowncraft.carpettctcaddition.util.StringUtil;
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
    //$$         )
    //$$ )
    //$$ private void onNeighborChanged(BlockPos blockPos, Block block, BlockPos blockPos2, CallbackInfo ci) {
    //$$     if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
    //$$         MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(), StringUtil.tr("message.server.updateSuppression.processed"));
    //$$         throw new UpdateSuppressionException("Update suppression");
    //$$     }
    //$$ }
    //#endif
}
