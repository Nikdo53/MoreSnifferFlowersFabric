package net.nikdo53.moresnifferflowers.worldgen.configurations;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.worldgen.configurations.tree.boblingtree.BoblingTreeTrunkPlacer;
import net.nikdo53.moresnifferflowers.worldgen.configurations.tree.corrupted.CorruptedGiantTrunkPlacer;
import net.nikdo53.moresnifferflowers.worldgen.configurations.tree.corrupted.CorruptedTrunkPlacer;
import net.nikdo53.moresnifferflowers.worldgen.configurations.tree.vivicus.VivicusTrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static LazyRegistrar<TrunkPlacerType<?>> TRUNKS = LazyRegistrar.create(BuiltInRegistries.TRUNK_PLACER_TYPE, MoreSnifferFlowers.MOD_ID);

    
    public static final RegistryObject<TrunkPlacerType<CorruptedTrunkPlacer>> CORRUPTED_TRUNK_PLACER = TRUNKS.register("corrupted_trunk_placer", () -> new TrunkPlacerType<>(CorruptedTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<CorruptedGiantTrunkPlacer>> CORRUPTED_GIANT_TRUNK_PLACER = TRUNKS.register("giant_corrupted_trunk_placer", () -> new TrunkPlacerType<>(CorruptedGiantTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<VivicusTrunkPlacer>> VIVICUS_TRUNK_PLACER = TRUNKS.register("vivicus_trunk_placer", () -> new TrunkPlacerType<>(VivicusTrunkPlacer.CODEC));
    public static final RegistryObject<TrunkPlacerType<BoblingTreeTrunkPlacer>> BOBLING_TREE_TRUNK = TRUNKS.register("bobling_tree_trunk", () -> new TrunkPlacerType<>(BoblingTreeTrunkPlacer.CODEC));
}
