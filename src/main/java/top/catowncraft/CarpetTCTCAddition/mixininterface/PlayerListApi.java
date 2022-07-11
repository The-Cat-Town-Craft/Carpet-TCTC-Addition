/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixininterface;

import com.mojang.authlib.GameProfile;

public interface PlayerListApi {
    default void tctc$setPermissionLevel(GameProfile gameProfile, int level) {
        throw new UnsupportedOperationException();
    }

    default void tctc$setBypassPlayerLimit(GameProfile gameProfile, boolean bypass) {
        throw new UnsupportedOperationException();
    }
}