package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.recipes.CropressingRecipe;

public class ModRecipeTypes {
    public static LazyRegistrar<RecipeType<?>> RECIPE_TYPES = LazyRegistrar.create(BuiltInRegistries.RECIPE_TYPE, MoreSnifferFlowers.MOD_ID);

    public static final RegistryObject<RecipeType<CropressingRecipe>> CROPRESSING = register("cropressing");
   // public static final RegistryObject<RecipeType<CorruptionRecipe>> CORRUPTION = register("corruption");

    static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(final String id) {
        return RECIPE_TYPES.register(id, () -> RecipeType.register(MoreSnifferFlowers.MOD_ID));
    }
}
