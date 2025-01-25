package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModPaintings {
    public static LazyRegistrar<PaintingVariant> PAINTINGS = LazyRegistrar.create(BuiltInRegistries.PAINTING_VARIANT, MoreSnifferFlowers.MOD_ID);

    
    public static final RegistryObject<PaintingVariant> HATTED_FERGUS_TATER = PAINTINGS.register("hatted_fergus_tater", () -> new PaintingVariant(16, 16));
}
