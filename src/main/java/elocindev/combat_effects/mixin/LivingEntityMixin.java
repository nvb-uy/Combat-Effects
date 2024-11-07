package elocindev.combat_effects.mixin;

import java.util.List;
import java.util.regex.Pattern;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import elocindev.combat_effects.CombatEffects;
import elocindev.combat_effects.api.DataHolders;
import elocindev.combat_effects.config.entries.MainConfig;
import elocindev.necronomicon.api.NecUtilsAPI;
import elocindev.necronomicon.api.ResourceIdentifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    
    @Inject(method = "damage", at = @At("HEAD"))
    public void damage(DamageSource damageSource, float amount, CallbackInfo ci) {
        LivingEntity entity = (LivingEntity) (Object) this;

        if (damageSource.getAttacker() != null && damageSource.getAttacker() instanceof LivingEntity) {
            LivingEntity attacker = (LivingEntity) damageSource.getAttacker();
            if (entity instanceof ServerPlayerEntity) {
                ServerPlayerEntity player = (ServerPlayerEntity) entity;
                processEffects(player, attacker, true);
            } else if (entity instanceof LivingEntity && damageSource.getAttacker() instanceof PlayerEntity) {
                LivingEntity livingEntity = (LivingEntity) entity;
                PlayerEntity player = (PlayerEntity) damageSource.getAttacker();
                processEffects(livingEntity, player, false);
            }
        }
    }

    private void processEffects(LivingEntity entity, LivingEntity attacker, boolean isPlayer) {
        for (DataHolders.EntityHolder entityHolder : MainConfig.INSTANCE.entities) {
            if (checkMatch(entity, entityHolder.entity_regex) && entity.getHealth() >= entityHolder.minimum_hp) {
                if (isPlayer || !entityHolder.only_on_player_hit) {
                    if (entityHolder.apply_to_attackers_instead) {
                        applyEffects(attacker, entityHolder.effects_to_apply);
                    } else {
                        applyEffects(entity, entityHolder.effects_to_apply);
                    }
                }
            }
        }
    }

    private void applyEffects(LivingEntity entityToApply, List<DataHolders.EffectHolder> effects) {
        for (DataHolders.EffectHolder effect : effects) {
            var actualEffect = Registries.STATUS_EFFECT.get(ResourceIdentifier.get(effect.effect_id));
            if (actualEffect == null) {
                CombatEffects.LOGGER.error("Effect with id {} not found!", effect.effect_id);
                continue;
            }

            StatusEffectInstance effectInstance = new StatusEffectInstance(
                actualEffect, 
                effect.duration, 
                effect.amplifier, 
                false, 
                effect.show_particles
            );

            entityToApply.addStatusEffect(effectInstance);
        }
    }

    private boolean checkMatch(LivingEntity entity, String regex) {
        return Pattern.matches(regex, NecUtilsAPI.getEntityId(entity));
    }
}