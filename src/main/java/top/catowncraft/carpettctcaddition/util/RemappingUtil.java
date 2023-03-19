/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.fabricmc.tinyremapper.IMappingProvider.Member;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This util from OptiFabric.
public class RemappingUtil {
    private static final MappingResolver RESOLVER = FabricLoader.getInstance().getMappingResolver();
    private static final String INTERMEDIARY = "intermediary";
    private static final Pattern CLASS_FINDER = Pattern.compile("Lnet\\/minecraft\\/([^;]+);");

    public static @NotNull String getClassName(String className) {
        return RemappingUtil.fromIntermediaryDot(className).replace('.', '/');
    }

    private static String fromIntermediaryDot(String className) {
        return RemappingUtil.RESOLVER.mapClassName(RemappingUtil.INTERMEDIARY, "net.minecraft." + className);
    }

    @Contract("_, _, _ -> new")
    public static @NotNull Member mapMethod(String owner, String name, String desc) {
        return new Member(getClassName(owner), getMethodName(owner, name, desc), mapMethodDescriptor(desc));
    }

    public static String getMethodName(String owner, String methodName, String desc) {
        return RemappingUtil.RESOLVER.mapMethodName(RemappingUtil.INTERMEDIARY, "net.minecraft." + owner, methodName, desc);
    }

    public static @NotNull String mapMethodDescriptor(String desc) {
        StringBuffer buf = new StringBuffer();

        Matcher matcher = RemappingUtil.CLASS_FINDER.matcher(desc);

        while (matcher.find()) {
            matcher.appendReplacement(buf, Matcher.quoteReplacement('L' + getClassName(matcher.group(1)) + ';'));
        }

        return matcher.appendTail(buf).toString();
    }

    public static String mapFieldName(String owner, String name, String desc) {
        return RemappingUtil.RESOLVER.mapFieldName(RemappingUtil.INTERMEDIARY, "net.minecraft.".concat(owner), name, desc);
    }
}
