package com.mervyn.effectsrebalancer.config;

public class SyncedConfig {
    // Volatile to ensure thread safety when mixins read off main thread while
    // server updates it
    public static volatile boolean enableCustomResistanceFormula = false;
    public static volatile double resistanceModifier = EffectsConfig.resistanceModifier;
    public static volatile float regenerationAmount = EffectsConfig.regenerationAmount;
    public static volatile boolean enableMaxHealthRegen = EffectsConfig.enableMaxHealthRegen;
    public static volatile float regenerationMaxHealthPercentage = EffectsConfig.regenerationMaxHealthPercentage;
    public static volatile int healingCooldownTicks = EffectsConfig.healingCooldownTicks;
    public static volatile int absorptionAmount = EffectsConfig.absorptionAmount;

    // Copies the standard local disk values into the active synced configuration
    public static void reset() {
        enableCustomResistanceFormula = EffectsConfig.enableCustomResistanceFormula;
        resistanceModifier = EffectsConfig.resistanceModifier;
        regenerationAmount = EffectsConfig.regenerationAmount;
        enableMaxHealthRegen = EffectsConfig.enableMaxHealthRegen;
        regenerationMaxHealthPercentage = EffectsConfig.regenerationMaxHealthPercentage;
        healingCooldownTicks = EffectsConfig.healingCooldownTicks;
        absorptionAmount = EffectsConfig.absorptionAmount;
    }
}
