package elocindev.combat_effects.config;

import elocindev.combat_effects.config.entries.ExampleConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

public class Configs {
    public static ExampleConfig MAIN = ExampleConfig.INSTANCE;

    public static void loadCommonConfigs() {
        NecConfigAPI.registerConfig(ExampleConfig.class);
    }

    public static void loadClientConfigs() {}
}
