package io.github.KacyBiscuit.effective_advancements.listener;

import io.github.KacyBiscuit.effective_advancements.EffectiveAdvancements;
import io.github.KacyBiscuit.effective_advancements.StatusEffectComponents;
import io.github.KacyBiscuit.effective_advancements.StatusEffectManager;
import io.github.KacyBiscuit.effective_advancements.callback.TotemUseCallback;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.util.ActionResult;

public class TotemUseListener {
	public static void register() {
		TotemUseCallback.EVENT.register((player) -> {
			StatusEffectManager manager = StatusEffectComponents.MANAGER.get(player);
			switch(EffectiveConfigManager.TOTEM_MODE.value()) {
				case NO_EFFECT:
					break;
				case REMOVE_EFFECTS:
					manager.reset();
					break;
				case REMOVE_DEBUFFS:
					manager.clearType(true);
					break;
			}
			return ActionResult.PASS;
		});
	}
}
