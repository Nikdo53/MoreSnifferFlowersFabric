package net.nikdo53.moresnifferflowers.client.gui.screen;

import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.client.gui.menu.RebrewingStandMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class RebrewingStandScreen extends AbstractContainerScreen<RebrewingStandMenu> {
    public static final ResourceLocation TEXTURE = MoreSnifferFlowers.loc("textures/gui/container/rebrewing_stand.png");
    private static final int[] BUBBLELENGTHS = new int[]{0, 5, 8, 12, 17, 22, 27};

    public RebrewingStandScreen(RebrewingStandMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
        this.renderOnboardingTooltips(guiGraphics, mouseX, mouseY, x, y);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int mouseX, int mouseY) {
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        int fuel = menu.getFuel();
        int progress = menu.getBrewingTicks();
        int renderFuel;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);
        if(menu.getCost() <= 16 && menu.getCost() != 0) {
            var cost = String.valueOf(menu.getCost());
            var color = Minecraft.getInstance().getResourceManager().listPacks().anyMatch(packResources -> packResources.packId().equals("more_sniffer_flowers_boring")) ? 0x00373737 : 0x00933c4d;
            var colorOutline = Minecraft.getInstance().getResourceManager().listPacks().anyMatch(packResources -> packResources.packId().equals("more_sniffer_flowers_boring")) ? 0x006d294a : 0x005e224f;
            drawCost(guiGraphics, cost, x, y, colorOutline, -1, 0);
            drawCost(guiGraphics, cost, x, y, colorOutline, +1, 0);
            drawCost(guiGraphics, cost, x, y, colorOutline, 0, -1);
            drawCost(guiGraphics, cost, x, y, colorOutline, 0, +1);
            drawCost(guiGraphics, cost, x, y, color, 0, 0);
        } else {
            guiGraphics.blit(TEXTURE, x + 35, y + 47, 198, 1, 13, 9);
        }

        if(fuel > 0) {
            renderFuel = fuel == 1 ? 2 : fuel == 16 ? 18 : fuel + 1;
            guiGraphics.blit(TEXTURE, x + 55, y + 39, 176, 29, renderFuel, 4);
        }

        if(progress > 0) {
            int arrowScale = (int) Mth.lerp((float) progress / 100, 0, 27);
            guiGraphics.blit(TEXTURE, x + 124, y + 18, 177, 1, 8, arrowScale);

            var bubbleFactor = BUBBLELENGTHS[progress / 2 % 7];
            guiGraphics.blit(TEXTURE, x + 59, y + 37 - bubbleFactor, 186, 28 - bubbleFactor, 11, bubbleFactor);
        }
    }

    public void renderOnboardingTooltips(GuiGraphics guiGraphics, int mouseX, int mouseY, int x, int y) {
        Optional<Component> optional = Optional.empty();

        if(isMouseOver(mouseX, mouseY, x + 55, y + 38, 19, 6)) {
            guiGraphics.renderTooltip(this.font, this.font.split(Component.literal(menu.getFuel() + "/16"), 115), mouseX, mouseY);
        }

        if(hoveredSlot != null && !this.hoveredSlot.hasItem()) {
            switch (hoveredSlot.index) {
                case 0 -> optional = Optional.of(component("fuel", "Add Cropressed Nether Wart"));
                case 1 -> optional = Optional.of(component("og_potion", "Add Extracted Potion"));
                case 2 -> optional = Optional.of(component("ingredient", "Add Ingredient"));
                case 3, 5, 4 -> optional = Optional.of(component("potion", "Add Water Bottle"));
                default -> {
                    return;
                }
            }
        }

        optional.ifPresent(component -> guiGraphics.renderTooltip(this.font, this.font.split(component, 115), mouseX, mouseY));
    }

    private Component component(String id, String fallback) {
        return Component.translatableWithFallback("tooltip.moresnifferflowers.rebrewing_stand." + id, fallback);
    }

    public static boolean isMouseOver(double mouseX, double mouseY, int x, int y, int sizeX, int sizeY) {
        return (mouseX >= x && mouseX <= x + sizeX) && (mouseY >= y && mouseY <= y + sizeY);
    }

    private void drawCost(GuiGraphics guiGraphics, String cost, int x, int y, int color, int xOffset, int yOffset) {
        this.font.drawInBatch(cost, (x + 42 - this.font.width(cost) / 2) + xOffset, (y + 48) + yOffset, color, false, guiGraphics.pose().last().pose(), guiGraphics.bufferSource(), Font.DisplayMode.NORMAL, 0, 15728880, this.font.isBidirectional());
    }
}
