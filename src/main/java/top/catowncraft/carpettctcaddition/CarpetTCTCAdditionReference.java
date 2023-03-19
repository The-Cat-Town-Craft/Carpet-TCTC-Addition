/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import lombok.Getter;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import top.catowncraft.carpettctcaddition.util.StringUtil;

public class CarpetTCTCAdditionReference {
    @Getter
    private static final String modIdentifier = "@MOD_IDENTIFIER@";
    @Getter
    private static final String modIdentifierCurrent = "@MOD_IDENTIFIER@-@MINECRAFT_VERSION_IDENTIFY@";
    @Getter
    private static final String modName = "@MOD_NAME@";
    @Getter
    private static final String modNameCurrent = FabricLoader.getInstance().getModContainer(modIdentifierCurrent).orElseThrow(RuntimeException::new).getMetadata().getName();
    @Getter
    private static final String modVersion = FabricLoader.getInstance().getModContainer(modIdentifierCurrent).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
    @Getter
    private static final String modVersionType = StringUtil.getVersionType(modVersion);
    @Getter
    private static final Logger logger = LogManager.getLogger(modIdentifier);
}
