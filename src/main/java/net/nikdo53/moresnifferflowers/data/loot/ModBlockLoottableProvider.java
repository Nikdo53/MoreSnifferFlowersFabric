package net.nikdo53.moresnifferflowers.data.loot;

import io.github.fabricators_of_create.porting_lib.data.ModdedBlockLootSubProvider;
import io.github.fabricators_of_create.porting_lib.data.ModdedLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;

import java.util.Set;

public class ModBlockLoottableProvider extends ModdedBlockLootSubProvider {


    protected ModBlockLoottableProvider(Set<Item> set, FeatureFlagSet featureFlagSet) {
        super(set, featureFlagSet);
    }

    @Override
    public void generate() {
        add(ModBlocks.AMBER_BLOCK.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .when(HAS_SILK_TOUCH)
                        .add(LootItem.lootTableItem(ModBlocks.AMBER_BLOCK.get())))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(HAS_NO_SILK_TOUCH)
                        .add(LootItem.lootTableItem(ModItems.AMBER_SHARD.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 4.0F)))
                                .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5F, 2))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .when(HAS_NO_SILK_TOUCH)
                        //COMMON
                        .add(LootItem.lootTableItem(Items.COAL).setWeight(100))
                        .add(LootItem.lootTableItem(Items.EMERALD).setWeight(100))
                        .add(LootItem.lootTableItem(Items.STICK).setWeight(100))
                        .add(LootItem.lootTableItem(ModItems.AMBER_SHARD.get()).setWeight(100))
                        //UNCOMMON
                        .add(LootItem.lootTableItem(Items.CARROT).setWeight(50))
                        .add(LootItem.lootTableItem(Items.POTATO).setWeight(50))
                        .add(LootItem.lootTableItem(Items.BEETROOT).setWeight(50))
                        .add(LootItem.lootTableItem(Items.BEETROOT_SEEDS).setWeight(50))
                        .add(LootItem.lootTableItem(Items.NETHER_WART).setWeight(50))
                        .add(LootItem.lootTableItem(Items.WHEAT).setWeight(50))
                        .add(LootItem.lootTableItem(Items.WHEAT_SEEDS).setWeight(50))
                ));
    }
}
