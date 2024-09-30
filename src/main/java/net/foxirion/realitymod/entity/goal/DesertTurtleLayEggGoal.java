package net.foxirion.realitymod.entity.goal;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class DesertTurtleLayEggGoal extends MoveToBlockGoal {
    private final DesertTurtleEntity turtle;

    public DesertTurtleLayEggGoal(DesertTurtleEntity turtle, double speedModifier) {
        super(turtle, speedModifier, 16);
        this.turtle = turtle;
    }

    @Override
    public boolean canUse() {
        return this.turtle.hasEgg() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && this.turtle.hasEgg();
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos pos) {
        return levelReader.getBlockState(pos).is(Blocks.SAND) && levelReader.getBlockState(pos.above()).isAir();
    }

    @Override
    public void tick() {
        super.tick();
        BlockPos blockpos = this.turtle.blockPosition();
        if (this.isReachedTarget()) {
            if (this.turtle.level().getBlockState(blockpos.below()).is(Blocks.SAND)) {
                this.turtle.level().setBlock(blockpos, ModBlocks.DESERT_TURTLE_EGG.get().defaultBlockState(), 3);
                this.turtle.setHasEgg(false);
            }
        }
    }
}