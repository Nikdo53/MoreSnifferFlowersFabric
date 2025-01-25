package net.nikdo53.moresnifferflowers.worldgen.placement;

import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.worldgen.configurations.ModConfiguredFeatures;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> CORRUPTED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, MoreSnifferFlowers.loc("corrupted_tree"));
    public static final ResourceKey<PlacedFeature> GIANT_CORRUPTED_TREE = ResourceKey.create(Registries.PLACED_FEATURE, MoreSnifferFlowers.loc("giant_corrupted_tree"));
    public static final ResourceKey<PlacedFeature> CURED_VIVICUS_TREE = ResourceKey.create(Registries.PLACED_FEATURE, MoreSnifferFlowers.loc("cured_vivicus_tree"));
    public static final ResourceKey<PlacedFeature> CORRUPTED_VIVICUS_TREE = ResourceKey.create(Registries.PLACED_FEATURE, MoreSnifferFlowers.loc("corrupted_vivicus_tree"));
    
    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        var holderGetter = context.lookup(Registries.CONFIGURED_FEATURE);
        var corruptedHolder = holderGetter.getOrThrow(ModConfiguredFeatures.CORRUPTED_TREE);
        var corruptedGiantHolder = holderGetter.getOrThrow(ModConfiguredFeatures.GIANT_CORRUPTED_TREE);
        var curedVivicusHolder = holderGetter.getOrThrow(ModConfiguredFeatures.CURED_VIVICUS_TREE);
        var corruptedVivicusHolder = holderGetter.getOrThrow(ModConfiguredFeatures.CORRUPTED_VIVICUS_TREE);
        
        PlacementUtils.register(context, CORRUPTED_TREE, corruptedHolder, List.of());
        PlacementUtils.register(context, GIANT_CORRUPTED_TREE, corruptedHolder, List.of());
        PlacementUtils.register(context, CURED_VIVICUS_TREE, curedVivicusHolder, List.of());
        PlacementUtils.register(context, CORRUPTED_VIVICUS_TREE, corruptedVivicusHolder, List.of());
    }
}