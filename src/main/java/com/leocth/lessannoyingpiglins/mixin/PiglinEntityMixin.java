package com.leocth.lessannoyingpiglins.mixin;

import com.leocth.lessannoyingpiglins.LessAnnoyingPiglin;
import com.leocth.lessannoyingpiglins.LessAnnoyingPiglins;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.mob.AbstractPiglinEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PiglinEntity.class)
public abstract class PiglinEntityMixin extends AbstractPiglinEntity
        implements LessAnnoyingPiglin // some heavy duty *duck* tape here
{
    @Shadow public abstract Brain<PiglinEntity> getBrain();

    private int cooldown;

    public PiglinEntityMixin(EntityType<? extends AbstractPiglinEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public int getCooldown() {
        return cooldown;
    }

    @Override
    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public int getMaxCooldown() {
        return LessAnnoyingPiglins.getConfig().getNeutralTicksAfterBarter();
    }

    @Inject(method = "mobTick", at = @At("TAIL"))
    private void mobTick(CallbackInfo info) {
        PiglinEntity piglin = (PiglinEntity) (Object) this; // fuckin pure evil
        if (cooldown > 0) {
            if (!PiglinBrainMixin.Invokers.isAdmiringItem(piglin))
                cooldown--;
        }
        else
            cooldown = 0;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void writeCustomDataToTag(NbtCompound tag, CallbackInfo info) {
        tag.putInt("LAPCooldown", this.getCooldown());
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void readCustomDataToTag(NbtCompound tag, CallbackInfo info) {
        if (tag.contains("LAPCooldown", 99))
            this.setCooldown(tag.getInt("LAPCooldown"));
    }

}
