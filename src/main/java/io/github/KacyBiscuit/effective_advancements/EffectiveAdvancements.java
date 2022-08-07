package io.github.KacyBiscuit.effective_advancements;

import io.github.KacyBiscuit.effective_advancements.listener.AchievementGetListener;
import io.github.KacyBiscuit.effective_advancements.listener.DrinkMilkListener;
import io.github.KacyBiscuit.effective_advancements.listener.PlayerRespawnListener;
import io.github.KacyBiscuit.effective_advancements.listener.TotemUseListener;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.config.QuiltConfig;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EffectiveAdvancements implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Effective Advancements");

	@Override
	public void onInitialize(ModContainer mod) {
		LOGGER.info("Loading...");
		AchievementGetListener.register();
		DrinkMilkListener.register();
		PlayerRespawnListener.register();
		TotemUseListener.register();
		AdvancementEffectCommand.register();
		LOGGER.info("Effective Advancements Loaded");
	}
}
