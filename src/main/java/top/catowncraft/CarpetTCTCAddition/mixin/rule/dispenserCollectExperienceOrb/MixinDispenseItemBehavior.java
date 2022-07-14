/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.dispenserCollectExperienceOrb;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
//#if MC < 11500
//$$ import net.minecraft.core.dispenser.DispenseItemBehavior;
//#endif
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
//#if MC < 11500
//$$ import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
//#else
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
//#endif
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;

import java.util.List;

//#if MC >= 11600
@Mixin(targets = "net.minecraft.core.dispenser.DispenseItemBehavior$24")
//#elseif MC >= 11500
//$$ @Mixin(targets = "net.minecraft.core.dispenser.DispenseItemBehavior$21")
//#else
//$$ @Mixin(DispenseItemBehavior.class)
//#endif
public class MixinDispenseItemBehavior {
    //#if MC >= 11500
    @Inject(
            method = "execute",
            at = @At(
                    value = "HEAD"
            ),
            cancellable = true
    )
    private void onExecute(BlockSource blockSource, ItemStack itemStack, CallbackInfoReturnable<ItemStack> cir) {
        if (CarpetTCTCAdditionSettings.dispenserCollectExperience) {
            BlockPos blockPos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
            List<ServerPlayer> list = blockSource.getLevel().getEntitiesOfClass(ServerPlayer.class, new AABB(blockPos), EntitySelector.NO_SPECTATORS);
            if (list.isEmpty()) {
                return;
            }
            ServerPlayer serverPlayer = list.get(0);
            System.out.println(serverPlayer.experienceLevel);
            if (serverPlayer.experienceLevel < 2) {
                return;
            }
            itemStack.shrink(1);
            serverPlayer.giveExperiencePoints(-11);
            ItemStack experienceBottle = new ItemStack(Items.EXPERIENCE_BOTTLE);
            new DefaultDispenseItemBehavior().dispense(blockSource, experienceBottle);
            cir.setReturnValue(itemStack);
        }
    }
    //#else
    //$$ @Inject(
    //$$         method = "bootStrap",
    //$$         at = @At(
    //$$                 value = "TAIL"
    //$$         )
    //$$ )
    //$$ private static void onBootStrap(CallbackInfo ci) {
    //$$     DispenserBlock.registerBehavior(Items.GLASS_BOTTLE,
    //$$             new DefaultDispenseItemBehavior() {
    //$$                 @Override
    //$$                 protected ItemStack execute(BlockSource blockSource, ItemStack itemStack) {
    //$$                     if (CarpetTCTCAdditionSettings.dispenserCollectExperienceOrb) {
    //$$                         BlockPos blockPos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
    //$$                         List<ServerPlayer> list = blockSource.getLevel().getEntitiesOfClass(ServerPlayer.class, new AABB(blockPos), EntitySelector.NO_SPECTATORS);
    //$$                         if (list.isEmpty()) {
    //$$                             return itemStack;
    //$$                         }
    //$$                         ServerPlayer serverPlayer = list.get(0);
    //$$                         if (serverPlayer.experienceLevel < 2) {
    //$$                             return itemStack;
    //$$                         }
    //$$                         itemStack.shrink(1);
    //$$                         serverPlayer.giveExperiencePoints(-11);
    //$$                         ItemStack experienceBottle = new ItemStack(Items.EXPERIENCE_BOTTLE);
    //$$                         new DefaultDispenseItemBehavior().dispense(blockSource, experienceBottle);
    //$$                         return itemStack;
    //$$                     }
    //$$                     return itemStack;
    //$$                 }
    //$$             }
    //$$     );
    //$$ }
    //#endif
}
