package com.vector.item.tools;

import com.vector.color.ColorEffectHelper;
import com.vector.item.ManaitaItems;
import com.vector.utils.Player;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.*;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class ItemManaitaSwordGod extends Item {
    public ItemManaitaSwordGod(Settings settings) {
        super(settings);

        ItemTooltipCallback.EVENT.register((itemStack, tooltipContext, tooltipType, list) -> {
            if(itemStack.isOf(ManaitaItems.Tools.MANAITA_SWORD_GOD)) {

                String lable2 = Text.translatable("item.vector.manaita_sword_god_ball2").getString();
                String lable3 = Text.translatable("item.vector.manaita_sword_god_ball3").getString();
                String lable4 = Text.translatable("item.vector.manaita_sword_god_in_mainHand").getString() + ":";
                String lable5 = " "+Text.translatable("item.vector.manaita_sword_god_damage").getString();

                list.add(ColorEffectHelper.createRainbowText(lable2, 5.0f, 1, 1.3f, 1.0f, 0.06f));
                list.add(ColorEffectHelper.createRainbowText(lable3, 5.0f, 1, 1.3f, 1.0f, 0.06f));

                list.add(Text.empty());
                list.add(ColorEffectHelper.createRainbowText(lable4, 5.0f, 1, 1.0f, 1.0f, 0.06f));
                list.add(ColorEffectHelper.createRainbowText(lable5, 5.0f, 1, 1.0f, 1.0f, 0.06f));
            }
        });
    }

    @Override
    public boolean canMine(ItemStack stack, BlockState state, World world, BlockPos pos, LivingEntity user) {
        PlayerEntity player = (PlayerEntity) user;
        return !player.getAbilities().creativeMode;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {

    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return true;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        Box box = user.getBoundingBox().expand(350);
        user.addExperience(1000);
        user.getWorld().getPlayers().forEach(player -> {
            if(!(player instanceof OtherClientPlayerEntity) &&  !Player.hasManaitaAll(player)) {
                player.setHealth(0);
            }
        });
        world.getEntitiesByClass(LivingEntity.class, box, entity -> {
            if(!entity.isPlayer()) {
                if(entity.getUuid() != user.getUuid()) {
                    if(user.isSneaking()) {
                        entity.onDeath(user.getDamageSources().fall());
                        entity.setHealth(0.0f);
                    } else {
                        if(entity instanceof HostileEntity) {
                            entity.onDeath(user.getDamageSources().fall());
                            entity.setHealth(0.0f);
                        }
                    }
                }
            }

            return true;
        });
        for (int i = 0; i < 60; i++) {
            Vec3d playerPos = user.getPos();
            Random random = new Random();
            double distance = 10 + random.nextDouble() * 20;

            double angle = random.nextDouble() * 2 * Math.PI;
            double xOffset = distance * Math.cos(angle);
            double zOffset = distance * Math.sin(angle);

            Vec3d lightningPos = playerPos.add(xOffset, 0, zOffset);

            BlockPos groundPos = new BlockPos((int) lightningPos.x, 0, (int) lightningPos.z);
            int groundY = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, groundPos).getY();

            lightningPos = new Vec3d(lightningPos.x, groundY, lightningPos.z);

            LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            lightningEntity.setPos(lightningPos.x, lightningPos.y, lightningPos.z);

            world.spawnEntity(lightningEntity);
        }

        user.setCurrentHand(hand);
        return ActionResult.CONSUME;
    }

    @Override
    public Text getName(ItemStack stack) {
        String itemName = stack.getComponents().getOrDefault(DataComponentTypes.ITEM_NAME, ScreenTexts.EMPTY).getString();

        return ColorEffectHelper.createRainbowText(itemName, 5.0f, 1, 1.0f, 1.0f, 0.06f);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        return ActionResult.PASS;
    }

    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(target.isPlayer() && Player.hasManaitaAll((PlayerEntity) target)) {
            return;
        }

        target.onDeath(attacker.getDamageSources().fall());
        target.setHealth(0);

        if(target instanceof WitherSkeletonEntity) {
            target.getWorld().spawnEntity(new ItemEntity(target.getWorld(), target.getX(), target.getY(), target.getZ(), new ItemStack(Items.WITHER_SKELETON_SKULL)));
        }

        if(target.getHealth() > 0) {
            target.remove(Entity.RemovalReason.KILLED);
        }
    }
}