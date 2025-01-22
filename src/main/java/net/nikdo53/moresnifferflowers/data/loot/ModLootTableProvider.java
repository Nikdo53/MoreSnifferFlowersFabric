package net.nikdo53.moresnifferflowers.data.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        addDrop(ModBlocks.AMBER_BLOCK, block -> LootTable.builder()
                .pool(LootPool.builder()
                        .conditionally(createSilkTouchCondition())
                        .with(ItemEntry.builder(ModBlocks.AMBER_BLOCK)))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(createWithoutSilkTouchCondition())
                        .with(ItemEntry.builder(ModItems.AMBER_SHARD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(2.0F, 4.0F)))
                                .apply(ApplyBonusLootFunction.uniformBonusCount(impl.getOrThrow(Enchantments.FORTUNE)))))
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1))
                                .conditionally(createWithoutSilkTouchCondition())
                        //COMMON
                        .with(ItemEntry.builder(Items.COAL).weight(100))
                        .with(ItemEntry.builder(Items.EMERALD).weight(100))
                        .with(ItemEntry.builder(Items.STICK).weight(100))
                        .with(ItemEntry.builder(ModItems.AMBER_SHARD).weight(100))
                    //    .with(ItemEntry.builder(ModItems.DRAGONFLY).weight(100))
                        //UNCOMMON 
                        .with(ItemEntry.builder(Items.CARROT).weight(50))
                        .with(ItemEntry.builder(Items.POTATO).weight(50))
                        .with(ItemEntry.builder(Items.BEETROOT).weight(50))
                        .with(ItemEntry.builder(Items.BEETROOT_SEEDS).weight(50))
                        .with(ItemEntry.builder(Items.NETHER_WART).weight(50))
                        .with(ItemEntry.builder(Items.WHEAT).weight(50))
                        .with(ItemEntry.builder(Items.WHEAT_SEEDS).weight(50))
                        //RARE
                      /*  .with(ItemEntry.builder(Items.SNORT_POTTERY_SHERD).weight(25))
                        .with(ItemEntry.builder(ModItems.BELT_PIECE).weight(25))
                        .with(ItemEntry.builder(ModItems.ENGINE_PIECE).weight(25))
                        .with(ItemEntry.builder(ModItems.SCRAP_PIECE).weight(25))
                        .with(ItemEntry.builder(ModItems.TUBE_PIECE).weight(25))
                        .with(ItemEntry.builder(ModItems.PRESS_PIECE).weight(25))
                        .with(ItemEntry.builder(ModItems.AROMA_ARMOR_TRIM_SMITHING_TEMPLATE).weight(25))
                        .with(ItemEntry.builder(ModItems.AMBUSH_BANNER_PATTERN).weight(25))
                        .with(ItemEntry.builder(ModItems.EXTRACTION_BOTTLE).weight(25))
                        //VERY RARE
                        .with(ItemEntry.builder(Items.TORCHFLOWER_SEEDS).weight(12))
                        .with(ItemEntry.builder(Items.PITCHER_POD).weight(12))
                        .with(ItemEntry.builder(Items.SNIFFER_EGG).weight(12))
                        .with(ItemEntry.builder(ModItems.DAWNBERRY_VINE_SEEDS).weight(12))
                        .with(ItemEntry.builder(ModItems.AMBUSH_SEEDS).weight(12))
                        .with(ItemEntry.builder(ModItems.DYESPRIA_SEEDS).weight(12))
                        .with(ItemEntry.builder(ModItems.BONDRIPIA_SEEDS).weight(12))
                        .with(ItemEntry.builder(ModBlocks.VIVICUS_SAPLING).weight(12))
                        .with(ItemEntry.builder(ModItems.BONMEELIA_SEEDS).weight(12))
                        */
                ));
                    

    }
}
