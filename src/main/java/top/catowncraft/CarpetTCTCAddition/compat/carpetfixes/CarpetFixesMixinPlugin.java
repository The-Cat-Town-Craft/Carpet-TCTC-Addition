/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.tinyremapper.IMappingProvider.Member;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import top.catowncraft.carpettctcaddition.util.RemappingUtil;
import top.hendrixshen.magiclib.MagicMixinPlugin;

public class CarpetFixesMixinPlugin extends MagicMixinPlugin {
    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        super.preApply(targetClassName, targetClass, mixinClassName, mixinInfo);
        // Skip Carpet Fixes Redirect.
        if ("top.catowncraft.carpettctcaddition.compat.carpetfixes.mixin.rule.enderPlatform.MixinServerPlayer".equals(mixinClassName)) {
            // Super class is used.
            // Lnet/minecraft/world/entity/Entity;changeDimension(Lnet/minecraft/server/level/ServerLevel;)Lnet/minecraft/world/entity/Entity;
            Member mixinMethod = RemappingUtil.mapMethod("class_1297", "method_5731", "(Lnet/minecraft/class_3218;)Lnet/minecraft/class_1297;");
            // Lnet/minecraft/server/level/ServerPlayer;createEndPlatform(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;)V
            Member mixinTarget = RemappingUtil.mapMethod("class_3222", "method_30313", "(Lnet/minecraft/class_3218;Lnet/minecraft/class_2338;)V");
            this.createFake(targetClass, mixinMethod, mixinTarget);
        } else if ("top.catowncraft.carpettctcaddition.compat.carpetfixes.mixin.rule.updateSuppressionCrashFix.MixinMinecraftServer".equals(mixinClassName)) {
            // Lnet/minecraft/server/MinecraftServer;tickChildren(Ljava/util/function/BooleanSupplier;)V
            Member mixinMethod = RemappingUtil.mapMethod("MinecraftServer", "method_3813", "(Ljava/util/function/BooleanSupplier;)V");
            // Cannot remap correctly.
            mixinMethod.owner = "net/minecraft/server/MinecraftServer";
            if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
                mixinMethod.name = "tickChildren";
            }
            // Lnet/minecraft/server/level/ServerLevel;tick(Ljava/util/function/BooleanSupplier;)V
            Member mixinTarget = RemappingUtil.mapMethod("class_3218", "method_18765", "(Ljava/util/function/BooleanSupplier;)V");

            this.createFake(targetClass, mixinMethod, mixinTarget);
        }
    }

    private void createFake(ClassNode targetClass, Member mixinMethod, Member mixinTarget) {
        for (MethodNode method : targetClass.methods) {
            if (method.name.equals(mixinMethod.name) && method.desc.equals(mixinMethod.desc)) {
                LabelNode skip = new LabelNode();

                InsnList extra = new InsnList();
                extra.add(new JumpInsnNode(Opcodes.GOTO, skip));
                extra.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, mixinTarget.owner, mixinTarget.name, mixinTarget.desc, false));
                extra.add(skip);

                method.instructions.insertBefore(method.instructions.getLast(), extra);

                break;
            }
        }
    }
}
