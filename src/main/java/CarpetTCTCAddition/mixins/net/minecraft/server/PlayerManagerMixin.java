package CarpetTCTCAddition.mixins.net.minecraft.server;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.packet.s2c.play.PlayerListS2CPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stats;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Mixin(PlayerManager.class)
public abstract class PlayerManagerMixin {
    @Shadow private static Logger LOGGER;
    @Shadow private  List<ServerPlayerEntity> players = Lists.newArrayList();
    @Shadow private MinecraftServer server;
    @Shadow private Map<UUID, ServerPlayerEntity> playerMap = Maps.newHashMap();
    @Shadow private Map<UUID, ServerStatHandler> statisticsMap;
    @Shadow private Map<UUID, PlayerAdvancementTracker> advancementTrackers;

    @Shadow protected abstract void savePlayerData(ServerPlayerEntity player);

    @Shadow public abstract void sendToAll(Packet<?> packet);

    @Inject(method = "remove", at = @At(value = "HEAD"), cancellable = true)
    public void llamaDupeExploit(ServerPlayerEntity player, CallbackInfo ci) {
        remove(player);
        ci.cancel();
    }
    public void remove(ServerPlayerEntity player) {
        ServerWorld serverWorld = player.getServerWorld();
        player.incrementStat(Stats.LEAVE_GAME);
        this.savePlayerData(player);
        if (player.hasVehicle()) {
            Entity entity = player.getRootVehicle();
            if (entity.hasPlayerRider()) {
                this.LOGGER.debug("Removing player mount");
                player.stopRiding();
                serverWorld.removeEntity(entity);
                Iterator var4 = entity.getPassengersDeep().iterator();
                entity.removed = !CarpetTCTCAdditionSettings.llamaDupeExploit;

                Entity entity2;
                for(Iterator var5 = entity.getPassengersDeep().iterator(); var5.hasNext(); entity2.removed = !CarpetTCTCAdditionSettings.llamaDupeExploit) {
                    entity2 = (Entity)var4.next();
                    serverWorld.removeEntity(entity2);
                }

                serverWorld.getChunk(player.chunkX, player.chunkZ).markDirty();
            }
        }
        player.detach();
        serverWorld.removePlayer(player);
        player.getAdvancementTracker().clearCriterions();
        this.players.remove(player);
        this.server.getBossBarManager().onPlayerDisconnenct(player);
        UUID uUID = player.getUuid();
        ServerPlayerEntity serverPlayerEntity = this.playerMap.get(uUID);
        if (serverPlayerEntity == player) {
            this.playerMap.remove(uUID);
            this.statisticsMap.remove(uUID);
            this.advancementTrackers.remove(uUID);
        }
        this.sendToAll((Packet)(new PlayerListS2CPacket(PlayerListS2CPacket.Action.REMOVE_PLAYER, new ServerPlayerEntity[]{player})));
    }
}
