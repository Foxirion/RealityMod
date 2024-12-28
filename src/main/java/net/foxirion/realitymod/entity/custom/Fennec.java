package net.foxirion.realitymod.entity.custom;

import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

import static net.minecraft.world.entity.animal.Wolf.PREY_SELECTOR;

public class Fennec extends TamableAnimal {
    public Fennec(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

            //Main

    //Animations
    public final AnimationState earFlapAnimationState = new AnimationState();
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public void tick() {
        super.tick();

        if (this.level().isClientSide){
            setupAnimationStates();
        }
    }

    private void setupAnimationStates(){
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

        // Start sitting animation when the Fennec is in sitting pose
        if (this.isInSittingPose() && !this.sittingAnimationState.isStarted()) {
            this.sittingAnimationState.start(this.tickCount);
        }
        // Stop sitting animation when no longer sitting
        else if (!this.isInSittingPose() && this.sittingAnimationState.isStarted()) {
            this.sittingAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float partialTick) {
        float f;
        if (this.getPose() == Pose.STANDING) {
            f = Math.min(partialTick * 6f, 1f);
        } else {
            f = 0f;
        }
        this.walkAnimation.update(f, 0.2f);
    }

    public boolean isSitting() {
        return this.isInSittingPose();
    }

    @Override
    public void setPose(Pose pose) {
        super.setPose(pose);
        if (pose == Pose.CROUCHING || this.isInSittingPose()) {
            this.refreshDimensions(); // Refresh hitbox when pose changes
        }
    }

    @Override
    public EntityDimensions getDimensions(Pose pose) {
        if (this.isInSittingPose()) {
            // Reduce hitbox height slightly for sitting pose
            return EntityDimensions.scalable(0.6F, 0.6F); // Adjust the height as needed
        }
        return super.getDimensions(pose);
    }



    // Attributes
    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createLivingAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.6F)
                .add(Attributes.MAX_HEALTH, 15.0D)
                .add(Attributes.FOLLOW_RANGE, 32.0D)
                .add(Attributes.ATTACK_DAMAGE, 2.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0D);
    }

    //Goals
    @Override
    public void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(0, new ClimbOnTopOfPowderSnowGoal(this, this.level()));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.150));
        this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));

        // Improved attack goal for both tamed and untamed states
        this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true) {
            @Override
            public boolean canUse() {
                // Allow attack for both tamed and untamed states
                return this.mob.getTarget() != null;
            }
        });
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
        this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(11, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        // Targeting goals
        // When tamed, prioritize owner's targets
        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());

        // Targeting for non-tamed state
        this.targetSelector.addGoal(5, new NonTameRandomTargetGoal<>(this, Animal.class, false, entity ->
                !isTame() && ( // Only target when not tamed
                        entity instanceof Rabbit ||
                                entity instanceof Chicken ||
                                entity instanceof Parrot ||
                                entity instanceof Salmon ||
                                entity instanceof Cod ||
                                entity instanceof TropicalFish ||
                                // Only target baby turtles (both desert and sea)
                                ((entity instanceof Turtle || entity instanceof DesertTurtle) &&
                                        (entity instanceof Turtle ? ((Turtle)entity).isBaby() : ((DesertTurtle)entity).isBaby()))
                ) && !entity.isInWater()
        ));

        // Custom targeting goal for tamed fennec
        this.targetSelector.addGoal(4, new TamedFennecTargetGoal(this));
    }

        //Details

    //AiStep
    public void aiStep() {
        super.aiStep();
        if (this.isDefending() && this.random.nextFloat() < 0.05F) {
            this.playSound(SoundEvents.FOX_AGGRO, 1.0F, 1.0F);
        }
    }

    boolean isDefending() {
        return this.getFlag(128);
    }

    private boolean getFlag(int flagId) {
        return (this.entityData.get(DATA_FLAGS_ID) & flagId) != 0;
    }


    //Food
    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModTags.Items.FENNEC_FOODS);
    }


    //Offspring
    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.FENNEC.get().create(serverLevel);
    }


    //Sounds
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return SoundEvents.FOX_AMBIENT;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return SoundEvents.FOX_DEATH;
    }

    @Override
    public SoundEvent getEatingSound(ItemStack pStack) {
        return SoundEvents.FOX_EAT;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundEvents.FOX_HURT;
    }

    protected float getSoundVolume() {
        return 0.4F;
    }


    //Spawn rules

    public static boolean checkFennecSpawnRules(EntityType<Fennec> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
        // Check if it's a desert biome and on sand
        if (!level.getBiome(pos).is(Biomes.DESERT) || !level.getBlockState(pos.below()).is(Blocks.SAND)) {
            return false;
        }

        // Check if it's nighttime (similar to sea turtles)
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
        if (random.nextFloat() > 0.01f) {  // 0.1% chance of spawning (decreased from 30%)
            return false;
        }

        // Check other animal spawn rules
        return Fennec.checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
    }


    //Taming
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            Entity entity = source.getEntity();
            if (!this.level().isClientSide) {
                this.setOrderedToSit(false);
            }

            if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
                amount = (amount + 1.0F) / 2.0F;
            }

            return super.hurt(source, amount);
        }
    }

    public void setTame(boolean tamed) {
        super.setTame(tamed);
        if (tamed) {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
            this.setHealth(20.0F);
        } else {
            this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(8.0D);
        }

        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(4.0D);
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            boolean flag = this.isOwnedBy(player) || this.isTame() || itemstack.is(Items.RABBIT_FOOT) && !this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else if (this.isTame()) {
            if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
                this.heal((float)itemstack.getFoodProperties(this).getNutrition());
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }

                this.gameEvent(GameEvent.EAT, this);
                return InteractionResult.SUCCESS;
            } else {
                InteractionResult interactionresult = super.mobInteract(player, hand);
                if ((!interactionresult.consumesAction() || this.isBaby()) && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    return InteractionResult.SUCCESS;
                } else {
                    return interactionresult;
                }
            }
        } else if (itemstack.is(Items.RABBIT_FOOT)) {
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }

            if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
                this.tame(player);
                this.navigation.stop();
                this.setTarget((LivingEntity)null);
                this.setOrderedToSit(true);
                this.level().broadcastEntityEvent(this, (byte)7);
            } else {
                this.level().broadcastEntityEvent(this, (byte)6);
            }

            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    //z_Goals
    // Improved custom target goal for tamed fennec
    private class TamedFennecTargetGoal extends TargetGoal {
        private final Fennec fennec;
        private LivingEntity target;

        public TamedFennecTargetGoal(Fennec fennec) {
            super(fennec, true);
            this.fennec = fennec;
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        }

        @Override
        public boolean canUse() {
            if (!fennec.isTame() || fennec.isOrderedToSit()) {
                return false;
            }

            LivingEntity owner = fennec.getOwner();
            if (owner == null) {
                return false;
            }

            // Check if the owner's last target or attacker is valid
            LivingEntity ownerTarget = owner.getLastHurtMob();
            LivingEntity attacker = owner.getLastHurtByMob();

            if (ownerTarget != null && ownerTarget != fennec && fennec.canAttack(ownerTarget)) {
                this.target = ownerTarget;
                return true;
            }

            if (attacker != null && attacker != fennec && fennec.canAttack(attacker)) {
                this.target = attacker;
                return true;
            }

            return false;
        }

        @Override
        public void start() {
            this.fennec.setTarget(this.target);
            super.start();
        }
    }

    // Modify doHurtTarget to ensure damage is dealt
    @Override
    public boolean doHurtTarget(Entity target) {
        if (target instanceof LivingEntity livingTarget) {
            // Calculate damage based on the ATTACK_DAMAGE attribute
            float damage = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
            boolean successfulHit = target.hurt(this.damageSources().mobAttack(this), damage);

            if (successfulHit) {
                // Apply enchantment effects like fire aspect, etc.
                this.doEnchantDamageEffects(this, target);

                // Apply knockback effects if the attribute is set
                double knockback = this.getAttributeValue(Attributes.ATTACK_KNOCKBACK);
                if (knockback > 0) {
                    livingTarget.knockback(
                            knockback * 0.5,
                            Mth.sin(this.getYRot() * ((float) Math.PI / 180F)),
                            -Mth.cos(this.getYRot() * ((float) Math.PI / 180F))
                    );
                }
            }

            return successfulHit;
        }

        return false;
    }

}
