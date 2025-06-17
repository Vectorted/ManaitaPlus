package com.vector.utils;

import com.vector.item.ManaitaItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;

public class Player {

    static boolean getGod(PlayerEntity player) {
        PlayerInventory playerInventory = player.getInventory();

        for(int i = 0; i < playerInventory.size(); i++) {
            if(playerInventory.getStack(i).isOf(ManaitaItems.Tools.MANAITA_SWORD_GOD)) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasManaitaAll(PlayerEntity player) {
        return getGod(player) ||
                player.getEquippedStack(EquipmentSlot.HEAD).isOf(ManaitaItems.Armor.MANAITA_HELMET) ||
                player.getEquippedStack(EquipmentSlot.CHEST).isOf(ManaitaItems.Armor.MANAITA_CHEST) ||
                player.getEquippedStack(EquipmentSlot.LEGS).isOf(ManaitaItems.Armor.MANAITA_LEGGING) ||
                player.getEquippedStack(EquipmentSlot.FEET).isOf(ManaitaItems.Armor.MANAITA_BOOT) ||
                player.getInventory().contains(ManaitaItems.Tools.MANAITA_BOW.getDefaultStack());
    }

    public static boolean getMainHnadManaitaSwordGod(PlayerEntity player) {
        return player.getMainHandStack().isOf(ManaitaItems.Tools.MANAITA_SWORD_GOD);
    }

    public static boolean getMainHandManaitaBow(PlayerEntity player) {
        return player.getMainHandStack().isOf(ManaitaItems.Tools.MANAITA_BOW);
    }

    public static boolean getOffHandManaitaBow(PlayerEntity player) {
        return player.getOffHandStack().isOf(ManaitaItems.Tools.MANAITA_BOW);
    }

    public static boolean hasManaitaArmor(PlayerEntity player) {
        return player.getEquippedStack(EquipmentSlot.HEAD).isOf(ManaitaItems.Armor.MANAITA_HELMET) ||
                player.getEquippedStack(EquipmentSlot.CHEST).isOf(ManaitaItems.Armor.MANAITA_CHEST) ||
                player.getEquippedStack(EquipmentSlot.LEGS).isOf(ManaitaItems.Armor.MANAITA_LEGGING) ||
                player.getEquippedStack(EquipmentSlot.FEET).isOf(ManaitaItems.Armor.MANAITA_BOOT);
    }
}
