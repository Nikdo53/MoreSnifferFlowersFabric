package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;

public class ModItems {
        public static LazyRegistrar<Item> ITEMS = LazyRegistrar.create(BuiltInRegistries.ITEM, MoreSnifferFlowers.MOD_ID);
    public static final RegistryObject<Item> AMBER_SHARD = ITEMS.register("amber_shard", () -> new Item(new Item.Properties()));

}
