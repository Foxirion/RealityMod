package net.foxirion.realitymod.entity.goal;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class DesertTurtleLayEggGoal extends MoveToBlockGoal {
    private final DesertTurtleEntity desertTurtle;

    public DesertTurtleLayEggGoal(DesertTurtleEntity turtle, double speedModifier) {
        super(turtle, speedModifier, 16);
        this.desertTurtle = turtle;
    }

    @Override
    public boolean canUse() {
        return this.desertTurtle.hasEgg() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return super.canContinueToUse() && this.desertTurtle.hasEgg();
    }

    @Override
    protected boolean isValidTarget(LevelReader levelReader, BlockPos pos) {
        return levelReader.getBlockState(pos).is(Blocks.SAND) && levelReader.getBlockState(pos.above()).isAir();
    }

    public void tick() {
        super.tick();
        BlockPos blockpos = this.desertTurtle.blockPosition();
        if (!this.desertTurtle.isInWater() && this.isReachedTarget()) {
            if (this.desertTurtle.layEggCounter < 1) {
                this.desertTurtle.setLayingEgg(true);
            } else if (this.desertTurtle.layEggCounter > this.adjustedTickDelay(200)) {
                Level level = this.desertTurtle.level();
                level.playSound((Player)null, blockpos, SoundEvents.TURTLE_LAY_EGG, SoundSource.BLOCKS, 0.3F, 0.9F + level.random.nextFloat() * 0.2F);
                BlockPos blockpos1 = this.blockPos.above();
                BlockState blockstate = ModBlocks.DESERT_TURTLE_EGG.get().defaultBlockState().setValue(TurtleEggBlock.EGGS, Integer.valueOf(this.desertTurtle.getRandom().nextInt(4) + 1));
                level.setBlock(blockpos1, blockstate, 3);
                level.gameEvent(GameEvent.BLOCK_PLACE, blockpos1, GameEvent.Context.of(this.desertTurtle, blockstate));
                this.desertTurtle.setHasEgg(false);
                this.desertTurtle.setLayingEgg(false);
                this.desertTurtle.setInLoveTime(600);
            }

            if (this.desertTurtle.isLayingEgg()) {
                ++this.desertTurtle.layEggCounter;
            }
        }

    }
}