package net.nikdo53.moresnifferflowers;

import net.fabricmc.api.ModInitializer;


import net.minecraft.resources.ResourceLocation;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreSnifferFlowers implements ModInitializer {
	public static final String MOD_ID = "moresnifferflowers";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.BLOCKS.register();
		ModItems.ITEMS.register();
	}

	public static ResourceLocation loc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}