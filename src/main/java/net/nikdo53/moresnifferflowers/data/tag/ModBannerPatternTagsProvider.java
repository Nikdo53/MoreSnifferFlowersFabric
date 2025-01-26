package net.nikdo53.moresnifferflowers.data.tag;

import io.github.fabricators_of_create.porting_lib.data.ExistingFileHelper;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.init.ModBannerPatterns;
import net.nikdo53.moresnifferflowers.init.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.block.entity.BannerPattern;

import java.util.concurrent.CompletableFuture;

public class ModBannerPatternTagsProvider extends FabricTagProvider<BannerPattern> {
    public ModBannerPatternTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, ExistingFileHelper helper) {
        super((FabricDataOutput) output, Registries.BANNER_PATTERN, provider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ModTags.ModBannerPatternTags.AMBUSH_BANNER_PATTERN).add(ModBannerPatterns.AMBUSH.getKey());
        tag(ModTags.ModBannerPatternTags.EVIL_BANNER_PATTERN).add(ModBannerPatterns.EVIL.getKey());
    }
}
