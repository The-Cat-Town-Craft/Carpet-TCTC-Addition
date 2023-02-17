/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

//#if MC >= 11600
import net.minecraft.world.level.storage.LevelResource;
//#endif
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;

import java.io.File;
import java.nio.file.Path;

public class FileUtil {
    public static @NotNull Path getLevelRoot() {
        //#if MC >= 11600
        return CarpetTCTCAdditionExtension.getServer().getWorldPath(LevelResource.ROOT);
        //#else
        //$$ return CarpetTCTCAdditionExtension.getServer().getStorageSource().getBaseDir().resolve(CarpetTCTCAdditionExtension.getServer().getLevelIdName());
        //#endif
    }

    public static @NotNull Path getDataRoot() {
        return FileUtil.getLevelRoot().resolve("CarpetTCTCAddition");
    }

    public static void checkDataRoot() {
        File file = FileUtil.getDataRoot().toFile();
        if (!file.exists()) {
            file.mkdirs();
        }
    }
}
