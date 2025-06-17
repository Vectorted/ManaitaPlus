package com.vector.item.crafting;

import com.vector.screen.CraftingManaitaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemManaitaPlayerCraftDiamond extends Item {
    public ItemManaitaPlayerCraftDiamond(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new CraftingManaitaScreenHandler(syncId, playerInventory, 32), Text.of("x32")));
        return ActionResult.SUCCESS;
    }
}
