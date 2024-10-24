package net.foxirion.realitymod.item.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.nbt.CompoundTag;

public class DesertTurtleHelmet extends ArmorItem {
    public static final int EFFECT_DURATION = 100; // 5 seconds
    private static final String COOLDOWN_TAG = "TurtleHelmetCooldown";

    public DesertTurtleHelmet(ArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);
        if (!level.isClientSide() && entity instanceof Player player) {
            int index = 0;
            for (ItemStack armorStack : player.getArmorSlots()) {
                if (armorStack.getItem() instanceof DesertTurtleHelmet) {
                    if (index == 3) { // Helmet slot
                        CompoundTag tag = armorStack.getOrCreateTag();
                        int cooldownTicks = tag.getInt(COOLDOWN_TAG);

                        if (cooldownTicks <= 0) {
                            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 0, false, false, true));
                        } else {
                            tag.putInt(COOLDOWN_TAG, cooldownTicks - 1);
                        }
                    }
                }
                index++;
            }
        }
    }

    public static void setCooldown(ItemStack stack, int ticks) {
        CompoundTag tag = stack.getOrCreateTag();
        tag.putInt(COOLDOWN_TAG, ticks);
    }
}