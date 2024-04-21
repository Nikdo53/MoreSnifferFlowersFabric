package net.abraxator.moresnifferflowers.init;

import net.abraxator.moresnifferflowers.MoreSnifferFlowers;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeType;

public class ModRecipeTypes {
    public static final RecipeType<?> CROPRESSOR = register("cropressor");

    static <T extends Recipe<?>> RecipeType<T> register(final String id) {
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return id;
            }
        };
    }

    public static void registerRecipeTypes() {
        MoreSnifferFlowers.LOGGER.info("Registering Recipetypes for" + MoreSnifferFlowers.MOD_ID);

    }
}
