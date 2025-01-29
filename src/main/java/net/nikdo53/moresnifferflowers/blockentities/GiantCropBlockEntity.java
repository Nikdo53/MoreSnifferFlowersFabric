package net.nikdo53.moresnifferflowers.blockentities;

import net.nikdo53.moresnifferflowers.init.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.Nullable;

public class GiantCropBlockEntity extends ModBlockEntity {
    public BlockPos center;
    public boolean canGrow = false;
    public double growProgress = 0;
    public int state = 0; //0 NONE; 1 ANIMATION; 2 SACK;
    public float staticGameTime;

    public GiantCropBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.GIANT_CROP.get(), pPos, pBlockState);
        this.center = this.getBlockPos();
    }

    @Override
    public void tick(Level level) {
        if(canGrow) {
            if(staticGameTime == 0) {
                staticGameTime = level.getGameTime();
            }
            
            growProgress += 0.10;
            this.level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), Block.UPDATE_CLIENTS);
            if(growProgress >= 1) {
                canGrow = false;
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag() {
        var tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putBoolean("canGrow", canGrow);
        pTag.putDouble("growProgress", growProgress);
        pTag.putFloat("staticGameTime", staticGameTime);
        pTag.put("center", NbtUtils.writeBlockPos(this.center));
        pTag.putInt("state", this.state);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.canGrow = pTag.getBoolean("canGrow");
        this.growProgress = pTag.getDouble("growProgress");
        this.staticGameTime = pTag.getFloat("staticGameTime");
        this.center = NbtUtils.readBlockPos(pTag.getCompound("center"));
        this.state = pTag.getInt("state");
    }
}