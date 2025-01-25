package net.nikdo53.moresnifferflowers.worldgen.structures;

import com.mojang.serialization.Codec;
import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;

public class ModStructureTypes {
    public static LazyRegistrar<StructurePieceType> STRUCTURE_PIECE = LazyRegistrar.create(BuiltInRegistries.STRUCTURE_PIECE, MoreSnifferFlowers.MOD_ID);



    private static <T extends Structure> StructureType<T> explicitStructureTypeTyping(Codec<T> structureCodec) {
        return () -> structureCodec;
    }
}

