package com.vector.item.crafting;

import com.vector.screen.CraftingManaitaScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemManaitaPlayerCraft extends Item {

    int maxFold = 32767 * 3000;

    int fold = 64;
    public ItemManaitaPlayerCraft(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {

        if(user.isSneaking()) {
            int data = fold * 2;
            if(data < maxFold) {
                fold = data;
            } else fold = 64;

            return ActionResult.SUCCESS;
        }

        user.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, playerInventory, player) -> new CraftingManaitaScreenHandler(syncId, playerInventory, fold), Text.of("x" + fold)));
        return ActionResult.SUCCESS;
    }
}
