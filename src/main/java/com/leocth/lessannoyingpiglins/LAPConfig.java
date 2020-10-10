package com.leocth.lessannoyingpiglins;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;

@Config(name = "lessannoyingpiglins")
public class LAPConfig implements ConfigData {
    private int neutralTicksAfterBarter = 300;

    public int getNeutralTicksAfterBarter() {
        return neutralTicksAfterBarter;
    }
}
