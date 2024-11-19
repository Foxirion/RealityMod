package net.foxirion.realitymod.item.custom;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import static net.foxirion.realitymod.item.ModItems.COCONUT_SHELL;

public class CoconutMilkItem extends Item {
    public CoconutMilkItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player) {
            if (!((Player)entity).getAbilities().instabuild) {
                if (stack.isEmpty()) {
                    return new ItemStack(COCONUT_SHELL.get());
                }

                Player player = (Player)entity;
                if (!player.getInventory().add(new ItemStack(COCONUT_SHELL.get()))) {
                    player.drop(new ItemStack(COCONUT_SHELL.get()), false);
                }
            }
        }

        return itemstack;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.DRINK;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }
}
