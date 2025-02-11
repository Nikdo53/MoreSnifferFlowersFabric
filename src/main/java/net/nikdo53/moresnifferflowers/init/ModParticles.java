package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModParticles {
    public static LazyRegistrar<ParticleType<?>> PARTICLES = LazyRegistrar.create(BuiltInRegistries.PARTICLE_TYPE, MoreSnifferFlowers.MOD_ID);

    public static final RegistryObject<SimpleParticleType> FLY = PARTICLES.register("fly", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> CARROT = PARTICLES.register("carrot", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> AMBUSH = PARTICLES.register("ambush", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GIANT_CROP = PARTICLES.register("giant_crop", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> GARBUSH = PARTICLES.register("garbush", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> BONDRIPIA_DRIP = PARTICLES.register("bondripia_drip", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BONDRIPIA_FALL = PARTICLES.register("bondripia_fall", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> BONDRIPIA_LAND = PARTICLES.register("bondripia_land", () -> new SimpleParticleType(false));

    public static final RegistryObject<SimpleParticleType> ACIDRIPIA_DRIP = PARTICLES.register("acidripia_drip", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ACIDRIPIA_FALL = PARTICLES.register("acidripia_fall", () -> new SimpleParticleType(false));
    public static final RegistryObject<SimpleParticleType> ACIDRIPIA_LAND = PARTICLES.register("acidripia_land", () -> new SimpleParticleType(false));


}
