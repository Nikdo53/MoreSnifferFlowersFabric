package net.nikdo53.moresnifferflowers.recipes.serializers;

import com.google.gson.JsonObject;
import net.nikdo53.moresnifferflowers.recipes.CropressingRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import org.jetbrains.annotations.Nullable;

public class CropressingSerializer implements RecipeSerializer<CropressingRecipe> {
    @Override
    public CropressingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
        var count = GsonHelper.getAsInt(pSerializedRecipe, "count");
        var ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "ingredient"));
        var result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "result"));

        return new CropressingRecipe(pRecipeId, ingredient, count, result);
    }

    @Override
    public @Nullable CropressingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
        return new CropressingRecipe(pRecipeId, Ingredient.fromNetwork(pBuffer), pBuffer.readVarInt(), pBuffer.readItem());
    }

    @Override
    public void toNetwork(FriendlyByteBuf pBuffer, CropressingRecipe pRecipe) {
        pRecipe.ingredient().toNetwork(pBuffer);
        pBuffer.writeVarInt(pRecipe.count());
        pBuffer.writeItem(pRecipe.result());
    }
}