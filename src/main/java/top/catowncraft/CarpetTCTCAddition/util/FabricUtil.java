/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import top.catowncraft.carpettctcaddition.CarpetTCTCAddition;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FabricUtil extends top.hendrixshen.magiclib.util.FabricUtil {
    public static boolean isVersionSatisfied(String modId, Pattern pattern, String versionPredicate) {
        Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(modId);
        if (modContainer.isPresent()) {
            String ver = modContainer.get().getMetadata().getVersion().getFriendlyString();
            Matcher matcher = pattern.matcher(ver);
            if (matcher.find()) {
                String realVer = matcher.group(0);
                Version version;
                try {
                    version = Version.parse(realVer);
                } catch (VersionParsingException e) {
                    CarpetTCTCAddition.getLogger().error("Cannot parse target version: {}", realVer);
                    e.printStackTrace();
                    return false;
                }
                return FabricUtil.isModLoaded(version, versionPredicate);
            }
        }
        return false;
    }
}
