/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetextra;

public class CEHelper {
    private static Class<?> ThrowableSuppression;

    static {
        try {
            ThrowableSuppression = Class.forName("carpetextra.helpers.ThrowableSuppression");
        } catch (ClassNotFoundException ignore) {
        }
    }

    public static Class<?> ThrowableSuppression() {
        return ThrowableSuppression;
    }
}
