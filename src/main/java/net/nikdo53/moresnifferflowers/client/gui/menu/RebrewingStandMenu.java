package net.nikdo53.moresnifferflowers.client.gui.menu;

import net.minecraft.world.inventory.*;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.init.ModMenuTypes;
import net.nikdo53.moresnifferflowers.init.ModTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;

public class RebrewingStandMenu extends AbstractContainerMenu {
    private final Container rebrewingStand;
    private final ContainerData rebrewingStandData;

    public RebrewingStandMenu(int id, Inventory playerInv) {
        this(id, playerInv, new SimpleContainer(6), new SimpleContainerData(3));
    }

    public RebrewingStandMenu(int id, Inventory playerInv, Container rebrewingStandContainer, ContainerData rebrewingStandContainerData) {
        super(ModMenuTypes.REBREWING_STAND.get(), id);
        checkContainerSize(rebrewingStandContainer, 6);
        checkContainerDataCount(rebrewingStandContainerData, 2);
        this.rebrewingStand = rebrewingStandContainer;
        this.rebrewingStandData = rebrewingStandContainerData;
        this.addSlot(new FuelSlot(rebrewingStandContainer, 0, 12, 12));
        this.addSlot(new OriginalPotionSlot(rebrewingStandContainer, 1, 79, 12));
        this.addSlot(new IngredientSlot(rebrewingStandContainer, 2, 102, 19));
        this.addSlot(new PotionSlot(rebrewingStandContainer, 3, 56, 51));
        this.addSlot(new PotionSlot(rebrewingStandContainer, 4, 79, 58));
        this.addSlot(new PotionSlot(rebrewingStandContainer, 5, 102, 51));

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInv, k, 8 + k * 18, 142));
        }

        addDataSlots(rebrewingStandContainerData);
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack movedStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            movedStack = itemstack1.copy();
            if ((pIndex < 0 || pIndex > 5)) {
                if (FuelSlot.mayPlaceItem(movedStack)) {
                    if (this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (OriginalPotionSlot.mayPlaceItem(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (IngredientSlot.mayPlaceItem(movedStack)) {
                    if (!this.moveItemStackTo(itemstack1, 2, 3, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (PotionSlot.mayPlaceItem(movedStack)) {
                    if (!this.moveItemStackTo(itemstack1, 3, 6, false)) {
                        return ItemStack.EMPTY;
                    }
                }
                else if (pIndex >= 6 && pIndex < 33) {
                    if (!this.moveItemStackTo(itemstack1, 33, 42, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex >= 33 && pIndex < 42) {
                    if (!this.moveItemStackTo(itemstack1, 6, 33, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 6, 42, false)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 6, 42, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, movedStack);
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == movedStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return movedStack;
    }

    public int getBrewingTicks() {
        return this.rebrewingStandData.get(0);
    }

    public int getFuel() {
        return this.rebrewingStandData.get(1);
    }

    public int getCost() {
        return this.rebrewingStandData.get(2);
    }


    @Override
    public boolean stillValid(Player pPlayer) {
        return this.rebrewingStand.stillValid(pPlayer);
    }

    static class FuelSlot extends Slot {
        public FuelSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.is(ModItems.CROPRESSED_NETHERWART.get());
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return mayPlaceItem(pStack);
        }

        @Override
        public int getMaxStackSize() {
            return 64;
        }
    }

    static class OriginalPotionSlot extends Slot {
        public OriginalPotionSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.is(ModItems.EXTRACTED_BOTTLE.get());
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return mayPlaceItem(pStack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }

    static class IngredientSlot extends Slot {
        public IngredientSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.is(ModTags.ModItemTags.REBREWING_STAND_INGREDIENTS);
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return mayPlaceItem(pStack);
        }
        @Override
        public int getMaxStackSize() {
            return 64;
        }
    }

    static class PotionSlot extends Slot {
        public PotionSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public static boolean mayPlaceItem(ItemStack itemStack) {
            return itemStack.getOrCreateTag().getString("Potion").equals("minecraft:water");
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return mayPlaceItem(pStack);
        }

        public void onTake(Player pPlayer, ItemStack pStack) {
            Potion potion = PotionUtils.getPotion(pStack);
            if (pPlayer instanceof ServerPlayer && pStack.is(ModTags.ModItemTags.REBREWED_POTIONS)) {
                CriteriaTriggers.BREWED_POTION.trigger((ServerPlayer)pPlayer, potion);
            }

            super.onTake(pPlayer, pStack);
        }

        @Override
        public int getMaxStackSize() {
            return 1;
        }
    }
}
