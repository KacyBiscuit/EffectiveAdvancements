package io.github.KacyBiscuit.effective_advancements.mixin;

import io.github.KacyBiscuit.effective_advancements.callback.TotemUseCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {

	@Inject(method = "tryUseTotem", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;)Z", ordinal = 0), cancellable = true)
	public void finishUsing(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
		if((LivingEntity)(Object)this instanceof ServerPlayerEntity) {
			TotemUseCallback.EVENT.invoker().useTotem((ServerPlayerEntity)(Object)this);
		}
	}
}
