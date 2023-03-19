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
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.players.PlayerList;
import top.catowncraft.carpettctcaddition.command.*;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.FreeCameraUtil;
import top.catowncraft.carpettctcaddition.util.MiscUtil;
import top.hendrixshen.magiclib.carpet.api.CarpetExtensionCompatApi;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

import java.util.Optional;

//#if MC > 11902
import java.util.EnumSet;
//#endif

public class CarpetTCTCAdditionExtension implements CarpetExtensionCompatApi {
    @Getter
    private static final CarpetTCTCAdditionSettingManager settingsManager = new CarpetTCTCAdditionSettingManager(
            CarpetTCTCAdditionReference.getModVersion(),
            CarpetTCTCAdditionReference.getModIdentifier(),
            CarpetTCTCAdditionReference.getModNameCurrent());
    @Getter
    private static MinecraftServer server;

    @Override
    public void onGameStarted() {
        WrappedSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier()).parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server) {
        CarpetTCTCAdditionExtension.server = server;
        // Load freecam data.
        FreeCameraUtil.load();
        CarpetTCTCAdditionExtension.settingsManager.registerRuleCallback(((source, rule, value) -> {
            if (rule.getName().equals("botTabListNamePrefix") || rule.getName().equals("botTabListNameSuffix")) {
                Optional.ofNullable(CarpetTCTCAdditionExtension.server).ifPresent(minecraftServer -> {
                    PlayerList playerList = CarpetTCTCAdditionExtension.server.getPlayerList();
                    playerList.broadcastAll(new ClientboundPlayerInfoUpdatePacket(
                            //#if MC > 11902
                            EnumSet.of(ClientboundPlayerInfoUpdatePacket.Action.UPDATE_DISPLAY_NAME),
                            //#else
                            //$$ ClientboundPlayerInfoPacket.Action.UPDATE_DISPLAY_NAME,
                            //#endif
                            playerList.getPlayers().stream().filter(MiscUtil::isBotEntity).toList()));
                });
            }
        }));
    }

    @Override
    public String version() {
        return CarpetTCTCAdditionReference.getModVersion();
    }

    @Override
    public WrappedSettingManager getSettingsManagerCompat() {
        return WrappedSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier());
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
