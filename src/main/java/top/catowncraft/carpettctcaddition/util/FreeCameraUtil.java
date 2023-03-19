/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.util;

import com.google.common.collect.Maps;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionReference;
import top.catowncraft.carpettctcaddition.helper.FreeCameraData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

//#if MC > 11902
import net.minecraft.core.registries.Registries;
//#endif

//#if MC > 11502
//#if MC < 11903
//$$ import static net.minecraft.core.Registry.DIMENSION_REGISTRY;
//#endif
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
//#else
//$$ import net.minecraft.world.level.dimension.DimensionType;
//#endif

public class FreeCameraUtil {
    private static Map<UUID, FreeCameraData> cameraData = new HashMap<>();

    public static FreeCameraData get(UUID uuid) {
        return FreeCameraUtil.cameraData.get(uuid);
    }

    public static void put(UUID uuid, FreeCameraData data) {
        FreeCameraUtil.cameraData.put(uuid, data);
    }

    public static @NotNull FreeCameraData createEntry(@NotNull JsonObject jsonObject) {
        GameType gameType = jsonObject.has("gameType") ? GameType.byName(jsonObject.get("gameType").getAsString()) : GameType.SURVIVAL;
        //#if MC > 11902
        ResourceKey<Level> dimension = jsonObject.has("dimension") ? ResourceKey.create(Registries.DIMENSION, new ResourceLocation(jsonObject.get("dimension").getAsString())) : null;
        //#elseif MC > 11502
        //$$ ResourceKey<Level> dimension = jsonObject.has("dimension") ? ResourceKey.create(DIMENSION_REGISTRY, new ResourceLocation(jsonObject.get("dimension").getAsString())) : null;
        //#else
        //$$ DimensionType dimension = jsonObject.has("dimension") ? DimensionType.getByName(new ResourceLocation(jsonObject.get("dimension").getAsString())) : null;
        //#endif
        double x = jsonObject.has("x") ? jsonObject.get("x").getAsDouble() : 0;
        double y = jsonObject.has("y") ? jsonObject.get("y").getAsDouble() : 0;
        double z = jsonObject.has("z") ? jsonObject.get("z").getAsDouble() : 0;
        float xRot = jsonObject.has("xRot") ? jsonObject.get("xRot").getAsFloat() : 0;
        float yRot = jsonObject.has("yRot") ? jsonObject.get("yRot").getAsFloat() : 0;
        boolean isFreecam = jsonObject.has("isFreecam") && jsonObject.get("isFreecam").getAsBoolean();
        //#if MC > 11502
        return new FreeCameraData(gameType, dimension != null ? dimension : Level.OVERWORLD, new Vec3(x, y, z), xRot, yRot, isFreecam);
        //#else
        //$$ return new FreeCameraData(gameType, dimension != null ? dimension : DimensionType.OVERWORLD, new Vec3(x, y, z), xRot, yRot, isFreecam);
        //#endif
    }

    static class Serializer implements JsonDeserializer<FreeCameraData>, JsonSerializer<FreeCameraData> {
        @Override
        public JsonElement serialize(@NotNull FreeCameraData src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            src.serialize(jsonObject);
            return jsonObject;
        }

        @Override
        public FreeCameraData deserialize(@NotNull JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                JsonObject jsonObject = json.getAsJsonObject();
                return FreeCameraUtil.createEntry(jsonObject);
            }

            return null;
        }
    }

    public static void load() {
        FreeCameraUtil.cameraData = FreeCameraUtil.loadFreeCameraData();
    }

    public static void save() {
        FreeCameraUtil.saveFreeCameraData(FreeCameraUtil.cameraData);
    }

    private static void saveFreeCameraData(@NotNull Map<UUID, FreeCameraData> data) {
        Path path = FileUtil.getDataRoot().resolve("freecam.json");

        if (data.isEmpty()) {
            try {
                Files.deleteIfExists(path);
            } catch (IOException e) {
                CarpetTCTCAdditionReference.getLogger().error("Cannot delete empty file: {}", path.toString());
                CarpetTCTCAdditionReference.getLogger().throwing(e);
            }

            return;
        }

        String string = new GsonBuilder().registerTypeAdapter(FreeCameraData.class, new Serializer()).create().toJson(data);
        FileUtil.checkDataRoot();

        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(string);
        } catch (IOException e) {
            CarpetTCTCAdditionReference.getLogger().error("Cannot write freeCameraData: {}", string);
            CarpetTCTCAdditionReference.getLogger().throwing(e);
        }
    }

    private static @Nullable Map<UUID, FreeCameraData> loadFreeCameraData() {
        Path legacyPath = FileUtil.getLevelRoot().resolve(String.format("%s_freeCameraStorage.json", CarpetTCTCAdditionReference.getModIdentifier()));

        if (legacyPath.toFile().exists()) {
            CarpetTCTCAdditionReference.getLogger().info("[{}]Legacy freecam data found!", CarpetTCTCAdditionReference.getModName());
            Map<UUID, FreeCameraData> legacy = Maps.newHashMap();

            try (BufferedReader reader = Files.newBufferedReader(legacyPath)) {
                legacy = new GsonBuilder().registerTypeAdapter(FreeCameraData.class, new Serializer()).create().fromJson(reader, new TypeToken<Map<UUID, FreeCameraData>>() {
                }.getType());
            } catch (IOException e) {
                CarpetTCTCAdditionReference.getLogger().error("Cannot load legacy freeCameraData.");
                CarpetTCTCAdditionReference.getLogger().throwing(e);
            }

            FreeCameraUtil.save();

            try {
                Files.deleteIfExists(legacyPath);
                CarpetTCTCAdditionReference.getLogger().info("[{}]Legacy file deleted.", CarpetTCTCAdditionReference.getModName());
            } catch (IOException e) {
                CarpetTCTCAdditionReference.getLogger().error("Cannot delete legacy freeCameraData.");
                CarpetTCTCAdditionReference.getLogger().throwing(e);
            }

            return legacy;
        }

        Path path = FileUtil.getDataRoot().resolve("freecam.json");

        if (!Files.isRegularFile(path)) {
            return Maps.newHashMap();
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            return new GsonBuilder().registerTypeAdapter(FreeCameraData.class, new Serializer()).create().fromJson(reader, new TypeToken<Map<UUID, FreeCameraData>>() {
            }.getType());
        } catch (IOException e) {
            CarpetTCTCAdditionReference.getLogger().error("Cannot load freeCameraData.");
            CarpetTCTCAdditionReference.getLogger().throwing(e);
        }

        return null;
    }
}