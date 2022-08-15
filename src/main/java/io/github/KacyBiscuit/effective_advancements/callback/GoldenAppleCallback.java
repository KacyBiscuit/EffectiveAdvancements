package io.github.KacyBiscuit.effective_advancements.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface GoldenAppleCallback {
	Event<GoldenAppleCallback> EVENT = EventFactory.createArrayBacked(GoldenAppleCallback.class,
			(listeners) -> (player) -> {
				for (GoldenAppleCallback listener : listeners) {
					ActionResult result = listener.eatApple(player);
					if(result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult eatApple(PlayerEntity player);

}
