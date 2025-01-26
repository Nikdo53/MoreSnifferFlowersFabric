package net.nikdo53.moresnifferflowers.data;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import io.github.fabricators_of_create.porting_lib.data.SoundDefinitionsProvider;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModSoundEvents;
import net.minecraft.data.PackOutput;


public class ModSoundProvider extends SoundDefinitionsProvider {
    protected ModSoundProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, MoreSnifferFlowers.MOD_ID, helper);
    }

    @Override
    public void registerSounds() {
        add(ModSoundEvents.CROPRESSOR_BELT, definition()
                .subtitle("sound.moresnifferflowers." + ModSoundEvents.CROPRESSOR_BELT.get().getLocation().getPath())
                .with(sound(MoreSnifferFlowers.loc("cropressor_sound_1")))
                .with(sound(MoreSnifferFlowers.loc("cropressor_sound_2")))
        );
        add(ModSoundEvents.DYESPRIA_PAINT, definition()
                .subtitle("sound.moresnifferflowers." + ModSoundEvents.DYESPRIA_PAINT.get().getLocation().getPath())
                .with(sound(MoreSnifferFlowers.loc("dyespria_paint"))));
    }
}
