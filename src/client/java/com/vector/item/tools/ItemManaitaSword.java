package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import com.vector.utils.Player;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ItemManaitaSword extends Item {
    public ItemManaitaSword(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_SWORD)) {
                list.add(ColorEffectHelper.createRainbowText(Text.translatable("item.vector.manaita_power").getString(), 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.of(stack.getComponents().getOrDefault(DataComponentTypes.ITEM_NAME, ScreenTexts.EMPTY).getString());
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target.isPlayer() && Player.hasManaitaAll((PlayerEntity) target)) {
            return;
        }

        target.onDeath(attacker.getDamageSources().fall());
        target.setHealth(0);

        if(target instanceof WitherSkeletonEntity) {
            target.getWorld().spawnEntity(new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), new ItemStack(Items.WITHER_SKELETON_SKULL)));
        }

        if(target.getHealth() > 0) {
            target.remove(Entity.RemovalReason.KILLED);
        }
    }
}
