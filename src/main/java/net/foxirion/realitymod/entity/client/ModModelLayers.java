package net.foxirion.realitymod.entity.client;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation DESERT_TURTLE_LAYER = new ModelLayerLocation(
            new ResourceLocation(RealityMod.MOD_ID, "desert_turtle_layer"), "main");

    public static final ModelLayerLocation PALM_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(RealityMod.MOD_ID, "boat/palm"), "main");
    public static final ModelLayerLocation PALM_CHEST_BOAT_LAYER = new ModelLayerLocation(
            new ResourceLocation(RealityMod.MOD_ID, "chest_boat/palm"), "main");
}
