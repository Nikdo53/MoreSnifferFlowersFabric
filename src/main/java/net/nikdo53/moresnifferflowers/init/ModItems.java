package net.nikdo53.moresnifferflowers.init;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.Component;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

import java.util.List;

public class ModItems {

    public static final Item DAWNBERRY = registerItem("dawnberry", new Item(new Item.Settings()));
    public static final Item AMBER_SHARD = registerItem("amber_shard", new Item(new Item.Settings()));

  /*  public static final Item DAWNBERRY_VINE_SEEDS = registerItem("dawnberry_vine_seeds", new BlockItem(ModBlocks.DAWNBERRY_VINE.get(), new Item.Settings()));
    public static final Item GLOOMBERRY_VINE_SEEDS = registerItem("gloomberry_vine_seeds", new BlockItem(ModBlocks.GLOOMBERRY_VINE.get(), new Item.Settings()));
    public static final Item DAWNBERRY = registerItem("dawnberry", new Item(new Item.Settings().food(ModFoods.DAWNBERRY)));
    public static final Item GLOOMBERRY = registerItem("gloomberry", new Item(new Item.Settings().food(ModFoods.GLOOMBERRY)));

    public static final Item AMBUSH_SEEDS = registerItem("ambush_seeds", new BlockItem(ModBlocks.AMBUSH_BOTTOM.get(), new Item.Settings()));
    public static final Item GARBUSH_SEEDS = registerItem("garbush_seeds", new BlockItem(ModBlocks.GARBUSH_BOTTOM.get(), new Item.Settings()));

    public static final Item AMBUSH_BANNER_PATTERN = registerItem("ambush_banner_pattern", new BannerPatternItem(ModTags.ModBannerPatternTags.AMBUSH_BANNER_PATTERN, new Item.Settings().stacksTo(1)));
    public static final Item EVIL_BANNER_PATTERN = registerItem("evil_banner_pattern", new BannerPatternItem(ModTags.ModBannerPatternTags.EVIL_BANNER_PATTERN, new Item.Settings().stacksTo(1)));

    public static final Item AMBER_SHARD = registerItem("amber_shard", new TrimMaterialItem(new Item.Settings()));
    public static final Item GARNET_SHARD = registerItem("garnet_shard", new TrimMaterialItem(new Item.Settings()));

    public static final Item AROMA_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("aroma_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.AROMA));
    public static final Item CARNAGE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("carnage_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.CARNAGE));
    public static final Item DRAGONFLY = registerItem("dragonfly", new DragonflyItem(new Item.Settings()));
    public static final Item DYESPRIA = registerItem("dyespria", new DyespriaItem(new Item.Settings().stacksTo(1)));
    public static final Item DYESCRAPIA = registerItem("dyescrapia", new DyescrapiaItem(new Item.Settings().stacksTo(1)));
    public static final Item DYESPRIA_SEEDS = registerItem("dyespria_seeds", new BlockItem(ModBlocks.DYESPRIA_PLANT.get(), new Item.Settings()) {
        @Override
        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            pTooltipComponents.add(Component.translatableWithFallback("tooltip.dyespria_seeds", "Shear to hide dye").withStyle(ChatFormatting.GOLD));
        }
    });

    public static final Item BONMEELIA_SEEDS = registerItem("bonmeelia_seeds", new BlockItem(ModBlocks.BONMEELIA.get(), new Item.Settings()));
    public static final Item JAR_OF_BONMEEL = registerItem("jar_of_bonmeel", new JarOfBonmeelItem(new Item.Settings()));
    public static final Item BONDRIPIA_SEEDS = registerItem("bondripia_seeds", new BlockItem(ModBlocks.BONDRIPIA.get(), new Item.Settings()) {
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            pTooltipComponents.add(Component.translatableWithFallback("tooltip.bondripia_seeds", "Plantable underneath an area of 5 blocks in a + shape").withStyle(ChatFormatting.GOLD));
        }
    });

    public static final Item BONWILTIA_SEEDS = registerItem("bonwiltia_seeds", new BlockItem(ModBlocks.BONWILTIA.get(), new Item.Settings()));
    public static final Item JAR_OF_ACID = registerItem("jar_of_acid", new JarOfAcidItem(new Item.Settings()) {
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            pTooltipComponents.add(Component.translatableWithFallback("tooltip.acid_jar", "Ungrows organic blocks").withStyle(ChatFormatting.GOLD));
        }
    });

    public static final Item ACIDRIPIA_SEEDS = registerItem("acidripia_seeds", new BlockItem(ModBlocks.ACIDRIPIA.get(), new Item.Settings()));

    public static final Item CROPRESSOR = registerItem("cropressor", new CropressorItem(ModBlocks.CROPRESSOR_OUT.get(), new Item.Settings()));
    public static final Item TUBE_PIECE = registerItem("tube_piece", new Item(new Item.Settings()));
    public static final Item BELT_PIECE = registerItem("belt_piece", new Item(new Item.Settings()));
    public static final Item SCRAP_PIECE = registerItem("scrap_piece", new Item(new Item.Settings()));
    public static final Item ENGINE_PIECE = registerItem("engine_piece", new Item(new Item.Settings()));
    public static final Item PRESS_PIECE = registerItem("press_piece", new Item(new Item.Settings()));

    public static final Item REBREWING_STAND = registerItem("rebrewing_stand", new BlockItem(ModBlocks.REBREWING_STAND_BOTTOM.get(), new Item.Settings()));
    public static final Item BROKEN_REBREWING_STAND = registerItem("broken_rebrewing_stand", new Item(new Item.Settings()));
    public static final Item EXTRACTION_BOTTLE = registerItem("extraction_bottle", new BottleOfExtractionItem(new Item.Settings().stacksTo(1)));
    public static final Item EXTRACTED_BOTTLE = registerItem("extracted_bottle", new PotionItem(new Item.Settings().stacksTo(1)) {
        @Override
        public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            pTooltipComponents.add(Component.translatableWithFallback("tooltip.extracted_bottle.obtain", "Obtainable using Bottle o' Extraction").withStyle(ChatFormatting.GOLD));
        }
    });
    public static final Item REBREWED_POTION = registerItem("rebrewed_potion", new PotionItem(new Item.Settings().stacksTo(1)));
    public static final Item REBREWED_SPLASH_POTION = registerItem("rebrewed_splash_potion", new SplashPotionItem(new Item.Settings().stacksTo(1)));
    public static final Item REBREWED_LINGERING_POTION = registerItem("rebrewed_lingering_potion", new LingeringPotionItem(new Item.Settings().stacksTo(1)));

    public static final Item CROPRESSED_POTATO = registerItem("cropressed_potato", new TrimMaterialItem(new Item.Settings()));
    public static final Item CROPRESSED_CARROT = registerItem("cropressed_carrot", new TrimMaterialItem(new Item.Settings()));
    public static final Item CROPRESSED_BEETROOT = registerItem("cropressed_beetroot", new TrimMaterialItem(new Item.Settings()));
    public static final Item CROPRESSED_NETHERWART = registerItem("cropressed_nether_wart", new TrimMaterialItem(new Item.Settings()));
    public static final Item CROPRESSED_WHEAT = registerItem("cropressed_wheat", new TrimMaterialItem(new Item.Settings()));

    public static final Item TATER_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("tater_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.TATER));
    public static final Item CAROTENE_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("carotene_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.CAROTENE));
    public static final Item BEAT_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("beat_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.BEAT));
    public static final Item NETHER_WART_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("nether_wart_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.NETHER_WART));
    public static final Item GRAIN_ARMOR_TRIM_SMITHING_TEMPLATE = registerItem("grain_armor_trim_smithing_template", () -> SmithingTemplateItem.createArmorTrimTemplate(ModTrimPatterns.GRAIN));

    public static final Item VIVICUS_ANTIDOTE = registerItem("vivicus_antidote", new VivicusAntidoteItem(new Item.Settings()) {
        @Override
        public void appendHoverText(ItemStack pStack, Item.TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
            super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
            pTooltipComponents.add(Component.translatableWithFallback("tooltip.wip", "WIP").withStyle(ChatFormatting.RED));
        }
    });
    public static final Item CORRUPTED_BOBLING_CORE = registerItem("corrupted_bobling_core", new Item(new Item.Settings()));
    public static final Item BOBLING_CORE = registerItem("bobling_core", new Item(new Item.Settings()));
    public static final Item CORRUPTED_SLIME_BALL = registerItem("corrupted_slime_ball", new CorruptedSlimeBallItem(new Item.Settings()));

    public static final Item CORRUPTED_SIGN = registerItem("corrupted_sign", new SignItem(new Item.Settings(), ModBlocks.CORRUPTED_SIGN.get(), ModBlocks.CORRUPTED_WALL_SIGN.get()));
    public static final Item CORRUPTED_HANGING_SIGN = registerItem("corrupted_hanging_sign", new HangingSignItem(ModBlocks.CORRUPTED_HANGING_SIGN.get(), ModBlocks.CORRUPTED_WALL_HANGING_SIGN.get(), new Item.Settings()));
    public static final Item CORRUPTED_BOAT = registerItem("corrupted_boat", new ModBoatItem(false, ModBoatEntity.Type.CORRUPTED, new Item.Settings()));
    public static final Item CORRUPTED_CHEST_BOAT = registerItem("corrupted_chest_boat", new ModBoatItem(true, ModBoatEntity.Type.CORRUPTED, new Item.Settings()));

    public static final Item VIVICUS_SIGN = registerItem("vivicus_sign", new SignItem(new Item.Settings(), ModBlocks.VIVICUS_SIGN.get(), ModBlocks.VIVICUS_WALL_SIGN.get()));
    public static final Item VIVICUS_HANGING_SIGN = registerItem("vivicus_hanging_sign", new HangingSignItem(ModBlocks.VIVICUS_HANGING_SIGN.get(), ModBlocks.VIVICUS_WALL_HANGING_SIGN.get(), new Item.Settings()));
    public static final Item VIVICUS_BOAT = registerItem("vivicus_boat", new ModBoatItem(false, ModBoatEntity.Type.VIVICUS, new Item.Settings()));
    public static final Item VIVICUS_CHEST_BOAT = registerItem("vivicus_chest_boat", new ModBoatItem(true, ModBoatEntity.Type.VIVICUS, new Item.Settings()));

    public static final Item BOBLING_SPAWN_EGG = registerItem("bobling_spawn_egg", new DeferredSpawnEggItem(ModEntityTypes.BOBLING, 0x312f35, 0xa55f85, new Item.Settings()));

    public static final Item CAULORFLOWER_SEEDS = registerItem("caulorflower_seeds", new BlockItem(ModBlocks.CAULORFLOWER.get(), new Item.Settings()));

    public static final Item CREATIVE_TAB_ICON = registerItem("creative_tab_icon", new CreativeTabItem(new Item.Settings()));
    public static final Item WAND_OF_CUBING = registerItem("wand_of_cubing", new WandOfCubingItem(new Item.Settings()));


   */

    private  static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, Identifier.of(MoreSnifferFlowers.MOD_ID, name), item);

    }
    public static void registerModItems(){
        MoreSnifferFlowers.LOGGER.info("Registering ModItems for" + MoreSnifferFlowers.MOD_ID);
    }
}
