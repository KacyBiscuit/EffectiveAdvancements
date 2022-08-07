package io.github.KacyBiscuit.effective_advancements.mixin;

import io.github.KacyBiscuit.effective_advancements.callback.DrinkMilkCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemMixin {

	@Inject(method = "finishUsing", at = @At("HEAD"), cancellable = true)
	public void finishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
		if(user instanceof ServerPlayerEntity) {
			ActionResult result = DrinkMilkCallback.EVENT.invoker().drinkMilk((ServerPlayerEntity) user);
			if (result == ActionResult.FAIL) {
				cir.cancel();
			}
		}
	}
}
