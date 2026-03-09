package com.mervyn.effectsrebalancer;

import com.mervyn.effectsrebalancer.config.EffectsConfig;
<<<<<<< HEAD
import eu.midnightdust.lib.config.MidnightConfig;
=======
>>>>>>> origin/master
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EffectsRebalancerMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("effects-rebalancer");

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Effects Rebalancer...");
<<<<<<< HEAD
        MidnightConfig.init("effects-rebalancer", EffectsConfig.class);
=======
        EffectsConfig.load();
>>>>>>> origin/master
        LOGGER.info("Loaded config: Resistance={}, Regen={}, Absorption={}",
                EffectsConfig.resistanceModifier,
                EffectsConfig.regenerationAmount,
                EffectsConfig.absorptionAmount);
    }
}
