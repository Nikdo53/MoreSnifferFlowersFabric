package net.nikdo53.moresnifferflowers.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record CropressorAnimation(boolean playing, double progress) {
    public static final Codec<CropressorAnimation> CODEC = RecordCodecBuilder.create(
            dyeInstance -> dyeInstance.group(
                    Codec.BOOL.fieldOf("playing").forGetter(CropressorAnimation::playing),
                    Codec.DOUBLE.fieldOf("progress").forGetter(CropressorAnimation::progress)
            ).apply(dyeInstance, CropressorAnimation::new)
    );
    
    public double getAddedProgress(double amount) {
        return this.progress + amount;
    }
}
