package net.nikdo53.moresnifferflowers.items;


import net.minecraft.ChatFormatting;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nikdo53.moresnifferflowers.init.ModAdvancementCritters;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class VivicusAntidoteItem extends Item {
    public VivicusAntidoteItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        var level = pContext.getLevel();
        var blockPos = pContext.getClickedPos();
        var relativePos = blockPos.relative(pContext.getClickedFace());
        var blockState = level.getBlockState(blockPos);
        var random = level.getRandom();
        var player = pContext.getPlayer();
        var particle = new DustParticleOptions(Vec3.fromRGB24(7118872).toVector3f(), 1);

        if(blockState.is(ModBlocks.VIVICUS_SAPLING.get()) && !blockState.getValue(ModStateProperties.VIVICUS_CURED)) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(ModStateProperties.VIVICUS_CURED, true));

            for(int i = 0; i <= 10; i++) {
                level.addParticle(particle, blockPos.getX() + random.nextDouble(), blockPos.getY() + random.nextDouble(), blockPos.getZ() + random.nextDouble(), 0, -0.3, 0);
            }
            
            if (player instanceof ServerPlayer serverPlayer) {
                ModAdvancementCritters.USED_CURE.trigger(serverPlayer);
            }
            
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        
        if(blockState.is(ModBlocks.CORRUPTED_SLUDGE.get()) && blockState.getValue(ModStateProperties.CURED).equals(false)) {
            level.setBlockAndUpdate(blockPos, blockState.setValue(ModStateProperties.CURED, true));

            for(int i = 0; i <= 10; i++) {
                level.addParticle(particle, relativePos.getX() + random.nextDouble(), relativePos.getY() + random.nextDouble(), relativePos.getZ() + random.nextDouble(), 0, -0.3, 0);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);

        }

        if (blockState.is(ModBlocks.CORRUPTED_GRASS_BLOCK.get())) {
            level.setBlockAndUpdate(blockPos, ModBlocks.CURED_GRASS_BLOCK.get().defaultBlockState());

            for(int i = 0; i <= 10; i++) {
                level.addParticle(particle, relativePos.getX() + random.nextDouble(), relativePos.getY() + random.nextDouble(), relativePos.getZ() + random.nextDouble(), 0, -0.3, 0);
            }
            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        
        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.translatableWithFallback("tooltip.wip", "WIP").withStyle(ChatFormatting.DARK_RED));
        pTooltip.add(Component.translatableWithFallback("tooltip.vivicus_antidote", "Cures Boblings and Corrupted Grass").withStyle(ChatFormatting.GOLD));
    }
}
