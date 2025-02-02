package net.nikdo53.moresnifferflowers.blocks.vivicus;

import net.nikdo53.moresnifferflowers.blocks.ColorableVivicusBlock;
import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public class VivicusRotatedPillarBlock extends RotatedPillarBlock implements ColorableVivicusBlock {
    public VivicusRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
        this.registerDefaultState(defaultBlockState().setValue(ModStateProperties.COLOR, DyeColor.WHITE).setValue(ModStateProperties.VIVICUS_TYPE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(ModStateProperties.COLOR);
        pBuilder.add(ModStateProperties.VIVICUS_TYPE);
    }
}
