package com.leocth.lessannoyingpiglins.mixin;

import com.leocth.lessannoyingpiglins.LessAnnoyingPiglin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.mob.PiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(PiglinBrain.class)
public abstract class PiglinBrainMixin {

    @Inject(
            method = "consumeOffHandItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/mob/PiglinBrain;doBarter(Lnet/minecraft/entity/mob/PiglinEntity;Ljava/util/List;)V",
                    ordinal = 0
            )
    )
    private static void onConsumeOffhandItemThenBarter(PiglinEntity piglin, boolean bl, CallbackInfo info) {
        ((LessAnnoyingPiglin) piglin).cooldown();
    }

    @Inject(
            method = "getPreferredTarget",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/entity/ai/brain/sensor/Sensor;testAttackableTargetPredicate(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/entity/LivingEntity;)Z",
                    ordinal = 0
            ),
            cancellable = true
    )
    // idk i just like long and descriptive names like this one
    private static void onGetPreferredTargetAtTestAttackableTargetPredicate(PiglinEntity piglin, CallbackInfoReturnable<Optional<? extends LivingEntity>> info) {
        if (((LessAnnoyingPiglin) piglin).getCooldown() > 0) {
            // gives up selecting player to chase
            info.setReturnValue(Optional.empty());
        }
    }

    @Mixin(PiglinBrain.class)
    public interface Invokers {
        @Invoker("isAdmiringItem")
        static boolean isAdmiringItem(PiglinEntity entity) {
            throw new AssertionError("wait what");
        }
    }
}
