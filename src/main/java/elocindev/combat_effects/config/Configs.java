package elocindev.combat_effects.config;

import elocindev.combat_effects.config.entries.MainConfig;
import elocindev.necronomicon.api.config.v1.NecConfigAPI;

public class Configs {
    public static MainConfig MAIN = MainConfig.INSTANCE;

    public static void loadCommonConfigs() {
        NecConfigAPI.registerConfig(MainConfig.class);
    }

    public static void loadClientConfigs() {}
}
