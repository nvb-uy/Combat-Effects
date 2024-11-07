package elocindev.combat_effects.api;

import java.util.List;

public class DataHolders {
    public static class EffectHolder {
        public static EffectHolder create(String effect_id, int duration, int amplifier, boolean ambient, boolean show_particles) {
            return new EffectHolder(effect_id, duration, amplifier, ambient, show_particles);
        }

        private EffectHolder(String effect_id, int duration, int amplifier, boolean ambient, boolean show_particles) {
            this.effect_id = effect_id;
            this.duration = duration;
            this.amplifier = amplifier;
            this.ambient = ambient;
            this.show_particles = show_particles;
        }

        public String effect_id;
        public int duration;
        public int amplifier;
        public boolean ambient;
        public boolean show_particles;
    }

    public static class EntityHolder {
        public static EntityHolder create(String entity_regex, List<EffectHolder> effects_to_apply, int minimum_hp, boolean only_on_player_hit, boolean apply_to_attackers_instead) {
            return new EntityHolder(entity_regex, effects_to_apply, minimum_hp, only_on_player_hit, apply_to_attackers_instead);
        }
        
        private EntityHolder(String entity_regex, List<EffectHolder> effects_to_apply, int minimum_hp, boolean only_on_player_hit, boolean apply_to_entity_instead_of_player) {
            this.entity_regex = entity_regex;
            this.effects_to_apply = effects_to_apply;
            this.minimum_hp = minimum_hp;
            this.only_on_player_hit = only_on_player_hit;
            this.apply_to_entity_instead_of_player = apply_to_entity_instead_of_player;
        }

        public String entity_regex;
        public List<EffectHolder> effects_to_apply;
        public int minimum_hp = 0;
        public boolean only_on_player_hit = false;
        public boolean apply_to_entity_instead_of_player = false;
    }
}
