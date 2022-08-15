package io.github.KacyBiscuit.effective_advancements.listener;

import io.github.KacyBiscuit.effective_advancements.StatusEffectComponents;
import io.github.KacyBiscuit.effective_advancements.StatusEffectManager;
import io.github.KacyBiscuit.effective_advancements.callback.DrinkMilkCallback;
import io.github.KacyBiscuit.effective_advancements.callback.GoldenAppleCallback;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.util.ActionResult;

public class GoldenAppleListener {
	public static void register() {
		GoldenAppleCallback.EVENT.register(player -> {
			System.out.println(4);
			StatusEffectManager manager = StatusEffectComponents.MANAGER.get(player);
			switch(EffectiveConfigManager.APPLE_MODE.value()) {
				case NO_EFFECT:
					break;
				case REMOVE_EFFECTS:
					manager.reset();
					break;
				case REMOVE_DEBUFFS:
					manager.clearType(true);
					break;
				case REMOVE_DEBUFF:
					manager.revoke(true);
					break;
			}
			return ActionResult.PASS;
		});
	}
}
