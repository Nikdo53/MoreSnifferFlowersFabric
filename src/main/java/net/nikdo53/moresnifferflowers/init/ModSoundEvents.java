package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.crafting.RecipeType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModSoundEvents {
    public static LazyRegistrar<SoundEvent> SOUNDS = LazyRegistrar.create(BuiltInRegistries.SOUND_EVENT, MoreSnifferFlowers.MOD_ID);


    public static final RegistryObject<SoundEvent> CROPRESSOR_BELT = variableRange("block.cropressor.belt");
    public static final RegistryObject<SoundEvent> DYESPRIA_PAINT = variableRange("item.dyespria.paint");

    private static RegistryObject<SoundEvent> variableRange(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(MoreSnifferFlowers.loc(name)));
    }
}
