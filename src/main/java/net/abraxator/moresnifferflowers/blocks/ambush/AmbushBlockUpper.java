package net.abraxator.moresnifferflowers.blocks.ambush;

import net.abraxator.moresnifferflowers.blocks.blockentities.AmbushBlockEntity;
import net.abraxator.moresnifferflowers.blocks.ModEntityBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntityType;

import net.minecraft.block.BlockState;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class AmbushBlockUpper extends AmbushBlockBase implements ModEntityBlock {
    public static final int AGE_TO_GROW_UP = 4;

    public AmbushBlockUpper(Settings pSettings) {
        super(pSettings);
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pPos, BlockState pState) {
        ENTITY_POS = pPos;
        return new AmbushBlockEntity(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return tickerHelper(pLevel);
    }


    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {

    }
}
