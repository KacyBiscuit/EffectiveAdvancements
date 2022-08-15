# Effective Advancements
### A Minecraft Mod for Quilt Loader 1.19.2
Effective Advancements is a minecraft mod that allows players to obtain permanent effects by completing certain in-game
advancements. It also offers a config file with a wide range of customisable options to allow you to change various
parts of the mod to fit your needs.

**In the current version [(1.0.0)](https://github.com/KacyBiscuit/EffectiveAdvancements/releases/tag/1.0.0), You're able to customise:**
- Which advancements are obtainable as buffs and debuffs
- The maximum level that each effect can get to
- How the effects are changed by actions such as death, drinking milk, using totems, and eating golden apples
- Which advancements allow you to obtain effects

**Features planned for future versions include**
- An improved Config Screen for ModMenu that allows you edit more of the config file
- The ability to choose the rarity of each effect
- A way to pair effects so that, for example, if you get swiftness whilst already having slowness, they will just cancel
out, and you will have neither.

### Commands
At the moment Effective Advancements only had one command, but it should be able to handle whatever you need.

`/advancementEffect <player> <add|remove> <random|specific|group> ...`

For the last option, Random means a random effect, Specific means any effect (it does not have to be in the config
files, but if it isn't it will be treated as a buff and have a maximum level of 10), And group allows you remove many at
a time, with the options being Buffs, Debuffs and Everything.

It's also worth nothing that in the command if it asks for True or False, False will mean a buff and true will mean a
debuff

For example:
`/advancementEffect StarlytKaitlyn add random true` grants StarlytKaitlyn (me) a random debuff.

### Notes
It is recommended that you play with the [ModMenu](https://modrinth.com/mod/modmenu) mod to allow yuo to edit the conifg in game

This mod should only be nessercary as server side but i recommend also running it client side just in case.
