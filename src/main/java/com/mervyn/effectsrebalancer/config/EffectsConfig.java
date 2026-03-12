package com.mervyn.effectsrebalancer.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class EffectsConfig extends MidnightConfig {
    @Entry
    public static boolean enableCustomResistanceFormula = false; // Enable custom resistance logic

    @Entry
    public static double resistanceModifier = 0.20; // 20% reduction per level

    @Entry
    public static float regenerationAmount = 1.0f; // 1 HP (0.5 hearts)

    @Entry
    public static boolean enableMaxHealthRegen = false; // Enable custom regen logic

    @Entry(min = 0.0f, max = 1.0f)
    public static float regenerationMaxHealthPercentage = 0.02f; // 2% of Max Health healed per second, per level

    @Entry(min = 1, max = 1200)
    public static int healingCooldownTicks = 20; // The amount of ticks that should pass before a healing is attempted

    @Entry(min = 0, max = 100)
    public static int absorptionAmount = 4; // 4 points (2 hearts) per level
}
