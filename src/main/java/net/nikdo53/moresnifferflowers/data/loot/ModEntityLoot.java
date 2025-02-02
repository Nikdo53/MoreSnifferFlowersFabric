package net.nikdo53.moresnifferflowers.data.loot;

import io.github.fabricators_of_create.porting_lib.data.ModdedEntityLootSubProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModEntityTypes;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.lootmodifers.conditions.BoblingTypeCondition;
import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceWithLootingCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.stream.Stream;

public class ModEntityLoot extends ModdedEntityLootSubProvider {

    protected ModEntityLoot() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        add(ModEntityTypes.BOBLING.get(), LootTable.lootTable().withPool(
                LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ModItems.CORRUPTED_BOBLING_CORE.get())
                                .when(BoblingTypeCondition.builder(false))
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.8F, 0.05F))
                        )
                        .add(LootItem.lootTableItem(ModItems.BOBLING_CORE.get())
                                .when(BoblingTypeCondition.builder(true))
                                .when(LootItemRandomChanceWithLootingCondition.randomChanceAndLootingBoost(0.4F, 0.1F))
                        )
        ));
        
    }


    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return BuiltInRegistries.ENTITY_TYPE.stream().filter(entities -> BuiltInRegistries.ENTITY_TYPE.getKey(entities).getNamespace().equals(MoreSnifferFlowers.MOD_ID));
    }
}
