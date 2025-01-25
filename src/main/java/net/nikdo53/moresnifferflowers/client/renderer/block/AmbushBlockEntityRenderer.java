package net.nikdo53.moresnifferflowers.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.nikdo53.moresnifferflowers.blockentities.XbushBlockEntity;
import net.nikdo53.moresnifferflowers.blocks.xbush.AbstractXBushBlockUpper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class AmbushBlockEntityRenderer implements BlockEntityRenderer<XbushBlockEntity> {
    private final BlockRenderDispatcher blockRenderer;

    public AmbushBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.blockRenderer = context.getBlockRenderDispatcher();
    }

    @Override
    public void render(XbushBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if(pBlockEntity.getBlockState().getBlock() instanceof AbstractXBushBlockUpper bushBlockUpper) {
            BlockState state = bushBlockUpper.getDropBlock().defaultBlockState();
            pPoseStack.pushPose();
            float progress = Math.min(pBlockEntity.growProgress, 1);
            float translate = 0.5f -(progress  * 0.5f);
            pPoseStack.translate(translate, translate, translate);
            pPoseStack.scale(progress, progress, progress);
            this.blockRenderer.renderSingleBlock(state, pPoseStack, pBufferSource, pPackedLight, pPackedOverlay);
            pPoseStack.popPose();
        }
    }
}