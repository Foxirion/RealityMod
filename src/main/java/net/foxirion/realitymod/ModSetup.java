package net.foxirion.realitymod;

import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class ModSetup {
    public static void init() {
        registerSpawnPlacements();
    }

    private static void registerSpawnPlacements() {
        SpawnPlacements.register(ModEntities.DESERT_TURTLE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DesertTurtleEntity::checkDesertTurtleSpawnRules);
    }
}