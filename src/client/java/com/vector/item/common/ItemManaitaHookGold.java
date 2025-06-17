package com.vector.item.common;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;

public class ItemManaitaHookGold extends Item {
    public ItemManaitaHookGold(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.of(stack.getComponents().getOrDefault(DataComponentTypes.ITEM_NAME, ScreenTexts.EMPTY).getString());
    }
}
