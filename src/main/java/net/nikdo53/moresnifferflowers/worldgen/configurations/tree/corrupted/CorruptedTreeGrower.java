package net.nikdo53.moresnifferflowers.worldgen.configurations.tree.corrupted;

import net.nikdo53.moresnifferflowers.worldgen.configurations.ModConfiguredFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractMegaTreeGrower;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class CorruptedTreeGrower extends AbstractMegaTreeGrower {
    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource p_255637_, boolean p_255764_) {
        return ModConfiguredFeatures.CORRUPTED_TREE;
    }

    public ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource p_255928_) {
        return ModConfiguredFeatures.GIANT_CORRUPTED_TREE;
    }
}
