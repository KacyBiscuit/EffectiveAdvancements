package io.github.KacyBiscuit.effective_advancements.config;

import org.quiltmc.config.api.WrappedConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.config.api.values.ValueMap;

public class EffectiveConfig extends WrappedConfig {

	@Comment("The Advancements that cause random buffs to be applied.")
	public final ValueList<String> advancementList = ValueList.create("", "story/mine_stone");

	@Comment("Defines the effect that can be given when a random buff is applied, followed by their maximum effect (starting from 0).")
	public final ValueMap<Integer> buffs = ValueMap.builder(0)
			.put("minecraft:speed", 4)
			.put("minecraft:resistance", 2)
			.put("minecraft:haste", 2)
			.build();

	@Comment("Defines the effect that can be given when a random debuff is applied, followed by their maximum effect (starting from 0).")
	public final ValueMap<Integer> debuffs = ValueMap.builder(0)
			.put("minecraft:slowness", 4)
			.put("minecraft:mining_fatigue", 2)
			.build();

	@Comment("If someone randomly rolls an effect they already have it will amplify it until its at it's maximum amplifier.")
	public final boolean amplifyDuplicates = true;

	@Comment("""
			Defines how the player's effects will change when a Bucket of Milk is drunk:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_DEBUFFS": Removes all debuffs from the player.
			""")
	public final EffectiveConfigEnums.PositiveModes milkMode = EffectiveConfigEnums.PositiveModes.NO_EFFECT;

	@Comment("""
			Defines how the player's effects will change when a player dies:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_RANDOM": Removes a random buff from the player.
			"RANDOM_DEBUFF": Adds a random debuff to the player.
			""")
	public final EffectiveConfigEnums.NegativeModes deathMode = EffectiveConfigEnums.NegativeModes.NO_EFFECT;

	@Comment("""
			Defines how the player's effects will change when a Totem of Undying is activated:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_DEBUFFS": Removes all debuffs from the player.
			""")
	public final EffectiveConfigEnums.PositiveModes totemMode = EffectiveConfigEnums.PositiveModes.NO_EFFECT;

	@Comment("The permission level required to run the /advancementEffect command.")
	public final int permissionLevel = 3;
}
