package io.github.KacyBiscuit.effective_advancements;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.util.Identifier;

public class StatusEffectComponents implements EntityComponentInitializer {
	public static final ComponentKey<StatusEffectManager> MANAGER = ComponentRegistryV3.INSTANCE.getOrCreate(
			new Identifier("effective_advancements", "manager"), StatusEffectManager.class);
	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerForPlayers(MANAGER, playerEntity -> new StatusEffectManager(playerEntity), RespawnCopyStrategy.ALWAYS_COPY);
	}
}
