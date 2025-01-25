package net.nikdo53.moresnifferflowers.blocks.vivicus;

import net.nikdo53.moresnifferflowers.blockentities.VivicusSignBlockEntity;
import net.nikdo53.moresnifferflowers.blocks.ColorableVivicusBlock;
import net.nikdo53.moresnifferflowers.blocks.signs.ModWallSignBlock;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.WoodType;

public class VivicusWallSignBlock extends ModWallSignBlock implements ColorableVivicusBlock {
    public VivicusWallSignBlock(WoodType p_56991_, Properties p_56990_) {
        super(p_56991_, p_56990_);
        this.defaultBlockState().setValue(ModStateProperties.COLOR, DyeColor.WHITE);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ModStateProperties.COLOR);
    }


    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new VivicusSignBlockEntity(pPos, pState);
    }
}
