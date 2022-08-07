package io.github.KacyBiscuit.effective_advancements.mixin;

import io.github.KacyBiscuit.effective_advancements.callback.AchievementGetCallback;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.PlayerAdvancementTracker;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerAdvancementTracker.class)
public class PlayerAdvancementTrackerMixin {
	@Shadow
	private ServerPlayerEntity owner;

	@Inject(method = "grantCriterion", at = @At("HEAD"), cancellable = true)
	private void grantCriterion(final Advancement advancement, final String criterionName, final CallbackInfoReturnable<Boolean> cir) {
		ActionResult result = AchievementGetCallback.EVENT.invoker().advancementGet(this.owner, advancement);
		if(result == ActionResult.FAIL) {
			cir.cancel();
		}
	}
}
