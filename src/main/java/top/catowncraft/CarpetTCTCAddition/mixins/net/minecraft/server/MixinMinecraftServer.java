package top.catowncraft.CarpetTCTCAddition.mixins.net.minecraft.server;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.BaseComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAddition;
import top.catowncraft.CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import top.catowncraft.CarpetTCTCAddition.utils.MessageUtil;
import top.catowncraft.CarpetTCTCAddition.utils.ThrowableSuppression;

import java.util.Iterator;
import java.util.function.BooleanSupplier;

import static carpet.utils.Translations.tr;

@Mixin(MinecraftServer.class)
public class MixinMinecraftServer {
    @Inject(
            method = "tickChildren",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/CrashReport;forThrowable(Ljava/lang/Throwable;Ljava/lang/String;)Lnet/minecraft/CrashReport;"
            ),
            locals = LocalCapture.CAPTURE_FAILHARD,
            cancellable = true
    )
    private void onTickingWorld(BooleanSupplier booleanSupplier, CallbackInfo ci, Iterator var2, ServerLevel serverLevel, Throwable throwable) {
        if (CarpetTCTCAdditionSettings.updateSuppressionCrashFix && (throwable.getCause() instanceof ThrowableSuppression)) {
            MessageUtil.sendServerMessage(CarpetTCTCAddition.getServer(), (BaseComponent) new TextComponent(tr("carpet-tctc-addition.message.server.updateSuppression.exception", "We caught an update suppression exception. This should cause the server to crash, but we prevented it.")).setStyle(new Style().setColor(ChatFormatting.GRAY).setItalic(true)));
            ci.cancel();
        }
    }
}
