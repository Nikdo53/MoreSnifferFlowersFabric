package net.nikdo53.moresnifferflowers.data;

import io.github.fabricators_of_create.porting_lib.data.DatapackBuiltinEntriesProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModTrimMaterials;
import net.nikdo53.moresnifferflowers.init.ModTrimPatterns;
import net.nikdo53.moresnifferflowers.worldgen.configurations.ModConfiguredFeatures;
import net.nikdo53.moresnifferflowers.worldgen.placement.*;
import net.nikdo53.moresnifferflowers.worldgen.structures.ModStructures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class RegistryDataGenerator extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.TRIM_MATERIAL, ModTrimMaterials::bootstrap)
            .add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap)
            .add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap)
            .add(Registries.STRUCTURE, ModStructures::bootstrap)
            .add(Registries.TRIM_PATTERN, ModTrimPatterns::bootstrap);

    public RegistryDataGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> provider) {
        super(output, provider, BUILDER, Set.of("minecraft", MoreSnifferFlowers.MOD_ID));
    }
}
