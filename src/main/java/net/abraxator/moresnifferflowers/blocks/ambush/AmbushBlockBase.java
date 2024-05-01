package net.abraxator.moresnifferflowers.blocks.ambush;

import net.abraxator.moresnifferflowers.blocks.ModEntityDoubleTallBlock;
import net.abraxator.moresnifferflowers.blocks.ModCropBlock;
import net.abraxator.moresnifferflowers.blocks.blockentities.AmbushBlockEntity;
import net.abraxator.moresnifferflowers.init.ModBlocks;
import net.abraxator.moresnifferflowers.init.ModItems;
import net.abraxator.moresnifferflowers.init.ModParticles;
import net.abraxator.moresnifferflowers.init.ModStateProperties;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class AmbushBlockBase extends ModEntityDoubleTallBlock implements ModCropBlock {
    public static final int AGE_TO_GROW_UP = 4;

    public AmbushBlockBase(Settings pSettings) {
        super(pSettings);
    }
    
    @Override
    public IntProperty getAgeProperty() {
        return ModStateProperties.AGE_8;
    }

    @Override   
    public boolean hasRandomTicks(BlockState pState) {
        return !isMaxAge(pState);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext pContext) {
        return this.getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState pState, WorldView pWorld, BlockPos pPos) {
        return this.mayPlaceOn(pWorld.getBlockState(pPos.down())) && sufficientLight(pWorld, pPos) && super.canSurvive(pState, (WorldAccess) pWorld, pPos);
    }

    @Override
    public void onRemove(BlockState pState, World pWorld, BlockPos pPos, BlockState pNewState, PlayerEntity pPlayerEntity, boolean pMovedByPiston) {
        if(!pState.isOf(pNewState.getBlock()) && isUpper(pState) && pWorld.getBlockEntity(pPos) instanceof AmbushBlockEntity entity && entity.hasGrown) {
            ItemEntity item = new ItemEntity(pWorld, pPos.getX(), pPos.getY(), pPos.getZ(), ModBlocks.AMBER.asItem().getDefaultStack());
            pWorld.spawnEntity(item);
        }

        super.onRemove(pState, pWorld, pPos, pNewState,pPlayerEntity, pMovedByPiston);
    }
    
//    @Override
//    public boolean mayPlaceOn(BlockState pState) {
//        return ModCropBlock.super.mayPlaceOn(pState) || pState.isOf(ModBlocks.REBREWING_STAND_BOTTOM.get());
//    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(ModStateProperties.AGE_8);
    }

//    @Override
//    public void entityInside(BlockState pState, World pWorld, BlockPos pPos, Entity pEntity) {
//        if(pEntity instanceof Ravager && pWorld.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING)) {
//            pWorld.destroyBlock(pPos, true, pEntity);
//        }
//
//        super.entityInside(pState, pWorld, pPos, pEntity);
//    }

//    @Override
//    public boolean isReplaceable(BlockState pState, ItemPlacementContext pUseContext) {
//        return false;
//    }

    @Override
    public void randomDisplayTick(BlockState pState, World pWorld, BlockPos pPos, Random pRandom) {
        if(getAge(pState) == 7 && pRandom.nextInt(100) < 10 && isLower(pState)) {
            double dx = pPos.getX() + pRandom.nextDouble();
            double dy = pPos.getY() + pRandom.nextDouble();
            double dz = pPos.getZ() + pRandom.nextDouble();
            pWorld.addParticle(ModParticles.AMBUSH, dx, dy, dz, 0, 0, 0);
        }
    }

    @Override
    public void randomTick(BlockState pState, ServerWorld pWorld, BlockPos pPos, Random pRandom) {
        float f = getGrowthSpeed(this, pWorld, pPos);
        if(pRandom.nextInt((int) ((25.0F / f) + 1)) == 0) {
            this.grow(pWorld, pState, pPos, 1);
        }
    }

    void grow(ServerWorld pWorld, BlockState pState, BlockPos pPos, int i) {
        int k = Math.min(getAge(pState) + i, getMaxAge());
        if(this.canGrow(pWorld, pPos, pState, k)) {
            pWorld.setBlockState(pPos, pState.setValue(getAgeProperty(), k), 2);
            if(k >= AGE_TO_GROW_UP && isLower(pState)) {
                pWorld.setBlockState(pPos.up(), ModBlocks.AMBUSH_TOP.getDefaultState().setValue(getAgeProperty(), k), 3);
            }

            if(ENTITY_POS != null && pWorld.getBlockEntity(ENTITY_POS) instanceof AmbushBlockEntity entity) {
                entity.growProgress = 0;
            }
        }
    }

    @Override
    public ActionResult use(BlockState pState, World pWorld, BlockPos pPos, PlayerEntity pPlayerEntity, Hand pHand, BlockHitResult pHit) {
        if(ENTITY_POS != null && pWorld.getBlockEntity(ENTITY_POS) instanceof AmbushBlockEntity entity && entity.hasGrown) {
            var lowerPos = isLower(pState) ? pPos : pPos.down();
            dropStack(pWorld, pPos, new ItemStack(ModBlocks.AMBER));
            
            for(int i = 0; i <= 1; i++) {
                var halfPos = i == 0 ? lowerPos : lowerPos.up();
                var state = pWorld.getBlockState(halfPos).setValue(getAgeProperty(), 7);
                pWorld.setBlockState(halfPos, state, 3);
                pWorld.emitGameEvent(GameEvent.BLOCK_CHANGE, halfPos, GameEvent.Emitter.of(pPlayerEntity, state));
            }

            entity.reset();
            return ActionResult.success(pWorld.isClient);
        } else {
            return ActionResult.PASS;
        }
    }

    private boolean canGrowInto(BlockState state) {
        return state.isAir() || state.isOf(ModBlocks.AMBUSH_TOP);
    }

    private boolean sufficientLight(WorldView pWorld, BlockPos pPos) {
        return pWorld.getBaseLightLevel(pPos, 0) >= 8 || pWorld.isSkyVisible(pPos);
    }

    @Override
    public int getMaxAge() {
        return ModCropBlock.super.getMaxAge() - 1;
    }

    private boolean canGrow(WorldView pWorld, BlockPos pPos, BlockState pState, int k) {
        return !this.isMaxAge(pState) && sufficientLight(pWorld, pPos) && (k < AGE_TO_GROW_UP || canGrowInto(pWorld.getBlockState(pPos.up()))) && isLower(pState);
    }

    private PosAndState getLowerHalf(WorldView level, BlockPos blockPos, BlockState state) {
        if(isLower(state)) {
            return new PosAndState(blockPos, state);
        } else {
            BlockPos posBelow = blockPos.down();
            BlockState stateBelow = level.getBlockState(posBelow);
            return isLower(stateBelow) ? new PosAndState(posBelow, stateBelow) : null;
        }
    }

    @Override
    public boolean isFertilizable(WorldView pWorld, BlockPos pPos, BlockState pState) {
        PosAndState posAndState = this.getLowerHalf(pWorld, pPos, pState);
        return posAndState != null && this.canGrow(pWorld, posAndState.blockPos(), posAndState.state(), getAge(posAndState.state()) + 1);
    }

    @Override
    public boolean canGrow(World world, net.minecraft.util.math.random.Random random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void grow(ServerWorld pWorld, net.minecraft.util.math.random.Random pRandom, BlockPos pPos, BlockState pState) {
        PosAndState posAndState = this.getLowerHalf(pWorld, pPos, pState);
        if(posAndState != null && pState.getValue(ModStateProperties.AGE_8) < 8) {
            this.grow(pWorld, posAndState.state(), posAndState.blockPos(), 1);
        }
    }

    @Override
    public void onPlaced(World pWorld, BlockPos pPos, BlockState pState, @Nullable LivingEntity pPlacer, ItemStack pStack) {}

    @Override
    public ItemStack getPickStack(WorldView pWorld, BlockPos pPos, BlockState pState) {
        return ModItems.AMBUSH_SEEDS.getDefaultStack();
    }

    @Override
    public Block getLowerBlock() {
        return ModBlocks.AMBUSH_BOTTOM;
    }

    @Override
    public Block getUpperBlock() {
        return ModBlocks.AMBUSH_TOP;
    }

}
