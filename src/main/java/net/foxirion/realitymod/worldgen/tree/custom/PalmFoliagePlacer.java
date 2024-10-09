package net.foxirion.realitymod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.foxirion.realitymod.worldgen.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final Codec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.create(palmFoliagePlacerInstance
            -> foliagePlacerParts(palmFoliagePlacerInstance).and(Codec.intRange(0, 16).fieldOf("height")
            .forGetter(fp -> fp.height)).apply(palmFoliagePlacerInstance, PalmFoliagePlacer::new));
    private final int height;

    public PalmFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int height) {
        super(pRadius, pOffset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config,
                                 int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {

        // Define the top of the trunk
        BlockPos topPos = attachment.pos().above(foliageHeight - 1);

        // Define offsets for a lighter top layer
        int[][] topLayerOffsets = {
                {0, 0},    // Center log position
                {1, 0},    // Right
                {-1, 0},   // Left
                {0, 1},    // Front
                {0, -1},   // Back
                {1, -1},   // Top-right diagonal
                {-1, -1},  // Top-left diagonal
                {1, 1},    // Bottom-right diagonal
                {-1, 1},   // Bottom-left diagonal
        };

        // Place the leaves for the lighter top layer
        for (int[] leafOffset : topLayerOffsets) {
            tryPlaceLeaf(level, blockSetter, random, config, topPos.offset(leafOffset[0], 0, leafOffset[1]));
        }

        // Define offsets for the main layer
        int[][] mainLayerOffsets = {
                {0, 0},    // Center log position
                {1, 0},    // Right
                {-1, 0},   // Left
                {0, 1},    // Front
                {0, -1},   // Back
                {1, 1},    // Bottom-right diagonal
                {1, -1},   // Top-right diagonal
                {-1, 1},   // Bottom-left diagonal
                {-1, -1},  // Top-left diagonal
                {2, 0},    // Right 2
                {-2, 0},   // Left 2
                {0, 2},    // Front 2
                {0, -2},   // Back 2
                {2, 1},    // Bottom-right diagonal 2
                {2, -1},   // Top-right diagonal 2
                {-2, 1},   // Bottom-left diagonal 2
                {-2, -1},  // Top-left diagonal 2
                {1, 2},    // Front-right diagonal
                {1, -2},   // Back-right diagonal
                {-1, 2},   // Front-left diagonal
                {-1, -2},  // Back-left diagonal
        };

        // Place the leaves for the main layer
        for (int[] leafOffset : mainLayerOffsets) {
            tryPlaceLeaf(level, blockSetter, random, config, topPos.below().offset(leafOffset[0], 0, leafOffset[1]));
        }

        // Additional connections to avoid decay at the bottom layer
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                if (Math.abs(i) + Math.abs(j) <= 2) { // Create a filled area
                    tryPlaceLeaf(level, blockSetter, random, config, topPos.below(2).offset(i, 0, j)); // Increased leaf count
                }
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return this.height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}
