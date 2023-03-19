/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.updateSuppressionCrashFix;

import org.spongepowered.asm.mixin.Mixin;

//#if MC > 11802
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.redstone.NeighborUpdater;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.helper.UpdateSuppressionException;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.util.MessageUtil;
//#else
//$$ import top.hendrixshen.magiclib.compat.preprocess.api.DummyClass;
//#endif

//#if MC > 11802
@Mixin(NeighborUpdater.class)
//#else
//$$ @Mixin(DummyClass.class)
//#endif
public interface MixinNeighborUpdater {
    //#if MC > 11802
    @Inject(
            method = "executeUpdate",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/CrashReport;forThrowable(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/CrashReport;"
            )
    )
    private static void onExecuteUpdate(Level level, BlockState blockState, BlockPos blockPos, Block block, BlockPos blockPos2, boolean bl, CallbackInfo ci) {
         if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix) {
             MessageUtil.sendServerMessage(CarpetTCTCAdditionExtension.getServer(), StringUtil.tr("message.server.updateSuppression.processed"));
             throw new UpdateSuppressionException("Update suppression");
         }
    }
    //#endif
}
