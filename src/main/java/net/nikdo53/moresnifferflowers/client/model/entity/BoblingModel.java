package net.nikdo53.moresnifferflowers.client.model.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class BoblingModel<T extends BoblingEntity> extends HierarchicalModel<T> {
	private final ModelPart root;
	private final ModelPart torso_lower;
	private final ModelPart torso_upper;
	private final ModelPart legs;
	private final ModelPart right_feet;
	private final ModelPart left_feet;
	private final ModelPart head;
	private final ModelPart leaves;

	public BoblingModel(ModelPart root) {
		this.root = root.getChild("root");
		this.torso_lower = this.root.getChild("torso_lower");
		this.torso_upper = this.root.getChild("torso_upper");
		this.legs = this.root.getChild("legs");
		this.right_feet = this.legs.getChild("right_feet");
		this.left_feet = this.legs.getChild("left_feet");
		this.head = this.root.getChild("head");
		this.leaves = this.head.getChild("leaves");
	}


	@Override
	public ModelPart root() {
		return this.root;
	}
	
	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 24.0F, 0.5F));

		PartDefinition torso_lower = root.addOrReplaceChild("torso_lower", CubeListBuilder.create().texOffs(0, 16).addBox(-2.5F, -4.0F, -2.5F, 5.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition torso_upper = root.addOrReplaceChild("torso_upper", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition torso_upper_r1 = torso_upper.addOrReplaceChild("torso_upper_r1", CubeListBuilder.create().texOffs(17, 24).addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.9599F, 0.0F));

		PartDefinition legs = root.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition right_feet = legs.addOrReplaceChild("right_feet", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(12, 16).addBox(-0.5F, 1.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 0.0F, 0.0F));

		PartDefinition left_feet = legs.addOrReplaceChild("left_feet", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, 1.0F, -1.5F, 1.0F, 0.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(0, 17).addBox(-0.5F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 0.0F, 0.0F));

		PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 16).addBox(-2.0F, -4.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.0F, 0.0F));

		PartDefinition leaves = head.addOrReplaceChild("leaves", CubeListBuilder.create(), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition leaf2_r1 = leaves.addOrReplaceChild("leaf2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -4.5F, 0.0F, 16.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.0303F, -3.5F, 0.0303F, 0.0F, 0.7854F, 0.0F));

		PartDefinition leaf1_r1 = leaves.addOrReplaceChild("leaf1_r1", CubeListBuilder.create().texOffs(0, 8).addBox(-8.0F, -8.0F, 0.0429F, 16.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	
	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.xRot = headPitch * (float) (Math.PI / 180.0);
		this.head.yRot = netHeadYaw * (float) (Math.PI / 180.0);
		
		this.right_feet.xRot = Mth.cos(limbSwing * 0.8F + (float) Math.PI) * 1.4F * limbSwingAmount;
		this.left_feet.xRot = Mth.cos(limbSwing * 0.8F) * 1.4F * limbSwingAmount;
		this.torso_upper.zRot = Mth.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount;
		this.head.zRot = Mth.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
		
		this.animate(entity.plantingAnimationState, BoblingAnimations.PLANT, ageInTicks);
		this.animate(entity.idleAnimationState, BoblingAnimations.IDLE, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack pPoseStack, VertexConsumer pBuffer, int pPackedLight, int pPackedOverlay, float pRed, float pGreen, float pBlue, float pAlpha) {
		root.render(pPoseStack, pBuffer, pPackedLight, pPackedOverlay, pRed, pGreen, pBlue, pAlpha);
	}
}