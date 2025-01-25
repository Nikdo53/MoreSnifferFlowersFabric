package net.nikdo53.moresnifferflowers;

import net.fabricmc.api.ModInitializer;


import net.minecraft.resources.ResourceLocation;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.networking.ModPacketHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;

public class MoreSnifferFlowers implements ModInitializer {
	public static final String MOD_ID = "moresnifferflowers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.BLOCKS.register();
		ModItems.ITEMS.register();
		ModPacketHandler.registerC2SPackets();
	}

	public static ResourceLocation loc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
	}

}