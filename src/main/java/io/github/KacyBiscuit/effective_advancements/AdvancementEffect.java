package io.github.KacyBiscuit.effective_advancements;

import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class AdvancementEffect {
	private final StatusEffect type;

	private final int maxAmplifier;
	private final boolean debuff;
	private int amplifier;

	AdvancementEffect(StatusEffect type, int maxAmplifier) {
		this(type, maxAmplifier, false);
	}
	AdvancementEffect(StatusEffect type, int maxAmplifier, boolean debuff) {
		this(type, maxAmplifier, debuff, 0);
	}
	AdvancementEffect(StatusEffect type, int maxAmplifier, boolean debuff, int amplifier) {
		this.type = type;
		this.maxAmplifier = maxAmplifier;
		this.debuff = debuff;
		this.amplifier = amplifier;
	}

	public StatusEffect getType() {
		return type;
	}
	public int getMaxAmplifier() {
		return maxAmplifier;
	}
	public boolean isDebuff() {
		return debuff;
	}
	public int getAmplifier() {
		return amplifier;
	}
	public int setAmplifier(int newAmplifier) {
		if(newAmplifier > maxAmplifier) {
			newAmplifier = maxAmplifier;
		}
		if(newAmplifier < 0) {
			newAmplifier = 0;
		}
		return amplifier = newAmplifier;
	}

	public StatusEffectInstance asInstance() {
		int definiteAmp = amplifier;
		if(!EffectiveConfigManager.AMPLIFY_DUPLICATES.value()) {
			definiteAmp = 0;
		}
		return new StatusEffectInstance(type, 3, definiteAmp, true, true);
	}

	@Override
	public String toString() {
		return type.getName().toString() + ": " + amplifier + "/" + maxAmplifier + " " + debuff;
	}

	public static AdvancementEffect fromNbt(NbtCompound tag) {
		return new AdvancementEffect(Registry.STATUS_EFFECT.get(new Identifier(tag.getString("effect"))),
				tag.getInt("maxAmplifier"), tag.getBoolean("debuff"), tag.getInt("amplifier"));
	}

	public NbtCompound asNbt() {
		NbtCompound tag = new NbtCompound();
		tag.putString("effect", Registry.STATUS_EFFECT.getId(type).toString());
		tag.putInt("maxAmplifier", maxAmplifier);
		tag.putInt("amplifier", amplifier);
		tag.putBoolean("debuff", debuff);
		return tag;
	}
}
