package io.github.KacyBiscuit.effective_advancements.config;

import org.quiltmc.config.api.values.TrackedValue;
import org.quiltmc.config.api.values.ValueList;
import org.quiltmc.config.api.values.ValueMap;
import org.quiltmc.loader.api.config.QuiltConfig;

import java.util.List;

public class EffectiveConfigManager {
	public static final EffectiveConfig CONFIG = QuiltConfig.create("effective_advancements", "config", EffectiveConfig.class);

	public static final TrackedValue<ValueList<String>> ADVANCEMENT_LIST = (TrackedValue<ValueList<String>>) CONFIG.getValue(List.of("advancementList"));

	public static final TrackedValue<ValueMap<Integer>> BUFFS = (TrackedValue<ValueMap<Integer>>) CONFIG.getValue(List.of("buffs"));
	public static final TrackedValue<ValueMap<Integer>> DEBUFFS = (TrackedValue<ValueMap<Integer>>) CONFIG.getValue(List.of("debuffs"));

	public static final TrackedValue<Boolean> AMPLIFY_DUPLICATES = (TrackedValue<Boolean>) CONFIG.getValue(List.of("amplifyDuplicates"));
	public static final TrackedValue<EffectiveConfigEnums.PositiveModes> MILK_MODE = (TrackedValue<EffectiveConfigEnums.PositiveModes>) CONFIG.getValue(List.of("milkMode"));
	public static final TrackedValue<EffectiveConfigEnums.NegativeModes> DEATH_MODE = (TrackedValue<EffectiveConfigEnums.NegativeModes>) CONFIG.getValue(List.of("deathMode"));
	public static final TrackedValue<EffectiveConfigEnums.PositiveModes> TOTEM_MODE = (TrackedValue<EffectiveConfigEnums.PositiveModes>) CONFIG.getValue(List.of("totemMode"));
	public static final TrackedValue<Integer> PERMISSION_LEVEL = (TrackedValue<Integer>) CONFIG.getValue(List.of("permissionLevel"));
}
