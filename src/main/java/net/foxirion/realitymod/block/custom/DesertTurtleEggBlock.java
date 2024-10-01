package net.foxirion.realitymod.block.custom;

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
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class DesertTurtleEggBlock extends Block {
    public static final IntegerProperty HATCH = BlockStateProperties.HATCH;
    public static final IntegerProperty EGGS = BlockStateProperties.EGGS;
    private static final VoxelShape ONE_EGG_AABB = Block.box(3.0D, 0.0D, 3.0D, 12.0D, 7.0D, 12.0D);
    private static final VoxelShape MULTI_EGG_AABB = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 7.0D, 15.0D);

    public DesertTurtleEggBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(HATCH, 0).setValue(EGGS, 1));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(HATCH, EGGS);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return state.getValue(EGGS) > 1 ? MULTI_EGG_AABB : ONE_EGG_AABB;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        return blockstate.is(this) ? blockstate.setValue(EGGS, Integer.min(4, blockstate.getValue(EGGS) + 1))
                : super.getStateForPlacement(context);
    }

    @Override
    public boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {
        return useContext.getItemInHand().is(this.asItem()) && state.getValue(EGGS) < 4 || super.canBeReplaced(state, useContext);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.shouldUpdateHatchLevel(level) && onSand(level, pos)) {
            int i = state.getValue(HATCH);
            if (i < 2) {
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.setBlock(pos, state.setValue(HATCH, i + 1), 2);
            } else {
                level.playSound(null, pos, SoundEvents.TURTLE_EGG_HATCH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                level.removeBlock(pos, false);

                for(int j = 0; j < state.getValue(EGGS); ++j) {
                    level.levelEvent(2001, pos, Block.getId(state));
                    DesertTurtleEntity desertTurtle = ModEntities.DESERT_TURTLE.get().create(level);
                    if (desertTurtle != null) {
                        desertTurtle.setAge(-24000);
                        desertTurtle.moveTo((double)pos.getX() + 0.3D + (double)j * 0.2D, pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
                        level.addFreshEntity(desertTurtle);
                    }
                }
            }
        }
    }

    private boolean shouldUpdateHatchLevel(ServerLevel level) {
        float f = level.getTimeOfDay(1.0F);
        if ((double)f < 0.69D && (double)f > 0.65D) {
            return true;
        } else {
            return level.random.nextInt(500) == 0;
        }
    }

    public static boolean onSand(BlockGetter level, BlockPos pos) {
        return level.getBlockState(pos.below()).is(Blocks.SAND);
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        this.tryTrample(level, pos, entity, 100);
        super.stepOn(level, pos, state, entity);
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        if (!(entity instanceof Zombie)) {
            this.tryTrample(level, pos, entity, 3);
        }
        super.fallOn(level, state, pos, entity, fallDistance);
    }

    private boolean canTrample(Level level, Entity entity) {
        if (entity instanceof DesertTurtleEntity || entity instanceof Turtle || entity instanceof Bat) {
            return false;
        } else if (!(entity instanceof LivingEntity)) {
            return false;
        } else {
            return entity instanceof Player || level.getGameRules().getBoolean(net.minecraft.world.level.GameRules.RULE_MOBGRIEFING);
        }
    }

    private void tryTrample(Level level, BlockPos pos, Entity entity, int chance) {
        if (this.canTrample(level, entity)) {
            if (!level.isClientSide && level.random.nextInt(chance) == 0) {
                BlockState blockstate = level.getBlockState(pos);
                if (blockstate.is(this)) {
                    this.decreaseEggs(level, pos, blockstate);
                }
            }
        }
    }

    private void decreaseEggs(Level level, BlockPos pos, BlockState state) {
        level.playSound(null, pos, SoundEvents.TURTLE_EGG_BREAK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
        int i = state.getValue(EGGS);
        if (i <= 1) {
            level.destroyBlock(pos, false);
        } else {
            level.setBlock(pos, state.setValue(EGGS, i - 1), 2);
            level.levelEvent(2001, pos, Block.getId(state));
        }
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (onSand(level, pos) && !level.isClientSide) {
            level.levelEvent(2005, pos, 0);
        }
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return true;
    }
}