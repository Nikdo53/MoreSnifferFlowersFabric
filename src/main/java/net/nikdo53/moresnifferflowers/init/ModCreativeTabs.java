package net.nikdo53.moresnifferflowers.init;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.loader.impl.lib.sat4j.pb.IPBSolver;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModCreativeTabs {
    public static final ItemGroup MORE_SNIFFER_FLOWERS = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MoreSnifferFlowers.MOD_ID, "moresnifferflowers"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.DAWNBERRY))
                    .displayName(Text.translatable("moresnifferflowers.creative_tab"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.DAWNBERRY);
                        // entries.add(ModItems.GLOOMBERRY_VINE_SEEDS);
                      //  entries.add(ModItems.GLOOMBERRY);

                      //  entries.add(ModItems.AMBUSH_SEEDS);
                        entries.add(ModBlocks.AMBER_BLOCK);
                        entries.add(ModBlocks.AMBER_MOSAIC);
                        entries.add(ModBlocks.AMBER_MOSAIC_STAIRS);
                        entries.add(ModBlocks.AMBER_MOSAIC_SLAB);
                     /*   entries.add(ModBlocks.AMBER_MOSAIC_WALL);
                        entries.add(ModBlocks.CHISELED_AMBER);
                        entries.add(ModBlocks.CHISELED_AMBER_SLAB);
                        entries.add(ModBlocks.CRACKED_AMBER);

                        entries.add(ModItems.AMBER_SHARD);
                        entries.add(ModItems.AROMA_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModItems.DRAGONFLY);
                        entries.add(ModItems.AMBUSH_BANNER_PATTERN);

                        entries.add(ModItems.GARBUSH_SEEDS);
                        entries.add(ModBlocks.GARNET_BLOCK);
                        entries.add(ModBlocks.GARNET_MOSAIC);
                        entries.add(ModBlocks.GARNET_MOSAIC_STAIRS);
                        entries.add(ModBlocks.GARNET_MOSAIC_SLAB);
                        entries.add(ModBlocks.GARNET_MOSAIC_WALL);
                        entries.add(ModBlocks.CHISELED_GARNET);
                        entries.add(ModBlocks.CHISELED_GARNET_SLAB);
                        entries.add(ModBlocks.CRACKED_GARNET);
                        entries.add(ModItems.GARNET_SHARD);

                        entries.add(ModItems.CARNAGE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModItems.EVIL_BANNER_PATTERN);

                        entries.add(ModItems.DYESPRIA_SEEDS);
                        entries.add(ModItems.DYESPRIA);
                        entries.add(ModItems.DYESCRAPIA);

                        entries.add(ModBlocks.CAULORFLOWER);

                        entries.add(ModItems.BONMEELIA_SEEDS);
                        entries.add(ModItems.JAR_OF_BONMEEL);
                        entries.add(ModItems.BONDRIPIA_SEEDS);
                        entries.add(ModItems.BONWILTIA_SEEDS);
                        entries.add(ModItems.JAR_OF_ACID);
                        entries.add(ModItems.ACIDRIPIA_SEEDS);

                        entries.add(ModItems.BELT_PIECE);
                        entries.add(ModItems.ENGINE_PIECE);
                        entries.add(ModItems.TUBE_PIECE);
                        entries.add(ModItems.SCRAP_PIECE);
                        entries.add(ModItems.PRESS_PIECE);
                        entries.add(ModItems.CROPRESSOR);

                        entries.add(ModItems.CROPRESSED_CARROT);
                        entries.add(ModItems.CAROTENE_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModBlocks.GIANT_CARROT);

                        entries.add(ModItems.CROPRESSED_POTATO);
                        entries.add(ModItems.TATER_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModBlocks.GIANT_POTATO);

                        entries.add(ModItems.CROPRESSED_WHEAT);
                        entries.add(ModItems.GRAIN_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModBlocks.GIANT_WHEAT);

                        entries.add(ModItems.CROPRESSED_BEETROOT);
                        entries.add(ModItems.BEAT_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModBlocks.GIANT_BEETROOT);

                        entries.add(ModItems.CROPRESSED_NETHERWART);
                        entries.add(ModItems.NETHER_WART_ARMOR_TRIM_SMITHING_TEMPLATE);
                        entries.add(ModBlocks.GIANT_NETHERWART);

                        entries.add(ModItems.EXTRACTION_BOTTLE);
                        entries.add(ModItems.BROKEN_REBREWING_STAND);
                        entries.add(ModItems.REBREWING_STAND);

                        entries.add(ModItems.BOBLING_SPAWN_EGG);
                        entries.add(ModItems.CORRUPTED_BOBLING_CORE);
                        entries.add(ModItems.CORRUPTED_SLIME_BALL);
                        entries.add(ModItems.BOBLING_CORE);
                        entries.add(ModItems.VIVICUS_ANTIDOTE);

                        entries.add(ModBlocks.DECAYED_LOG);
                        entries.add(ModBlocks.CORRUPTED_GRASS_BLOCK);

                        entries.add(ModBlocks.VIVICUS_LOG);
                        entries.add(ModBlocks.VIVICUS_WOOD);
                        entries.add(ModBlocks.STRIPPED_VIVICUS_LOG);
                        entries.add(ModBlocks.STRIPPED_VIVICUS_WOOD);
                        entries.add(ModBlocks.VIVICUS_PLANKS);
                        entries.add(ModBlocks.VIVICUS_STAIRS);
                        entries.add(ModBlocks.VIVICUS_SLAB);
                        entries.add(ModBlocks.VIVICUS_FENCE);
                        entries.add(ModBlocks.VIVICUS_FENCE_GATE);
                        entries.add(ModBlocks.VIVICUS_DOOR);
                        entries.add(ModBlocks.VIVICUS_TRAPDOOR);
                        entries.add(ModBlocks.VIVICUS_PRESSURE_PLATE);
                        entries.add(ModBlocks.VIVICUS_BUTTON);
                        entries.add(ModBlocks.VIVICUS_LEAVES);
                        entries.add(ModBlocks.VIVICUS_SAPLING);
                        entries.add(ModBlocks.VIVICUS_LEAVES_SPROUT);
                        entries.add(ModItems.VIVICUS_SIGN);
                        entries.add(ModItems.VIVICUS_HANGING_SIGN);
                        entries.add(ModItems.VIVICUS_BOAT);
                        entries.add(ModItems.VIVICUS_CHEST_BOAT);

                        entries.add(ModBlocks.CORRUPTED_LOG);
                        entries.add(ModBlocks.CORRUPTED_WOOD);
                        entries.add(ModBlocks.STRIPPED_CORRUPTED_LOG);
                        entries.add(ModBlocks.STRIPPED_CORRUPTED_WOOD);
                        entries.add(ModBlocks.CORRUPTED_PLANKS);
                        entries.add(ModBlocks.CORRUPTED_STAIRS);
                        entries.add(ModBlocks.CORRUPTED_SLAB);
                        entries.add(ModBlocks.CORRUPTED_FENCE);
                        entries.add(ModBlocks.CORRUPTED_FENCE_GATE);
                        entries.add(ModBlocks.CORRUPTED_DOOR);
                        entries.add(ModBlocks.CORRUPTED_TRAPDOOR);
                        entries.add(ModBlocks.CORRUPTED_PRESSURE_PLATE);
                        entries.add(ModBlocks.CORRUPTED_BUTTON);
                        entries.add(ModBlocks.CORRUPTED_LEAVES);
                        entries.add(ModBlocks.CORRUPTED_LEAVES_BUSH);
                        entries.add(ModBlocks.CORRUPTED_SAPLING);
                        entries.add(ModBlocks.CORRUPTED_SLUDGE);
                        entries.add(ModBlocks.CORRUPTED_SLIME_LAYER);
                        entries.add(ModItems.CORRUPTED_SIGN);
                        entries.add(ModItems.CORRUPTED_HANGING_SIGN);
                        entries.add(ModItems.CORRUPTED_BOAT);
                        entries.add(ModItems.CORRUPTED_CHEST_BOAT);

                      */

                    }).build());


    public static void   registerCreativeTabs() {
        MoreSnifferFlowers.LOGGER.info("Registering ModCreativeTabs for" + MoreSnifferFlowers.MOD_ID);
    }
}
