package net.nikdo53.moresnifferflowers.client.renderer.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.client.model.ModModelLayerLocations;
import net.nikdo53.moresnifferflowers.client.model.entity.DragonflyModel;
import net.nikdo53.moresnifferflowers.entities.DragonflyProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class DragonflyRenderer extends EntityRenderer<DragonflyProjectile> {
    public static final ResourceLocation TEXTURE = MoreSnifferFlowers.loc("textures/entity/dragonfly.png");
    private final DragonflyModel model;

    public DragonflyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new DragonflyModel(pContext.bakeLayer(ModModelLayerLocations.DRAGONFLY));
    }

    @Override
    public void render(DragonflyProjectile pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight) {
        pPoseStack.pushPose();
        pPoseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.yRotO, pEntity.getYRot()) - 180F));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(Mth.lerp(pPartialTick, pEntity.xRotO, pEntity.getXRot())));
        pPoseStack.translate(0, -1, 0.5);
        this.model.renderToBuffer(
                pPoseStack,
                pBufferSource.getBuffer(this.model.renderType(this.getTextureLocation(pEntity))),
                pPackedLight,
                OverlayTexture.NO_OVERLAY,
                1, 1, 1, 1);
        model.animate(pPartialTick);
        pPoseStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBufferSource, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(DragonflyProjectile dragonflyProjectile) {
        return TEXTURE;
    }
}
