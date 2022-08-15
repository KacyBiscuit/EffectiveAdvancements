package io.github.KacyBiscuit.effective_advancements.config;

public class EffectiveConfigEnums {
	public enum PositiveModes implements ConfigEnum{
		NO_EFFECT,
		REMOVE_EFFECTS,
		REMOVE_DEBUFFS,
		REMOVE_DEBUFF;

		@Override
		public Enum<?> next() {
			var enumValues = this.getDeclaringClass().getEnumConstants();
			return enumValues[this.ordinal() + 1 < enumValues.length ? this.ordinal() + 1 : 0];
		}
	}
	public enum NegativeModes implements ConfigEnum{
		NO_EFFECT,
		REMOVE_EFFECTS,
		REMOVE_RANDOM,
		RANDOM_DEBUFF;

		@Override
		public Enum<?> next() {
			var enumValues = this.getDeclaringClass().getEnumConstants();
			return enumValues[this.ordinal() + 1 < enumValues.length ? this.ordinal() + 1 : 0];
		}
	}


	public interface ConfigEnum {
		Enum<?> next();
	}
}
