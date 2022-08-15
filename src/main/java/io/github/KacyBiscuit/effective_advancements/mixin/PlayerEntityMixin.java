package io.github.KacyBiscuit.effective_advancements.mixin;

import io.github.KacyBiscuit.effective_advancements.callback.DrinkMilkCallback;
import io.github.KacyBiscuit.effective_advancements.callback.GoldenAppleCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
	public void finishUsing(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
		if(stack.isFood()) {
			if(stack.getItem().getFoodComponent().getStatusEffects().isEmpty()) {
				return;
			}
			ActionResult result = GoldenAppleCallback.EVENT.invoker().eatApple((PlayerEntity)(Object)this);
			if (result == ActionResult.FAIL) {
				cir.cancel();
			}
		}
	}
}
