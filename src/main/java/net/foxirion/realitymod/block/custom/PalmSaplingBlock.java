package net.foxirion.realitymod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;

public class PalmSaplingBlock extends SaplingBlock {
    public PalmSaplingBlock(AbstractTreeGrower pTreeGrower, Properties pProperties) {
        super(pTreeGrower, pProperties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        Block blockBelow = worldIn.getBlockState(pos.below()).getBlock();
        // Allow placement on Sand and Dirt types of blocks
        return blockBelow == Blocks.SAND || blockBelow == Blocks.DIRT ||
                blockBelow == Blocks.GRASS_BLOCK || blockBelow == Blocks.COARSE_DIRT ||
                blockBelow == Blocks.PODZOL || blockBelow == Blocks.FARMLAND;
    }
}
