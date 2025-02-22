package net.nikdo53.moresnifferflowers.items;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.nikdo53.moresnifferflowers.blockentities.DyespriaPlantBlockEntity;
import net.nikdo53.moresnifferflowers.components.Colorable;
import net.nikdo53.moresnifferflowers.components.Dye;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModTags;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.List;

import static net.nikdo53.moresnifferflowers.items.DyespriaItem.copyAllBlockStateProperties;

public class DyescrapiaItem extends BlockItem {
    public DyescrapiaItem(Properties pProperties) {
        super(ModBlocks.DYESCRAPIA_PLANT.get(), pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        var pos = pContext.getClickedPos();
        var state = pContext.getLevel().getBlockState(pos);
        var player = pContext.getPlayer();
        var level = pContext.getLevel();
        var stack = pContext.getItemInHand();
        int uses = getDyescrapiaUses(stack) + 1;


        if(state.getBlock() instanceof Colorable colorable) {
            var dye = new Dye(colorable.getDyeFromBlock(state).color(), 1);
            if(!dye.color().equals(DyeColor.WHITE)) {
                colorable.colorBlock(level, pos, state, new Dye(DyeColor.WHITE, 1));
                
                if(uses >= 4) {
                    player.addItem(Dye.stackFromDye(dye));
                    uses = 0;
                }

                CompoundTag tag = stack.getOrCreateTag();
                tag.putInt("uses", uses);
                stack.setTag(tag);
                return InteractionResult.sidedSuccess(level.isClientSide);
            }            
        } else if (state.is(ModTags.ModBlockTags.DYED)){

            ResourceLocation location = BuiltInRegistries.BLOCK.getKey(state.getBlock());
            String modId = location.getNamespace();
            String blockId = location.getPath();
            String finalBlockId;
            String validColorName = "white|light_gray|gray|black|brown|red|orange|yellow|lime|green|cyan|light_blue|blue|purple|magenta|pink";
            boolean colorless = false;


            if(blockId.endsWith("candle") || blockId.endsWith("shulker_box") || (blockId.endsWith("terracotta") && !blockId.endsWith("glazed_terracotta"))) {
                finalBlockId = blockId.replaceFirst((validColorName), "").replaceFirst("_","");
                colorless = true;
            } else if (blockId.endsWith("stained_glass") || blockId.endsWith("stained_glass_pane") ){
                finalBlockId = blockId.replaceFirst((validColorName), "").replaceFirst("_stained_", "");
                colorless = true;
            } else {
                finalBlockId = blockId.replaceFirst(validColorName, "");
            }

            if ((!blockId.contains("white_") || colorless) && !finalBlockId.equals(blockId)){
                Block finalBlock = colorless ? BuiltInRegistries.BLOCK.get(new ResourceLocation(modId, finalBlockId)) : BuiltInRegistries.BLOCK.get(new ResourceLocation(modId,"white" + finalBlockId));
                BlockState finalBlockState = finalBlock.defaultBlockState();

                BlockEntity originalShulker = level.getBlockEntity(pos);
                CompoundTag shulkerData = null;

                if(originalShulker instanceof ShulkerBoxBlockEntity entity) {
                    shulkerData = entity.saveWithoutMetadata();
                }

                if (finalBlock != Blocks.AIR) level.setBlockAndUpdate(pos, copyAllBlockStateProperties(state, finalBlockState));

                if (shulkerData != null && level.getBlockEntity(pos) instanceof ShulkerBoxBlockEntity newShulkerBox) {
                    newShulkerBox.loadFromTag(shulkerData);
                }

                if(uses >= 4) {
                    String dyeName = blockId.replace(blockId.replaceFirst(validColorName, ""), "") + "_dye";
                    player.addItem(BuiltInRegistries.ITEM.get(new ResourceLocation("minecraft", dyeName)).getDefaultInstance());
                    uses = 0;
                }

                CompoundTag tag = stack.getOrCreateTag();
                tag.putInt("uses", uses);
                stack.setTag(tag);
                return InteractionResult.sidedSuccess(level.isClientSide);

            } else return InteractionResult.FAIL;

        }
        
        return handlePlacement(pos, level, player, pContext.getHand(), pContext.getItemInHand());
    }

    private InteractionResult handlePlacement(BlockPos blockPos, Level level, Player player, InteractionHand hand, ItemStack stack) {
        var posForDyespria = blockPos.above();
        var blockHitResult = new BlockHitResult(posForDyespria.below().getCenter(), Direction.UP, posForDyespria.below(), false);
        var useOnCtx = new UseOnContext(level, player, hand, stack, blockHitResult);
        var result = super.useOn(useOnCtx);

        if (level.getBlockEntity(blockPos.above()) instanceof DyespriaPlantBlockEntity entity) {
            entity.dye = Dye.getDyeFromDyespria(stack);
            entity.setChanged();
        }

        return result;
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarColor(ItemStack stack) {
        int lowColor = 0x8c1111;
        int highColor = 0x179529;
        int input = getDyescrapiaUses(stack)-1;
        int maxInput= 4;

        int lowRed = (lowColor >> 16) & 0xFF;
        int lowGreen = (lowColor >> 8) & 0xFF;
        int lowBlue = lowColor & 0xFF;

        int highRed = (highColor >> 16) & 0xFF;
        int highGreen = (highColor >> 8) & 0xFF;
        int highBlue = highColor & 0xFF;
        
        float[] lowHSB =  Color.RGBtoHSB(lowRed, lowGreen, lowBlue, null);
        float[] highHSB =  Color.RGBtoHSB(highRed, highGreen, highBlue, null);


        float finalHue = ((lowHSB[0] * (Math.abs(input - maxInput))) + (highHSB[0] * input)) / maxInput;

        return Mth.hsvToRgb(finalHue, 1.0F, 1.0F);
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(getDyescrapiaUses(stack) * 13.0F / 4);
    }

    public static int getDyescrapiaUses(ItemStack stack) {
        CompoundTag tag = stack.getOrCreateTag();
        return tag.contains("uses") ? tag.getInt("uses") : 0;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<net.minecraft.network.chat.Component> pTooltip, TooltipFlag pFlag) {
        super.appendHoverText(pStack, pLevel, pTooltip, pFlag);
        pTooltip.add(Component.translatableWithFallback("tooltip.dyescrapia", "Scrapes the dye off of colored blocks").withStyle(ChatFormatting.GOLD));
    }
}
