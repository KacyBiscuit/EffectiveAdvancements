package io.github.KacyBiscuit.effective_advancements.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;

public interface TotemUseCallback {
	Event<TotemUseCallback> EVENT = EventFactory.createArrayBacked(TotemUseCallback.class,
			(listeners) -> (player) -> {
				for (TotemUseCallback listener : listeners) {
					ActionResult result = listener.useTotem(player);

					if(result != ActionResult.PASS) {
						return result;
					}
				}
				return ActionResult.PASS;
			});

	ActionResult useTotem(PlayerEntity player);
}
