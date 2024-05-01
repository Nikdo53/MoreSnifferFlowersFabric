package net.abraxator.moresnifferflowers.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public interface IModEntityDoubleTallBlock {
    Block getLowerBlock();

    Block getUpperBlock();

    default boolean isLower(BlockState blockState) {
        return !isUpper(blockState);
    }

    default boolean isUpper(BlockState blockState) {
        return blockState.isOf(getUpperBlock());
    }
    
    default boolean isStateThis(BlockState blockState) {
        return blockState.isOf(getLowerBlock()) || blockState.isOf(getUpperBlock());
    }
    
    default boolean areTwoHalfSame(BlockState block1, BlockState block2) {
        return (isUpper(block1) && isUpper(block2)) || (isLower(block1) && isLower(block2));
    }

    @Nullable PistonBehavior getPistonBehavior(BlockState state);

    void onRemove(BlockState pState, World pWorld, BlockPos pPos, BlockState pNewState, PlayerEntity pPlayerEntity, boolean pMovedByPiston);

    boolean canSurvive(BlockState pState, WorldAccess pWorld, BlockPos pPos);
}
