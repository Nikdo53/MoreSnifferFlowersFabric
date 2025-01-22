package net.nikdo53.moresnifferflowers;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.nikdo53.moresnifferflowers.data.loot.ModLootTableProvider;
import net.nikdo53.moresnifferflowers.data.tag.ModBlockTagProvider;

public class MoreSnifferFlowersDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModBlockTagProvider::new);
		pack.addProvider(ModLootTableProvider::new);
	}
}
