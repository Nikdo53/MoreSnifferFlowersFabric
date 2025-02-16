package net.nikdo53.moresnifferflowers.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Map;
import java.util.function.Predicate;

public class ModLayeredCauldronBlock extends LayeredCauldronBlock {
    public ModLayeredCauldronBlock(Properties properties, Predicate<Biome.Precipitation> fillPredicate, Map<Item, CauldronInteraction> interactions) {
        super(properties, fillPredicate, interactions);
        this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 1));
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return Blocks.CAULDRON.asItem().getDefaultInstance();
    }
}
