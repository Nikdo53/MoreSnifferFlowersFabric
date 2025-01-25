package net.nikdo53.moresnifferflowers.blockentities;

import net.nikdo53.moresnifferflowers.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.level.block.state.BlockState;

public class BondripiaBlockEntity extends ModBlockEntity {
    public BlockPos center;
    
    public BondripiaBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.BONDRIPIA.get(), pPos, pBlockState);
        this.center = pPos;
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.put("center", NbtUtils.writeBlockPos(this.center));
    }
    
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.center = NbtUtils.readBlockPos(pTag.getCompound("center"));
    }
}
