package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.lootmodifers.conditions.BoblingTypeCondition;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public class ModLoot {
    public static final LazyRegistrar<LootItemConditionType> CONDITIONS = LazyRegistrar.create(Registries.LOOT_CONDITION_TYPE, MoreSnifferFlowers.MOD_ID);

    public static final RegistryObject<LootItemConditionType> BOBLING_TYPE = CONDITIONS.register("bobling_type", () -> new LootItemConditionType(new BoblingTypeCondition.ConditionSerializer()));
}
