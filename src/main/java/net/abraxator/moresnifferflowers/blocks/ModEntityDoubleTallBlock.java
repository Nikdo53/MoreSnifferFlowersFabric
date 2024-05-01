package net.abraxator.moresnifferflowers.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public abstract class ModEntityDoubleTallBlock extends Block implements IModEntityDoubleTallBlock {
    protected BlockPos ENTITY_POS;
    
    public ModEntityDoubleTallBlock(Settings pSettings) {
        super(pSettings);
    }
    
    @Override
    public @Nullable PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.BLOCK;
    }
    
    @Override
    public BlockState onBreak(World pWorld, BlockPos pPos, BlockState pState, PlayerEntity pPlayerEntity) {
        if(!pWorld.isClient) {
            if(pPlayerEntity.isCreative()) {
                preventCreativeDropFromBottomPart(pWorld, pPos, pState, pPlayerEntity);
            } else {
                var blockEntity = isUpper(pState) ? pWorld.getBlockEntity(pPos) : null;
                dropStacks(pState, pWorld, pPos, blockEntity, pPlayerEntity, pPlayerEntity.getMainHandStack());
            }
        }

        super.onBreak(pWorld, pPos, pState, pPlayerEntity);
        return pState;
    }

    @Override
    public void afterBreak(World pWorld, PlayerEntity pPlayerEntity, BlockPos pPos, BlockState pState, @Nullable BlockEntity pBlockEntity, ItemStack pTool) {
        super.onBreak(pWorld, pPos, Blocks.AIR.getDefaultState(), pPlayerEntity);
    }

    @Override
    public void onRemove(BlockState pState, World pWorld, BlockPos pPos, BlockState pNewState, PlayerEntity pPlayerEntity, boolean pMovedByPiston) {
        if(!pState.isOf(pNewState.getBlock()) && isUpper(pState)) {
            BlockEntity blockentity = pWorld.getBlockEntity(pPos);
            if (blockentity instanceof Inventory) {
                ItemScatterer.spawn(pWorld, pPos, (Inventory)blockentity);
                pWorld.updateComparators(pPos, this);
            }
        }

        super.onBreak(pWorld, pPos, pNewState, pPlayerEntity);
    }
    
    @Override
    public BlockState getStateForNeighborUpdate(BlockState pState, Direction pFacing, BlockState pFacingState, WorldAccess pWorld, BlockPos pCurrentPos, BlockPos pFacingPos) {
        if (pFacing.getAxis() != Direction.Axis.Y || isLower(pState) != (pFacing == Direction.UP) || isStateThis(pFacingState) && !areTwoHalfSame(pState, pFacingState)) {
            return isLower(pState) && pFacing == Direction.DOWN && !canSurvive(pState, pWorld, pCurrentPos) ? Blocks.AIR.getDefaultState() : super.getStateForNeighborUpdate(pState, pFacing, pFacingState, pWorld, pCurrentPos, pFacingPos);
        } else {
            return Blocks.AIR.getDefaultState();
        }
    }
    
    @Override
    public boolean canSurvive(BlockState pState, WorldAccess pWorld, BlockPos pPos) {
        if (isLower(pState)) {
            return super.canPlaceAt(pState, pWorld, pPos);
        } else {
            BlockState blockstate = pWorld.getBlockState(pPos.down());
            if (!isStateThis(pState)) return super.canPlaceAt(pState, pWorld, pPos); //Forge: This function is called during world gen and placement, before this block is set, so if we are not 'here' then assume it's the pre-check.
            return isStateThis(blockstate) && isLower(blockstate);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        BlockPos blockPos = pContext.getBlockPos();
        World level = pContext.getWorld();

        return blockPos.getY() < level.getTopY() - 1 && level.getBlockState(blockPos.up()).isReplaceable() ? super.getPlacementState(pContext) : null;
    }
    
    @Override
    public void onPlaced(World pWorld, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {
        pWorld.setBlockState(pPos.up(), getUpperBlock().getDefaultState());
    }
    
    public void preventCreativeDropFromBottomPart(World pWorld, BlockPos pPos, BlockState pState, PlayerEntity pPlayerEntity) {
        if (isUpper(pState)) {
            BlockPos blockPosBelow = pPos.down();
            BlockState blockStateBelow = pWorld.getBlockState(blockPosBelow);
            if (isStateThis(blockStateBelow) && isLower(blockStateBelow)) {
                BlockState blockStateForReplacement = blockStateBelow.getFluidState().isOf(Fluids.WATER) ? Blocks.WATER.getDefaultState() : Blocks.AIR.getDefaultState();
                pWorld.setBlockState(blockPosBelow, blockStateForReplacement, 35);
                pWorld.syncWorldEvent(pPlayerEntity, 2001, blockPosBelow, Block.getRawIdFromState(blockStateBelow));
            }
        }
    }
}
