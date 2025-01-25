package net.nikdo53.moresnifferflowers.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.blockentities.BondripiaBlockEntity;
import net.nikdo53.moresnifferflowers.client.model.ModModelLayerLocations;
import net.nikdo53.moresnifferflowers.init.ModBlocks;
import net.nikdo53.moresnifferflowers.init.ModStateProperties;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.phys.Vec3;

public class BondripiaBlockEntityRenderer<T extends BondripiaBlockEntity> implements BlockEntityRenderer<T> {
    private ModelPart model;
    private static final Material BONDRIPIA_TEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, MoreSnifferFlowers.loc("block/bondripia"));
    private static final Material ACID_DRIPIATEXTURE = new Material(TextureAtlas.LOCATION_BLOCKS, MoreSnifferFlowers.loc("block/acidripia"));

    public BondripiaBlockEntityRenderer(BlockEntityRendererProvider.Context pContext) {
        this.model = pContext.bakeLayer(ModModelLayerLocations.BONDRIPIA);
    }

    @Override
    public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        if(pBlockEntity.getBlockState().getValue(ModStateProperties.CENTER) && pBlockEntity.getBlockState().getValue(ModStateProperties.AGE_2) >= 2) {
            pPoseStack.translate(0.5, 1.5, 0.5);
            pPoseStack.mulPose(Axis.XP.rotationDegrees(180));
            Material material = pBlockEntity.getBlockState().is(ModBlocks.ACIDRIPIA.get()) ? ACID_DRIPIATEXTURE : BONDRIPIA_TEXTURE;
            this.model.render(pPoseStack, material.buffer(pBufferSource, RenderType::entityCutout), pPackedLight, pPackedOverlay);
        }
    }

    @Override
    public boolean shouldRenderOffScreen(T pBlockEntity) {
        return true;
    }

    public int getViewDistance() {
        return 256;
    }

    public boolean shouldRender(T pBlockEntity, Vec3 pCameraPos) {
        return Vec3.atCenterOf(pBlockEntity.getBlockPos()).multiply(1.0D, 0.0D, 1.0D).closerThan(pCameraPos.multiply(1.0D, 0.0D, 1.0D), this.getViewDistance());
    }
}
