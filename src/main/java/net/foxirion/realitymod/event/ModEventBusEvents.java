package net.foxirion.realitymod.event;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

@EventBusSubscriber(modid = RealityMod.MODID,bus = EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    //Mob Spawning
    @SubscribeEvent
    public static void onSpawnPlacementRegister(RegisterSpawnPlacementsEvent event) {
        event.register(
                ModEntities.DESERT_TURTLE.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DesertTurtleEntity::checkDesertTurtleSpawnRules,
                RegisterSpawnPlacementsEvent.Operation.AND);
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DESERT_TURTLE.get(), DesertTurtleEntity.createAttributes().build());
    }
}
