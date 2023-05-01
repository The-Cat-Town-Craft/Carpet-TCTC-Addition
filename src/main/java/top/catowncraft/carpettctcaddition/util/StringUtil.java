/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.hendrixshen.magiclib.carpet.impl.WrappedSettingManager;

public class StringUtil extends top.hendrixshen.magiclib.util.StringUtil {
    public static String tr(String node) {
        WrappedSettingManager settingManager = WrappedSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier());
        return settingManager.tr(settingManager.getCurrentLanguageCode(),
                String.format("%s.%s", CarpetTCTCAdditionReference.getModIdentifier(), node));
    }

    public static String tr(String node, Object... objects) {
        WrappedSettingManager settingManager = WrappedSettingManager.get(CarpetTCTCAdditionReference.getModIdentifier());
        return settingManager.tr(settingManager.getCurrentLanguageCode(),
                String.format("%s.%s", CarpetTCTCAdditionReference.getModIdentifier(), node), objects);
    }
}
