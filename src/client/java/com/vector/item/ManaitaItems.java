package com.vector.item;

import com.vector.item.common.*;
import com.vector.item.crafting.*;
import com.vector.item.tools.*;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BlocksAttacksComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ManaitaItems {

    public static class Crafting {

        public static List<Item> getAll() {
            return Arrays.asList(MANAITA_CRAFTING, MANAITA_CRAFTING_WOODEN, MANAITA_CRAFTING_STONE, MANAITA_CRAFTING_IRON, MANAITA_CRAFTING_GOLD, MANAITA_CRAFTING_DIAMOND, MANAITA_CRAFTING_EMERALD, MANAITA_CRAFTING_REDSTONE);
        }

        public static void init() {

        }

        public static final Item PLAYER_MANAITA_CRAFTING = register("player_crafting_manaita", ItemManaitaPlayerCraft::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_WOODEN = register("player_crafting_manaita_wooden", ItemManaitaPlayerCraftWooden::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_STONE = register("player_crafting_manaita_stone", ItemManaitaPlayerCraftStone::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_IRON = register("player_crafting_manaita_iron", ItemManaitaPlayerCraftIron::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_GOLD = register("player_crafting_manaita_gold", ItemManaitaPlayerCraftGold::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_DIAMOND = register("player_crafting_manaita_diamond", ItemManaitaPlayerCraftDiamond::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_EMERALD = register("player_crafting_manaita_emerald", ItemManaitaPlayerCraftEmerald::new, new Item.Settings().maxCount(64));

        public static final Item PLAYER_MANAITA_CRAFTING_REDSTONE = register("player_crafting_manaita_redstone", ItemManaitaPlayerCraftRedStone::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING = register("crafting_manaita", ItemManaitaCraft::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_WOODEN = register("crafting_manaita_wooden", ItemManaitaCraftWooden::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_STONE = register("crafting_manaita_stone", ItemManaitaCraftStone::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_IRON = register("crafting_manaita_iron", ItemManaitaCraftIron::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_GOLD = register("crafting_manaita_gold", ItemManaitaCraftGold::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_EMERALD = register("crafting_manaita_emerald", ItemManaitaCraftEmerald::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_DIAMOND = register("crafting_manaita_diamond", ItemManaitaCraftDiamond::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_CRAFTING_REDSTONE = register("crafting_manaita_redstone", ItemManaitaCraftRedStone::new, new Item.Settings().maxCount(64));
    }

    public static class Common {
        public static final Item MANAITA = register("manaita", ItemManaita::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK = register("manaita_hook", ItemManaitaHook::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_WOODEN = register("manaita_hook_wooden", ItemManaitaHookWooden::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_STONE = register("manaita_hook_stone", ItemManaitaHookStone::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_IRON = register("manaita_hook_iron", ItemManaitaHookIron::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_GOLD = register("manaita_hook_gold", ItemManaitaHookGold::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_REDSTOONE = register("manaita_hook_redstone", ItemManaitaHookRedStone::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_EMERALD = register("manaita_hook_emerald", ItemManaitaHookEmerald::new, new Item.Settings().maxCount(64));

        public static final Item MANAITA_HOOK_DIAMOND = register("manaita_hook_diamond", ItemManaitaHookEmerald::new, new Item.Settings().maxCount(64));

        public static void init() {

        }
    }

    public static class Tools {

        public static void init() {

        }
        public static final Item MANAITA_SWORD_GOD = register("manaita_sword_god", ItemManaitaSwordGod::new, new Item.Settings().maxCount(1).component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(
                0.0f,
                0.0f,
                null,
                null,
                null,
                null,
                null
        )));

        public static final Item MANAITA_SWORD = register("manaita_sword", ItemManaitaSword::new, new Item.Settings().maxCount(1).component(DataComponentTypes.BLOCKS_ATTACKS, new BlocksAttacksComponent(
                1.0f,
                1.0f,
                null,
                null,
                null,
                null,
                null
        )));

        public static final Item MANAITA_PICKAXE = register("manaita_pickaxe", ItemManaitaPickaxe::new, new Item.Settings().maxCount(1).pickaxe(ToolMaterial.NETHERITE, Integer.MAX_VALUE, Integer.MAX_VALUE).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_AXE = register("manaita_axe", ItemManaitaAxe::new, new Item.Settings().maxCount(1).axe(ToolMaterial.NETHERITE, Integer.MAX_VALUE, Integer.MAX_VALUE).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_HOE = register("manaita_hoe", ItemManaitaHoe::new, new Item.Settings().maxCount(1).axe(ToolMaterial.NETHERITE, Integer.MAX_VALUE, Integer.MAX_VALUE).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_SHOVEL = register("manaita_shovel", ItemManaitaShovel::new, new Item.Settings().maxCount(1).axe(ToolMaterial.NETHERITE, Integer.MAX_VALUE, Integer.MAX_VALUE).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_BOW = register("manaita_bow", ItemManaitaBow::new, new Item.Settings().maxCount(1));

        public static final Item MANAITA_SHEARS = register("manaita_shears", ItemManaitaShears::new, new Item.Settings().maxCount(1));
    }

    public static class Armor {

        public static void init() {

        }
        public static final RegistryKey<EquipmentAsset> ARMOR_MATERIAL_KEY = RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, Identifier.of("vector", ""));

        public static final Item MANAITA_HELMET = register("manaita_helmet", ItemManaitaHelmet::new, new Item.Settings().maxCount(1).armor(new ArmorMaterial(
                Integer.MAX_VALUE,
                Map.of(
                        EquipmentType.HELMET, 5
                ),
                5,
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                0.0F,
                0.0F,
                null,
                ARMOR_MATERIAL_KEY
        ), EquipmentType.HELMET).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_CHEST = register("manaita_chest", ItemManaitaChest::new, new Item.Settings().maxCount(1).armor(new ArmorMaterial(
                Integer.MAX_VALUE,
                Map.of(
                        EquipmentType.CHESTPLATE, 5
                ),
                5,
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                0.0F,
                0.0F,
                null,
                ARMOR_MATERIAL_KEY
        ), EquipmentType.CHESTPLATE).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_LEGGING = register("manaita_legging", ItemManaitaLegging::new, new Item.Settings().maxCount(1).armor(new ArmorMaterial(
                Integer.MAX_VALUE,
                Map.of(
                        EquipmentType.LEGGINGS, 5
                ),
                5,
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                0.0F,
                0.0F,
                null,
                ARMOR_MATERIAL_KEY
        ), EquipmentType.LEGGINGS).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));

        public static final Item MANAITA_BOOT = register("manaita_boot", ItemManaitaBoot::new, new Item.Settings().maxCount(1).armor(new ArmorMaterial(
                Integer.MAX_VALUE,
                Map.of(
                        EquipmentType.BOOTS, 5
                ),
                5,
                SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE,
                0.0F,
                0.0F,
                null,
                ARMOR_MATERIAL_KEY
        ), EquipmentType.BOOTS).component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE));
    }

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of("vector", name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));

        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
    public static void initialize() {
        Crafting.init();
        Common.init();
        Tools.init();
        Armor.init();
    }
}
