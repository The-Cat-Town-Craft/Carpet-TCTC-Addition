/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

public class VersionParser {
    public static String getVersionType(String version) {
        if (version.endsWith("stable")) {
            return "Public Release";
        } else if (version.endsWith("beta")) {
            return "Public Beta";
        } else if (version.endsWith("dev")) {
            return "Development";
        }
        return "Unknown";
    }
}
