/*
 * Copyright (c) Copyright 2020 - 2023 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import top.catowncraft.carpettctcaddition.rule.RuleCategory;
import top.hendrixshen.magiclib.api.rule.annotation.Command;
import top.hendrixshen.magiclib.api.rule.annotation.Rule;

public class CarpetTCTCAdditionSettings {
    @Rule(
            categories = {
                    RuleCategory.MISC,
                    RuleCategory.PROTOCOL
            }
    )
    public static boolean blockIllegalUsername = false;

    @Command(full = true)
    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static String commandFix = "ops";

    @Command(full = true)
    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static String commandFreecam = "true";

    @Command(full = true)
    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static String commandGC = "ops";

    @Command(full = true)
    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static String commandHere = "true";

    @Command(full = true)
    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static String commandOperator = "ops";

    @Rule(
            categories = {
                    RuleCategory.COMMAND,
                    RuleCategory.FEATURE
            }
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
            categories = {
                    RuleCategory.CLIENT,
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.MISC
            }
    )
    public static boolean disableIllegalChatCharacterCheck = false;

    @Rule(
            categories = {
                    RuleCategory.CLIENT,
                    RuleCategory.FEATURE,
                    RuleCategory.MISC
            }
    )
    public static boolean disableParticlesPackets = false;

    @Rule(
            categories = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static boolean dispenserCollectExperience = false;

    @Rule(
            categories = {
                    RuleCategory.FEATURE,
                    RuleCategory.WORLD_GENERATE
            }
    )
    //#if MC >= 11600
    public static EnderPlatformOptions enderPlatform = EnderPlatformOptions.ALL;
    //#else
    //$$ public static EnderPlatformOptions enderPlatform = EnderPlatformOptions.PLAYER;
    //#endif

    public enum EnderPlatformOptions {
        ALL,
        NONE,
        PLAYER
    }

    @Rule(
            categories = {
                    RuleCategory.FEATURE
            }
    )
    public static EndGatewayChunkLoaderOptions endGatewayChunkLoader = EndGatewayChunkLoaderOptions.OFF;

    public enum EndGatewayChunkLoaderOptions {
        ALL,
        EXCEPT_PLAYER,
        ITEM_ONLY,
        OFF
    }

    @Rule(
            categories = {
                    RuleCategory.COMMAND,
                    RuleCategory.MISC
            }
    )
    public static boolean freecamRestoreLocation = true;

    @Rule(
            categories = {
                    RuleCategory.COMMAND,
                    RuleCategory.MISC
            },
            options = {
                    "0",
                    "15"
            },
            strict = false
    )
    public static int hereGlowTime = 15;

    @Rule(
            categories = {
                    RuleCategory.FEATURE
            }
    )
    public static boolean illegalPistonActionFix = false;

    @Rule(
            categories = {
                    RuleCategory.FEATURE
            }
    )
    public static boolean noteBlockChunkLoader = false;

    @Rule(
            categories = {
                    RuleCategory.COMMAND
            }
    )
    public static boolean opLevelBelowSelf = true;

    @Rule(
            categories = {
                    RuleCategory.FEATURE
            }
    )
    public static PlayerStatsOptions playerStats = PlayerStatsOptions.BOTH;

    public enum PlayerStatsOptions {
        BOT(false, true),
        BOTH(false, false),
        NONE(true, true),
        PLAYER(false, true);

        public final boolean disablePlayer;
        public final boolean disableBot;

        PlayerStatsOptions(boolean disablePlayer, boolean disableBot) {

            this.disablePlayer = disablePlayer;
            this.disableBot = disableBot;
        }
    }

    @Rule(
            categories = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean shulkerBoxDupeFix = false;

    @Rule(
            categories = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean updateSuppressionCrashFix = false;

    @Rule(
            categories = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static VoidDevouringBehaviorOptions voidDevouringBehavior = VoidDevouringBehaviorOptions.VANILLA;

    public enum VoidDevouringBehaviorOptions {
        INSTANT_DEATH(true, false, false),
        TELEPORT_TO_SPAWN(false, false, true),
        TELEPORT_TO_SPAWN_COST_TOTEM(false, true, true),
        TELEPORT_TO_SPAWN_COST_TOTEM_OR_DEATH(true, true, true),
        VANILLA(false, false, false);

        public final boolean immediateDeath;
        public final boolean totemBypass;
        public final boolean teleportToSpawn;

        VoidDevouringBehaviorOptions(boolean immediateDeath, boolean totemBypass, boolean teleportToSpawn) {
            this.immediateDeath = immediateDeath;
            this.totemBypass = totemBypass;
            this.teleportToSpawn = teleportToSpawn;
        }
    }

    @Rule(
            categories = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean voidTradeFix = false;

    @Rule(
            categories = {
                    RuleCategory.PROTOCOL
            },
            options = {
                    "#none",
                    "creative",
                    "mirror",
                    "survival"
            },
            strict = false
    )
    public static String voxelMapWorldName = "#none";

    @Rule(
            categories = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static boolean wetSpongeAbsorbLava = false;

    @Rule(
            categories = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static int wetSpongeAbsorbLavaLimit = 32;

    @Rule(
            categories = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static int wetSpongeAbsorbLavaRange = 3;

    @Rule(
            categories = {
                    RuleCategory.PROTOCOL
            },
            options = {
                    "#none",
                    "creative",
                    "mirror",
                    "survival"
            },
            strict = false
    )
    public static String xaeroMapWorldName = "#none";
}
