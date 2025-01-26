package net.nikdo53.moresnifferflowers.recipes.serializers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;

import java.util.ArrayList;

public class CorruptionSerializer {
 /*   @Override
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
    public CorruptionRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
        return null;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buffer, CorruptionRecipe recipe) {

    }

  */

  /*  @Override
    public @Nullable CorruptionRecipe fromNetwork(ResourceLocation resourceLocation, FriendlyByteBuf buf) {
        List<CorruptionRecipe.Entry> list = new ArrayList<>();
        String source = buf.readUtf();
        
        for (int i = 0; i < buf.readInt(); i++) {
            CorruptionRecipe.Entry entry = new CorruptionRecipe.Entry(buf.readRegistryId(), buf.readInt());
            list.add(entry);
        }
        
        return new CorruptionRecipe(resourceLocation, source, list);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, CorruptionRecipe recipe) {
        buf.writeUtf(recipe.source());
        
        buf.writeInt(recipe.list().size());
        recipe.list().forEach(entry -> {
            buf.writeRegistryId(ForgeRegistries.BLOCKS, entry.block());
            buf.writeInt(entry.weight());
        });
    }

   */
}
