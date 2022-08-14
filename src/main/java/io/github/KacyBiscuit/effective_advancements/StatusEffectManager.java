package io.github.KacyBiscuit.effective_advancements;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;

import java.util.*;

public class StatusEffectManager implements Component, ServerTickingComponent {
	private static final Random rand = new Random();
	protected static EffectList buffs = new EffectList(EffectiveConfigManager.BUFFS.value(), false);
	protected static EffectList debuffs = new EffectList(EffectiveConfigManager.DEBUFFS.value(), true);

	private EffectList effects = new EffectList();
	private final PlayerEntity player;

	public StatusEffectManager(PlayerEntity player) {
		this.player = player;
	}

	public static StatusEffect randomEffect(boolean debuff) {
		if (debuff) {
			return debuffs.get(rand.nextInt(debuffs.size())).getType();
		} else {
			return buffs.get(rand.nextInt(buffs.size())).getType();
		}
	}

	public void grant(boolean debuff) {
		add(randomEffect(debuff));
	}
	public void revoke(boolean debuff) {
		boolean complete = false;
		while(!complete) {
			StatusEffect effect = randomEffect(debuff);
			if(effects.has(effect)) {
				remove(effect, false, true);
				complete = true;
			}
		}
	}

	public void clearType(boolean debuff) {
		effects.forEach((effect) -> {
			if(effect.isDebuff() == debuff) {
				remove(effect.getType(), true, false);
			}
		});
		effects.confirmRemove();
	}
	public void reset() {
		effects.clear();
	}
	public void add(StatusEffect effect) {
		effects.add(effect);
	}
	public void remove(StatusEffect effect, boolean complete, boolean confirm) {
		effects.remove(effect, complete);
		if(confirm) {
			effects.confirmRemove();
		}
	}
	public void applyEffects() {
		effects.forEach(effect -> player.addStatusEffect(effect.asInstance()));
	}


	@Override
	public void readFromNbt(NbtCompound tag) {
		if(EffectList.fromNbt(tag.getList("effects", NbtElement.COMPOUND_TYPE)) != null) {
			this.effects = EffectList.fromNbt(tag.getList("effects", NbtElement.COMPOUND_TYPE));
		} else {
			this.effects = new EffectList();
		}
	}
	@Override
	public void writeToNbt(NbtCompound tag) {
		if(effects != null) {
			tag.put("effects", effects.asNbt());
		} else {
			tag.put("effects", null);
		}
	}
	@Override
	public void serverTick() {
		if(player.isAlive() && effects != null) {
			applyEffects();
		}
	}
}
