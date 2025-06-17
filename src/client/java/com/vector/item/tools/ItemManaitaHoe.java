package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

public class ItemManaitaHoe extends Item {

    final int max = 100;

    int size = 1;
    public ItemManaitaHoe(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_HOE)) {
                list.add(ColorEffectHelper.createRainbowText(Text.translatable("item.vector.manaita_more").getString() + ": " + size + "x" + size, 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    public BlockPos getPlayerLookingAtBlock(PlayerEntity player) {
        if (player == null) return null;
        double reachDistance = 5.0;
        Vec3d playerPos = player.getEyePos();

        Vec3d playerLookDirection = player.getRotationVec(1.0F);
        Vec3d endPos = playerPos.add(playerLookDirection.x * reachDistance, playerLookDirection.y * reachDistance, playerLookDirection.z * reachDistance);

        RaycastContext context = new RaycastContext(playerPos, endPos, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, player);

        BlockHitResult hitResult = player.getWorld().raycast(context);

        if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
            BlockPos blockPos = hitResult.getBlockPos();
            return blockPos;
        }

        return null;
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

        BlockPos startBlock = getPlayerLookingAtBlock(user);
        if (startBlock == null) {
            return ActionResult.FAIL;
        }

        int y = startBlock.getY();

        for (int dx = -(size - 1); dx <= size; dx++) {
            for (int dz = -(size - 1); dz <= size; dz++) {
                BlockPos targetPos = startBlock.add(dx, 0, dz);
                BlockState targetState = world.getBlockState(targetPos);

                if (targetState.getBlock() == Blocks.DIRT || targetState.getBlock() == Blocks.GRASS_BLOCK) {
                    world.setBlockState(targetPos, Blocks.FARMLAND.getDefaultState());
                }
            }
        }

        return ActionResult.SUCCESS;
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
