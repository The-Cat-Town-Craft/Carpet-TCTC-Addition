/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat;

import com.google.common.collect.Lists;
import org.objectweb.asm.tree.AnnotationNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import org.spongepowered.asm.mixin.transformer.meta.MixinMerged;
import org.spongepowered.asm.util.Annotations;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionMixinPlugin;
import top.catowncraft.carpettctcaddition.util.mixin.MixinType;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicInterruption;
import top.catowncraft.carpettctcaddition.util.mixin.annotation.MagicAttack;

import java.util.ArrayList;
import java.util.List;

public class CompatMixinPlugin extends CarpetTCTCAdditionMixinPlugin {
    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {
        super.postApply(targetClassName, targetClass, mixinClassName, mixinInfo);

        // Magical chanting.
        ClassNode mixinClass = mixinInfo.getClassNode(0);
        AnnotationNode magicInterruption = Annotations.getVisible(mixinClass, MagicInterruption.class);

        if (magicInterruption == null) {
            return;
        }

        for (MethodNode methodNode : mixinClass.methods) {
            AnnotationNode magicAttack = Annotations.getVisible(methodNode, MagicAttack.class);

            if (magicAttack == null) {
                continue;
            }

            this.jikuTsuiho(targetClass, methodNode, magicInterruption, magicAttack);
        }
    }

    /**
     * 時空追放.
     * <p>Erase target and infuse soul.
     * @param targetClass Injection target class.
     * @param magicAttackMethodNode Spell casting MethodNode.
     * @param magicInterruption Spell casting interruption AnnotationNode.
     * @param magicAttack Magical attacking AnnotationNode.
     */
    private void jikuTsuiho(ClassNode targetClass, MethodNode magicAttackMethodNode, AnnotationNode magicInterruption, AnnotationNode magicAttack) {
        List<Class<?>> tekitaiClass = Annotations.getValue(magicInterruption, "value", true);
        List<String> tekitaiTarget = Annotations.getValue(magicInterruption, "targets", true);

        ArrayList<String> tekitaiClasses = Lists.newArrayList();
        for (Class<?> cls : tekitaiClass) {
            String s = cls.getName();
            if (!tekitaiClasses.contains(s)) {
                tekitaiClasses.add(s);
            }
        }
        for (String s : tekitaiTarget) {
            if (!tekitaiClasses.contains(s)) {
                tekitaiClasses.add(s);
            }
        }

        MethodNode tekitaiMethod = this.findTekitaiMethod(targetClass, magicAttack, tekitaiClasses);

        if (tekitaiMethod == null) {
            return;
        }

        // Magic releasing.
        targetClass.methods.remove(tekitaiMethod);

        tekitaiMethod.instructions.clear();
        tekitaiMethod.instructions.add(magicAttackMethodNode.instructions);
        tekitaiMethod.localVariables.clear();
        tekitaiMethod.localVariables.addAll(magicAttackMethodNode.localVariables);
        tekitaiMethod.tryCatchBlocks.clear();
        tekitaiMethod.tryCatchBlocks.addAll(magicAttackMethodNode.tryCatchBlocks);

        // Magic Harvesting.
        targetClass.visibleAnnotations.removeIf(annotationNode -> annotationNode.desc.equals("Ltop/catowncraft/carpettctcaddition/util/mixin/annotation/MagicInterruption;"));
        targetClass.methods.removeIf(methodNode -> methodNode.name.contentEquals(magicAttackMethodNode.name) && methodNode.desc.contentEquals(magicAttackMethodNode.desc));
    }

    /**
     * Find the enemy's MethodNode.
     * @param targetClass Injection target class.
     * @param magicAttack Spell casting MethodNode.
     * @param tekitaiTarget Enemy's class.
     * @return Enemy's MethodNode.
     */

    private MethodNode findTekitaiMethod(ClassNode targetClass, AnnotationNode magicAttack, ArrayList<String> tekitaiTarget) {
        List<MixinType> type = Annotations.getValue(magicAttack, "type", true, MixinType.class);
        String name = Annotations.getValue(magicAttack, "name");
        int priority = Annotations.getValue(magicAttack, "priority", 1000);
        for (MethodNode methodNode : targetClass.methods) {
            if (!(methodNode.name.contains(type.get(0).getPrefix()) && methodNode.name.contains(name))) {
                continue;
            }

            AnnotationNode annotationNode = Annotations.getVisible(methodNode, MixinMerged.class);
            String mixin = Annotations.getValue(annotationNode, "mixin");

            if (!tekitaiTarget.contains(mixin)) {
                continue;
            }

            int targetPriority = Annotations.getValue(annotationNode, "priority");
            if (priority != targetPriority) {
                continue;
            }

            return methodNode;
        }
        return null;
    }
}
