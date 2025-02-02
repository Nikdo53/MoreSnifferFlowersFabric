package net.nikdo53.moresnifferflowers.data.loot;

import io.github.fabricators_of_create.porting_lib.data.ModdedLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.nikdo53.moresnifferflowers.init.ModBuiltinLoottables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Map;

public class ModLoottableProvider extends ModdedLootTableProvider {
    public ModLoottableProvider(FabricDataOutput output) {
        super(output, ModBuiltinLoottables.MOD_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLoottableProvider::new, LootContextParamSets.BLOCK),
                new LootTableProvider.SubProviderEntry(ModArcheologyLoot::new, LootContextParamSets.ARCHAEOLOGY),
                new LootTableProvider.SubProviderEntry(ModChestLoot::new, LootContextParamSets.CHEST),
                new LootTableProvider.SubProviderEntry(ModEntityLoot::new, LootContextParamSets.CHEST)

        ));
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationcontext) {

    }
}
