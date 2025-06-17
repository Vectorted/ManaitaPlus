package com.vector.item.crafting;

import com.vector.screen.CraftingManaitaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemManaitaPlayerCraftRedStone extends Item {
    public ItemManaitaPlayerCraftRedStone(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new CraftingManaitaScreenHandler(syncId, playerInventory, 9), Text.of("x9")));
        return ActionResult.SUCCESS;
    }
}
