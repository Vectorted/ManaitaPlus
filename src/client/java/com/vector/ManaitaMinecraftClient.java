package com.vector;

import com.vector.item.ManaitaItems;
import com.vector.render.Lightning;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradedItem;
import net.minecraft.village.VillagerProfession;

import java.util.Optional;

public class ManaitaMinecraftClient implements ClientModInitializer {

	public static PlayerEntity player;

	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(EntityType.LIGHTNING_BOLT, Lightning::new);

		ManaitaItems.Crafting.getAll().forEach(crafting -> {
			TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,1,  factories -> {
				factories.add((entity, random) -> new TradeOffer(
						new TradedItem(crafting, 64),
						new ItemStack(ManaitaItems.Tools.MANAITA_BOW,1),
						1, 1000,0.0f
				));
			});
		});
		TradeOfferHelper.registerVillagerOffers(VillagerProfession.WEAPONSMITH,1,  factories -> {
			factories.add((entity, random) -> new TradeOffer(
					new TradedItem(Items.NETHER_STAR, 64),
					Optional.of(new TradedItem(ManaitaItems.Tools.MANAITA_BOW, 1)),
					new ItemStack(ManaitaItems.Tools.MANAITA_SWORD_GOD,1),
					1,1000,0.0f
			));
		});

		ManaitaItems.initialize();
		Groups.initialize();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (client.player != null) {

				client.player.getWorld().getPlayers().forEach(play -> {
					player = client.player;
				});
			}
		});
	}
}