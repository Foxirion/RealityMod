package net.foxirion.realitymod.event;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = RealityMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DESERT_TURTLE.get(), DesertTurtleEntity.createAttributes().build());
    }
}
