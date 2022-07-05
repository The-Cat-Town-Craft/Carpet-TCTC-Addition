/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

//#if MC >= 11600
import net.minecraft.world.level.storage.LevelResource;
//#endif
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;

import java.io.File;
import java.nio.file.Path;

public class FileUtil {
    public static Path getLevelRoot() {
        //#if MC >= 11600
        return CarpetTCTCAddition.getServer().getWorldPath(LevelResource.ROOT);
        //#else
        //$$ return CarpetTCTCAddition.getServer().getStorageSource().getBaseDir().resolve(CarpetTCTCAddition.getServer().getLevelIdName());
        //#endif
    }

    public static Path getDataRoot() {
        return FileUtil.getLevelRoot().resolve("CarpetTCTCAddition");
    }

    public static void checkDataRoot() {
        File file = FileUtil.getDataRoot().toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
