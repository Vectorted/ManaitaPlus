package com.vector.screen;

import com.vector.item.ManaitaItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.ScreenHandlerSlotUpdateS2CPacket;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CraftingManaitaScreenHandler extends CraftingScreenHandler {

    private static CraftingManaitaScreenHandler craftingManaitaScreenHandler;

    static int fold = 1;

    public CraftingManaitaScreenHandler(int syncId, PlayerInventory playerInventory, int maxFold) {
        super(syncId, playerInventory);
        craftingManaitaScreenHandler = this;

        fold = maxFold;
    }

    private static List<Slot> getSlotSize(List<Slot> slots) {
        List<Slot> _slots = new ArrayList<>();

        slots.forEach(slot -> {
            if(!slot.getStack().isEmpty()) {
                _slots.add(slot);
            }
        });
        return _slots;
    }

    private static ItemStack getManaitaWithItem() {
        List<Slot> slots = getSlotSize(craftingManaitaScreenHandler.getInputSlots());
        int size = slots.size();

        ItemStack item = ItemStack.EMPTY;

        if(size == 2) {
            for (int i = 0; i < size; i++) {
                if (slots.get(i).getStack().isOf(ManaitaItems.Common.MANAITA)) {
                    slots.remove(i);
                    return slots.get(0).getStack();
                }
            }
        }
        return null;
    }

    /**
     * @param syncId
     * @param playerInventory
     * @param maxFold
     * @return
     */
    @SuppressWarnings("unused")
    public static CraftingManaitaScreenHandler create(int syncId, PlayerInventory playerInventory, int maxFold) {
        if(craftingManaitaScreenHandler == null) {
            craftingManaitaScreenHandler = new CraftingManaitaScreenHandler(syncId, playerInventory, maxFold);
        }

        return craftingManaitaScreenHandler;
    }

    protected static void _updateResult(ScreenHandler handler, ServerWorld world, PlayerEntity player, RecipeInputInventory craftingInventory, CraftingResultInventory resultInventory, @Nullable RecipeEntry<CraftingRecipe> recipe) {
        CraftingRecipeInput craftingRecipeInput = craftingInventory.createRecipeInput();
        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)player;
        ItemStack itemStack = ItemStack.EMPTY;
        Optional<RecipeEntry<CraftingRecipe>> optional = world.getServer().getRecipeManager().getFirstMatch(RecipeType.CRAFTING, craftingRecipeInput, world, recipe);
        if (optional.isPresent()) {
            RecipeEntry<CraftingRecipe> recipeEntry = (RecipeEntry)optional.get();
            CraftingRecipe craftingRecipe = (CraftingRecipe)recipeEntry.value();
            if (resultInventory.shouldCraftRecipe(serverPlayerEntity, recipeEntry)) {
                ItemStack itemStack2 = craftingRecipe.craft(craftingRecipeInput, world.getRegistryManager());
                if (itemStack2.isItemEnabled(world.getEnabledFeatures())) {
                    itemStack = itemStack2;
                }
            }
        }

        ItemStack itemStack_return = ItemStack.EMPTY;

        ItemStack a = getManaitaWithItem();
        if(a != null) {
            itemStack_return = new ItemStack(a.getItem(), a.getCount() * fold);
        } else {
            itemStack_return = new ItemStack(itemStack.getItem(), itemStack.getCount() * fold);
        }

        resultInventory.setStack(0, itemStack_return);
        handler.setReceivedStack(0, itemStack_return);
        serverPlayerEntity.networkHandler.sendPacket(new ScreenHandlerSlotUpdateS2CPacket(handler.syncId, handler.nextRevision(), 0, itemStack_return));
    }

    @Override
    public void onInputSlotFillFinish(ServerWorld world, RecipeEntry<CraftingRecipe> recipe) {
        _updateResult(this, world, this.getPlayer(), this.craftingInventory, this.craftingResultInventory, null);
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        World world = this.getPlayer().getWorld();

        if (world instanceof ServerWorld) {
            _updateResult(this, (ServerWorld) world, this.getPlayer(), this.craftingInventory, this.craftingResultInventory, null);
        }
    }
    @Override
    public void onClosed(PlayerEntity player) {
        super.onClosed(player);
        this.dropInventory(player, this.craftingInventory);
    }
}
