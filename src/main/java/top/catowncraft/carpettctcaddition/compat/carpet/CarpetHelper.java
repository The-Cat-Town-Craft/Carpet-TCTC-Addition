/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpet;

public class CarpetHelper {
    private static Class<?> ThrowableSuppression;

    static {
        try {
            ThrowableSuppression = Class.forName("carpet.helpers.ThrowableSuppression");
        } catch (ClassNotFoundException ignore) {
        }
    }

    public static Class<?> ThrowableSuppression() {
        return ThrowableSuppression;
    }
}
