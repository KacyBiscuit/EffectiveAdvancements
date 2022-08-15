package io.github.KacyBiscuit.effective_advancements.config;

import org.quiltmc.config.api.WrappedConfig;
import org.quiltmc.config.api.annotations.Comment;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.config.api.values.ValueMap;

public class EffectiveConfig extends WrappedConfig {

	@Comment("The Advancements that cause random buffs to be applied.")
	public final ValueList<String> advancementList = ValueList.create("Advancement",
			"story/obtain_armor",
			"story/form_obsidian",
			"story/mine_diamond",
			"story/enter_the_nether",
			"story/enchant_item",
			"story/cure_zombie_villager",
			"story/follow_ender_eye",
			"nether/return_to_sender",
			"nether/obtain_ancient_debris",
			"nether/uneasy_alliance",
			"nether/loot_bastion",
			"nether/netherite_armor",
			"nether/get_wither_skull",
			"nether/summon_wither",
			"nether/all_potions",
			"nether/create_full_beacon",
			"nether/all_effects",
			"end/kill_dragon",
			"end/respawn_dragon",
			"end/dragon_breath",
			"end/find_end_city",
			"end/elytra",
			"adventure/fall_from_world_height",
			"adventure/avoid_vibration",
			"adventure/hero_of_the_village",
			"adventure/kill_all_mods",
			"adventure/totem_of_undying",
			"adventure/arbalistic",
			"adventure/adventuring_time",
			"adventure/sniper_duel",
			"husbandry/obtain_netherite_hoe");

	@Comment("Defines the effect that can be given when a random buff is applied, followed by their maximum effect (starting from 0).")
	public final ValueMap<Integer> buffs = ValueMap.builder(0)
			.put("minecraft:speed", 4)
			.put("minecraft:resistance", 1)
			.put("minecraft:haste", 2)
			.put("minecraft:strength", 0)
			.put("minecraft:jump_boost", 2)
			.put("minecraft:regeneration", 2)
			.put("minecraft:night_vision", 0)
			.put("minecraft:saturation", 1)
			.put("minecraft:luck", 3)
			.put("minecraft:dolphins_grace", 2)
			.put("minecraft:hero_of_the_village", 1)
			.build();

	@Comment("Defines the effect that can be given when a random debuff is applied, followed by their maximum effect (starting from 0).")
	public final ValueMap<Integer> debuffs = ValueMap.builder(0)
			.put("minecraft:slowness", 4)
			.put("minecraft:mining_fatigue", 2)
			.put("minecraft:hunger", 1)
			.put("minecraft:bad_luck", 3)
			.put("minecraft:weakness", 2)
			.put("minecraft:blindness", 1)
			.put("minecraft:bad_omen", 2)
			.build();

	@Comment("If someone randomly rolls an effect they already have it will amplify it until its at it's maximum amplifier.")
	public final boolean amplifyDuplicates = true;

	@Comment("""
			Defines how the player's effects will change when a Bucket of Milk is drunk:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_DEBUFFS": Removes all debuffs from the player.
			"REMOVE_DEBUFF": Removes a single random debuff from the player.
			""")
	public final EffectiveConfigEnums.PositiveModes milkMode = EffectiveConfigEnums.PositiveModes.NO_EFFECT;

	@Comment("""
			Defines how the player's effects will change when a player dies:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_RANDOM": Removes a random buff from the player.
			"RANDOM_DEBUFF": Adds a random debuff to the player.
			""")
	public final EffectiveConfigEnums.NegativeModes deathMode = EffectiveConfigEnums.NegativeModes.RANDOM_DEBUFF;

	@Comment("""
			Defines how the player's effects will change when a Totem of Undying is activated:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_DEBUFFS": Removes all debuffs from the player.
			"REMOVE_DEBUFF": Removes a single random debuff from the player.
			""")
	public final EffectiveConfigEnums.PositiveModes totemMode = EffectiveConfigEnums.PositiveModes.REMOVE_DEBUFFS;

	@Comment("""
			Defines how the player's effects will change when a food is eaten that grants effects, such as a golden apple:
			"NO_EFFECT": Nothing changes.
			"REMOVE_EFFECTS": Removes all advancement effects from the player.
			"REMOVE_DEBUFFS": Removes all debuffs from the player.
			"REMOVE_DEBUFF": Removes a single random debuff from the player.
			""")
	public final EffectiveConfigEnums.PositiveModes appleMode = EffectiveConfigEnums.PositiveModes.REMOVE_DEBUFF;

	@Comment("The permission level required to run the /advancementEffect command.")
	public final int permissionLevel = 3;
}
