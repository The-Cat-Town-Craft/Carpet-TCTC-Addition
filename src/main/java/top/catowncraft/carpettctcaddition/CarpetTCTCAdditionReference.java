/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import net.fabricmc.loader.api.FabricLoader;
import top.hendrixshen.magiclib.util.VersionParser;

public class CarpetTCTCAdditionReference {
    private static final String MOD_ID = "carpet-tctc-addition";

    //#if MC > 11802
    private static final String CURRENT_MOD_ID = String.format("%s-1_19_3", MOD_ID);
    //#elseif MC > 11701
    //$$ private static final String CURRENT_MOD_ID = String.format("%s-1_18_2", MOD_ID);
    //#elseif MC > 11605
    //$$ private static final String CURRENT_MOD_ID = String.format("%s-1_17_1", MOD_ID);
    //#elseif MC > 11502
    //$$ private static final String CURRENT_MOD_ID = String.format("%s-1_16_5", MOD_ID);
    //#elseif MC > 11404
    //$$ private static final String CURRENT_MOD_ID = String.format("%s-1_15_2", MOD_ID);
    //#else
    //$$ private static final String CURRENT_MOD_ID = String.format("%s-1_14_4", MOD_ID);
    //#endif
    private static final String CURRENT_MOD_NAME = FabricLoader.getInstance().getModContainer(CURRENT_MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getName();
    private static final String MOD_NAME = "Carpet TCTC Addition";
    private static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(CURRENT_MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    private static final String MOD_VERSION_TYPE = VersionParser.getVersionType(MOD_VERSION);

    public static String getCurrentModId() {
        return CURRENT_MOD_ID;
    }

    public static String getCurrentModName() {
        return CURRENT_MOD_NAME;
    }

    public static String getModId() {
        return MOD_ID;
    }

    public static String getModName() {
        return MOD_NAME;
    }

    public static String getModVersion() {
        return MOD_VERSION;
    }

    public static String getModVersionType() {
        return MOD_VERSION_TYPE;
    }
}
