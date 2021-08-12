/*
 * Copyright (c) 2021-2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.dimension.DimensionType;

public class MessageUtil {
    public static void sendMessage(CommandSourceStack source, String message)
    {
        if (source != null)
            source.sendSuccess(new TextComponent(message),source.getServer() != null && source.getServer().getLevel(DimensionType.OVERWORLD) != null);
    }
    public static void sendMessage(CommandSourceStack source, BaseComponent component)
    {
        if (source != null)
            source.sendSuccess(component,source.getServer() != null && source.getServer().getLevel(DimensionType.OVERWORLD) != null);
    }
}
