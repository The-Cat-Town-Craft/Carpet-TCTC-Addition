/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.fabricmc.tinyremapper.IMappingProvider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// This util from OptiFabric.
public class RemappingUtil {
    private static final Pattern CLASS_FINDER = Pattern.compile("Lnet\\/minecraft\\/([^;]+);");
    private static final String INTERMEDIARY = "intermediary";
    private static final MappingResolver RESOLVER = FabricLoader.getInstance().getMappingResolver();

    private static String fromIntermediaryDot(String className) {
        return RESOLVER.mapClassName(INTERMEDIARY, String.format("net.minecraft.%s", className));
    }

    public static String getClassName(String className) {
        return RemappingUtil.fromIntermediaryDot(className).replace('.', '/');
    }

    public static String getMethodName(String owner, String methodName, String desc) {
        return RemappingUtil.RESOLVER.mapMethodName(INTERMEDIARY, "net.minecraft." + owner, methodName, desc);
    }

    public static IMappingProvider.Member mapMethod(String owner, String name, String desc) {
        return new IMappingProvider.Member(RemappingUtil.getClassName(owner), RemappingUtil.getMethodName(owner, name, desc), RemappingUtil.mapMethodDescriptor(desc));
    }

    public static String mapMethodDescriptor(String desc) {
        StringBuilder stringBuffer = new StringBuilder();

        Matcher matcher = CLASS_FINDER.matcher(desc);
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, Matcher.quoteReplacement('L' + RemappingUtil.getClassName(matcher.group(1)) + ';'));
        }

        return matcher.appendTail(stringBuffer).toString();
    }
}
