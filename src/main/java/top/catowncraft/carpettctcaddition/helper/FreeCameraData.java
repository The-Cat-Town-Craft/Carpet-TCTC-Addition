/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.helper;

import com.google.gson.JsonObject;
//#if MC >= 11600
import net.minecraft.resources.ResourceKey;
//#endif
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
//#if MC < 16000
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class FreeCameraData {
    public final @NotNull GameType gameType;
    //#if MC > 11502
    public final @NotNull ResourceKey<Level> dimension;
    //#else
    //$$ public final @NotNull DimensionType dimension;
    //#endif
    public final @NotNull Vec3 vec3;
    public final float xRot;
    public final float yRot;
    public boolean isFreecam;

    //#if MC > 11502
    public FreeCameraData(@NotNull GameType gameType, @NotNull ResourceKey<Level> dimension, @NotNull Vec3 vec3, float xRot, float yRot, boolean isFreecam) {
    //#else
    //$$ public FreeCameraData(@NotNull GameType gameType, @NotNull DimensionType dimension, @NotNull Vec3 vec3, float xRot, float yRot, boolean isFreecam) {
    //#endif
        this.gameType = gameType;
        this.dimension = dimension;
        this.vec3 = vec3;
        this.xRot = xRot;
        this.yRot = yRot;
        this.isFreecam = isFreecam;
    }

    public FreeCameraData(@NotNull ServerPlayer serverPlayer, boolean isFreecam) {
        this(serverPlayer.gameMode.getGameModeForPlayer(),
                //#if MC > 11502
                serverPlayer.getLevel().dimension(),
                //#else
                //$$ serverPlayer.dimension,
                //#endif
                serverPlayer.position(),
                serverPlayer.getXRotCompat(),
                serverPlayer.getYRotCompat(),
                isFreecam
        );
    }

    public void serialize(@NotNull JsonObject jsonObject) {
        jsonObject.addProperty("gameType", this.gameType.getName());
        //#if MC > 11502
        jsonObject.addProperty("dimension", this.dimension.location().toString());
        //#else
        //$$ jsonObject.addProperty("dimension", this.dimension.toString());
        //#endif
        jsonObject.addProperty("x", this.vec3.x);
        jsonObject.addProperty("y", this.vec3.y);
        jsonObject.addProperty("z", this.vec3.z);
        jsonObject.addProperty("xRot", this.xRot);
        jsonObject.addProperty("yRot", this.yRot);
        jsonObject.addProperty("isFreecam", this.isFreecam);
    }
}
