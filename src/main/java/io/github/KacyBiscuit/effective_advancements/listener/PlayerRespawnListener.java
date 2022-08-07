package io.github.KacyBiscuit.effective_advancements.listener;

import io.github.KacyBiscuit.effective_advancements.EffectiveAdvancements;
import io.github.KacyBiscuit.effective_advancements.StatusEffectComponents;
import io.github.KacyBiscuit.effective_advancements.StatusEffectManager;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;

public class PlayerRespawnListener {
	public static void register() {
		ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) -> {
			StatusEffectManager manager = StatusEffectComponents.MANAGER.get(newPlayer);
			switch(EffectiveConfigManager.DEATH_MODE.value()) {
				case NO_EFFECT:
					break;
				case REMOVE_EFFECTS:
					manager.reset();
					break;
				case REMOVE_RANDOM:
					manager.revoke(false);
					break;
				case RANDOM_DEBUFF:
					manager.grant(true);
					break;
			}
		});
	}
}
