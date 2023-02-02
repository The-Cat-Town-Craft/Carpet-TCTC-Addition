/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import top.catowncraft.carpettctcaddition.util.WorldMapUtil;
import top.hendrixshen.magiclib.api.rule.WrapperSettingManager;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

public class CarpetTCTCAddition implements ModInitializer {
    @Dependencies(and = {
            //#if MC >= 11903
            @Dependency(value = "carpet", versionPredicate = ">=1.4.91"),
            //#elseif MC >= 11800
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.4.69"),
            //#elseif MC >= 11700
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.4.57"),
            //#elseif MC >= 11600
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.4.44"),
            //#elseif MC >= 11500
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.4.8"),
            //#else
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.3.7"),
            //#endif
            @Dependency(value = "fabric")})
    @Override
    public void onInitialize() {
        // Register mod as carpet extension.
        WrapperSettingManager.register(CarpetTCTCAdditionReference.getModIdentifier(), CarpetTCTCAdditionExtension.getSettingsManager());
        CarpetServer.manageExtension(new CarpetTCTCAdditionExtension());
        // Register packet handler.
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("worldinfo", "world_id"), WorldMapUtil::voxelMapPacketHandler);
    }
}
