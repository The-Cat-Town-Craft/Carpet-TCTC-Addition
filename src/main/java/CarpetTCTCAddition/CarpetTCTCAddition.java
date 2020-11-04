package CarpetTCTCAddition;

import CarpetTCTCAddition.commands.CameraModeCommand;
import CarpetTCTCAddition.commands.FindCommand;
import CarpetTCTCAddition.commands.HereCommand;
import CarpetTCTCAddition.commands.TPSCommand;
import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

public class CarpetTCTCAddition implements CarpetExtension
{
    public static final String version = "1.0.3";
    public static void noop() { }
    private static SettingsManager mySettingManager;
    static
    {
        mySettingManager = new SettingsManager(version, "carpetTCTCAddition", "carpet TCTC Addition");
        CarpetServer.manageExtension(new CarpetTCTCAddition());
    }

    @Override
    public void onGameStarted()
    {
        CarpetServer.settingsManager.parseSettingsClass(CarpetTCTCAdditionSettings.class);
    }

    @Override
    public void onServerLoaded(MinecraftServer server)
    {
        // reloading of /carpet settings is handled by carpet
        // reloading of own settings is handled as an extension, since we claim own settings manager
    }

    @Override
    public void onTick(MinecraftServer server)
    {
        // no need to add this.
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher)
    {
        CameraModeCommand.register(dispatcher);
        FindCommand.register(dispatcher);
        HereCommand.register(dispatcher);
        TPSCommand.register(dispatcher);
    }


    @Override
    public SettingsManager customSettingsManager()
    {
        // this will ensure that our settings are loaded properly when world loads
        return mySettingManager;
    }

    @Override
    public void onPlayerLoggedIn(ServerPlayerEntity player)
    {
        //
    }

    @Override
    public void onPlayerLoggedOut(ServerPlayerEntity player)
    {
        //
    }
}
