/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.LevelResource;
import org.apache.commons.io.IOUtils;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionReference;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import static net.minecraft.core.Registry.DIMENSION_REGISTRY;

public class FreeCameraUtil {
    public static HashMap<UUID, CameraData> freeCameraData = new HashMap<>();

    public static class CameraData {
        boolean isFreeCamera;
        String gameType;
        String resourceKey;
        double x;
        double y;
        double z;
        float yRot;
        float xRot;

        public CameraData(GameType gameType, ResourceKey<Level> resourceKey, double x, double y, double z, float yRot, float xRot, boolean isFreeCamera) {
            this.isFreeCamera = isFreeCamera;
            this.gameType = gameType.getName();
            this.resourceKey = resourceKey.location().toString();
            this.x = x;
            this.y = y;
            this.z = z;
            this.yRot = yRot;
            this.xRot = xRot;
        }

        public boolean isFreeCamera() {
            return this.isFreeCamera;
        }

        public GameType getGameType() {
            return GameType.byName(this.gameType);
        }

        public ResourceKey<Level> getResourceKey() {
            return ResourceKey.create(DIMENSION_REGISTRY, new ResourceLocation(this.resourceKey));
        }

        public double getX() {
            return this.x;
        }

        public double getY() {
            return this.y;
        }

        public double getZ() {
            return this.z;
        }

        public float getYRot() {
            return this.yRot;
        }

        public float getXRot() {
            return this.xRot;
        }

        public void setFreeCamera(boolean isFreeCamera) {
            this.isFreeCamera = isFreeCamera;
        }

        public void setGameType(GameType gameType) {
            this.gameType = gameType.getName();
        }

        public void setDimensionType(ResourceKey<Level> resourceKey) {
            this.resourceKey = resourceKey.toString();
        }

        public void setX(double x) {
            this.x = x;
        }

        public void setY(double y) {
            this.y = y;
        }

        public void setZ(double z) {
            this.z = z;
        }

        public void setYRot(float yRot) {
            this.yRot = yRot;
        }

        public void setXRot(float xRot) {
            this.xRot = xRot;
        }
    }

    public static void loadFreeCameraData() {
        String dataJSON;
        try {
            dataJSON = IOUtils.toString(new FileInputStream(getFile()), StandardCharsets.UTF_8);
        } catch (NullPointerException | IOException e) {
            dataJSON = "{}";
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        freeCameraData = gson.fromJson(dataJSON, new TypeToken<HashMap<String, CameraData>>() {
        }.getType());
    }

    public static void saveFreeCameraData() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(getFile()), StandardCharsets.UTF_8)) {
            writer.write(gson.toJson(freeCameraData));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getFile() {
        return CarpetTCTCAddition.getServer().getWorldPath(LevelResource.ROOT).resolve(String.format("%s_freeCameraStorage.json", CarpetTCTCAdditionReference.getModId())).toFile();
    }
}