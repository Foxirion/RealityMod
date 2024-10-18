package net.foxirion.realitymod.block.custom;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.TurtleEggBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

public class DesertTurtleEggBlock extends TurtleEggBlock {

    public DesertTurtleEggBlock(Properties properties) {
        super(properties);
    }

    // Hatch Eggs
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.shouldUpdateHatchLevel(level) && onSand(level, pos)) {
            int i = state.getValue(HATCH);
            if (i < 2) {
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.setBlock(pos, state.setValue(HATCH, Integer.valueOf(i + 1)), 2);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(state));
            } else {
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.removeBlock(pos, false);
                level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));

                for(int j = 0; j < state.getValue(EGGS); ++j) {
                    level.levelEvent(2001, pos, Block.getId(state));
                    DesertTurtleEntity turtle = ModEntities.DESERT_TURTLE.get().create(level);
                    if (turtle != null) {
                        turtle.setAge(-24000);
                        turtle.moveTo((double)pos.getX() + 0.3D + (double)j * 0.2D, (double)pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
                        level.addFreshEntity(turtle);
                    }
                }
            }
        }
    }

    private boolean shouldUpdateHatchLevel(Level level) {
        float f = level.getTimeOfDay(1.0F);
        return (double)f < 0.69 && (double)f > 0.65 ? true : level.random.nextInt(500) == 0;
    }

    // Trample Eggs
    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (!entity.isSteppingCarefully()) {
            this.destroyEgg(level, state, pos, entity, 100);
        }

        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof Zombie)) {
            this.destroyEgg(level, state, pos, entity, 3);
        }

        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private void destroyEgg(Level level, BlockState state, BlockPos pos, Entity entity, int chance) {
        if (this.canDestroyEgg(level, entity)) {
            if (!level.isClientSide && level.random.nextInt(chance) == 0 && state.is(ModBlocks.DESERT_TURTLE_EGG)) {
                this.decreaseEggs(level, pos, state);
            }
        }
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        int i = state.getValue(EGGS);
        if (i <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(EGGS, Integer.valueOf(i - 1)), 2);
            level.gameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Context.of(state));
            level.levelEvent(2001, pos, Block.getId(state));
        }
    }

    private boolean canDestroyEgg(Level level, Entity entity) {
        if (entity instanceof Turtle || entity instanceof Bat) {
            return false;
        } else {
            return !(entity instanceof LivingEntity) ? false : entity instanceof Player || net.neoforged.neoforge.event.EventHooks.canEntityGrief(level, entity);
        }
    }
}