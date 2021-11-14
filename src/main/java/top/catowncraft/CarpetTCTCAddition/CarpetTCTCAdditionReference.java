/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition;

import net.fabricmc.loader.api.FabricLoader;

public class CarpetTCTCAdditionReference {
    private static final String MOD_ID = "carpet-tctc-addition";
    private static final String MOD_NAME = "Carpet TCTC Addition";
    private static final String MOD_VERSION = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    private static final String MOD_VERSION_TYPE = "Development";

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
