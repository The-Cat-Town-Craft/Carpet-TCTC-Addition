/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.fakes;

import com.mojang.authlib.GameProfile;

public interface PlayerListInterface {
    void setPermissionLevel(GameProfile gameProfile, int level);

    void setBypassPlayerLimit(GameProfile gameProfile, boolean bypass);
}