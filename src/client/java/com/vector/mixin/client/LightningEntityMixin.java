package com.vector.mixin.client;

import net.minecraft.entity.LightningEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public class LightningEntityMixin {

    @Shadow
    @Final
    private static int field_30062 = 10;

    @Inject(method = "tick", at = @At("RETURN"))
    public void tick(CallbackInfo info) {

    }

    @Inject(method = "spawnFire", at = @At("HEAD"), cancellable = true)
    private void spawnFire(int spreadAttempts, CallbackInfo info) {
        info.cancel();
    }
}
