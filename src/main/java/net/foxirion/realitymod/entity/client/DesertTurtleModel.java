package net.foxirion.realitymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class DesertTurtleModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart turtle;
	private final ModelPart head;

	public DesertTurtleModel(ModelPart root) {
		this.turtle = root.getChild("turtle");
		this.head = turtle.getChild("body").getChild("torso").getChild("head");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition turtle = partdefinition.addOrReplaceChild("turtle", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition body = turtle.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition shell = torso.addOrReplaceChild("shell", CubeListBuilder.create().texOffs(0, 26).addBox(-7.0F, -8.7F, 8.0F, 13.0F, 20.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-10.0F, -8.7F, 2.0F, 19.0F, 20.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(28, 28).addBox(-6.0F, -8.7F, -1.0F, 11.0F, 18.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.9F, -2.0F, 1.3963F, 0.0F, 0.0F));

		PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -10.0F, -12.0F, -0.0873F, 0.0F, 0.0F));

		PartDefinition head_r1 = head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(0, 47).addBox(-3.5F, -12.3F, -11.6F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 8.0F, 0.0436F, 0.0F, 0.0F));

		PartDefinition left_back_leg = body.addOrReplaceChild("left_back_leg", CubeListBuilder.create(), PartPose.offset(7.5F, -7.0F, 6.0F));

		PartDefinition left_back_leg_r1 = left_back_leg.addOrReplaceChild("left_back_leg_r1", CubeListBuilder.create().texOffs(48, 49).addBox(5.5F, -2.75F, 10.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.5F, 7.0F, -11.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition right_back_leg = body.addOrReplaceChild("right_back_leg", CubeListBuilder.create(), PartPose.offset(-7.0F, -6.0F, 6.0F));

		PartDefinition right_back_leg_r1 = right_back_leg.addOrReplaceChild("right_back_leg_r1", CubeListBuilder.create().texOffs(36, 49).addBox(-9.5F, -2.75F, 10.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 6.0F, -11.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition right_front_leg = body.addOrReplaceChild("right_front_leg", CubeListBuilder.create(), PartPose.offset(-7.0F, -6.0F, -10.0F));

		PartDefinition right_front_leg_r1 = right_front_leg.addOrReplaceChild("right_front_leg_r1", CubeListBuilder.create().texOffs(24, 49).addBox(-9.5F, -6.5F, -6.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, 6.0F, 5.0F, -0.1309F, 0.0F, 0.0F));

		PartDefinition left_front_leg = body.addOrReplaceChild("left_front_leg", CubeListBuilder.create(), PartPose.offset(6.0F, -6.0F, -10.0F));

		PartDefinition left_front_leg_r1 = left_front_leg.addOrReplaceChild("left_front_leg_r1", CubeListBuilder.create().texOffs(50, 0).addBox(5.5F, -6.5F, -6.5F, 3.0F, 7.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 6.0F, 5.0F, -0.1309F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		turtle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return turtle;
	}
}