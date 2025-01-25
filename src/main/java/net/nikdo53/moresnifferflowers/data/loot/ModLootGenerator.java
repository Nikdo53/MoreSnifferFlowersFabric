package net.nikdo53.moresnifferflowers.data.loot;

import io.github.fabricators_of_create.porting_lib.data.ModdedLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.nikdo53.moresnifferflowers.init.ModBuiltinLoottables;

import java.util.List;
import java.util.Set;

public class ModLootGenerator extends ModdedLootTableProvider {

    public ModLootGenerator(FabricDataOutput output) {
        super(output, ModBuiltinLoottables.MOD_LOOT_TABLES, List.of(
                new LootTableProvider.SubProviderEntry(ModBlockLoottableProvider::new, LootContextParamSets.BLOCK)
        ));
    }
}
