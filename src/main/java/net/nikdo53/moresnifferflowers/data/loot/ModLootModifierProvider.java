package net.nikdo53.moresnifferflowers.data.loot;

import io.github.fabricators_of_create.porting_lib.loot.GlobalLootModifierProvider;
import io.github.fabricators_of_create.porting_lib.loot.LootTableIdCondition;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.lootmodifers.AddItemsModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.List;

public class ModLootModifierProvider extends GlobalLootModifierProvider {
    public ModLootModifierProvider(PackOutput output) {
        super(output, MoreSnifferFlowers.MOD_ID);
    }

    @Override
    protected void start() {
        add("seeds_from_sniffing", new AddItemsModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("gameplay/sniffer_digging")).build()
        }, List.of(ModItems.DAWNBERRY_VINE_SEEDS.get(), ModItems.DYESPRIA_SEEDS.get(), ModItems.AMBUSH_SEEDS.get(), ModItems.CAULORFLOWER_SEEDS.get(),
                ModItems.BONMEELIA_SEEDS.get(), ModItems.BONDRIPIA_SEEDS.get(), ModBlocks.VIVICUS_SAPLING.get().asItem())));
    }


}
