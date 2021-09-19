/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.mixins.net.minecraft.world.entity.projectile;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;

@Mixin(FireworkRocketEntity.class)
public class MixinFireworkRocketEntity {
    @Shadow private LivingEntity attachedToEntity;

    @ModifyArgs(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;",
                    ordinal = 0
            )
    )
    private void modifiedFireworkRocketSpeed(Args args) {
        Vec3 angle = attachedToEntity.getLookAngle();
        Vec3 movement = attachedToEntity.getDeltaMovement();
        double fireworkSpeedupCoefficient = CarpetTCTCAdditionSettings.fireworkSpeedupCoefficient;
        args.set(0, angle.x * 0.1D + (angle.x * fireworkSpeedupCoefficient - movement.x) * 0.5D);
        args.set(1, angle.y * 0.1D + (angle.y * fireworkSpeedupCoefficient - movement.y) * 0.5D);
        args.set(2, angle.z * 0.1D + (angle.z * fireworkSpeedupCoefficient - movement.z) * 0.5D);
    }
}
