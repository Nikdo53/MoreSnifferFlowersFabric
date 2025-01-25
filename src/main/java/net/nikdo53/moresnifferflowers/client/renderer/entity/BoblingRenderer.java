package net.nikdo53.moresnifferflowers.client.renderer.entity;

import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.client.model.ModModelLayerLocations;
import net.nikdo53.moresnifferflowers.client.model.entity.BoblingModel;
import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class BoblingRenderer extends MobRenderer<BoblingEntity, BoblingModel<BoblingEntity>> {
    public static final ResourceLocation CORRUPTED_TEXTURE = MoreSnifferFlowers.loc("textures/entity/bobling/corrupted_bobling.png");
    public static final ResourceLocation CURED_TEXTURE = MoreSnifferFlowers.loc("textures/entity/bobling/bobling.png");
    public static final ResourceLocation BONMEELED_TEXTURE = MoreSnifferFlowers.loc("textures/entity/bobling/bonmeeled_bobling.png");
    
    public BoblingRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new BoblingModel<>(pContext.bakeLayer(ModModelLayerLocations.BOBLING)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(BoblingEntity pEntity) {
        if (pEntity.getBoblingType() == BoblingEntity.Type.CORRUPTED) {
            return CORRUPTED_TEXTURE;
        } else {
            return CURED_TEXTURE;
        }
    }
}
