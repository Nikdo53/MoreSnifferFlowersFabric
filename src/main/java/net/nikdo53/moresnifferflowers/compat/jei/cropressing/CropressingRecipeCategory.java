package net.nikdo53.moresnifferflowers.compat.jei.cropressing;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.recipes.CropressingRecipe;
import net.minecraft.network.chat.Component;

public class CropressingRecipeCategory implements IRecipeCategory<CropressingRecipe> {
    public static final RecipeType<CropressingRecipe> CROPRESSING = RecipeType.create(MoreSnifferFlowers.MOD_ID, "cropressing", CropressingRecipe.class);
    private final IDrawable background;
    private final IDrawable icon;
    private final Component localizedName;

    public CropressingRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(MoreSnifferFlowers.loc("textures/gui/container/cropressor_gui.png"), 0, 0, 176, 84);
        this.icon = helper.createDrawableItemStack(ModItems.CROPRESSOR.get().getDefaultInstance());
        this.localizedName = Component.translatableWithFallback("gui.mores_sniffer_flowers.cropressing_category", "Cropressing");
    }

    @Override
    public RecipeType<CropressingRecipe> getRecipeType() {
        return CROPRESSING;
    }
    
    @Override
    public Component getTitle() {
        return this.localizedName;
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, CropressingRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 20, 34).addItemStack(recipe.ingredient().getItems()[0].copyWithCount(recipe.count()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 140, 34).addItemStack(recipe.result());
    }
}
