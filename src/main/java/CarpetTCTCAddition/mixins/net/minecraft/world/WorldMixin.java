package CarpetTCTCAddition.mixins.net.minecraft.world;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import CarpetTCTCAddition.helps.ThrowableSuppression;
import carpet.utils.Messenger;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(World.class)
public abstract class WorldMixin {
    @Shadow @Final public boolean isClient;
    @Shadow public abstract BlockState getBlockState(BlockPos pos);

    @Shadow public abstract World getWorld();

    @Shadow public abstract MinecraftServer getServer();

    //@Inject(method = "updateNeighbor", at = @At(value = "INVOKE", target = "net/minecraft/util/crash/CrashException.<init> (Lnet/minecraft/util/crash/CrashReport;)V"), cancellable = true)
    @Inject(method = "updateNeighbor", at = @At(value = "HEAD"), cancellable = true)
    private void SupressorFix(BlockPos sourcePos, Block sourceBlock, BlockPos neighborPos, CallbackInfo ci) {
        /**
        if (CarpetTCTCAdditionSettings.removeUpdateSuppression) {
            ci.cancel();
        }
        **/
        updateNeighbor(sourcePos, sourceBlock, neighborPos);
        ci.cancel();
    }

    public void updateNeighbor(BlockPos sourcePos, Block sourceBlock, BlockPos neighborPos) {
        if (!this.isClient) {
            BlockState blockState = this.getBlockState(sourcePos);
            try {
                blockState.neighborUpdate(this.getWorld(), sourcePos, sourceBlock, neighborPos, false);
            } catch (Throwable var8) {
                if (CarpetTCTCAdditionSettings.removeUpdateSuppression)
                    return;
                CrashReport crashReport = CrashReport.create(var8, "Exception while updating neighbours");
                CrashReportSection crashReportSection = crashReport.addElement("Block being updated");
                crashReportSection.add("Source block type", () -> {
                    try {
                        return String.format("ID #%s (%s // %s)", Registry.BLOCK.getId(sourceBlock), sourceBlock.getTranslationKey(), sourceBlock.getClass().getCanonicalName());
                    } catch (Throwable var2) {
                        return "ID #" + Registry.BLOCK.getId(sourceBlock);
                    }
                });
                CrashReportSection.addBlockInfo(crashReportSection, sourcePos, blockState);
                if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix && (crashReport.getCause() instanceof StackOverflowError)) {
                    Messenger.print_server_message(this.getServer(), "引发异常");
                    throw new ThrowableSuppression("Update suppression");
                } else {
                    throw new CrashException(crashReport);
                }
            }
        }
    }
}
