package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class ItemManaitaBow extends Item {
    public ItemManaitaBow(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_BOW)) {
                list.add(ColorEffectHelper.createRainbowText(Text.translatable("item.vector.manaita_power").getString(), 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    @Override
    public Text getName(ItemStack stack) {
        return Text.of(stack.getComponents().getOrDefault(DataComponentTypes.ITEM_NAME, ScreenTexts.EMPTY).getString());
    }

    private void shootArrow(PlayerEntity player, World world) {
        ArrowEntity arrow = new ArrowEntity(EntityType.ARROW, world);
        arrow.setPosition(player.getEyePos());
        arrow.setDamage(Integer.MAX_VALUE);
        arrow.setNoGravity(true);
        arrow.setCustomName(Text.of("manaita"));

        float f = player.getYaw() * MathHelper.PI / 180.0F;
        float g = player.getPitch() * MathHelper.PI / 180.0F;
        arrow.setVelocity(
                -MathHelper.sin(f) * MathHelper.cos(g),
                -MathHelper.sin(g),
                MathHelper.cos(f) * MathHelper.cos(g),
                2.0F, 0.0F);

        world.spawnEntity(arrow);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        shootArrow(user, world);
        return ActionResult.FAIL;
    }
}
