package com.vector;

import com.vector.item.ManaitaItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public final class Groups {

    public static final ItemGroup MANAITA_GROUP = Registry.register(Registries.ITEM_GROUP, Identifier.of("vector", "group"), FabricItemGroup.builder()
            .icon(() -> new ItemStack(ManaitaItems.Tools.MANAITA_SWORD_GOD))
            .displayName(Text.translatable("itemGroup.vector.group"))
            .entries((context, entries) -> {
                entries.add(ManaitaItems.Tools.MANAITA_SWORD_GOD);
                entries.add(ManaitaItems.Tools.MANAITA_SWORD);
                entries.add(ManaitaItems.Tools.MANAITA_SHEARS);
                entries.add(ManaitaItems.Tools.MANAITA_SHOVEL);
                entries.add(ManaitaItems.Tools.MANAITA_HOE);
                entries.add(ManaitaItems.Tools.MANAITA_PICKAXE);
                entries.add(ManaitaItems.Tools.MANAITA_AXE);
                entries.add(ManaitaItems.Tools.MANAITA_BOW);
                entries.add(ManaitaItems.Armor.MANAITA_HELMET);
                entries.add(ManaitaItems.Armor.MANAITA_CHEST);
                entries.add(ManaitaItems.Armor.MANAITA_LEGGING);
                entries.add(ManaitaItems.Armor.MANAITA_BOOT);

                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_WOODEN);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_STONE);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_IRON);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_GOLD);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_DIAMOND);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_EMERALD);
                entries.add(ManaitaItems.Crafting.PLAYER_MANAITA_CRAFTING_REDSTONE);

                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_WOODEN);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_STONE);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_IRON);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_GOLD);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_EMERALD);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_DIAMOND);
                entries.add(ManaitaItems.Crafting.MANAITA_CRAFTING_REDSTONE);

                entries.add(ManaitaItems.Common.MANAITA);

                entries.add(ManaitaItems.Common.MANAITA_HOOK);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_WOODEN);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_STONE);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_IRON);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_GOLD);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_REDSTOONE);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_EMERALD);
                entries.add(ManaitaItems.Common.MANAITA_HOOK_DIAMOND);
            })
            .build());

    public static void initialize() {
    }
}
