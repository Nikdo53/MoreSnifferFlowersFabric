package net.nikdo53.moresnifferflowers.events;

import io.github.fabricators_of_create.porting_lib.entity.events.LivingEntityEvents;
import io.github.fabricators_of_create.porting_lib.event.common.BlockEvents;
import net.minecraft.core.Direction;
import net.nikdo53.moresnifferflowers.blockentities.BondripiaBlockEntity;
import net.nikdo53.moresnifferflowers.blockentities.GiantCropBlockEntity;
import net.nikdo53.moresnifferflowers.init.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class ForgeEvents {

    public static void init(){
        LivingEntityEvents.LivingJumpEvent.JUMP.register(ForgeEvents::onLivingJump);
       // UseItemCallback.EVENT.register(ModeEvents::onPlayerInteractRightClickItem);
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


   /* private static InteractionResultHolder<ItemStack> onPlayerInteractRightClickItem(Player player, Level level, InteractionHand interactionHand) {
        var itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
        var block = level.getBlockState();

        if(itemStack.getItem() instanceof JarOfBonmeelItem item && block.is(ModTags.ModBlockTags.BONMEELABLE)) {
            event.setCanceled(true);
            item.useOn(new UseOnContext(event.getEntity(), event.getHand(), event.getHitVec()));
        } else if((itemStack.is(ModItems.REBREWED_POTION.get()) || itemStack.is(ModItems.EXTRACTED_BOTTLE.get())) && block.is(Blocks.DIRT)) {
            event.setCanceled(true);
        } else if(itemStack.is(ItemTags.AXES) && (block.is(ModBlocks.VIVICUS_LOG.get()) || block.is(ModBlocks.VIVICUS_WOOD.get()))) {
            var strippedBlock = AxeItem.STRIPPABLES.get(block.getBlock());
            var state = strippedBlock.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, block.getValue(RotatedPillarBlock.AXIS))
                    .setValue(ModStateProperties.COLOR, block.getValue(ModStateProperties.COLOR));

            if (event.getEntity() instanceof ServerPlayer serverPlayer) {
                CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, event.getPos(), itemStack);
            }

            event.getLevel().setBlock(event.getPos(), state, 3);
            event.getLevel().gameEvent(GameEvent.BLOCK_CHANGE, event.getPos(), GameEvent.Context.of(event.getEntity(), state));
            itemStack.hurtAndBreak(1, event.getEntity(), player -> {
                player.broadcastBreakEvent(player.getUsedItemHand());
            });

            event.setCancellationResult(InteractionResult.sidedSuccess(event.getLevel().isClientSide));
        }
    }*/

    private static void onBlockBreak(BlockEvents.BreakEvent breakEvent) {

        if(breakEvent.getLevel().getBlockEntity(breakEvent.getPos()) instanceof GiantCropBlockEntity entity) {
            BlockPos.betweenClosed(entity.pos1, entity.pos2).forEach(blockPos -> {
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

/*    public static void onGetAdvancement(AdvancementEvent.AdvancementEarnEvent event) {
        if(event.getAdvancement().getId().equals(ResourceLocation.tryParse("husbandry/obtain_sniffer_egg")) && event.getEntity() instanceof ServerPlayer serverPlayer) {
            ModAdvancementCritters.EARN_SNIFFER_ADVANCEMENT.trigger(serverPlayer);
        }
    }*/

}
