/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import carpet.patches.EntityPlayerMPFake;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class MiscUtil extends top.hendrixshen.magiclib.util.MiscUtil {
    public static boolean isBotEntity(Player player) {
        return player instanceof EntityPlayerMPFake;
    }

    public static boolean isRealPlayer(Player player) {
        return player instanceof ServerPlayer && !(player instanceof EntityPlayerMPFake);
    }
}
