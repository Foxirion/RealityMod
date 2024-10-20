package net.foxirion.realitymod.worldgen.tree.custom;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.foxirion.realitymod.worldgen.tree.ModFoliagePlacers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class PalmFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<PalmFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(palmFoliagePlacerInstance
            -> foliagePlacerParts(palmFoliagePlacerInstance).and(Codec.intRange(1, 4).fieldOf("frond_length")
            .forGetter(fp -> fp.frondLength)).apply(palmFoliagePlacerInstance, PalmFoliagePlacer::new));

    private final int frondLength;

    public PalmFoliagePlacer(IntProvider pRadius, IntProvider pOffset, int frondLength) {
        super(pRadius, pOffset);
        this.frondLength = frondLength;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacers.PALM_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random, TreeConfiguration config,
                                 int maxFreeTreeHeight, FoliageAttachment attachment, int foliageHeight, int foliageRadius, int offset) {
        BlockPos pos = attachment.pos().above(offset);

        // Create the top cluster
        tryPlaceLeaf(level, blockSetter, random, config, pos);
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            tryPlaceLeaf(level, blockSetter, random, config, pos.relative(direction));
        }

        // Create the palm fronds
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            createFrond(level, blockSetter, random, config, pos, direction);
        }
    }

    private void createFrond(LevelSimulatedReader level, FoliageSetter blockSetter, RandomSource random,
                             TreeConfiguration config, BlockPos startPos, Direction direction) {
        BlockPos.MutableBlockPos currentPos = startPos.mutable();

        for (int i = 0; i < frondLength; i++) {
            currentPos.move(direction).move(Direction.DOWN);

            if (i == 0) {
                // First block of the frond
                tryPlaceLeaf(level, blockSetter, random, config, currentPos.above());
                tryPlaceLeaf(level, blockSetter, random, config, currentPos);
            } else if (i == 1) {
                // Second block of the frond
                tryPlaceLeaf(level, blockSetter, random, config, currentPos.above());
                tryPlaceLeaf(level, blockSetter, random, config, currentPos);
                tryPlaceLeaf(level, blockSetter, random, config, currentPos.relative(direction));
            } else if (i == 2) {
                // Third block of the frond
                tryPlaceLeaf(level, blockSetter, random, config, currentPos);
                tryPlaceLeaf(level, blockSetter, random, config, currentPos.relative(direction));
            }

            // Add some randomness to the frond length
            if (i == frondLength - 1 && random.nextBoolean()) {
                tryPlaceLeaf(level, blockSetter, random, config, currentPos.relative(direction));
            }
        }
    }

    @Override
    public int foliageHeight(RandomSource pRandom, int pHeight, TreeConfiguration pConfig) {
        return 0; // Palm trees typically have leaves only at the top
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource pRandom, int pLocalX, int pLocalY, int pLocalZ, int pRange, boolean pLarge) {
        return false;
    }
}