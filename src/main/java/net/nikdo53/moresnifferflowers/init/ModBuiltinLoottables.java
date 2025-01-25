package net.nikdo53.moresnifferflowers.init;

import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

import java.util.Set;

public class ModBuiltinLoottables {
    public static final Set<ResourceLocation> MOD_LOOT_TABLES = Sets.newHashSet();

    public static final ResourceLocation SNOW_SNIFFER_TEMPLE = register("snow_sniffer_temple");

    private static ResourceLocation register(String name) {
        if(MOD_LOOT_TABLES.add(MoreSnifferFlowers.loc(name))) {
            return MoreSnifferFlowers.loc(name);
        } else throw new IllegalArgumentException("Unknown loot table: " + name);
    }
}
