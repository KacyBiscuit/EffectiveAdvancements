package io.github.KacyBiscuit.effective_advancements.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface AchievementGetCallback {
	Event<AchievementGetCallback> EVENT = EventFactory.createArrayBacked(AchievementGetCallback.class,
			(listeners) -> (player, advancement) -> {
				for (AchievementGetCallback listener : listeners) {
					ActionResult result = listener.advancementGet(player, advancement);

					if(result != ActionResult.PASS) {
						return result;
					}
				}

				return ActionResult.PASS;
			});

	ActionResult advancementGet(PlayerEntity player, Advancement advancement);

}
