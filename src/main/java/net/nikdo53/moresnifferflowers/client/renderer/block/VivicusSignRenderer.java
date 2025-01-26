package net.nikdo53.moresnifferflowers.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.nikdo53.moresnifferflowers.blocks.ColorableVivicusBlock;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

@Environment(EnvType.CLIENT)
public class VivicusSignRenderer extends SignRenderer {
    public VivicusSignRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
    }

   // @Override
    public void renderSignWithText(SignBlockEntity pSignEntity, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, BlockState pState, SignBlock pSignBlock, WoodType pWoodType, Model pModel) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5F, 0.75F * this.getSignModelRenderScale(), 0.5F);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(-pSignBlock.getYRotationDegrees(pState)));
        if (!(pState.getBlock() instanceof StandingSignBlock)) {
            pPoseStack.translate(0.0F, -0.3125F, -0.4375F);
        }
        renderVivicusSign(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pWoodType, pModel, pState);
        this.renderSignText(
                pSignEntity.getBlockPos(),
                pSignEntity.getFrontText(),
                pPoseStack,
                pBuffer,
                pPackedLight,
                pSignEntity.getTextLineHeight(),
                pSignEntity.getMaxTextLineWidth(),
                true
        );
        this.renderSignText(
                pSignEntity.getBlockPos(),
                pSignEntity.getBackText(),
                pPoseStack,
                pBuffer,
                pPackedLight,
                pSignEntity.getTextLineHeight(),
                pSignEntity.getMaxTextLineWidth(),
                false
        );
        pPoseStack.popPose();
    }
    
    private void renderVivicusSign(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay, WoodType pWoodType, Model pModel, BlockState pState) {
        pPoseStack.pushPose();
        float f = this.getSignModelRenderScale();
        pPoseStack.scale(f, -f, -f);
        Material material = Sheets.getSignMaterial(pWoodType);
        VertexConsumer vertexconsumer = material.buffer(pBuffer, pModel::renderType);
        var color = -1;
        if(pState.getBlock() instanceof ColorableVivicusBlock colorableVivicusBlock) {
            var dyeColor = pState.getValue(ModStateProperties.COLOR);
            color = colorableVivicusBlock.colorValues().get(dyeColor);
            vertexconsumer.color(color);
        }
        this.renderSignModel(pPoseStack, pPackedLight, pPackedOverlay, pModel, vertexconsumer, color);
        pPoseStack.popPose();
    }

    void renderSignModel(PoseStack pPoseStack, int pPackedLight, int pPackedOverlay, Model pModel, VertexConsumer pVertexConsumer, int color) {
        SignModel signrenderer$signmodel = (SignModel)pModel;
        signrenderer$signmodel.root.render(pPoseStack, pVertexConsumer, pPackedLight, pPackedOverlay, (color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF, 1);
    }
}
