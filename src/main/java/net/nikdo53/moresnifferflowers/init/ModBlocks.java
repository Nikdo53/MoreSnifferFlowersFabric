package net.nikdo53.moresnifferflowers.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModBlocks {

    public static final Block AMBER_BLOCK = registerBlockWithItem("amber_block", new Block(AbstractBlock.Settings.create().strength(4F).requiresTool().sounds(BlockSoundGroup.GLASS)));
    public static final Block AMBER_MOSAIC = registerBlockWithItem("amber_mosaic", new Block(AbstractBlock.Settings.create().strength(4F).requiresTool().sounds(BlockSoundGroup.GLASS)));
    public static final Block AMBER_MOSAIC_SLAB = registerBlockWithItem("amber_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(AMBER_MOSAIC)));
    public static final Block AMBER_MOSAIC_STAIRS = registerBlockWithItem("amber_mosaic_stairs", new StairsBlock(AMBER_MOSAIC.getDefaultState(), AbstractBlock.Settings.copy(AMBER_MOSAIC)));

 /*   public static final Block DAWNBERRY_VINE = registerBlockNoItem("dawnberry_vine", new DawnberryVineBlock(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).emissiveLighting(value -> value.getLuminance(DawnberryVineBlock.AGE) >= 3 ? 3 : 0).ignitedByLava().randomTicks().noOcclusion(), false));
    public static final Block GLOOMBERRY_VINE = registerBlockNoItem("gloomberry_vine", new GloomberryVineBlock(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).ignitedByLava().pushReaction(PushReaction.DESTROY).randomTicks().noOcclusion()));

    public static final Block AMBUSH_BOTTOM = registerBlockNoItem("ambush_bottom", new AmbushBlockLower(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));
    public static final Block AMBUSH_TOP = registerBlockNoItem("ambush_top", new AmbushBlockUpper(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));
    public static final Block GARBUSH_BOTTOM = registerBlockNoItem("garbush_bottom", new GarbushBlockLower(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));
    public static final Block GARBUSH_TOP = registerBlockNoItem("garbush_top", new GarbushBlockUpper(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));

    public static final Block AMBER_BLOCK = registerBlockWithItem("amber_block", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block CHISELED_AMBER = registerBlockWithItem("chiseled_amber", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block CHISELED_AMBER_SLAB = registerBlockWithItem("chiseled_amber_slab", new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.CHISELED_AMBER)));
    public static final Block CRACKED_AMBER = registerBlockWithItem("cracked_amber", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block AMBER_MOSAIC = registerBlockWithItem("amber_mosaic", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block AMBER_MOSAIC_SLAB = registerBlockWithItem("amber_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.AMBER_MOSAIC)));
    public static final Block AMBER_MOSAIC_STAIRS = registerBlockWithItem("amber_mosaic_stairs", () -> stair(AMBER_MOSAIC));
    public static final Block AMBER_MOSAIC_WALL = registerBlockWithItem("amber_mosaic_wall", new WallBlock(AbstractBlock.Settings.copy(ModBlocks.AMBER_MOSAIC)));
    public static final Block GARNET_BLOCK = registerBlockWithItem("garnet_block", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(5.0F).noOcclusion()));
    public static final Block CHISELED_GARNET = registerBlockWithItem("chiseled_garnet", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block CHISELED_GARNET_SLAB = registerBlockWithItem("chiseled_garnet_slab", new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.CHISELED_GARNET)));
    public static final Block CRACKED_GARNET = registerBlockWithItem("cracked_garnet", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block GARNET_MOSAIC = registerBlockWithItem("garnet_mosaic", new TranslucentBlock(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).noOcclusion()));
    public static final Block GARNET_MOSAIC_SLAB = registerBlockWithItem("garnet_mosaic_slab", new SlabBlock(AbstractBlock.Settings.copy(ModBlocks.GARNET_MOSAIC)));
    public static final Block GARNET_MOSAIC_STAIRS = registerBlockWithItem("garnet_mosaic_stairs", () -> stair(GARNET_MOSAIC));
    public static final Block GARNET_MOSAIC_WALL = registerBlockWithItem("garnet_mosaic_wall", new WallBlock(AbstractBlock.Settings.copy(ModBlocks.GARNET_MOSAIC)));

    public static final Block CAULORFLOWER = registerBlockNoItem("caulorflower", () ->  new CaulorflowerBlock(AbstractBlock.Settings.create().mapColor(MapColor.COLOR_GREEN).sounds(BlockSoundGroup.GRASS).strength(2.0F).noCollision().noOcclusion().randomTicks()));

    public static final Block GIANT_CARROT = registerGiantCrop("giant_carrot", () ->  new GiantCropBlock(AbstractBlock.Settings.create().mapColor(MapColor.COLOR_YELLOW).instrument(NoteBlockInstrument.BANJO).strength(3.0F).sounds(BlockSoundGroup.MOSS_CARPET).noOcclusion().pushReaction(PushReaction.BLOCK)));
    public static final Block GIANT_POTATO = registerGiantCrop("giant_potato", () ->  new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).noOcclusion()));
    public static final Block GIANT_NETHERWART = registerGiantCrop("giant_netherwart", () ->  new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).noOcclusion()));
    public static final Block GIANT_BEETROOT = registerGiantCrop("giant_beetroot", () ->  new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).noOcclusion()));
    public static final Block GIANT_WHEAT = registerGiantCrop("giant_wheat", () ->  new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).noOcclusion()));

    public static final Block BONMEELIA = registerBlockNoItem("bonmeelia", () ->  new BonmeeliaBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F).lightLevel(value -> 3).noOcclusion(), false));
    public static final Block BONWILTIA = registerBlockNoItem("bonwiltia", () ->  new BonmeeliaBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F).lightLevel(value -> 3).noOcclusion(), true));
    public static final Block BONDRIPIA = registerBlockNoItem("bondripia", () ->  new BondripiaBlock(AbstractBlock.Settings.copy(Blocks.SPORE_BLOSSOM).strength(0.2F).lightLevel(value -> 3).noOcclusion().randomTicks().pushReaction(PushReaction.BLOCK)));
    public static final Block ACIDRIPIA = registerBlockNoItem("acidripia", () ->  new AciddripiaBlock(AbstractBlock.Settings.copy(Blocks.SPORE_BLOSSOM).strength(0.2F).lightLevel(value -> 3).noOcclusion().randomTicks().pushReaction(PushReaction.BLOCK)));
    public static final Block BONMEEL_FILLED_CAULDRON = registerBlockNoItem("bonmeel_filled_cauldron", () ->  new LayeredCauldronBlock(Biome.Precipitation.NONE, ModCauldronInteractions.BONMEEL, AbstractBlock.Settings.copy(Blocks.CAULDRON)));
    public static final Block ACID_FILLED_CAULDRON = registerBlockNoItem("acid_filled_cauldron", () ->  new LayeredCauldronBlock(Biome.Precipitation.NONE, ModCauldronInteractions.ACID, AbstractBlock.Settings.copy(Blocks.CAULDRON)));

    public static final Block CROPRESSOR_CENTER = registerBlockNoItem("cropressor_center", () ->  new CropressorBlockBase(AbstractBlock.Settings.copy(Blocks.ANVIL), CropressorBlockBase.Part.CENTER));
    public static final Block CROPRESSOR_OUT = registerBlockNoItem("cropressor_out", () ->  new CropressorBlockOut(AbstractBlock.Settings.copy(Blocks.ANVIL), CropressorBlockBase.Part.OUT));

    public static final Block REBREWING_STAND_BOTTOM = registerBlockNoItem("rebrewing_stand_bottom", new RebrewingStandBlockBase(AbstractBlock.Settings.create().requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));
    public static final Block REBREWING_STAND_TOP = registerBlockNoItem("rebrewing_stand_top", new RebrewingStandBlockTop(AbstractBlock.Settings.create().requiresCorrectToolForDrops().strength(0.5F).noOcclusion()));

    public static final Block DYESPRIA_PLANT = registerBlockNoItem("dyespria_plant", () ->  new DyespriaPlantBlock(AbstractBlock.Settings.create().mapColor(MapColor.PLANT).noCollision().instabreak().sounds(BlockSoundGroup.GRASS).pushReaction(PushReaction.DESTROY)));
    public static final Block DYESCRAPIA_PLANT = registerBlockNoItem("dyescrapia_plant", () ->  new DyescrapiaPlantBlock(AbstractBlock.Settings.copy(DYESPRIA_PLANT)));

    public static final Block CORRUPTED_LOG = registerBlockWithItem("corrupted_log", new RotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.WARPED_STEM)));
    public static final Block CORRUPTED_WOOD = registerBlockWithItem("corrupted_wood", new RotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.WARPED_HYPHAE)));
    public static final Block STRIPPED_CORRUPTED_LOG = registerBlockWithItem("stripped_corrupted_log", new RotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_STEM)));
    public static final Block STRIPPED_CORRUPTED_WOOD = registerBlockWithItem("stripped_corrupted_wood", new RotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_WARPED_HYPHAE)));
    public static final Block CORRUPTED_PLANKS = registerBlockWithItem("corrupted_planks", new Block(AbstractBlock.Settings.copy(Blocks.WARPED_PLANKS)));
    public static final Block CORRUPTED_STAIRS = registerBlockWithItem("corrupted_stairs", () -> stair(CORRUPTED_PLANKS));
    public static final Block CORRUPTED_SLAB = registerBlockWithItem("corrupted_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.WARPED_SLAB)));
    public static final Block CORRUPTED_FENCE = registerBlockWithItem("corrupted_fence", new FenceBlock(AbstractBlock.Settings.copy(Blocks.WARPED_FENCE)));
    public static final Block CORRUPTED_FENCE_GATE = registerBlockWithItem("corrupted_fence_gate", new FenceGateBlock(ModWoodTypes.CORRUPTED, AbstractBlock.Settings.copy(Blocks.WARPED_FENCE_GATE)));
    public static final Block CORRUPTED_DOOR = registerBlockWithItem("corrupted_door", new DoorBlock(BlockSetType.WARPED, AbstractBlock.Settings.copy(Blocks.WARPED_DOOR)));
    public static final Block CORRUPTED_TRAPDOOR = registerBlockWithItem("corrupted_trapdoor", new TrapDoorBlock(BlockSetType.WARPED, AbstractBlock.Settings.copy(Blocks.WARPED_TRAPDOOR)));
    public static final Block CORRUPTED_PRESSURE_PLATE = registerBlockWithItem("corrupted_pressure_plate", new PressurePlateBlock(BlockSetType.WARPED, AbstractBlock.Settings.copy(Blocks.WARPED_PRESSURE_PLATE)));
    public static final Block CORRUPTED_BUTTON = registerBlockWithItem("corrupted_button", new ButtonBlock(BlockSetType.WARPED, 30, AbstractBlock.Settings.copy(Blocks.WARPED_BUTTON)));
    public static final Block CORRUPTED_LEAVES = registerBlockWithItem("corrupted_leaves", new CorruptedLeavesBlock(AbstractBlock.Settings.copy(Blocks.ACACIA_LEAVES).noOcclusion()));
    public static final Block CORRUPTED_SAPLING = registerBlockWithItem("corrupted_sapling", new SaplingBlock(ModTreeGrowers.CORRUPTED_TREE, AbstractBlock.Settings.copy(Blocks.ACACIA_SAPLING)));
    public static final Block CORRUPTED_SLUDGE = registerBlockWithItem("corrupted_sludge", new CorruptedSludgeBlock(AbstractBlock.Settings.create().mapColor(MapColor.COLOR_MAGENTA).strength(2.0F).friction(0.8F).sounds(BlockSoundGroup.SLIME_BLOCK).lightLevel(value -> 4)));
    public static final Block CORRUPTED_SLIME_LAYER = registerBlockWithItem("corrupted_slime_layer", new CorruptedSlimeLayerBlock(AbstractBlock.Settings.create().mapColor(MapColor.COLOR_MAGENTA).strength(0.5F).friction(0.8F).noOcclusion().randomTicks().requiresCorrectToolForDrops().pushReaction(PushReaction.DESTROY).sounds(BlockSoundGroup.SLIME_BLOCK).lightLevel(value -> 4)));
    public static final Block CORRUPTED_SIGN = registerBlockNoItem("corrupted_sign", new ModStandingSignBlock(ModWoodTypes.CORRUPTED, AbstractBlock.Settings.copy(Blocks.WARPED_SIGN)));
    public static final Block CORRUPTED_WALL_SIGN = registerBlockNoItem("corrupted_wall_sign", new ModWallSignBlock(ModWoodTypes.CORRUPTED, AbstractBlock.Settings.copy(Blocks.WARPED_WALL_SIGN)));
    public static final Block CORRUPTED_HANGING_SIGN = registerBlockNoItem("corrupted_hanging_sign", new ModHangingSignBlock(ModWoodTypes.CORRUPTED, AbstractBlock.Settings.copy(Blocks.WARPED_HANGING_SIGN)));
    public static final Block CORRUPTED_WALL_HANGING_SIGN = registerBlockNoItem("corrupted_wall_hanging_sign", new ModWallHangingSign(ModWoodTypes.CORRUPTED, AbstractBlock.Settings.copy(Blocks.WARPED_WALL_HANGING_SIGN)));
    public static final Block CORRUPTED_LEAVES_BUSH = registerBlockWithItem("corrupted_leaves_bush", new CorruptedLeavesBlock(AbstractBlock.Settings.copy(ModBlocks.CORRUPTED_LEAVES).noOcclusion()));

    public static final Block DECAYED_LOG = registerBlockWithItem("decayed_log", new RotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block CORRUPTED_GRASS_BLOCK = registerBlockWithItem("corrupted_grass_block", new CorruptedGrassBlock(AbstractBlock.Settings.create().mapColor(MapColor.COLOR_PURPLE).randomTicks().strength(0.6F).sounds(BlockSoundGroup.WET_GRASS)));

    public static final Block VIVICUS_LOG = registerBlockWithItem("vivicus_log", new VivicusRotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LOG)));
    public static final Block VIVICUS_WOOD = registerBlockWithItem("vivicus_wood", new VivicusRotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_WOOD)));
    public static final Block STRIPPED_VIVICUS_LOG = registerBlockWithItem("stripped_vivicus_log", new VivicusRotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_LOG)));
    public static final Block STRIPPED_VIVICUS_WOOD = registerBlockWithItem("stripped_vivicus_wood", new VivicusRotatedPillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_CHERRY_WOOD)));
    public static final Block VIVICUS_PLANKS = registerBlockWithItem("vivicus_planks", new VivicusBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_PLANKS)));
    public static final Block VIVICUS_STAIRS = registerBlockWithItem("vivicus_stairs", () -> vivicusStair(VIVICUS_PLANKS));
    public static final Block VIVICUS_SLAB = registerBlockWithItem("vivicus_slab", new VivicusSlabBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_SLAB)));
    public static final Block VIVICUS_FENCE = registerBlockWithItem("vivicus_fence", new VivicusFenceBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE)));
    public static final Block VIVICUS_FENCE_GATE = registerBlockWithItem("vivicus_fence_gate", new VivicusFenceGateBlock(ModWoodTypes.VIVICUS, AbstractBlock.Settings.copy(Blocks.CHERRY_FENCE_GATE)));
    public static final Block VIVICUS_DOOR = registerBlockWithItem("vivicus_door", new VivicusDoorBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_DOOR)));
    public static final Block VIVICUS_TRAPDOOR = registerBlockWithItem("vivicus_trapdoor", new VivicusTrapDoorBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_TRAPDOOR)));
    public static final Block VIVICUS_PRESSURE_PLATE = registerBlockWithItem("vivicus_pressure_plate", new VivicusPressurePlateBlock(BlockSetType.CHERRY, AbstractBlock.Settings.copy(Blocks.CHERRY_PRESSURE_PLATE)));
    public static final Block VIVICUS_BUTTON = registerBlockWithItem("vivicus_button", new VivicusButtonBlock(BlockSetType.CHERRY, 30, AbstractBlock.Settings.copy(Blocks.CHERRY_BUTTON)));
    public static final Block VIVICUS_LEAVES = registerBlockWithItem("vivicus_leaves", new VivicusLeavesBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LEAVES)));
    public static final Block VIVICUS_SAPLING = registerBlockWithItem("vivicus_sapling", new VivicusSaplingBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_SAPLING)));
    public static final Block VIVICUS_LEAVES_SPROUT = registerBlockWithItem("vivicus_leaves_sprout", new VivicusSproutingBlock(AbstractBlock.Settings.copy(Blocks.MANGROVE_PROPAGULE)));
    public static final Block VIVICUS_SIGN = registerBlockNoItem("vivicus_sign", new VivicusStandingSignBlock(ModWoodTypes.VIVICUS, AbstractBlock.Settings.copy(Blocks.CHERRY_SIGN)));
    public static final Block VIVICUS_WALL_SIGN = registerBlockNoItem("vivicus_wall_sign", new VivicusWallSignBlock(ModWoodTypes.VIVICUS, AbstractBlock.Settings.copy(Blocks.CHERRY_WALL_SIGN)));
    public static final Block VIVICUS_HANGING_SIGN = registerBlockNoItem("vivicus_hanging_sign", new VivicusHangingSignBlock(ModWoodTypes.VIVICUS, AbstractBlock.Settings.copy(Blocks.CHERRY_HANGING_SIGN)));
    public static final Block VIVICUS_WALL_HANGING_SIGN = registerBlockNoItem("vivicus_wall_hanging_sign", new VivicusHangingWallSignBlock(ModWoodTypes.VIVICUS, AbstractBlock.Settings.copy(Blocks.CHERRY_WALL_HANGING_SIGN)));

    public static final Block BOBLING_HEAD = registerBlockNoItem("bobling_head", new BoblingHeadBlock(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK).noOcclusion()));
    public static final Block BOBLING_SACK = registerBlockNoItem("bobling_sack", new BoblingSackBlock(AbstractBlock.Settings.copy(Blocks.CHERRY_LEAVES)));

    public static final Block POTTED_DYESPRIA = registerBlockNoItem("potted_dyespria", new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, DYESPRIA_PLANT, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final Block POTTED_CORRUPTED_SAPLING = registerBlockNoItem("potted_corrupted_sapling", new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, CORRUPTED_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));
    public static final Block POTTED_VIVICUS_SAPLING = registerBlockNoItem("potted_vivicus_sapling", new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, VIVICUS_SAPLING, AbstractBlock.Settings.copy(Blocks.FLOWER_POT)));


  */

    private static Block registerBlockNoItem(String name, Block block){
        return Registry.register(Registries.BLOCK, Identifier.of(MoreSnifferFlowers.MOD_ID, name), block);
    }

    private static Block registerBlockWithItem(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MoreSnifferFlowers.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block){
        Registry.register(Registries.ITEM, Identifier.of(MoreSnifferFlowers.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        MoreSnifferFlowers.LOGGER.info("Registering ModBlocks for"+ MoreSnifferFlowers.MOD_ID);
    }
}
