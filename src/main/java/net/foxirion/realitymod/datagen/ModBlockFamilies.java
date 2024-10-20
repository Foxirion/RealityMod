package net.foxirion.realitymod.datagen;

import com.google.common.collect.Maps;
import net.foxirion.realitymod.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.BlockFamily;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.stream.Stream;

public class ModBlockFamilies {
    public static final Map<Block, BlockFamily> MAP = Maps.newHashMap();
    public static final String RECIPE_GROUP_PREFIX_WOODEN = "wooden";
    public static final String RECIPE_UNLOCKED_BY_HAS_PLANKS = "has_planks";

    public static final BlockFamily PALM_PLANKS = familyBuilder(ModBlocks.PALM_PLANKS.get())
            .button(ModBlocks.PALM_BUTTON.get())
            .fence(ModBlocks.PALM_FENCE.get())
            .fenceGate(ModBlocks.PALM_FENCE_GATE.get())
            .pressurePlate(ModBlocks.PALM_PRESSURE_PLATE.get())
            .sign(ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get())
            .slab(ModBlocks.PALM_SLAB.get())
            .stairs(ModBlocks.PALM_STAIRS.get())
            .door(ModBlocks.PALM_DOOR.get())
            .trapdoor(ModBlocks.PALM_TRAPDOOR.get())
            .recipeGroupPrefix("wooden")
            .recipeUnlockedBy("has_planks")
            .getFamily();

    public static BlockFamily.Builder familyBuilder(Block block) {
        BlockFamily.Builder blockfamily$builder = new BlockFamily.Builder(block);
        BlockFamily blockfamily = MAP.put(block, blockfamily$builder.getFamily());
        if (blockfamily != null) {
            throw new IllegalStateException("Duplicate family definition for " + BuiltInRegistries.BLOCK.getKey(block));
        } else {
            return blockfamily$builder;
        }
    }

    public static Stream<BlockFamily> getAllFamilies() {
        return MAP.values().stream();
    }
}
