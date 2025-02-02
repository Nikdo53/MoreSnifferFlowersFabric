package net.nikdo53.moresnifferflowers.events;

import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.event.common.BlockEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.nikdo53.moresnifferflowers.blockentities.BondripiaBlockEntity;
import net.nikdo53.moresnifferflowers.blockentities.GiantCropBlockEntity;
import net.nikdo53.moresnifferflowers.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.nikdo53.moresnifferflowers.items.JarOfBonmeelItem;

public class ForgeEvents {

    public static void init(){
        LivingEntityEvents.LivingJumpEvent.JUMP.register(ForgeEvents::onLivingJump);
        UseBlockCallback.EVENT.register(ForgeEvents::onPlayerInteractRightClickItem);
        BlockEvents.BLOCK_BREAK.register(ForgeEvents::onBlockBreak);

    }

    public static void onLivingJump(LivingEntityEvents.LivingJumpEvent livingJumpEvent) {
        LivingEntity livingEntity = livingJumpEvent.getEntity();
        Level level = livingEntity.level();
        Vec3 loc = livingEntity.position();
        BlockPos blockPos = BlockPos.containing(loc);

        if(level.getBlockState(blockPos).is(ModBlocks.CORRUPTED_SLIME_LAYER.get()) || level.getBlockState(blockPos.below()).is(ModBlocks.CORRUPTED_SLIME_LAYER.get())) {
            livingEntity.setDeltaMovement(livingEntity.getDeltaMovement().multiply(1, 0.3, 1));
        }
    }


    private static InteractionResult onPlayerInteractRightClickItem(Player player, Level level, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        var itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        BlockPos pos = blockHitResult.getBlockPos();
        BlockState block = level.getBlockState(pos);

        if(itemStack.getItem() instanceof JarOfBonmeelItem item && block.is(ModTags.ModBlockTags.BONMEELABLE)) {
            item.useOn(new UseOnContext(player, interactionHand, blockHitResult));
            return InteractionResult.SUCCESS;

        } else if((itemStack.is(ModItems.REBREWED_POTION.get()) || itemStack.is(ModItems.EXTRACTED_BOTTLE.get())) && block.is(Blocks.DIRT)) {
            return InteractionResult.FAIL;
        } else if(itemStack.is(ItemTags.AXES) && (block.is(ModBlocks.VIVICUS_LOG.get()) || block.is(ModBlocks.VIVICUS_WOOD.get()))) {
            var strippedBlock = AxeItem.STRIPPABLES.get(block.getBlock());
            var state = strippedBlock.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, block.getValue(RotatedPillarBlock.AXIS))
                    .setValue(ModStateProperties.COLOR, block.getValue(ModStateProperties.COLOR));

            if (player instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
            }

            level.setBlock(pos, state, 3);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
            itemStack.hurtAndBreak(1, player, consumer -> {
                consumer.broadcastBreakEvent(consumer.getUsedItemHand());
            });

            return InteractionResult.sidedSuccess(level.isClientSide);
        }
        return InteractionResult.PASS;
    }

    private static void onBlockBreak(BlockEvents.BreakEvent breakEvent) {

        if(breakEvent.getLevel().getBlockEntity(breakEvent.getPos()) instanceof GiantCropBlockEntity entity) {
            BlockPos.withinManhattanStream(entity.center, 1, 1, 1).forEach(blockPos -> {
                breakEvent.getLevel().destroyBlock(blockPos, true);
            });
        }
        
        if (breakEvent.getLevel().getBlockEntity(breakEvent.getPos()) instanceof BondripiaBlockEntity entity) {
            Direction.Plane.HORIZONTAL.forEach(direction -> {
                BlockPos blockPos = entity.center.relative(direction);

                breakEvent.getLevel().destroyBlock(blockPos, true);
            });

            breakEvent.getLevel().destroyBlock(entity.center, true);
        }
    }

 /*   public static void onGetAdvancement(AdvancementEvent.AdvancementEarnEvent event) {
        if(event.getAdvancement().getId().equals(ResourceLocation.tryParse("husbandry/obtain_sniffer_egg")) && event.getEntity() instanceof ServerPlayer serverPlayer) {
            ModAdvancementCritters.EARN_SNIFFER_ADVANCEMENT.trigger(serverPlayer);
        }
    }*/

}
