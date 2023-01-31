/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.mixin.rule.blockIllegalUsername;

import carpet.commands.PlayerCommand;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.util.MessageUtil;

@Mixin(PlayerCommand.class)
public class MixinPlayerCommand {
    @Inject(
            method = "spawn",
            at = @At(
                    value = "HEAD"
            ),
            remap = false,
            cancellable = true
    )
    private static void onSpawn(CommandContext<CommandSourceStack> context, CallbackInfoReturnable<Integer> cir) {
        String playerName = StringArgumentType.getString(context, "player");
        if (CarpetTCTCAdditionSettings.blockIllegalUsername && (playerName.matches("[^a-zA-Z0-9_]+$") || playerName.length() > 16)) {
            MessageUtil.sendMessage(context.getSource(), StringUtil.tr("message.server.checkIllegalUsername.fakePlayer", playerName));
            cir.cancel();
        }
    }
}
