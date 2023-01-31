/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import top.catowncraft.carpettctcaddition.CarpetTCTCAdditionSettings;
import top.catowncraft.carpettctcaddition.rule.CarpetTCTCAdditionSettingManager;
import top.catowncraft.carpettctcaddition.util.StringUtil;
import top.hendrixshen.magiclib.util.MessageUtil;

import static net.minecraft.commands.Commands.literal;

public class GCCommand {
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> gc = literal("gc")
                .requires(s -> CarpetTCTCAdditionSettingManager.canUseCommand(s, CarpetTCTCAdditionSettings.commandGC))
                .executes(GCCommand::gc);
        dispatcher.register(gc);
    }

    private static int gc(@NotNull CommandContext<CommandSourceStack> context) {
        Runnable runnable = new CleanerThread(context.getSource());
        Thread gcThread = new Thread(runnable, "MemoryCleaner GC Thread");
        gcThread.setDaemon(true);
        gcThread.start();
        return 1;
    }

    static class CleanerThread implements Runnable {
        private final CommandSourceStack sourceStack;

        public CleanerThread(CommandSourceStack commandSourceStack) {
            this.sourceStack = commandSourceStack;
        }

        @Override
        public void run() {
            MessageUtil.sendMessage(this.sourceStack, StringUtil.tr("message.command.gc.started"));
            System.gc();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException ignored) {
            }
            System.gc();
            MessageUtil.sendMessage(this.sourceStack, StringUtil.tr("message.command.gc.finished"));
        }
    }
}
