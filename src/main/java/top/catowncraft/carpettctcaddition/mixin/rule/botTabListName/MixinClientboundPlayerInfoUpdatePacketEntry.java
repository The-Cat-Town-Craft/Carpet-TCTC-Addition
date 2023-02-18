/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.botTabListName;

import carpet.patches.EntityPlayerMPFake;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionExtension;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.hendrixshen.magiclib.compat.minecraft.network.chat.ComponentCompatApi;

import java.util.ArrayList;
import java.util.regex.Pattern;

//#if MC > 11902
import net.minecraft.network.chat.RemoteChatSession;

import java.util.UUID;
//#elseif MC > 11802
//$$ import net.minecraft.world.entity.player.ProfilePublicKey;
//#endif
//#if MC > 11502
import net.minecraft.network.chat.MutableComponent;
//#else
//$$ import net.minecraft.network.chat.BaseComponent;
//#endif

@Mixin(ClientboundPlayerInfoUpdatePacket.Entry.class)
public class MixinClientboundPlayerInfoUpdatePacketEntry {
    @Mutable
    @Shadow
    @Final
    @Nullable
    private Component displayName;

    private final Pattern cta$formatPattern = Pattern.compile("(?i)&([0-9A-FK-OR])");

    @Inject(
            //#if MC > 11902
            method = "<init>(Ljava/util/UUID;Lcom/mojang/authlib/GameProfile;ZILnet/minecraft/world/level/GameType;Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/RemoteChatSession$Data;)V",
            //#else
            //$$ method = "<init>",
            //#endif
            at = @At(
                    value = "RETURN"
            )
    )
    //#if MC > 11902
    private void modifyDisplayName(UUID profileId, @NotNull GameProfile profile, boolean listed, int latency, GameType gameMode, Component displayName, RemoteChatSession.Data chatSession, CallbackInfo ci) {
    //#elseif MC > 11802
    //$$ private void modifyDisplayName(@NotNull GameProfile profile, int latency, GameType gameMode, Component displayName, ProfilePublicKey.Data profilePublicKey, CallbackInfo ci) {
    //#elseif MC > 11605
    //$$ private void modifyDisplayName(@NotNull GameProfile profile, int latency, GameType gameMode, Component displayName, CallbackInfo ci) {
    //#else
    //$$ private void modifyDisplayName(ClientboundPlayerInfoPacket clientboundPlayerInfoPacket, @NotNull GameProfile profile, int latency, GameType gameMode, Component displayName, CallbackInfo ci) {
    //#endif
        if (!CarpetTCTCAdditionSettings.botTabListNamePrefix.equals("#none") ||
                !CarpetTCTCAdditionSettings.botTabListNameSuffix.equals("#none")) {
            ServerPlayer serverPlayer = CarpetTCTCAdditionExtension.getServer().getPlayerList().getPlayer(profile.getId());
            if (serverPlayer instanceof EntityPlayerMPFake) {
                ArrayList<Component> components = Lists.newArrayList();
                if (!CarpetTCTCAdditionSettings.botTabListNamePrefix.equals("#none")) {
                    components.add(ComponentCompatApi.literal(cta$formatPattern.matcher(CarpetTCTCAdditionSettings.botTabListNamePrefix).replaceAll("§$1")));
                }
                components.add(serverPlayer.getDisplayName());
                if (!CarpetTCTCAdditionSettings.botTabListNameSuffix.equals("#none")) {
                    components.add(ComponentCompatApi.literal(cta$formatPattern.matcher(CarpetTCTCAdditionSettings.botTabListNameSuffix).replaceAll("§$1")));
                }
                //#if MC > 11502
                MutableComponent ret = ComponentCompatApi.literal("");
                //#else
                //$$ BaseComponent ret = ComponentCompatApi.literal("");
                //#endif
                components.forEach(ret::append);
                this.displayName = ret;
            }
        }
    }
}
