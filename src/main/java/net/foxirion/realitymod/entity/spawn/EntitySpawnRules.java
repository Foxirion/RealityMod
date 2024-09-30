package net.foxirion.realitymod.entity.spawn;

import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.LightLayer;

public class EntitySpawnRules {

    //Desert Turtle Spawn rules

    public static boolean checkDesertTurtleSpawnRules(EntityType<DesertTurtleEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        // Check if it's a desert biome and on sand
        if (!level.getBiome(pos).is(Biomes.DESERT) || !level.getBlockState(pos.below()).is(Blocks.SAND)) {
            return false;
        }

        // Check if it's night time (similar to sea turtles)
        long timeOfDay = level.getLevelData().getDayTime() % 24000;
        if (timeOfDay < 13000 || timeOfDay > 23000) {
            return false;
        }

        // Check for nearby water (desert oasis)
        boolean nearWater = false;
        int checkRadius = 4;
        for (BlockPos checkPos : BlockPos.betweenClosed(pos.offset(-checkRadius, -1, -checkRadius), pos.offset(checkRadius, 1, checkRadius))) {
            if (level.getBlockState(checkPos).is(Blocks.WATER)) {
                nearWater = true;
                break;
            }
        }
        if (!nearWater) {
            return false;
        }

        // Check light level (desert turtles prefer lighter areas)
        if (level.getBrightness(LightLayer.SKY, pos) > 8) {
            return true;
        }

        // Spawn chance (adjust this value to control spawn rate)
        if (random.nextFloat() > 0.1f) {  // 10% chance of spawning
            return false;
        }

        // Check other animal spawn rules
        return DesertTurtleEntity.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

    // You can add more spawn rule methods for other entities here
}