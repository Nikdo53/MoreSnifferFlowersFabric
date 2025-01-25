package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.effects.ExtractedEffect;

public class ModMobEffects {
    public static LazyRegistrar<MobEffect> EFFECTS = LazyRegistrar.create(BuiltInRegistries.MOB_EFFECT, MoreSnifferFlowers.MOD_ID);


    public static final RegistryObject<MobEffect> EXTRACTED = EFFECTS.register("extracted", () -> new ExtractedEffect(MobEffectCategory.NEUTRAL, 14058905));
}
