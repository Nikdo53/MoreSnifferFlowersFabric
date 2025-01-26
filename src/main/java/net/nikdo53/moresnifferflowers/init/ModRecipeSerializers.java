package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.recipes.RebrewedTippedArrowRecipe;
import net.nikdo53.moresnifferflowers.recipes.serializers.CorruptionSerializer;
import net.nikdo53.moresnifferflowers.recipes.serializers.CropressingSerializer;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ModRecipeSerializers {
    public static LazyRegistrar<RecipeSerializer<?>> RECIPE_SERIALIZERS = LazyRegistrar.create(BuiltInRegistries.RECIPE_SERIALIZER, MoreSnifferFlowers.MOD_ID);


    public static final RegistryObject<CropressingSerializer> CROPRESSING = RECIPE_SERIALIZERS.register("cropressing", CropressingSerializer::new);
    public static final RegistryObject<RecipeSerializer<RebrewedTippedArrowRecipe>> REBREWED_TIPPED_ARROW = RECIPE_SERIALIZERS.register("rebrewed_tipped_arrow", () -> new SimpleCraftingRecipeSerializer<RebrewedTippedArrowRecipe>(RebrewedTippedArrowRecipe::new));
   // public static final RegistryObject<CorruptionSerializer> CORRUPTION = RECIPE_SERIALIZERS.register("corruption", CorruptionSerializer::new);
}