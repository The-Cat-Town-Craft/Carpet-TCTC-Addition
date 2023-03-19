/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.resources.ResourceLocation;
import top.catowncraft.carpettctcaddition.util.WorldMapUtil;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class CarpetTCTCAddition implements ModInitializer {
    @Override
    public void onInitialize() {
        // Register mod as carpet extension.
        WrappedSettingManager.register(
                CarpetTCTCAdditionReference.getModIdentifier(),
                CarpetTCTCAdditionExtension.getSettingsManager(),
                new CarpetTCTCAdditionExtension());
        // Register packet handler.
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("worldinfo", "world_id"), WorldMapUtil::voxelMapPacketHandler);
    }
}
