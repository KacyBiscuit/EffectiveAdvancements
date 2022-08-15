package io.github.KacyBiscuit.effective_advancements.listener;

import io.github.KacyBiscuit.effective_advancements.EffectiveAdvancements;
import io.github.KacyBiscuit.effective_advancements.StatusEffectComponents;
import io.github.KacyBiscuit.effective_advancements.StatusEffectManager;
import io.github.KacyBiscuit.effective_advancements.callback.DrinkMilkCallback;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.util.ActionResult;

public class DrinkMilkListener {
	public static void register() {
		DrinkMilkCallback.EVENT.register(player -> {
			StatusEffectManager manager = StatusEffectComponents.MANAGER.get(player);
			System.out.println(EffectiveConfigManager.MILK_MODE.value());
			switch(EffectiveConfigManager.MILK_MODE.value()) {
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
