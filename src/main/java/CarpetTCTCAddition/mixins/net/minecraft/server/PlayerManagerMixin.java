package CarpetTCTCAddition.mixins.net.minecraft.server;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import net.minecraft.entity.Entity;
import net.minecraft.server.PlayerManager;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    @Redirect(method="remove", at = @At(value="FIELD", target = "Lnet/minecraft/entity/Entity;removed:Z", opcode = Opcodes.PUTFIELD))
    private void llamaDupeExploit(Entity entity, boolean value) {
        entity.removed = !CarpetTCTCAdditionSettings.llamaDupeExploit;
    }
}
