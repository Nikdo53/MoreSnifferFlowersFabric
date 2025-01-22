package net.nikdo53.moresnifferflowers;

import net.fabricmc.api.ModInitializer;

import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModCreativeTabs;
import net.nikdo53.moresnifferflowers.init.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MoreSnifferFlowers implements ModInitializer {
	public static final String MOD_ID = "moresnifferflowers";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModCreativeTabs.registerCreativeTabs();

	}
}