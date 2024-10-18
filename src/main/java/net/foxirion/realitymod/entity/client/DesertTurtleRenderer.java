package net.foxirion.realitymod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DesertTurtleRenderer extends MobRenderer<DesertTurtleEntity, DesertTurtleModel<DesertTurtleEntity>> {
    public DesertTurtleRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new DesertTurtleModel<>(pContext.bakeLayer(ModModelLayers.DESERT_TURTLE_LAYER)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(DesertTurtleEntity pEntity){
        return ResourceLocation.fromNamespaceAndPath(RealityMod.MODID, "textures/entity/desert_turtle.png");
    }

    @Override
    public void render(DesertTurtleEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()){
            pMatrixStack.scale(0.3F, 0.3F, 0.3F);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
