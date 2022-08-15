package io.github.KacyBiscuit.effective_advancements.mixin;

import io.github.KacyBiscuit.effective_advancements.callback.GoldenAppleCallback;
import io.github.KacyBiscuit.effective_advancements.callback.TotemUseCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
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
	@Inject(method = "eatFood", at = @At("HEAD"))
	public void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		System.out.println(3);
		if(stack.isFood() && (LivingEntity)(Object)this instanceof ServerPlayerEntity) {
			System.out.println(1);
			if(stack.getItem().getFoodComponent().getStatusEffects().isEmpty()) {
				System.out.println(2);
				return;
			}
			ActionResult result = GoldenAppleCallback.EVENT.invoker().eatApple((PlayerEntity)(Object)this);
			if (result == ActionResult.FAIL) {
				cir.cancel();
			}
		}
	}
}
