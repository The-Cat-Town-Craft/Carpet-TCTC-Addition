package CarpetTCTCAddition.mixins.net.minecraft.entity;

import CarpetTCTCAddition.CarpetTCTCAdditionSettings;
import carpet.CarpetSettings;
import carpet.fakes.TntEntityInterface;
import carpet.logging.LoggerRegistry;
import carpet.logging.logHelpers.TNTLogHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion.DestructionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Iterator;

@Mixin(value = TntEntity.class, priority = 900) //覆盖 Carpet
public abstract class TntEntityMixin extends Entity implements TntEntityInterface {
    @Shadow
    private int field_7196;
    private TNTLogHelper logHelper;
    private boolean mergeBool = false;
    private int mergedTNT = 1;

    public TntEntityMixin(EntityType<?> entityType_1, World world_1) {
        super(entityType_1, world_1);
    }

    @Inject(
            method = {"<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"},
            at = {@At("RETURN")}
    )
    private void modifyTNTAngle(World world, double x, double y, double z, LivingEntity entity, CallbackInfo ci) {
        if (CarpetSettings.hardcodeTNTangle != -1.0D) {
            this.setVelocity(-Math.sin(CarpetSettings.hardcodeTNTangle) * 0.02D, 0.2D, -Math.cos(CarpetSettings.hardcodeTNTangle) * 0.02D);
        }

    }

    @Inject(
            method = {"<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V"},
            at = {@At("RETURN")}
    )
    private void initTNTLoggerPrime(EntityType<? extends TntEntity> entityType_1, World world_1, CallbackInfo ci) {
        if (LoggerRegistry.__tnt && !world_1.isClient) {
            this.logHelper = new TNTLogHelper();
        }

    }

    @Inject(
            method = {"tick"},
            at = {@At("HEAD")}
    )
    private void initTracker(CallbackInfo ci) {
        if (LoggerRegistry.__tnt && this.logHelper != null && !this.logHelper.initialized) {
            this.logHelper.onPrimed(this.getX(), this.getY(), this.getZ(), this.getVelocity());
        }

    }

    @Inject(
            method = {"<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/entity/LivingEntity;)V"},
            at = {@At("RETURN")}
    )
    private void initTNTLogger(World world_1, double double_1, double double_2, double double_3, LivingEntity livingEntity_1, CallbackInfo ci) {
        if (CarpetSettings.tntPrimerMomentumRemoved) {
            this.setVelocity(new Vec3d(0.0D, 0.20000000298023224D, 0.0D));
        }

    }

    @Inject(
            method = {"explode"},
            at = {@At("HEAD")}
    )
    private void onExplode(CallbackInfo ci) {
        if (LoggerRegistry.__tnt && this.logHelper != null) {
            this.logHelper.onExploded(this.getX(), this.getY(), this.getZ(), this.world.getTime());
        }

        if (this.mergedTNT > 1) {
            for(int i = 0; i < this.mergedTNT - 1; ++i) {
                this.world.createExplosion(this, this.getX(), this.getY() + (double)(this.getHeight() / 16.0F), this.getZ(), 4.0F, DestructionType.BREAK);
            }
        }

    }

    @Inject(
            method = {"tick"},
            at = {@At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/TntEntity;setVelocity(Lnet/minecraft/util/math/Vec3d;)V",
                    ordinal = 2
            )}
    )
    private void tryMergeTnt(CallbackInfo ci) {
        if (CarpetSettings.mergeTNT) {
            Vec3d velocity = this.getVelocity();
            if (!this.world.isClient && this.mergeBool && velocity.x == 0.0D && velocity.y == 0.0D && velocity.z == 0.0D) {
                this.mergeBool = false;
                Iterator var3 = this.world.getOtherEntities(this, this.getBoundingBox()).iterator();

                while(var3.hasNext()) {
                    Entity entity = (Entity)var3.next();
                    if (entity instanceof TntEntity && !entity.removed) {
                        TntEntity entityTNTPrimed = (TntEntity)entity;
                        Vec3d tntVelocity = entityTNTPrimed.getVelocity();
                        int FuseTimer;
                        if (CarpetTCTCAdditionSettings.tweakMergeTNT) {
                            FuseTimer = entityTNTPrimed.getFuseTimer() + 1; // 来自 HIT-Carpet
                        } else {
                            FuseTimer = entityTNTPrimed.getFuseTimer();
                        }
                        if (tntVelocity.x == 0.0D && tntVelocity.y == 0.0D && tntVelocity.z == 0.0D && this.getX() == entityTNTPrimed.getX() && this.getZ() == entityTNTPrimed.getZ() && this.getY() == entityTNTPrimed.getY() && this.field_7196 == FuseTimer) {
                            this.mergedTNT += ((TntEntityInterface)entityTNTPrimed).getMergedTNT();
                            entityTNTPrimed.remove();
                        }
                    }
                }
            }
        }

    }

    @Inject(
            method = {"tick"},
            at = {@At(
                    value = "FIELD",
                    target = "Lnet/minecraft/entity/TntEntity;fuseTimer:I",
                    ordinal = 0
            )}
    )
    private void setMergeable(CallbackInfo ci) {
        Vec3d velocity = this.getVelocity();
        if (!this.world.isClient && (velocity.y != 0.0D || velocity.x != 0.0D || velocity.z != 0.0D)) {
            this.mergeBool = true;
        }

    }

    public int getMergedTNT() {
        return this.mergedTNT;
    }
}