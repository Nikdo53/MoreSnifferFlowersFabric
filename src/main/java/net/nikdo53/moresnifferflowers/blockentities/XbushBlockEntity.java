package net.nikdo53.moresnifferflowers.blockentities;

import net.nikdo53.moresnifferflowers.init.ModBlockEntities;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class XbushBlockEntity extends GrowingCropBlockEntity {
    public XbushBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.XBUSH.get(), pPos, pBlockState, pBlockState.is(ModBlocks.AMBUSH_TOP.get()) ? 0.001f : 0.0005F);
    }

    @Override
    public boolean canGrow(float growProgress, boolean hasGrown) {
        return this.getBlockState().getValue(ModStateProperties.AGE_8).equals(7) && super.canGrow(growProgress, hasGrown);
    }
}