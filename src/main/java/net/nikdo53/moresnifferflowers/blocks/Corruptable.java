package net.nikdo53.moresnifferflowers.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Optional;

public interface Corruptable {
    default Optional<Block> getCorruptedBlock(Block block, Level level) {
     //   return CorruptionRecipe.getCorruptedBlock(block, level);
        return null;
    }
    
    default void onCorrupt(Level level, BlockPos pos, BlockState oldState, Block corruptedBlock) {
        var corruptedState = corruptedBlock.withPropertiesOf(oldState);
        level.setBlockAndUpdate(pos, corruptedState);
    }
}
