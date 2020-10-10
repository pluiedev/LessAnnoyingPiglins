package com.leocth.lessannoyingpiglins;

/**
 * More like a less annoying 'duck', ain't it?
 * Hahaha very funni joke
 */
public interface LessAnnoyingPiglin {

    int getCooldown();
    int getMaxCooldown();
    void setCooldown(int cooldown);

    default void cooldown() {
        this.setCooldown(this.getMaxCooldown());
    }

}
