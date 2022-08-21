/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.catowncraft.carpettctcaddition.command.*;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingsManager;
import top.catowncraft.carpettctcaddition.util.CarpetTCTCAdditionTranslations;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;
import top.catowncraft.carpettctcaddition.util.WorldMapUtil;
import top.hendrixshen.magiclib.compat.carpet.CarpetExtensionCompatApi;
import top.hendrixshen.magiclib.compat.carpet.WrapperSettingsManager;
import top.hendrixshen.magiclib.dependency.annotation.Dependencies;
import top.hendrixshen.magiclib.dependency.annotation.Dependency;

import java.util.Map;

public class CarpetTCTCAddition implements ModInitializer, CarpetExtensionCompatApi {
    private static final Logger logger = LogManager.getLogger(CarpetTCTCAdditionReference.getModId());
    private static MinecraftServer minecraftServer;
    private static final CarpetTCTCAdditionSettingsManager settingsManager = new CarpetTCTCAdditionSettingsManager(
            CarpetTCTCAdditionReference.getModVersion(),
            CarpetTCTCAdditionReference.getModId(),
            CarpetTCTCAdditionReference.getCurrentModName());

    public static MinecraftServer getServer() {
        return minecraftServer;
    }

    public static Logger getLogger() {
        return logger;
    }

    @Dependencies(and = {
            //#if MC >= 11900
            //$$ @Dependency(value = "carpet", versionPredicate = ">=1.4.83"),
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
        CarpetServer.manageExtension(new CarpetTCTCAddition());
        ServerPlayNetworking.registerGlobalReceiver(new ResourceLocation("worldinfo", "world_id"), WorldMapUtil::voxelMapPacketHandler);
    }

    @Override
    public void onGameStarted() {
        CarpetTCTCAddition.settingsManager.parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        CarpetTCTCAddition.minecraftServer = server;
        // Load freecam data.
        FreeCameraUtil.load();
    }

    @Override
    public String version() {
        return CarpetTCTCAdditionReference.getModVersion();
    }


    @Override
    public WrapperSettingsManager getSettingsManagerCompat() {
        return CarpetTCTCAddition.settingsManager;
    }

    @Override
    public void registerCommandCompat(CommandDispatcher<CommandSourceStack> dispatcher) {
        FixCommand.register(dispatcher);
        FreecamCommand.register(dispatcher);
        GCCommand.register(dispatcher);
        HereCommand.register(dispatcher);
        OperatorCommand.register(dispatcher);
    }

    @Override
    public Map<String, String> canHasTranslationsCompat(String lang) {
        return CarpetTCTCAdditionTranslations.getTranslationFromResourcePath(lang);
    }
}
