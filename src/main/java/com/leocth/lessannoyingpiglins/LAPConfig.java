package com.leocth.lessannoyingpiglins;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = "lessannoyingpiglins")
public class LAPConfig implements ConfigData {
    private int neutralTicksAfterBarter = 300;

    public int getNeutralTicksAfterBarter() {
        return neutralTicksAfterBarter;
    }
}
