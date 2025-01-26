package net.nikdo53.moresnifferflowers.data.tag;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.entity.decoration.PaintingVariant;

import java.util.concurrent.CompletableFuture;

public class ModPaintingTagsProvider extends TagsProvider<PaintingVariant> {
    public ModPaintingTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, ExistingFileHelper existingFileHelper) {
        super(pOutput, Registries.PAINTING_VARIANT, pLookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //ModPaintings.HATTED_FERGUS_TATER.unwrapKey().ifPresent(paintingVariantResourceKey -> this.tag(PaintingVariantTags.PLACEABLE).add(paintingVariantResourceKey));
    }
}
