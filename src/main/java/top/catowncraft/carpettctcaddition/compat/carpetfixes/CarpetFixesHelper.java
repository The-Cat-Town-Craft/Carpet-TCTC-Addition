/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.compat.carpetfixes;

public class CarpetFixesHelper {
    private static Class<?> UpdateSuppressionException;

    static {
        try {
            UpdateSuppressionException = Class.forName("carpetfixes.helpers.UpdateSuppressionException");
        } catch (ClassNotFoundException ignore) {
        }
    }

    public static Class<?> UpdateSuppressionException() {
        return UpdateSuppressionException;
    }
}
