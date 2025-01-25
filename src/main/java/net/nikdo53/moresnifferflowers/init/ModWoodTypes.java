package net.nikdo53.moresnifferflowers.init;

import net.fabricmc.fabric.api.object.builder.v1.block.type.WoodTypeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModWoodTypes {
    public static final WoodType CORRUPTED = register(ResourceLocation.tryParse(MoreSnifferFlowers.MOD_ID + ":corrupted"), BlockSetType.WARPED);
    public static final WoodType VIVICUS = register(ResourceLocation.tryParse(MoreSnifferFlowers.MOD_ID + ":vivicus"), BlockSetType.CHERRY);

    private static WoodType register(ResourceLocation id, BlockSetType setType) {
        return new WoodTypeBuilder().register(id, setType);
    }
}
