package net.foxirion.realitymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.custom.DesertTurtle;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DesertTurtleRenderer extends MobRenderer<DesertTurtle, DesertTurtleModel<DesertTurtle>> {
    public DesertTurtleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DesertTurtleModel<>(pContext.bakeLayer(ModModelLayers.DESERT_TURTLE_LAYER)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(DesertTurtle pEntity){
        return new ResourceLocation(RealityMod.MOD_ID, "textures/entity/desert_turtle.png");
    }

    @Override
    public void render(DesertTurtle pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()){
            pMatrixStack.scale(0.3F, 0.3F, 0.3F);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
