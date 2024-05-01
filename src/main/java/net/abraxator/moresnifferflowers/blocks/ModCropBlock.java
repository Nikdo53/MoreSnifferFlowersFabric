package net.abraxator.moresnifferflowers.blocks;

import net.minecraft.block.*;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Pair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public interface ModCropBlock extends Fertilizable {
    IntProperty getAgeProperty();

    default boolean isMaxAge (BlockState blockState) {
        return getAge(blockState) >= getMaxAge();
    }

    default int getMaxAge() {
        return getAgeProperty().getValues().stream().toList().get(getAgeProperty().getValues().size() - 1);
    }

    default int getAge(BlockState blockState) {
        return blockState.get(getAgeProperty());
    }

//    default void makeGrowOnTick(Block block, BlockState blockState, World level, BlockPos blockPos) {
//        if (!isMaxAge(blockState) && level.isAreaLoaded(blockPos, 1) && level.getBaseLightLevel(BlockPos, 0) >= 9) {
//            float f = getGrowthSpeed(block, level, blockPos);
//            if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(level, blockPos, blockState, level.getRandom().nextInt((int)(25.0F / f) + 1) == 0)) {
//                level.setBlock(blockPos, blockState.setValue(getAgeProperty(), (blockState.getValue(getAgeProperty()) + 1)), 2);
//                net.minecraftforge.common.ForgeHooks.onCropsGrowPost(level, blockPos, blockState);
//            }
//        }
//    }

//    default void makeGrowOnBonemeal(World level, BlockPos blockPos, BlockState blockState) {
//        level.setBlock(blockPos, blockState.setValue(getAgeProperty(), getAge(blockState) >= 3 ? getAge(blockState) : getAge(blockState) + 1), 2);
//    }

    default boolean mayPlaceOn(BlockState pState) {
        return pState.isOf(Blocks.FARMLAND);
    }

    default float getGrowthSpeed(Block pBlock, BlockView pWorld, BlockPos pPos) {
        float f = 1.0F;
        BlockPos blockpos = pPos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
//                BlockState blockstate = pWorld.getBlockState(blockpos.offset(i, 0, j));
//                if (blockstate.canPlaceAt(pWorld, blockpos.offset(i, 0, j), net.minecraft.util.math.Direction.UP, pBlock)) {
//                    f1 = 1.0F;
//                    if (blockstate.isFertile(pWorld, pPos.offset(i, 0, j))) {
//                        f1 = 3.0F;
//                    }
//                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pPos.north();
        BlockPos blockpos2 = pPos.south();
        BlockPos blockpos3 = pPos.west();
        BlockPos blockpos4 = pPos.east();
        boolean flag = pWorld.getBlockState(blockpos3).isOf(pBlock) || pWorld.getBlockState(blockpos4).isOf(pBlock);
        boolean flag1 = pWorld.getBlockState(blockpos1).isOf(pBlock) || pWorld.getBlockState(blockpos2).isOf(pBlock);
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = pWorld.getBlockState(blockpos3.north()).isOf(pBlock) || pWorld.getBlockState(blockpos4.north()).isOf(pBlock) || pWorld.getBlockState(blockpos4.south()).isOf(pBlock) || pWorld.getBlockState(blockpos3.south()).isOf(pBlock);
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    record PosAndState(BlockPos blockPos, BlockState state) {}
}
