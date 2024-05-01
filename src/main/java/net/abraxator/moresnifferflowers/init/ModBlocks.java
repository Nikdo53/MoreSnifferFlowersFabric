package net.abraxator.moresnifferflowers.init;

import net.abraxator.moresnifferflowers.MoreSnifferFlowers;
import net.abraxator.moresnifferflowers.blocks.*;
import net.abraxator.moresnifferflowers.blocks.ambush.AmbushBlockBase;
import net.abraxator.moresnifferflowers.blocks.ambush.AmbushBlockUpper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block DAWNBERRY_VINE = registerBlockNoItem("dawnberry_vine", new DawnberryVineBlock(AbstractBlock.Settings.create().mapColor(MapColor.LICHEN_GREEN).replaceable().noCollision().strength(0.2F).sounds(BlockSoundGroup.GLOW_LICHEN).luminance(state -> state.get(DawnberryVineBlock.AGE) >= 3 ? 3 : 0).burnable().pistonBehavior(PistonBehavior.DESTROY).ticksRandomly().nonOpaque()));
    public static final Block BOBLING_HEAD = registerBlockNoItem("bobling_head", new BoblingHeadBlock(AbstractBlock.Settings.copy(Blocks.MOSS_BLOCK).nonOpaque()));
    public static final Block AMBUSH_TOP = registerBlockNoItem("ambush_top", new AmbushBlockUpper(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));
    public static final Block AMBUSH_BOTTOM = registerBlockNoItem("ambush_bottom", new AmbushBlockBase(AbstractBlock.Settings.copy(Blocks.WHEAT).strength(0.2F)));
    public static final Block AMBER = registerBlockWithItem("amber", new Block(AbstractBlock.Settings.create().mapColor(MapColor.ORANGE).sounds(BlockSoundGroup.GLASS).strength(3.0F).nonOpaque()));
    public static final Block CAULORFLOWER = registerBlockWithItem("caulorflower", new CaulorflowerBlock(AbstractBlock.Settings.create().mapColor(MapColor.GREEN).sounds(BlockSoundGroup.GRASS).strength(2.0F).noCollision().nonOpaque().ticksRandomly()));
    public static final Block GIANT_CARROT = registerBlockNoItem("giant_carrot", new GiantCropBlock(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW).instrument(Instrument.BANJO).strength(0.5F).sounds(BlockSoundGroup.MOSS_CARPET).nonOpaque()));
    public static final Block GIANT_POTATO = registerBlockNoItem("giant_potato", new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).nonOpaque()));
    public static final Block GIANT_NETHERWART = registerBlockNoItem("giant_netherwart", new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).nonOpaque()));
    public static final Block GIANT_BEETROOT = registerBlockNoItem("giant_beetroot", new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).nonOpaque()));
    public static final Block GIANT_WHEAT = registerBlockNoItem("giant_wheat", new GiantCropBlock(AbstractBlock.Settings.copy(ModBlocks.GIANT_CARROT).nonOpaque()));
    public static final Block BONMEELIA = registerBlockNoItem("bonmeelia", new BonmeeliaBlock(AbstractBlock.Settings.copy(Blocks.WHEAT).luminance(value -> 3).nonOpaque()));
    public static final Block CROPRESSOR = registerBlockNoItem("cropressor", new CropressorBlock(AbstractBlock.Settings.copy(Blocks.ANVIL)));
    public static final Block MORE_SNIFFER_FLOWER = registerBlockNoItem("more_sniffer_flower", new CropressorBlock(AbstractBlock.Settings.copy(Blocks.ANVIL)));

    public static void registerBlockProperties() {
    }

    private static Block registerBlockNoItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(MoreSnifferFlowers.MOD_ID, name), block);
    }

    private static Block registerBlockWithItem(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MoreSnifferFlowers.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(MoreSnifferFlowers.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        MoreSnifferFlowers.LOGGER.info("Registering Blocks for" + MoreSnifferFlowers.MOD_ID);

    }

}
