package net.nikdo53.moresnifferflowers.blocks.vivicus;

import net.nikdo53.moresnifferflowers.blocks.ColorableVivicusBlock;
import net.nikdo53.moresnifferflowers.blocks.ModCropBlock;
import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class VivicusSproutingBlock extends VivicusLeavesBlock implements ModCropBlock, ColorableVivicusBlock {
    public VivicusSproutingBlock(Properties p_54422_) {
        super(p_54422_);
        defaultBlockState().setValue(ModStateProperties.VIVICUS_TYPE, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ModStateProperties.AGE_3);
        pBuilder.add(ModStateProperties.VIVICUS_TYPE);
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return ModStateProperties.AGE_3;
    }

    @Override
    public boolean isRandomlyTicking(BlockState pState) {
        return super.isRandomlyTicking(pState) || !isMaxAge(pState);
    }

    public void grow(BlockState pState, Level pLevel, BlockPos pPos) {
        makeGrowOnBonemeal(pLevel, pPos, pState);
        
        if(isMaxAge(pLevel.getBlockState(pPos))) {
            BoblingEntity boblingEntity = new BoblingEntity(pLevel, false);
            boblingEntity.setPos(pPos.getCenter());
            pLevel.addFreshEntity(boblingEntity);
            pLevel.removeBlock(pPos, false);
        }
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        return level.getBlockState(pos.above()).is(ModBlocks.VIVICUS_LEAVES.get());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pFacing, BlockState pFacingState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pFacingPos) {
        return pFacing == Direction.UP && !pState.canSurvive(pLevel, pCurrentPos)
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape(pState, pFacing, pFacingState, pLevel, pCurrentPos, pFacingPos);
    }

    @Override
    public void randomTick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if(pRandom.nextDouble() <= 0.5D) {
            grow(pState, pLevel, pPos);
        }
        
        super.randomTick(pState, pLevel, pPos, pRandom);
    }
    
    @Override
    public boolean isValidBonemealTarget(LevelReader pLevel, BlockPos pPos, BlockState pState, boolean pIsClient) {
        return !isMaxAge(pState);
    }

    @Override
    public boolean isBonemealSuccess(Level pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel pLevel, RandomSource pRandom, BlockPos pPos, BlockState pState) {
        grow(pState, pLevel, pPos);
    }
}
