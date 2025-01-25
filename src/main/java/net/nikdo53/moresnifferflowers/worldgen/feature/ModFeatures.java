package net.nikdo53.moresnifferflowers.worldgen.feature;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;

public class ModFeatures {
    public static LazyRegistrar<Feature<?>> FEATURES = LazyRegistrar.create(BuiltInRegistries.FEATURE, MoreSnifferFlowers.MOD_ID);
    
    public static final RegistryObject<Feature<TreeConfiguration>> VIVICUS_TREE = FEATURES.register("vivicus_tree", () -> new VivicusTreeFeature(TreeConfiguration.CODEC));
}
