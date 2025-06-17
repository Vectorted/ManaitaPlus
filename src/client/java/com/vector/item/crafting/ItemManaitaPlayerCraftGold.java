package com.vector.item.crafting;

import com.vector.screen.CraftingManaitaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemManaitaPlayerCraftGold extends Item {
    public ItemManaitaPlayerCraftGold(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new CraftingManaitaScreenHandler(syncId, playerInventory, 16), Text.of("x16")));
        return ActionResult.SUCCESS;
    }
}
