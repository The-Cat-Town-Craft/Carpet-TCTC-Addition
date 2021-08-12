/*
 * Copyright (c) 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;
import top.catowncraft.CarpetTCTCAddition.utils.CarpetTCTCAdditionTranslations;

import java.util.Map;

public class CarpetTCTCAddition implements CarpetExtension, ModInitializer {
    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetTCTCAdditionSettings.class);
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
}
