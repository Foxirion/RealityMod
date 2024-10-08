package net.foxirion.realitymod.worldgen.tree.custom;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.foxirion.realitymod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;
import net.minecraft.core.Direction;

import java.util.List;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {
    public static final Codec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.create(palmTrunkPlacerInstance ->
            trunkPlacerParts(palmTrunkPlacerInstance).apply(palmTrunkPlacerInstance, PalmTrunkPlacer::new));

    public PalmTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.PALM_TRUNK_PLACER.get();
    }
    

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter,
                                                            RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {

        // Setting the trunk height to match the visual pattern
        int height = 8; // Fixed height to achieve 7-9 logs in total

        BlockPos currentPos = pPos;

        // Randomly select a variant (0, 1, 2) for the trunk shape
        int variant = pRandom.nextInt(3); // 0, 1, or 2

        // Randomly select a direction (N, S, E, W)
        Direction direction = Direction.from2DDataValue(pRandom.nextInt(4)); // Randomly choose direction

        if (variant == 0) {
            // Variant 1
            for (int i = 0; i < height; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, currentPos, pConfig);

                // Adjust placement for variant 1
                if (i < 3) {  // Place the first three logs in a step-like formation
                    currentPos = currentPos.above().relative(direction.getOpposite()); // Go up and then back
                } else {
                    currentPos = currentPos.above(); // Continue growing vertically for the remaining logs
                }
            }
        } else if (variant == 1) {
            // Variant 2
            for (int i = 0; i < height; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, currentPos, pConfig);

                // Adjust placement for variant 2
                if (i < 3) {  // Place the first three logs in a diagonal formation
                    currentPos = currentPos.above().relative(direction); // Go up and shift based on direction
                } else {
                    currentPos = currentPos.above(); // Continue growing vertically for the remaining logs
                }
            }
        } else {
            // Variant 3 (small and rare)
            for (int i = 0; i < height; i++) {
                placeLog(pLevel, pBlockSetter, pRandom, currentPos, pConfig);

                // Adjust placement for variant 3
                if (i == 0) { // First log is placed slightly up
                    currentPos = currentPos.above().relative(direction.getOpposite().getOpposite()); // Shift up and back
                } else if (i == 1) { // Second log is placed directly below
                    currentPos = currentPos.above().relative(direction); // Shift again based on direction
                } else {
                    currentPos = currentPos.above(); // Continue growing vertically
                }
            }
        }

        // Position for foliage attachment at the top
        BlockPos foliagePos = currentPos.above();

        return ImmutableList.of(new FoliagePlacer.FoliageAttachment(foliagePos, 0, false));
    }

}