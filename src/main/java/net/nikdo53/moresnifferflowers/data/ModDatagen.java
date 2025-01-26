package net.nikdo53.moresnifferflowers.data;

import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.data.advancement.ModAdvancementGenerator;
import net.nikdo53.moresnifferflowers.data.advancement.ModAdvancementProvider;
import net.nikdo53.moresnifferflowers.data.loot.ModLootModifierProvider;
import net.nikdo53.moresnifferflowers.data.loot.ModLoottableProvider;
import net.nikdo53.moresnifferflowers.data.recipe.ModRecipesProvider;
import net.nikdo53.moresnifferflowers.data.tag.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

public class ModDatagen implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator gen) {
        FabricDataGenerator.Pack pack = gen.createPack();

        pack.addProvider(ModAdvancementGenerator::new);

    }

}
