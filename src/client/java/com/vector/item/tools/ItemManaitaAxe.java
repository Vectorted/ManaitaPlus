package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.BlockState;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ItemManaitaAxe extends Item {
    public ItemManaitaAxe(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_AXE)) {
                list.add(ColorEffectHelper.createRainbowText(Text.translatable("item.vector.manaita_sword_god_ball2").getString(), 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return Float.MAX_VALUE;
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.of(stack.getComponents().getOrDefault(DataComponentTypes.ITEM_NAME, ScreenTexts.EMPTY).getString());
    }
}
