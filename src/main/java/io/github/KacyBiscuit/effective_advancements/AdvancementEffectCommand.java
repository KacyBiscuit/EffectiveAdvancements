package io.github.KacyBiscuit.effective_advancements;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.KacyBiscuit.effective_advancements.config.EffectiveConfigManager;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.StatusEffectArgumentType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.quiltmc.qsl.command.api.CommandRegistrationCallback;

import static com.mojang.brigadier.arguments.BoolArgumentType.bool;
import static com.mojang.brigadier.arguments.BoolArgumentType.getBool;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class AdvancementEffectCommand {
	public static void register() {
		CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, environment) -> {
			dispatcher.register(literal("advancementEffect").requires(source -> source.hasPermissionLevel(EffectiveConfigManager.PERMISSION_LEVEL.value()))
					.then(argument("player", EntityArgumentType.players())
							.then(literal("add")
									.then(literal("random")
											.then(argument("debuff", bool())
													.executes(context -> action(context, false, getBool(context, "debuff"), true, 0))))
									.then(literal("specific")
											.then(argument("effect", StatusEffectArgumentType.statusEffect())
													.executes(context -> action(context, false, false, false, 0)))))
							.then(literal("remove")
									.then(literal("random")
											.then(argument("debuff", bool())
													.executes(context -> action(context, true, getBool(context, "debuff"), true, 0))))
									.then(literal("specific")
											.then(argument("effect", StatusEffectArgumentType.statusEffect())
													.executes(context -> action(context, true, false, false, 0))))
									.then(literal("group")
											.then(literal("buffs")
													.executes(context -> action(context, true, false, true, 1)))
											.then(literal("debuffs")
													.executes(context -> action(context, true, true, true, 1)))
											.then(literal("everything")
													.executes(context -> action(context, true, false, true, 2)))))));


		});
/*
		CommandRegistrationCallback.EVENT.register(((dispatcher, buildContext, environment) -> {
			dispatcher.register(literal("debug")
					.executes((context -> {
						StatusEffectManager manager = context.getSource().getPlayer().getComponent(StatusEffectComponents.MANAGER);
						for (AdvancementEffect effect: manager.advancementEffects) {
							System.out.println(effect.toString());
						}
						return 1;
					})));
		}));
*/
	}

	private static int action(CommandContext<ServerCommandSource> context, boolean remove, boolean debuff, boolean random, int everything) throws CommandSyntaxException {
		try {
			StatusEffect effect = StatusEffectManager.randomEffect(debuff);
			if (!random) {
				effect = StatusEffectArgumentType.getStatusEffect(context, "effect");
			}
			for (ServerPlayerEntity player : EntityArgumentType.getPlayers(context, "player")) {
				StatusEffectManager manager = player.getComponent(StatusEffectComponents.MANAGER);
				if (remove) {
					switch (everything) {
						case 0:
							manager.remove(effect, false, true);
							break;
						case 1:
							manager.clearType(debuff);
							break;
						case 2:
							manager.reset();
							break;
					}
				} else {
					manager.add(effect);
				}
				context.getSource().sendFeedback(Text.literal("Success!"), true);
				manager.applyEffects();
			}
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}


}
