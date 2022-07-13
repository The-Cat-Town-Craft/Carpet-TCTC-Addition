/*
 * Copyright (c) Copyright 2020 - 2022 The Cat Town Craft and contributors.
 * This source code is subject to the terms of the GNU Lesser General Public
 * License, version 3. If a copy of the LGPL was not distributed with this
 * file, You can obtain one at: https://www.gnu.org/licenses/lgpl-3.0.txt
 */
package top.catowncraft.carpettctcaddition;

import carpet.settings.Rule;
import top.catowncraft.carpettctcaddition.rule.RuleCategory;

public class CarpetTCTCAdditionSettings {
    @Rule(
            name = "blockIllegalUsername",
            desc = "Verify username using online mode rules.",
            category = {
                    RuleCategory.MISC,
                    RuleCategory.PROTOCOL
            }
    )
    public static boolean blockIllegalUsername = false;

    @Rule(
            name = "commandFix",
            desc = "Enables /fix command to fix chunk data.",
            category = {
                    RuleCategory.COMMAND
            },
            options = {
                    "true",
                    "false",
                    "ops",
                    "0",
                    "1",
                    "2",
                    "3",
                    "4"
            }
    )
    public static String commandFix = "ops";

    @Rule(
            name = "commandFreecam",
            desc = "Enables /freecam command to toggle your camera mode.",
            category = {
                    RuleCategory.COMMAND
            },
            options = {
                    "true",
                    "false",
                    "ops",
                    "0",
                    "1",
                    "2",
                    "3",
                    "4"
            }
    )
    public static String commandFreecam = "true";

    @Rule(
            name = "commandGC",
            desc = "Enables /gc command to allow you to force gc with jvm.",
            category = {
                    RuleCategory.COMMAND
            },
            options = {
                    "true",
                    "false",
                    "ops",
                    "0",
                    "1",
                    "2",
                    "3",
                    "4"
            }
    )
    public static String commandGC = "ops";

    @Rule(
            name = "commandHere",
            desc = "Enables /here command to allow you to share your location with other players.",
            category = {
                    RuleCategory.COMMAND
            },
            options = {
                    "true",
                    "false",
                    "ops",
                    "0",
                    "1",
                    "2",
                    "3",
                    "4"
            }
    )
    public static String commandHere = "true";

    @Rule(
            name = "commandOperator",
            desc = "Enables /operator command to allow you to change the player's permission level.",
            category = {
                    RuleCategory.COMMAND
            },
            options = {
                    "true",
                    "false",
                    "ops",
                    "0",
                    "1",
                    "2",
                    "3",
                    "4"
            }
    )
    public static String commandOperator = "ops";

    @Rule(
            name = "cameraModeDisableSpectatePlayers",
            desc = "Disables players in /c from spectating other players.",
            category = {
                    RuleCategory.COMMAND,
                    RuleCategory.FEATURE
            }
    )
    public static boolean cameraModeDisableSpectatePlayers = false;

    @Rule(
            name = "disableIllegalChatCharacterCheck",
            desc = "Allows you to use characters such as subsections in the game.",
            category = {
                    RuleCategory.CLIENT,
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.MISC
            }
    )
    public static boolean disableIllegalChatCharacterCheck = false;

    @Rule(
            name = "disableParticlesPackets",
            desc = "Disable particle packet sending to reduce bandwidth usage.",
            category = {
                    RuleCategory.CLIENT,
                    RuleCategory.FEATURE,
                    RuleCategory.MISC
            }
    )
    public static boolean disableParticlesPackets = false;

    @Rule(
            name = "enderPlatform",
            desc = "Is obsidian platform generated in the end.",
            extra = {
                    "all - Generate ender platform when all entities are transferred to the_end dimension.",
                    "none - Ender platform will not be generated anyway.",
                    "player - Ender platform is generated only when the player entity teleports to the_end dimension.",
            },
            category = {
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
            name = "endGatewayChunkLoader",
            desc = "When the entity passes through the ender gateway, the target chunk will be loaded for 15 seconds like nether portal.",
            extra = {
                    "all - Give the target chunk a load ticket when all entities through the ender gateway.",
                    "except_player - Give the target chunk a load ticket when all entities except the player through the ender gateway.",
                    "item_only - Give the target chunk a load ticket when the player entities except the player through the ender gateway.",
                    "off - Vanilla action.",
            },
            category = {
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
            name = "freecamRestoreLocation",
            desc = "Return to the starting position when you have finished spectating.",
            category = {
                    RuleCategory.COMMAND,
                    RuleCategory.MISC
            }
    )
    public static boolean freecamRestoreLocation = true;

    @Rule(
            name = "hereGlowTime",
            desc = "Time highlighted when using here command shared coordinates.",
            category = {
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
            name = "illegalPistonActionFix",
            desc = "Fix headless pistons to destroy blocks.",
            category = {
                    RuleCategory.FEATURE
            }
    )
    public static boolean illegalPistonActionFix = false;

    @Rule(
            name = "opLevelBelowSelf",
            desc = "Operators can only grant the same or lower permissions to other players than themselves.",
            category = {
                    RuleCategory.COMMAND
            }
    )
    public static boolean opLevelBelowSelf = true;

    @Rule(
            name = "playerStats",
            desc = "Control of player and bot statistics.",
            extra = {
                    "bot - Only bots are allowed to gain stats.",
                    "both - Both bots and players are allowed to gain stats.",
                    "none - No one allowed to gain stats.",
                    "player - Only players are allowed to gain stats."
            },
            category = {
                    RuleCategory.FEATURE
            }
    )
    public static PlayerStatsOptions playerStats = PlayerStatsOptions.BOTH;

    public enum PlayerStatsOptions {
        BOT,
        BOTH,
        NONE,
        PLAYER
    }

    @Rule(
            name = "shulkerBoxDupeFix",
            desc = "Taking items from a shulker box while destroying it will result in item duplication.",
            category = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean shulkerBoxDupeFix = false;

    @Rule(
            name = "updateSuppressionCrashFix",
            desc = "Fix updates suppression causing server crashes.",
            category = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean updateSuppressionCrashFix = false;

    @Rule(
            name = "voidDevouringBehavior",
            desc = "The action that will occur when the player falls into the void.",
            extra = {
                    "instant_death - Dies immediately when entering the void.",
                    "teleport_to_spawn - Teleport to the player's spawn point when entering the void, or to the world spawn point if it does not exist.",
                    "vanilla - Vanilla behaviour.",
            },
            category = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static VoidDevouringBehaviorOptions voidDevouringBehavior = VoidDevouringBehaviorOptions.VANILLA;

    public enum VoidDevouringBehaviorOptions {
        INSTANT_DEATH,
        TELEPORT_TO_SPAWN,
        VANILLA,
    }

    @Rule(
            name = "voidTradeFix",
            desc = "Once the villager entity is unloaded, the trading interface will be closed.",
            category = {
                    RuleCategory.BUGFIX
            }
    )
    public static boolean voidTradeFix = false;

    @Rule(
            name = "voxelMapWorldName",
            desc = "Send VoxelMap world information packets to client.",
            category = {
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
            name = "wetSpongeAbsorbLava",
            desc = "Wet sponges will be able to absorb lava.",
            category = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static boolean wetSpongeAbsorbLava = false;

    @Rule(
            name = "wetSpongeAbsorbLavaLimit",
            desc = "Maximum offset limit for wet sponge.",
            category = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static int wetSpongeAbsorbLavaLimit = 32;

    @Rule(
            name = "wetSpongeAbsorbLavaRange",
            desc = "Maximum lava sucking for wet sponge.",
            category = {
                    RuleCategory.EXPERIMENTAL,
                    RuleCategory.FEATURE
            }
    )
    public static int wetSpongeAbsorbLavaRange = 3;

    @Rule(
            name = "xaeroMapWorldName",
            desc = "Send XaeroMap world information packets to client.",
            category = {
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
