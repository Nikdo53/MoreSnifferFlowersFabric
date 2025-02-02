package net.nikdo53.moresnifferflowers.lootmodifers.conditions;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Serializer;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.nikdo53.moresnifferflowers.init.ModLoot;

public record BoblingTypeCondition(boolean cured) implements LootItemCondition {


    @Override
    public LootItemConditionType getType() {
        return ModLoot.BOBLING_TYPE.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        return lootContext.getParam(LootContextParams.THIS_ENTITY) instanceof BoblingEntity bobling && bobling.isCured() == cured;
    }


    public static Builder builder(boolean cured) {
        return () -> new BoblingTypeCondition(cured);
    }


    public static class ConditionSerializer implements Serializer<BoblingTypeCondition> {
        @Override
        public void serialize(JsonObject json, BoblingTypeCondition value, JsonSerializationContext context) {
            json.addProperty("cured", value.cured);
        }

        @Override
        public BoblingTypeCondition deserialize(JsonObject json, JsonDeserializationContext context) {
            return new BoblingTypeCondition(GsonHelper.getAsBoolean(json, "cured", false));
        }
    }
}
