package net.nikdo53.moresnifferflowers.data.loot;

import net.nikdo53.moresnifferflowers.init.ModBuiltinLoottables;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetStewEffectFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.function.BiConsumer;

public record ModArcheologyLoot() implements LootTableSubProvider {
    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> pOutput) {
        pOutput.accept(
                ModBuiltinLoottables.SNOW_SNIFFER_TEMPLE, //5 rolls
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.SNIFFER_EGG).setWeight(1))
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(5))
                                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(1))
                                        .add(LootItem.lootTableItem(Items.DIAMOND_BLOCK).setWeight(1))
                                        .add(LootItem.lootTableItem(Items.NAUTILUS_SHELL).setWeight(3))
                                        .add(LootItem.lootTableItem(Items.HEART_OF_THE_SEA).setWeight(2))
                                        .add(LootItem.lootTableItem(Items.TIDE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1))

                                        .add(
                                                LootItem.lootTableItem(Items.SUSPICIOUS_STEW)
                                                        .apply(
                                                                SetStewEffectFunction.stewEffect()
                                                                        .withEffect(MobEffects.DIG_SPEED, UniformGenerator.between(60.0F, 100.0F))

                                                        )
                                                        .setWeight(2)
                                        )
                        )
        );
        pOutput.accept(
                ModBuiltinLoottables.DESSERT_SNIFFER_TEMPLE, //9 rolls
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.SNIFFER_EGG).setWeight(1))
                                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE).setWeight(5))
                                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE).setWeight(2))
                                        .add(LootItem.lootTableItem(Items.GOLD_BLOCK).setWeight(3))
                                        .add(LootItem.lootTableItem(Items.EMERALD_BLOCK).setWeight(2))
                                        .add(LootItem.lootTableItem(Items.DUNE_ARMOR_TRIM_SMITHING_TEMPLATE).setWeight(1))

                                        .add(
                                                LootItem.lootTableItem(Items.SUSPICIOUS_STEW)
                                                        .apply(
                                                                SetStewEffectFunction.stewEffect()
                                                                        .withEffect(MobEffects.SATURATION, UniformGenerator.between(20.0F, 30.0F))


                                                        )
                                                        .setWeight(2)
                                        )
                        )
        );
        pOutput.accept(
                ModBuiltinLoottables.SWAMP_SNIFFER_TEMPLE, //5 rolls
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.ARMS_UP_POTTERY_SHERD).setWeight(2))
                                        .add(
                                                LootItem.lootTableItem(Items.SUSPICIOUS_STEW)
                                                        .apply(
                                                                SetStewEffectFunction.stewEffect()
                                                                        .withEffect(MobEffects.NIGHT_VISION, UniformGenerator.between(7.0F, 10.0F))
                                                                        .withEffect(MobEffects.JUMP, UniformGenerator.between(7.0F, 10.0F))
                                                                        .withEffect(MobEffects.WEAKNESS, UniformGenerator.between(6.0F, 8.0F))
                                                                        .withEffect(MobEffects.BLINDNESS, UniformGenerator.between(5.0F, 7.0F))
                                                                        .withEffect(MobEffects.POISON, UniformGenerator.between(10.0F, 20.0F))
                                                                        .withEffect(MobEffects.SATURATION, UniformGenerator.between(7.0F, 10.0F))
                                                                        .withEffect(MobEffects.SLOW_FALLING, UniformGenerator.between(7.0F, 10.0F))
                                                                        .withEffect(MobEffects.FIRE_RESISTANCE, UniformGenerator.between(7.0F, 10.0F))


                                                        )
                                        )
                        )
        );
        pOutput.accept(
                ModBuiltinLoottables.SNIFFER_EGG, //1 roll (guaranteed)
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1.0F))
                                        .add(LootItem.lootTableItem(Items.SNIFFER_EGG).setWeight(1))
                        )
        );
    }
}
