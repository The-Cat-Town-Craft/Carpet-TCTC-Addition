/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.hendrixshen.magiclib.api.rule.WrapperSettingManager;

public class StringUtil {
    public static String tr(String node, Object... objects) {
        WrapperSettingManager settingManager = WrapperSettingManager.get(CarpetTCTCAdditionReference.getModId());
        return settingManager.tr(settingManager.getCurrentLanguageCode(),
                String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node), objects);
    }

    public static String original(String node) {
        WrapperSettingManager settingManager = WrapperSettingManager.get(CarpetTCTCAdditionReference.getModId());
        return settingManager.tr(settingManager.getCurrentLanguageCode(),
                String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node));
    }
}
