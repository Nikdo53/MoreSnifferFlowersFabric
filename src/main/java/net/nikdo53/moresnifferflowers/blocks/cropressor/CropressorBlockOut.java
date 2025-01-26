package net.nikdo53.moresnifferflowers.blocks.cropressor;

import net.nikdo53.moresnifferflowers.blockentities.CropressorBlockEntity;
import net.nikdo53.moresnifferflowers.blocks.ModEntityBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CropressorBlockOut extends CropressorBlockBase implements ModEntityBlock {
    public CropressorBlockOut(Properties pProperties, Part part) {
        super(pProperties, part);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        return tickerHelper(pLevel);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if(level.getBlockEntity(pos) instanceof CropressorBlockEntity entity && !newState.is(this)) {
            for (int i = 0; i < 5; i++) {
                int count = entity.cropCount[i];
                Item crop = CropressorBlockEntity.Crop.values()[i].item;
                ItemStack stack = new ItemStack(crop, count);
                
                Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), stack);
            }
        }
        
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        ENTITY_POS = pPos;
        return new CropressorBlockEntity(pPos, pState);
    }
}
