package com.mervyn.effectsrebalancer.mixin;

import com.mervyn.effectsrebalancer.config.SyncedConfig;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StatusEffect.class)
public class RegenerationMixin {

    @ModifyArg(method = "applyUpdateEffect(Lnet/minecraft/entity/LivingEntity;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;heal(F)V"), index = 0)
    public float modifyRegenAmount(float amount) {
        if ("effect.minecraft.regeneration".equals(((StatusEffect) (Object) this).getTranslationKey())) {
            return SyncedConfig.regenerationAmount;
        }
        return amount;
    }
}
