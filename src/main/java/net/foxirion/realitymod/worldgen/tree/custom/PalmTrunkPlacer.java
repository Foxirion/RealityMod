package net.foxirion.realitymod.worldgen.tree.custom;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.foxirion.realitymod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.OptionalInt;
import java.util.function.BiConsumer;

public class PalmTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<PalmTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(palmTrunkPlacerInstance ->
            trunkPlacerParts(palmTrunkPlacerInstance).apply(palmTrunkPlacerInstance, PalmTrunkPlacer::new)
    );

    public PalmTrunkPlacer(int pBaseHeight, int pHeightRandA, int pHeightRandB) {
        super(pBaseHeight, pHeightRandA, pHeightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.PALM_TRUNK_PLACER.get();
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader pLevel, BiConsumer<BlockPos, BlockState> pBlockSetter, RandomSource pRandom, int pFreeTreeHeight, BlockPos pPos, TreeConfiguration pConfig) {
        List<FoliagePlacer.FoliageAttachment> list = Lists.newArrayList();
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(pRandom);
        int i = pFreeTreeHeight - pRandom.nextInt(4) - 1;
        int j = 3 - pRandom.nextInt(3);
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();
        int k = pPos.getX();
        int l = pPos.getZ();
        OptionalInt optionalint = OptionalInt.empty();

        for(int i1 = 0; i1 < pFreeTreeHeight; ++i1) {
            int j1 = pPos.getY() + i1;
            if (i1 >= i && j > 0) {
                k += direction.getStepX();
                l += direction.getStepZ();
                --j;
            }

            if (this.placeLog(pLevel, pBlockSetter, pRandom, blockpos$mutableblockpos.set(k, j1, l), pConfig)) {
                optionalint = OptionalInt.of(j1 + 1);
            }
        }

        if (optionalint.isPresent()) {
            list.add(new FoliagePlacer.FoliageAttachment(new BlockPos(k, optionalint.getAsInt(), l), 1, false));
        }

        return list;
    }
}