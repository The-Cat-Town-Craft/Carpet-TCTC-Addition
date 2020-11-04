package CarpetTCTCAddition.mixins.carpet.commands;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.commands.PlayerCommand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PlayerCommand.class)
public abstract class PlayerCommandMixin {
    @ModifyConstant(
        method = "spawn",
        constant = @Constant(intValue = 40),
        remap = false
    )
    private static int fakePlayerNameLengthLimit(int value)
    {
        if (CarpetTCTCAdditionSettings.fakePlayerNameLengthLimit) {
            return 16;
        }
        return value;
    }
}