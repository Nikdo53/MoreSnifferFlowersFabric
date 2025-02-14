package net.nikdo53.moresnifferflowers.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.client.gui.screen.RebrewingStandScreen;
import net.nikdo53.moresnifferflowers.compat.jei.cropressing.CropressingRecipeCategory;
import net.nikdo53.moresnifferflowers.compat.jei.rebrewing.JeiRebrewingRecipe;
import net.nikdo53.moresnifferflowers.compat.jei.rebrewing.RebrewingCategory;
import net.nikdo53.moresnifferflowers.init.ModItems;
import net.nikdo53.moresnifferflowers.init.ModRecipeTypes;
import net.nikdo53.moresnifferflowers.recipes.CropressingRecipe;

import java.util.ArrayList;
import java.util.List;

@JeiPlugin
public class MoreSnifferFlowersJEIPlugin implements IModPlugin {
    public static final ResourceLocation ID = new ResourceLocation("jei", MoreSnifferFlowers.MOD_ID);

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(ModItems.CROPRESSOR.get().getDefaultInstance(), CropressingRecipeCategory.CROPRESSING);
        registration.addRecipeCatalyst(ModItems.REBREWING_STAND.get().getDefaultInstance(), RebrewingCategory.REBREWING);
      //  registration.addRecipeCatalyst(ModItems.CORRUPTED_SLIME_BALL.get().getDefaultInstance(), CorruptionCategory.CORRUPTION);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(RebrewingStandScreen.class, 123, 17, 9, 28, RebrewingCategory.REBREWING);
    }
    
    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CropressingRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new RebrewingCategory(registration.getJeiHelpers().getGuiHelper()));
       // registration.addRecipeCategories(new CorruptionCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<CropressingRecipe> cropressingRecipes = new ArrayList<>(recipeManager.getAllRecipesFor(ModRecipeTypes.CROPRESSING.get()));
/*        List<CorruptionRecipe> corruptionRecipes = new ArrayList<>(recipeManager.getAllRecipesFor(ModRecipeTypes.CORRUPTION.get()));

        for (Map.Entry<Block, Block> entry : CorruptionRecipe.HARDCODED_BLOCK.entrySet()) {
            ResourceLocation id = ForgeRegistries.BLOCKS.getKey(entry.getKey());
            corruptionRecipes.add(new CorruptionRecipe(id, id.toString(), List.of(new CorruptionRecipe.Entry(entry.getValue(), 100))));
        }*/

        registration.addRecipes(CropressingRecipeCategory.CROPRESSING, cropressingRecipes);
        registration.addRecipes(RebrewingCategory.REBREWING, JeiRebrewingRecipe.createRecipes());
      //  registration.addRecipes(CorruptionCategory.CORRUPTION, corruptionRecipes);
    }
}
