package net.foxirion.realitymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.custom.Fennec;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FennecRenderer extends MobRenderer<Fennec, FennecModel<Fennec>> {

    public FennecRenderer(EntityRendererProvider.Context context) {
        super(context, new FennecModel<>(context.bakeLayer(ModModelLayers.FENNEC_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Fennec entity) {
        return new ResourceLocation(RealityMod.MOD_ID, "textures/entity/fennec.png");
    }

    @Override
    public void render(Fennec entity, float entityYaw, float partialTicks, PoseStack matrixStack,
                       MultiBufferSource buffer, int packedLight) {
        // Scale down if the fennec is a baby
        if (entity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        // Apply a downward translation when the fennec is sitting
        if (entity.isInSittingPose()) {
            matrixStack.translate(0, -0.1875, 0); // Move model down by 3 pixels
        }

        super.render(entity, entityYaw, partialTicks, matrixStack, buffer, packedLight);
    }
}

