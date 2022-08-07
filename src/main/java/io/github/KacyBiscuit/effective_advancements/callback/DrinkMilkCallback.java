package io.github.KacyBiscuit.effective_advancements.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface DrinkMilkCallback {
	Event<DrinkMilkCallback> EVENT = EventFactory.createArrayBacked(DrinkMilkCallback.class,
			(listeners) -> (player) -> {
				for (DrinkMilkCallback listener : listeners) {
					ActionResult result = listener.drinkMilk(player);

					if(result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult drinkMilk(PlayerEntity player);
}
