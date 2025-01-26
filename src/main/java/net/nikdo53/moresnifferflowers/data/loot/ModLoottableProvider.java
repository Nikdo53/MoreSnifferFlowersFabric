package net.nikdo53.moresnifferflowers.data.loot;

import net.nikdo53.moresnifferflowers.init.ModBuiltinLoottables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;

public class ModLoottableProvider extends LootTableProvider {
    public ModLoottableProvider(PackOutput pOutput) {
        super(pOutput, ModBuiltinLoottables.MOD_LOOT_TABLES, List.of(
                new SubProviderEntry(ModBlockLoottableProvider::new, LootContextParamSets.BLOCK),
                new SubProviderEntry(ModArcheologyLoot::new, LootContextParamSets.ARCHAEOLOGY),
                new SubProviderEntry(ModChestLoot::new, LootContextParamSets.CHEST)
        ));
    }
}
