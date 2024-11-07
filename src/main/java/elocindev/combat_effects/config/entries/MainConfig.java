package elocindev.combat_effects.config.entries;

import java.util.List;

import elocindev.combat_effects.CombatEffects;
import elocindev.combat_effects.api.DataHolders;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;
import elocindev.necronomicon.config.Comment;
import elocindev.necronomicon.config.NecConfig;

public class MainConfig {
    @NecConfig public static MainConfig INSTANCE;

    public static String getFile() {
        return NecConfigAPI.getFile(CombatEffects.MODID+".json5");
    }

    @Comment("-------------------------------------------------------------------------------------------------------------------------")
	@Comment("                                             Combat Effects by ElocinDev")
	@Comment("-------------------------------------------------------------------------------------------------------------------------")
	@Comment(" ")
	@Comment("Definitions:")
	@Comment("  - entity_regex: A regex pattern to match an entity or multiple entities.")
    @Comment("    * This is the entity the player engages in combat to apply the effects to the player.")
    @Comment("  - effects_to_apply: A list of effects to apply, it can have a single one or multiple.")
    @Comment("    * effect_id: The id of the effect to apply.")
    @Comment("    * duration: The duration of the effect in ticks. (20 ticks = 1 second)")
    @Comment("    * amplifier: The amplifier of the effect (Starts counting from 0!).")
    @Comment("    * show_particles: If true, the particles of the effect will be shown.")
    @Comment("  - minimum_hp: The minimum health the entity must have to apply the effects.")
    @Comment("    * This is specially useful for bosses, you can set the minimum health to 200 to apply the effects to all entities.")
    @Comment("  - only_on_player_hit: If true, the effects will only be applied if the player gets hit, and will not apply by just the player attacking the entity.")
    @Comment("  - apply_to_attackers_instead: If true, the effects will be applied to the player's attackers instead of the player.")
	@Comment(" ")
	@Comment("-------------------------------------------------------------------------------------------------------------------------")
    @Comment(" ")
    @Comment("The default config adds makes the player get poison and wither when fighting example_entity.")
    @Comment(" ")
    public List<DataHolders.EntityHolder> entities = List.of(
        DataHolders.EntityHolder.create("examplemod:example_entity", List.of(
            DataHolders.EffectHolder.create("minecraft:poison", 100, 0, true, true),
            DataHolders.EffectHolder.create("minecraft:wither", 100, 0, true, true)
        ),
        0, false, false)
    );
}
