/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.catowncraft.CarpetTCTCAddition.commands.*;
import top.catowncraft.CarpetTCTCAddition.utils.FreeCameraUtil;
import top.catowncraft.CarpetTCTCAddition.utils.WorldMapUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CarpetTCTCAddition implements CarpetExtension, ModInitializer {
    private static final Logger logger = LogManager.getLogger(CarpetTCTCAdditionReference.getModId());
    private static MinecraftServer minecraftServer;
    private static CarpetTCTCAddition instance;
    private Map<UUID, FreeCameraUtil.FreeCameraData> cameraData = new HashMap<>();

    public static MinecraftServer getServer() {
        return minecraftServer;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        FixCommand.register(dispatcher);
        FreecamCommand.register(dispatcher);
        GCCommand.register(dispatcher);
        HereCommand.register(dispatcher);
        OperatorCommand.register(dispatcher);
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraftServer = server;
        instance = this;
        // Load freecam data.
        try {
            this.cameraData = FreeCameraUtil.loadFreeCameraData();
        } catch (Exception e) {
            CarpetTCTCAddition.getLogger().error(e.getMessage());
        }
    }

    @Override
    public String version() {
        return CarpetTCTCAdditionReference.getModVersion();
    }

    @Override
    public void onInitialize() {
        // Register mod as carpet extension.
        CarpetServer.manageExtension(new CarpetTCTCAddition());

        // Register packet handler
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("worldinfo", "world_id"), WorldMapUtil::voxelMapPacketHandler);
    }

    public static CarpetTCTCAddition getInstance() {
        return instance;
    }

    public void onLevelSave() {
        try {
            FreeCameraUtil.saveFreeCameraData(this.cameraData);
        } catch (IOException e) {
            CarpetTCTCAddition.getLogger().error(e.getMessage());
        }
    }

    public Map<UUID, FreeCameraUtil.FreeCameraData> getCameraData() {
        return this.cameraData;
    }

}
