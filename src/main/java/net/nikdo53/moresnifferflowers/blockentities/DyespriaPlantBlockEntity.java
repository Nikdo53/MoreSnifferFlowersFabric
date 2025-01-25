package net.nikdo53.moresnifferflowers.blockentities;

import net.nikdo53.moresnifferflowers.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class DyespriaPlantBlockEntity extends ColoredBlockEntity {
    public DyespriaPlantBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.DYESPRIA_PLANT.get(), pPos, pBlockState);
    }
}
