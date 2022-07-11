/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import carpet.CarpetSettings;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.hendrixshen.magiclib.language.I18n;

public class StringUtil {
    public static String tr(String node, Object... objects) {
        //#if MC >= 11500
        return I18n.getByCode(!CarpetSettings.language.equals("none") ? CarpetSettings.language : "en_us", String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node), objects);
        //#else
        //$$ return I18n.get(String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node), objects);
        //#endif
    }

    public static String original(String node) {
        //#if MC >= 11500
        return I18n.getByCode(!CarpetSettings.language.equals("none") ? CarpetSettings.language : "en_us", String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node));
        //#else
        //$$ return I18n.get(String.format("%s.%s", CarpetTCTCAdditionReference.getModId(), node));
        //#endif
    }
}
