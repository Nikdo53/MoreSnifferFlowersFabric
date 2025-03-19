package net.nikdo53.moresnifferflowers.lootmodifers;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;

public class LoottableEvents {
    public static void modifyVanillaLootTables() {
        LootTableEvents.MODIFY.register((resource, loot, id, builder, source) -> {
            if (BuiltInLootTables.SNIFFER_DIGGING.equals(id)) {
                builder.modifyPools(itemEntry -> {
                    itemEntry.with((LootItem.lootTableItem(ModItems.DAWNBERRY_VINE_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModItems.AMBUSH_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModItems.CAULORFLOWER_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModItems.DYESPRIA_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModItems.BONMEELIA_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModBlocks.VIVICUS_SAPLING.get().asItem()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                    itemEntry.with((LootItem.lootTableItem(ModItems.BONDRIPIA_SEEDS.get()).apply(SetItemCountFunction.setCount(ConstantValue.exactly(1))).build()));
                });
            }
        });
    }
}
