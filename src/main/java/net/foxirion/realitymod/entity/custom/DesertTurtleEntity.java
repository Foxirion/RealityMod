package net.foxirion.realitymod.entity.custom;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.custom.DesertTurtleEggBlock;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.goal.DesertTurtleLayEggGoal;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DesertTurtleEntity extends Animal {
    public DesertTurtleEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1F);
        } else {
            f = 0;
        }
        this.walkAnimation.update(f, 0.2F);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 0.45D));
        this.goalSelector.addGoal(1, new DesertTurtleLayEggGoal(this, 0.45D));
        this.goalSelector.addGoal(2, new TemptGoal(this, 0.5D, Ingredient.of(Items.CACTUS), false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 0.4D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 40.0D)
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ARMOR_TOUGHNESS, 0.2f)
                .add(Attributes.KNOCKBACK_RESISTANCE, 0.5f);
    }

    //Food
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.CACTUS);
    }

    //Sounds
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.TURTLE_AMBIENT_LAND;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.TURTLE_HURT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.TURTLE_DEATH;
    }

    //Breeding
    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(DesertTurtleEntity.class, EntityDataSerializers.BOOLEAN);
    private static final int BREEDING_COOLDOWN = 6000; // 5 minutes in ticks
    private int breedingCooldown = 0;

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HAS_EGG, false);
    }

    private boolean hasEgg;

    public boolean hasEgg() {
        return this.hasEgg;
    }

    public void setHasEgg(boolean hasEgg) {
        this.hasEgg = hasEgg;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (!this.level().isClientSide && this.isAlive() && this.tickCount % 6000 == 0) {
            this.heal(1.0F);
        } else if (this.breedingCooldown > 0) {
            this.breedingCooldown--;
        }
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (!(otherAnimal instanceof DesertTurtleEntity)) {
            return false;
        } else {
            DesertTurtleEntity otherTurtle = (DesertTurtleEntity) otherAnimal;
            return this.isInLove() && otherTurtle.isInLove() && !this.hasEgg() && !otherTurtle.hasEgg() && this.breedingCooldown == 0 && otherTurtle.breedingCooldown == 0;
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.DESERT_TURTLE.get().create(serverLevel);
    }

    //Drop Dead Bush at Lightning hit (easter egg)
    @Override
    public void thunderHit(ServerLevel pLevel, LightningBolt pLightning) {
        super.thunderHit(pLevel, pLightning);
        this.spawnAtLocation(Items.DEAD_BUSH);
    }

    //Drops when killed
    @Override
    public void die(DamageSource pCause) {
        super.die(pCause);
        if (!this.level().isClientSide()) {
            // Only drop items if the turtle is an adult
            if (!this.isBaby()) {
                int cactusCount = this.random.nextInt(3);
                for (int i = 0; i < cactusCount; i++) {
                    this.spawnAtLocation(Items.CACTUS);
                }
            }

            // Drop XP
            int xpAmount = 1 + this.random.nextInt(3);
            this.spawnExperience(xpAmount);
        }
    }

    // Helper method to spawn experience orbs
    private void spawnExperience(int amount) {
        while (amount > 0) {
            int i = ExperienceOrb.getExperienceValue(amount);
            amount -= i;
            this.level().addFreshEntity(new ExperienceOrb(this.level(), this.getX(), this.getY(), this.getZ(), i));
        }
    }

    //Despawn = false
    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    //Babies growing drops
    @Override
    public void setAge(int age) {
        int oldAge = this.getAge();
        super.setAge(age);
        if (oldAge < 0 && age >= 0 && !this.level().isClientSide()) {
            this.spawnAtLocation(ModItems.DESERT_TURTLE_SCUTE.get());
        }
    }

    //Desert Turtle Spawn rules

    public static boolean checkDesertTurtleSpawnRules(EntityType<DesertTurtleEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        // Check if it's a desert biome and on sand
        if (!level.getBiome(pos).is(Biomes.DESERT) || !level.getBlockState(pos.below()).is(Blocks.SAND)) {
            return false;
        }

        // Check if it's night time (similar to sea turtles)
        long timeOfDay = level.getLevelData().getDayTime() % 24000;
        if (timeOfDay < 13000 || timeOfDay > 23000) {
            return false;
        }

        // Remove the water check to increase spawn chances

        // Check light level (desert turtles prefer lighter areas)
        if (level.getBrightness(LightLayer.SKY, pos) > 8) {
            return true;
        }

        // Increase spawn chance
        if (random.nextFloat() > 0.3f) {  // 30% chance of spawning (increased from 10%)
            return false;
        }

        // Check other animal spawn rules
        return DesertTurtleEntity.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }

    @Override
    public void spawnChildFromBreeding(ServerLevel world, Animal mate) {
        // Ensure we're dealing with another DesertTurtleEntity
        if (!(mate instanceof DesertTurtleEntity)) {
            return;
        }

        DesertTurtleEntity mateDesertTurtle = (DesertTurtleEntity) mate;

        // Randomly choose which turtle will be considered the "egg layer"
        DesertTurtleEntity eggLayer = world.random.nextBoolean() ? this : mateDesertTurtle;

        // Only set hasEgg for the chosen turtle
        eggLayer.setHasEgg(true);

        // Set breeding cooldown for both turtles
        this.breedingCooldown = BREEDING_COOLDOWN;
        mateDesertTurtle.breedingCooldown = BREEDING_COOLDOWN;

        // Reset love for both turtles
        this.resetLove();
        mate.resetLove();

        // Spawn eggs only once for the chosen turtle
        BlockPos eggPos = eggLayer.blockPosition().offset(0, 0, 1);  // Spawn eggs in front of the egg-laying turtle
        int eggCount = world.random.nextInt(4) + 1; // 1, 2, 3, or 4
        if (eggCount > 0) {
            BlockState eggState = ModBlocks.DESERT_TURTLE_EGG.get().defaultBlockState().setValue(DesertTurtleEggBlock.EGGS, eggCount);
            world.setBlock(eggPos, eggState, 3);

            // Create green particles
            for (int i = 0; i < eggCount; i++) {
                double x = eggPos.getX() + 0.5 + world.random.nextDouble() * 0.2 - 0.1;
                double y = eggPos.getY() + 0.5;
                double z = eggPos.getZ() + 0.5 + world.random.nextDouble() * 0.2 - 0.1;
                world.addParticle(ParticleTypes.HAPPY_VILLAGER, x, y, z, 0.0, 0.2, 0.0);
            }
        }

        // The other turtle doesn't spawn eggs
        mateDesertTurtle.setHasEgg(false);
    }
}