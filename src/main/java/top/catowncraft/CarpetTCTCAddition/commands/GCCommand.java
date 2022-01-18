/*
 * Copyright (c) Copyright 2020 - 2021 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU General Public
 * License, version 3. If a copy of the GPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/gpl-3.0.txt
 */
package top.catowncraft.CarpetTCTCAddition.commands;

import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;

import static net.minecraft.commands.Commands.literal;

public class GCCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        LiteralArgumentBuilder<CommandSourceStack> gc = literal("gc")
                .requires(s -> SettingsManager.canUseCommand(s, CarpetTCTCAdditionSettings.commandGC))
                .executes(GCCommand::gc);
        dispatcher.register(gc);
    }

    private static int gc(CommandContext<CommandSourceStack> context) {
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
            MessageUtil.sendMessage(this.sourceStack, "Memory cleaner thread started!");
            System.gc();
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException interruptedException) {
                // ignored
            }
            System.gc();
            MessageUtil.sendMessage(this.sourceStack, "Memory cleaner thread finished!");
        }
    }
}
