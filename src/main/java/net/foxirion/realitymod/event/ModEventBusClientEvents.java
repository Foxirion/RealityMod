package net.foxirion.realitymod.event;

import net.foxirion.realitymod.block.entity.ModBlockEntities;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.client.DesertTurtleModel;
import net.foxirion.realitymod.entity.client.DesertTurtleRenderer;
import net.foxirion.realitymod.entity.client.ModBoatRenderer;
import net.foxirion.realitymod.entity.client.ModModelLayers;
import net.foxirion.realitymod.util.ModWoodTypes;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static net.foxirion.realitymod.RealityMod.MODID;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        EntityRenderers.register(ModEntities.DESERT_TURTLE.get(), DesertTurtleRenderer::new);
        EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
        EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

        Sheets.addWoodType(ModWoodTypes.PALM);
    }

    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.DESERT_TURTLE_LAYER, DesertTurtleModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.PALM_BOAT_LAYER, BoatModel::createBodyModel);
        event.registerLayerDefinition(ModModelLayers.PALM_CHEST_BOAT_LAYER, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(ModBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
