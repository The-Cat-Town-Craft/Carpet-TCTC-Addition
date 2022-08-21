/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.hendrixshen.magiclib.compat.carpet;

//#if MC >= 11901
import carpet.api.settings.SettingsManager;
//#else
//$$ import carpet.settings.SettingsManager;
//#endif
import net.minecraft.commands.CommandSourceStack;

public class WrapperSettingsManager extends SettingsManager {
    public WrapperSettingsManager(String version, String identifier, String fancyName) {
        super(version, identifier, fancyName);
    }

    // Compat for legacy Minecraft version.
    public static boolean canUseCommand(CommandSourceStack source, Object commandLevel) {
        if (commandLevel instanceof Boolean) {
            return (Boolean)commandLevel;
        } else {
            String commandLevelString = commandLevel.toString();
            switch(commandLevelString) {
                case "true":
                    return true;
                case "ops":
                    return source.hasPermission(2);
                case "0":
                case "1":
                case "2":
                case "3":
                case "4":
                    return source.hasPermission(Integer.parseInt(commandLevelString));
                default:
                    return false;
            }
        }
    }
}
