package damageevent.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import damageevent.DamageeventClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@SuppressWarnings("resource")
    @Inject(method = "damage(Lnet/minecraft/entity/damage/DamageSource;F)Z", at = @At(value = "INVOKE",
            target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"))
	private void onDamageLivingEntity(DamageSource source, float amount, final CallbackInfoReturnable<Boolean> info) {
        try {
            var living = (LivingEntity) (Object) this;
            if (!living.getUuid().equals(MinecraftClient.getInstance().player.getUuid())) {
                return;
            }
    
            DamageeventClient.instance.dispatch();
        } catch (Exception ignore) { }
	}
}