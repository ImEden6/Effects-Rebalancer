package com.mervyn.effectsrebalancer.mixin;

import com.mervyn.effectsrebalancer.config.EffectsConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.registry.tag.DamageTypeTags;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LivingEntity.class)
public abstract class ResistanceMixin {

    @Shadow
    public abstract boolean hasStatusEffect(StatusEffect effect);

    @Shadow
    @Nullable
    public abstract StatusEffectInstance getStatusEffect(StatusEffect effect);

    /**
     * @author Mervyn
     * @reason Disable vanilla resistance calculation to apply custom logic.
     *         Note: use Redirect here specifically on the call within
     *         modifyAppliedDamage to isolate our change.
     */
    @Redirect(method = "modifyAppliedDamage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;hasStatusEffect(Lnet/minecraft/entity/effect/StatusEffect;)Z"))
    private boolean disableVanillaResistance(LivingEntity instance, StatusEffect effect) {
        if (effect == StatusEffects.RESISTANCE) {
            return false;
        }
        return this.hasStatusEffect(effect);
    }

    /**
     * Apply custom resistance logic based on configuration.
     */
    @ModifyVariable(method = "modifyAppliedDamage", at = @At("HEAD"), argsOnly = true)
    private float customResistance(float amount, DamageSource source) {
        // Check standard bypass logic
        if (!source.isIn(DamageTypeTags.BYPASSES_RESISTANCE) && this.hasStatusEffect(StatusEffects.RESISTANCE)) {
            StatusEffectInstance effect = this.getStatusEffect(StatusEffects.RESISTANCE);
            if (effect == null) {
                return amount;
            }

            int amplifier = effect.getAmplifier() + 1;
            float reduction = Math.min((float) (amplifier * EffectsConfig.resistanceModifier), 1.0F);

            float newAmount = amount * (1.0F - reduction);
            return Math.max(newAmount, 0.0F);
        }
        return amount;
    }
}
