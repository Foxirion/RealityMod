package net.foxirion.realitymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.foxirion.realitymod.entity.animations.ModAnimationDefinitions;
import net.foxirion.realitymod.entity.custom.Fennec;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class FennecModel<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart fennec;
	private final ModelPart head;
	private final ModelPart ear0;
	private final ModelPart ear1;
	private final ModelPart body;
	private final ModelPart tail;
	private final ModelPart main;
	private final ModelPart leg0;
	private final ModelPart leg1;
	private final ModelPart leg2;
	private final ModelPart leg3;

	public FennecModel(ModelPart root) {
		this.fennec = root.getChild("fennec");
		this.head = fennec.getChild("head");
		this.ear0 = head.getChild("ear0");
		this.ear1 = head.getChild("ear1");
		this.body = fennec.getChild("body");
		this.tail = body.getChild("tail");
		this.main = body.getChild("main");
		this.leg0 = fennec.getChild("leg0");
		this.leg1 = fennec.getChild("leg1");
		this.leg2 = fennec.getChild("leg2");
		this.leg3 = fennec.getChild("leg3");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition fennec = partdefinition.addOrReplaceChild("fennec", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, -0.5F));

		PartDefinition head = fennec.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 9).addBox(-3.0F, -4.0F, -5.0F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(2, 22).addBox(-2.0F, -1.0F, -7.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.5F));

		PartDefinition ear0 = head.addOrReplaceChild("ear0", CubeListBuilder.create(), PartPose.offset(1.7F, -5.0F, -4.0F));

		PartDefinition cube_r1 = ear0.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(2, 2).addBox(-1.5166F, -2.5F, 0.0F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition ear1 = head.addOrReplaceChild("ear1", CubeListBuilder.create().texOffs(2, 2).addBox(-1.4F, -2.5F, -0.5F, 3.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.8F, -5.0F, -3.5F, 0.0F, 0.0F, -0.1309F));

		PartDefinition body = fennec.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(2.0F, 5.0F, -0.5F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(17, 1).addBox(-3.0F, -6.0F, 5.0F, 2.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition main = body.addOrReplaceChild("main", CubeListBuilder.create().texOffs(8, 20).addBox(-4.0F, -7.0F, -3.0F, 4.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leg0 = fennec.addOrReplaceChild("leg0", CubeListBuilder.create().texOffs(11, 4).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, -2.0F));

		PartDefinition leg1 = fennec.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(11, 4).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, -2.0F));

		PartDefinition leg2 = fennec.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(11, 4).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 2.0F, 3.0F));

		PartDefinition leg3 = fennec.addOrReplaceChild("leg3", CubeListBuilder.create().texOffs(11, 4).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 2.0F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyHeadRotation(netHeadYaw, headPitch, ageInTicks);

		this.animateWalk(ModAnimationDefinitions.FENNEC_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((Fennec) entity).idleAnimationState, ModAnimationDefinitions.FENNEC_IDLE, ageInTicks, 1f);

		Fennec fennec = (Fennec) entity;

		// Sitting animation takes priority
		if (fennec.isSitting()) {
			this.animate(fennec.sittingAnimationState, ModAnimationDefinitions.FENNEC_IDLE_STANDING, ageInTicks, 1f);
		} else {
			// Only play walk and idle if not sitting
			this.animateWalk(ModAnimationDefinitions.FENNEC_WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
			this.animate(fennec.idleAnimationState, ModAnimationDefinitions.FENNEC_IDLE, ageInTicks, 1f);
		}

		// Ear flapping animation (can still play while sitting/walking/idle)
		if (fennec.earFlapAnimationState.isStarted()) {
			this.animate(fennec.earFlapAnimationState, ModAnimationDefinitions.FENNEC_EARS_FLAPPING, ageInTicks, 1f);
		}

		// Randomly start the ear flap animation
		if (!fennec.earFlapAnimationState.isStarted() && fennec.getRandom().nextFloat() < 0.02f) { // 2% chance each tick
			fennec.earFlapAnimationState.start((int) ageInTicks);
		}
	}

	private void applyHeadRotation(float netHeadYaw, float headPitch, float ageInTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		fennec.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return fennec;
	}
}