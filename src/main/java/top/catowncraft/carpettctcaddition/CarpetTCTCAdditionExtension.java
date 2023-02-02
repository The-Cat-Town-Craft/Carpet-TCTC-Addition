/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import com.mojang.brigadier.CommandDispatcher;
import lombok.Getter;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import top.catowncraft.carpettctcaddition.command.*;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;
import top.hendrixshen.magiclib.api.rule.CarpetExtensionCompatApi;
import top.hendrixshen.magiclib.api.rule.WrapperSettingManager;

public class CarpetTCTCAdditionExtension implements CarpetExtensionCompatApi {
    @Getter
    private static final CarpetTCTCAdditionSettingManager settingsManager = new CarpetTCTCAdditionSettingManager(
            CarpetTCTCAdditionReference.getModVersion(),
            CarpetTCTCAdditionReference.getModIdentifier(),
            CarpetTCTCAdditionReference.getCurrentModName());
    @Getter
    private static MinecraftServer server;

    @Override
    public void onGameStarted() {
        WrapperSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier()).parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        CarpetTCTCAdditionExtension.server = server;
        // Load freecam data.
        FreeCameraUtil.load();
    }

    @Override
    public String version() {
        return CarpetTCTCAdditionReference.getModVersion();
    }

    @Override
    public WrapperSettingManager getSettingsManagerCompat() {
        return WrapperSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier());
    }

    @Override
    public void registerCommandCompat(CommandDispatcher<CommandSourceStack> dispatcher) {
        FixCommand.register(dispatcher);
        FreecamCommand.register(dispatcher);
        GCCommand.register(dispatcher);
        HereCommand.register(dispatcher);
        OperatorCommand.register(dispatcher);
    }
}
