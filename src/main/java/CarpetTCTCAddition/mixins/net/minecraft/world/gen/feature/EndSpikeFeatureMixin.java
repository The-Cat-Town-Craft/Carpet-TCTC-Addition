package CarpetTCTCAddition.mixins.net.minecraft.world.gen.feature;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.gen.feature.EndSpikeFeature;
import net.minecraft.world.gen.feature.EndSpikeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(EndSpikeFeature.class)
public class EndSpikeFeatureMixin {
    @Inject(
        method = "generateSpike",
        at = @At(
            value = "HEAD"
        ),
        cancellable = true
    )
    private void endSpike(ServerWorldAccess world, Random random, EndSpikeFeatureConfig config, EndSpikeFeature.Spike spike, CallbackInfo ci) {
        if (!CarpetTCTCAdditionSettings.endSpike) {
            ci.cancel();
        }
    }
}
