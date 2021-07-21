package com.leocth.lessannoyingpiglins;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LessAnnoyingPiglins implements ModInitializer {

    public static final Logger LOGGER = LogManager.getLogger("LessAnnoyingPiglins");
    private static LAPConfig config;

    public static LAPConfig getConfig() {
        return config;
    }

    @Override
    public void onInitialize() {
        AutoConfig.register(LAPConfig.class, Toml4jConfigSerializer::new);
        config = AutoConfig.getConfigHolder(LAPConfig.class).getConfig();
        LOGGER.info("The piglins are now more vulnerable to the shiny, golden bars of wealth you have graciously given to them. Hurray.");
    }
}
