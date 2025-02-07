package net.nikdo53.moresnifferflowers.recipes.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.recipes.CorruptionRecipe;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CorruptionSerializer implements RecipeSerializer<CorruptionRecipe> {
    @Override
    public CorruptionRecipe fromJson(ResourceLocation resourceLocation, JsonObject jsonObject) {
        String source = GsonHelper.getAsString(jsonObject, "source");
        
        List<CorruptionRecipe.Entry> list = new ArrayList<>();
        for (JsonElement corrupted : GsonHelper.getAsJsonArray(jsonObject, "corrupted")) {
            CorruptionRecipe.Entry entry = CorruptionRecipe.Entry.fromJsonElement(corrupted);
            list.add(entry);
        }
        
        return new CorruptionRecipe(resourceLocation, source, list);
    }


    @Override
    public @Nullable CorruptionRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
        List<CorruptionRecipe.Entry> list = new ArrayList<>();
        String source = buf.readUtf();
        int count = buf.readInt();

        for (int i = 0; i < count; i++) {
            CorruptionRecipe.Entry entry = new CorruptionRecipe.Entry(BuiltInRegistries.BLOCK.byId(buf.readInt()), buf.readInt());
            list.add(entry);
        }
        
        return new CorruptionRecipe(resourceLocation, source, list);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, CorruptionRecipe recipe) {
        buf.writeUtf(recipe.source());
        
        buf.writeInt(recipe.list().size());
        recipe.list().forEach(entry -> {
            buf.writeInt(BuiltInRegistries.BLOCK.getId(entry.block()));;
            buf.writeInt(entry.weight());
        });
    }


}
