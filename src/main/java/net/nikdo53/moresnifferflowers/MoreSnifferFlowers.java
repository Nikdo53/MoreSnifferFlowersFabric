package net.nikdo53.moresnifferflowers;

import com.google.common.collect.Maps;
import net.fabricmc.api.ModInitializer;


import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.FireBlock;
import net.nikdo53.moresnifferflowers.events.ForgeEvents;
import net.nikdo53.moresnifferflowers.init.*;
import net.nikdo53.moresnifferflowers.networking.ModPacketHandler;
import net.nikdo53.moresnifferflowers.worldgen.configurations.ModTrunkPlacerTypes;
import net.nikdo53.moresnifferflowers.worldgen.feature.ModFeatures;
import net.nikdo53.moresnifferflowers.worldgen.structures.ModStructureTypes;
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
		ModFeatures.FEATURES.register();
		ModCreativeTabs.TABS.register();
		ModMobEffects.EFFECTS.register();
		ModSoundEvents.SOUNDS.register();
		ModPaintings.PAINTINGS.register();
		ModParticles.PARTICLES.register();
		ModMenuTypes.MENU_TYPES.register();
		ModEntityTypes.ENTITIES.register();
		ModTrunkPlacerTypes.TRUNKS.register();
		ModRecipeTypes.RECIPE_TYPES.register();
		ModBlockEntities.BLOCK_ENTITIES.register();
		ModLootModifiers.LOOT_MODIFIERS.register();
		ModStructureTypes.STRUCTURE_PIECE.register();
		ModBannerPatterns.BANNER_PATTERNS.register();
		ModRecipeSerializers.RECIPE_SERIALIZERS.register();
		ModEntityTypes.init();
		ModAdvancementCritters.init();

		ModCauldronInteractions.bootstrap();
		init();
		initEvents();

	}

	public static void initEvents(){
		ForgeEvents.init();
	}

	public static void init() {
		ModPacketHandler.init();

		AxeItem.STRIPPABLES = Maps.newHashMap(AxeItem.STRIPPABLES);
		AxeItem.STRIPPABLES.put(ModBlocks.CORRUPTED_LOG.get(), ModBlocks.STRIPPED_CORRUPTED_LOG.get());
		AxeItem.STRIPPABLES.put(ModBlocks.VIVICUS_LOG.get(), ModBlocks.STRIPPED_VIVICUS_LOG.get());



	/*	FlowerPotBlock pot = (FlowerPotBlock) Blocks.FLOWER_POT;
		pot.addPlant(ModBlocks.DYESPRIA_PLANT.getId(), ModBlocks.POTTED_DYESPRIA);
		pot.addPlant(ModBlocks.CORRUPTED_SAPLING.getId(), ModBlocks.POTTED_CORRUPTED_SAPLING);
		pot.addPlant(ModBlocks.VIVICUS_SAPLING.getId(), ModBlocks.POTTED_VIVICUS_SAPLING);

	 */

		FireBlock fireBlock = (FireBlock) Blocks.FIRE;
		fireBlock.setFlammable(ModBlocks.CORRUPTED_LOG.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_WOOD.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.STRIPPED_CORRUPTED_LOG.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.STRIPPED_CORRUPTED_WOOD.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_STAIRS.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_SLAB.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_FENCE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_DOOR.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_TRAPDOOR.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_PRESSURE_PLATE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_BUTTON.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_LEAVES.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.CORRUPTED_SAPLING.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_LOG.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_WOOD.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.STRIPPED_VIVICUS_LOG.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.STRIPPED_VIVICUS_WOOD.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_PLANKS.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_STAIRS.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_SLAB.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_FENCE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_FENCE_GATE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_DOOR.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_TRAPDOOR.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_PRESSURE_PLATE.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_BUTTON.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_LEAVES.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_SAPLING.get(), 5, 20);
		fireBlock.setFlammable(ModBlocks.VIVICUS_LEAVES_SPROUT.get(), 5, 20);

		ComposterBlock.add(0.3F, ModItems.DAWNBERRY_VINE_SEEDS.get());
		ComposterBlock.add(0.3F, ModItems.DAWNBERRY.get());
		ComposterBlock.add(0.3F, ModItems.AMBUSH_SEEDS.get());
		ComposterBlock.add(0.4F, ModItems.CAULORFLOWER_SEEDS.get());
		ComposterBlock.add(0.4F, ModItems.DYESPRIA_SEEDS.get());
		ComposterBlock.add(0.5F, ModItems.BONMEELIA_SEEDS.get());
		ComposterBlock.add(1.0F, ModItems.CROPRESSED_BEETROOT.get());
		ComposterBlock.add(1.0F, ModItems.CROPRESSED_NETHERWART.get());
		ComposterBlock.add(1.0F, ModItems.CROPRESSED_WHEAT.get());
		ComposterBlock.add(1.0F, ModItems.CROPRESSED_POTATO.get());
		ComposterBlock.add(1.0F, ModItems.CROPRESSED_CARROT.get());
		ComposterBlock.add(1.0F, ModBlocks.CORRUPTED_SAPLING.get());
		ComposterBlock.add(1.0F, ModBlocks.VIVICUS_SAPLING.get());
		ComposterBlock.add(1.0F, ModBlocks.CORRUPTED_LEAVES.get());
		ComposterBlock.add(1.0F, ModBlocks.VIVICUS_LEAVES.get());

	}

	public static ResourceLocation loc(String path) {
		return new ResourceLocation(MOD_ID, path);
	}

	public static ResourceLocation prefix(String name) {
		return new ResourceLocation(MOD_ID, name.toLowerCase(Locale.ROOT));
	}

}