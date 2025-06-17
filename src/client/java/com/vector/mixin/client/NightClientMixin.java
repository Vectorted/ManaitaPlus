package com.vector.mixin.client;

import com.vector.item.ManaitaItems;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({LightmapTextureManager.class})
public class NightClientMixin {

    public boolean hasManaitaHelmet(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).isOf(ManaitaItems.Armor.MANAITA_HELMET);
    }

    @Redirect(method = "update", at = @At(value = "INVOKE", target = "Ljava/lang/Double;floatValue()F"))
    private float update(Double value) {
        PlayerEntity player = MinecraftClient.getInstance().player;
        if(player != null && hasManaitaHelmet(player)) {
            return ((float) 1500 / 100);
        }
        return value.floatValue();
    }
}
