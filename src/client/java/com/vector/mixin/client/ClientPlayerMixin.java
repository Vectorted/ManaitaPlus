package com.vector.mixin.client;

import com.vector.ManaitaMinecraftClient;
import com.vector.utils.Player;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class ClientPlayerMixin extends LivingEntity {

    protected ClientPlayerMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    LivingEntity entity;

    @Inject(at = @At("HEAD"), method = "damage", cancellable = true)
    public void onDamage(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> infoReturnable) {
        if(entity != null && entity.isPlayer()) {
            if(Player.hasManaitaAll((PlayerEntity) entity)) {
                infoReturnable.setReturnValue(false);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void onTick(CallbackInfo callbackInfo) {
        entity = (LivingEntity) (Object)this;
        if(ManaitaMinecraftClient.player != null) {
            if (Player.hasManaitaAll(ManaitaMinecraftClient.player)) {
                ManaitaMinecraftClient.player.getAbilities().allowFlying = true;
                ManaitaMinecraftClient.player.setHealth(ManaitaMinecraftClient.player.getMaxHealth());
            } else {
                if (!ManaitaMinecraftClient.player.getAbilities().creativeMode) {
                    ManaitaMinecraftClient.player.getAbilities().allowFlying = false;
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "addExhaustion", cancellable = true)
    public void addExhaustion(float exhaustion, CallbackInfo info) {
        if(ManaitaMinecraftClient.player != null && Player.hasManaitaAll(ManaitaMinecraftClient.player)) {
            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "onDeath", cancellable = true)
    private void onDeath(DamageSource damageSource, CallbackInfo callbackInfo) {
        if(ManaitaMinecraftClient.player != null && Player.hasManaitaAll(ManaitaMinecraftClient.player)) {
            callbackInfo.cancel();
        }
    }
}
