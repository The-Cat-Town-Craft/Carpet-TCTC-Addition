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
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.catowncraft.CarpetTCTCAddition.commands.FixCommand;
import top.catowncraft.CarpetTCTCAddition.commands.HereCommand;
import top.catowncraft.CarpetTCTCAddition.utils.CarpetTCTCAdditionTranslations;

import java.util.Map;

public class CarpetTCTCAddition implements CarpetExtension, ModInitializer {
    public static final Logger logger = LogManager.getLogger(Reference.MOD_ID);
    private static MinecraftServer minecraftServer;

    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher) {
        FixCommand.register(dispatcher);
        HereCommand.register(dispatcher);
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        minecraftServer = server;
    }

    @Override
    public String version() {
        return Reference.MOD_ID;
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return CarpetTCTCAdditionTranslations.getTranslationFromResourcePath(lang);
    }

    @Override
    public void onInitialize() {
        CarpetServer.manageExtension(new CarpetTCTCAddition());
    }

    public static MinecraftServer getServer() {
        return minecraftServer;
    }
}
