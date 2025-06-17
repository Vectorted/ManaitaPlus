package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemManaitaShears extends Item {

    final int max = 64;

    int size = 1;
    public ItemManaitaShears(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_SHEARS)) {
                list.add(ColorEffectHelper.createRainbowText(Text.translatable("item.vector.manaita_more").getString() + ": " + size + "x" + size, 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        return Float.MAX_VALUE;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(user.isSneaking()) {
            int s = size * 2;
            if(s < max) {
                size = s;
            } else size = 1;
            user.sendMessage(ColorEffectHelper.createRainbowText(size + "x" + size, 5.0f, 1, 1.0f, 1.0f, 0.06f), true);
            return ActionResult.PASS;
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        for (int dx = -(size - 1); dx <= size; dx++) {
            for (int dy = -(size - 1); dy <= size; dy++) {
                for (int dz = -(size - 1); dz <= size; dz++) {
                    BlockPos targetPos = pos.add(dx, dy, dz);
                    BlockState targetState = world.getBlockState(targetPos);
                    Block block = targetState.getBlock();

                    if(block.toString().contains("leaves")) {
                        world.breakBlock(targetPos, false);
                        world.spawnEntity(new ItemEntity(world,targetPos.getX(), targetPos.getY(), targetPos.getZ(), new ItemStack(block)));
                    }
                }
            }
        }
        return true;
    }
}
