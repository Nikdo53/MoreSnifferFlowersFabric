package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModBannerPatterns {
    public static LazyRegistrar<BannerPattern> BANNER_PATTERNS = LazyRegistrar.create(BuiltInRegistries.BANNER_PATTERN, MoreSnifferFlowers.MOD_ID);
    
    public static final RegistryObject<BannerPattern> AMBUSH = BANNER_PATTERNS.register("ambush", () -> new BannerPattern("msfa"));
    public static final RegistryObject<BannerPattern> EVIL = BANNER_PATTERNS.register("evil", () -> new BannerPattern("msfe"));
}
