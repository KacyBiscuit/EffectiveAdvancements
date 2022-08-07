package io.github.KacyBiscuit.effective_advancements;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.quiltmc.config.api.values.ValueMap;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;

public class EffectList extends ArrayList<AdvancementEffect> {
	public ArrayList<AdvancementEffect> toRemove = new ArrayList<>();

	public EffectList() {
	}
	public EffectList(ValueMap<Integer> list, boolean debuff) {
		for(Map.Entry<String, Integer> pair : list) {
			add(new AdvancementEffect(Registry.STATUS_EFFECT.get(new Identifier(pair.getKey())), pair.getValue(), debuff));
		}
	}

	public void add(StatusEffect effect) {
		if(has(effect)) {
			amplify(effect, 1);
		} else {
			if(StatusEffectManager.buffs.has(effect)) {
				add(StatusEffectManager.buffs.get(effect));
			} else if (StatusEffectManager.debuffs.has(effect)) {
				add(StatusEffectManager.debuffs.get(effect));
			} else {
				add(new AdvancementEffect(effect, 9));
			}
		}
	}
	public void remove(StatusEffect effect, boolean complete) {
		for(AdvancementEffect advancementEffect : this) {
			if(advancementEffect.getType().equals(effect)) {
				if (complete || advancementEffect.getAmplifier() <= 0) {
					toRemove.add(advancementEffect);
				} else {
					amplify(advancementEffect.getType(), -1);
				}
			}
		}
	}
	public void confirmRemove() {
		for(AdvancementEffect advancementEffect : toRemove) {
			remove(advancementEffect);
		}
	}

	public void amplify(StatusEffect effect, int mod) {
		AdvancementEffect advancementEffect = get(effect);
		if(advancementEffect != null) {
			advancementEffect.setAmplifier(advancementEffect.getAmplifier() + mod);
		}
	}

	public boolean has(StatusEffect effect) {
		for(AdvancementEffect advancementEffect : this) {
			if(advancementEffect.getType().equals(effect)) {
				return true;
			}
		}
		return false;
	}
	public AdvancementEffect get(StatusEffect effect) {
		for(AdvancementEffect advancementEffect : this) {
			if(advancementEffect.getType().equals(effect)) {
				return advancementEffect;
			}
		}
		return null;
	}

	public NbtList asNbt() {
		NbtList list = new NbtList();
		for(AdvancementEffect advancementEffect : this) {
			list.add(advancementEffect.asNbt());
		}
		return list;
	}
	public static EffectList fromNbt(NbtElement nbt) {
		EffectList list = new EffectList();
		for (NbtElement compound : (NbtList) nbt) {
			list.add(AdvancementEffect.fromNbt((NbtCompound) compound));
		}
		return list;
	}
}
