package io.github.KacyBiscuit.effective_advancements.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import io.github.KacyBiscuit.effective_advancements.StatusEffectManager;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;
import org.quiltmc.config.api.values.ValueList;

public class EffectiveConfigScreen  implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {

		return parent -> {
			ConfigBuilder builder = ConfigBuilder.create()
					.setParentScreen(parent)
					.setTitle(Text.translatable("effectiveAdvancements.config.title"));
			ConfigEntryBuilder entryBuilder = builder.entryBuilder();

			//

			ConfigCategory settings = builder.getOrCreateCategory(Text.translatable("effectiveAdvancements.config.category"));

			settings.addEntry(entryBuilder.startBooleanToggle(Text.translatable("effectiveAdvancements.config.amplify.name"), EffectiveConfigManager.AMPLIFY_DUPLICATES.value())
					.setDefaultValue(true)
					.setTooltip(Text.translatable("effectiveAdvancements.config.amplify.tooltip"))
					.setSaveConsumer(newValue -> EffectiveConfigManager.AMPLIFY_DUPLICATES.setValue(newValue, true))
					.build());

			settings.addEntry(entryBuilder.startSelector(Text.translatable("effectiveAdvancements.config.milk.name"), EffectiveConfigEnums.PositiveModes.values(), EffectiveConfigManager.MILK_MODE.value())
					.setDefaultValue(EffectiveConfigManager.MILK_MODE.getDefaultValue())
					.setTooltip(Text.translatable("effectiveAdvancements.config.milk.tooltip"))
					.setSaveConsumer(newValue -> EffectiveConfigManager.MILK_MODE.setValue(newValue, true))
					.build());
			settings.addEntry(entryBuilder.startSelector(Text.translatable("effectiveAdvancements.config.death.name"), EffectiveConfigEnums.NegativeModes.values(), EffectiveConfigManager.DEATH_MODE.value())
					.setDefaultValue(EffectiveConfigManager.DEATH_MODE.getDefaultValue())
					.setTooltip(Text.translatable("effectiveAdvancements.config.death.tooltip"))
					.setSaveConsumer(newValue -> EffectiveConfigManager.DEATH_MODE.setValue(newValue, true))
					.build());
			settings.addEntry(entryBuilder.startSelector(Text.translatable("effectiveAdvancements.config.totem.name"), EffectiveConfigEnums.PositiveModes.values(), EffectiveConfigManager.TOTEM_MODE.value())
					.setDefaultValue(EffectiveConfigManager.TOTEM_MODE.getDefaultValue())
					.setTooltip(Text.translatable("effectiveAdvancements.config.totem.tooltip"))
					.setSaveConsumer(newValue -> EffectiveConfigManager.TOTEM_MODE.setValue(newValue, true))
					.build());
			settings.addEntry(entryBuilder.startSelector(Text.translatable("effectiveAdvancements.config.apple.name"), EffectiveConfigEnums.PositiveModes.values(), EffectiveConfigManager.APPLE_MODE.value())
					.setDefaultValue(EffectiveConfigManager.APPLE_MODE.getDefaultValue())
					.setTooltip(Text.translatable("effectiveAdvancements.config.apple.tooltip"))
					.setSaveConsumer(newValue -> EffectiveConfigManager.APPLE_MODE.setValue(newValue, true))
					.build());
			settings.addEntry(entryBuilder.startStrList(Text.translatable("effectiveAdvancements.config.advancements.name"), EffectiveConfigManager.ADVANCEMENT_LIST.value())
					.setDefaultValue(EffectiveConfigManager.ADVANCEMENT_LIST.getDefaultValue())
					.setTooltip(Text.translatable("effectiveAdvancements.config.advancements.tooltip"))
					.setSaveConsumer(newValue -> {
						EffectiveConfigManager.ADVANCEMENT_LIST.value().clear();
						for (String value : newValue) {
							EffectiveConfigManager.ADVANCEMENT_LIST.value().add(value);
						}
					})
					.build());

			return builder.build();
		};
	}
}
