/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionReference;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FreeCameraUtil {
    public static class FreeCameraData {
        public final @NotNull GameType gameType;
        public final @NotNull DimensionType dimension;
        public final @NotNull Vec3 vec3;
        public final float xRot;
        public final float yRot;
        public boolean isFreecam;

        public FreeCameraData(@NotNull GameType gameType, @NotNull DimensionType dimension, @NotNull Vec3 vec3, float xRot, float yRot, boolean isFreecam) {
            this.gameType = gameType;
            this.dimension = dimension;
            this.vec3 = vec3;
            this.xRot = xRot;
            this.yRot = yRot;
            this.isFreecam = isFreecam;
        }

        public FreeCameraData(@NotNull ServerPlayer serverPlayer, boolean isFreecam) {
            this.gameType = serverPlayer.gameMode.getGameModeForPlayer();
            this.dimension = serverPlayer.dimension;
            this.vec3 = serverPlayer.position();
            this.xRot = serverPlayer.xRot;
            this.yRot = serverPlayer.yRot;
            this.isFreecam = isFreecam;
        }

        public void serialize(JsonObject jsonObject) {
            jsonObject.addProperty("gameType", this.gameType.getName());
            jsonObject.addProperty("dimension", this.dimension.toString());
            jsonObject.addProperty("x", this.vec3.x);
            jsonObject.addProperty("y", this.vec3.y);
            jsonObject.addProperty("z", this.vec3.z);
            jsonObject.addProperty("xRot", this.xRot);
            jsonObject.addProperty("yRot", this.yRot);
            jsonObject.addProperty("isFreecam", this.isFreecam);
        }
    }

    public static Path getFile() {
        return CarpetTCTCAddition.getServer().getStorageSource().getFile(CarpetTCTCAddition.getServer().getLevelIdName(), String.format("%s_freeCameraStorage.json", CarpetTCTCAdditionReference.getModId())).toPath();
    }

    public static FreeCameraData createEntry(JsonObject jsonObject) {
        GameType gameType = jsonObject.has("gameType") ? GameType.byName(jsonObject.get("gameType").getAsString()) : GameType.SURVIVAL;
        DimensionType dimension = jsonObject.has("dimension") ? DimensionType.getByName(new ResourceLocation(jsonObject.get("dimension").getAsString())) : null;
        double x = jsonObject.has("x") ? jsonObject.get("x").getAsDouble() : 0;
        double y = jsonObject.has("y") ? jsonObject.get("y").getAsDouble() : 0;
        double z = jsonObject.has("z") ? jsonObject.get("z").getAsDouble() : 0;
        float xRot = jsonObject.has("xRot") ? jsonObject.get("xRot").getAsFloat() : 0;
        float yRot = jsonObject.has("yRot") ? jsonObject.get("yRot").getAsFloat() : 0;
        boolean isFreecam = jsonObject.has("isFreecam") && jsonObject.get("isFreecam").getAsBoolean();
        return new FreeCameraData(gameType, dimension != null ? dimension : DimensionType.OVERWORLD, new Vec3(x, y, z), xRot, yRot, isFreecam);
    }

    static class Serializer implements JsonDeserializer<FreeCameraData>, JsonSerializer<FreeCameraData> {
        private Serializer() {
        }

        @Override
        public JsonElement serialize(FreeCameraData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            src.serialize(jsonObject);
            return jsonObject;
        }

        @Override
        public FreeCameraData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();
                return FreeCameraUtil.createEntry(jsonObject);
            }
            return null;
        }
    }

    public static void saveFreeCameraData(Map<UUID, FreeCameraData> data) throws IOException {
        Path file = getFile();

        if (data.isEmpty()) {
            Files.deleteIfExists(file);
            return;
        }

        String string = new GsonBuilder().registerTypeAdapter(FreeCameraData.class, new Serializer()).create().toJson(data);

        try (BufferedWriter writer = Files.newBufferedWriter(file)) {
            writer.write(string);
        } catch (Throwable throwable) {
            CarpetTCTCAddition.getLogger().error("Cannot write freeCameraData: {}", string);
        }
    }

    public static Map<UUID, FreeCameraData> loadFreeCameraData() throws IOException {
        Path file = getFile();

        if (!Files.isRegularFile(file)) {
            return new HashMap<>();
        }
        try (BufferedReader reader = Files.newBufferedReader(file)) {
            return new GsonBuilder().registerTypeAdapter(FreeCameraData.class, new Serializer()).create().fromJson(reader, new TypeToken<Map<UUID, FreeCameraData>>() {
            }.getType());
        }
    }
}