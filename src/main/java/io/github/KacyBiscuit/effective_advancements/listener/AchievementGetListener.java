package io.github.KacyBiscuit.effective_advancements.listener;

import io.github.KacyBiscuit.effective_advancements.StatusEffectComponents;
import io.github.KacyBiscuit.effective_advancements.callback.AchievementGetCallback;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.util.ActionResult;

public class AchievementGetListener {
	public static void register() {
		AchievementGetCallback.EVENT.register((player, advancement) -> {
			if(EffectiveConfigManager.ADVANCEMENT_LIST.value().contains(advancement.getId().getPath())) {
				StatusEffectComponents.MANAGER.get(player).grant(false);
			}
			return ActionResult.PASS;
		});
	}
}
