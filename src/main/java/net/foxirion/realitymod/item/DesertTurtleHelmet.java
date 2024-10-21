package net.foxirion.realitymod.item;

import net.foxirion.realitymod.init.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class DesertTurtleHelmet extends ArmorItem {
    public static final int EFFECT_DURATION = 100; // 5 seconds

    public DesertTurtleHelmet(Holder<ArmorMaterial> material, Type type, Properties properties) {
        super(material, type, properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        super.inventoryTick(stack, level, entity, slotId, isSelected);

        if (!level.isClientSide() && entity instanceof Player player) {
            int index = 0;

            for (ItemStack armorStack : player.getArmorSlots()) {
                if (armorStack.getItem() instanceof DesertTurtleHelmet) {
                    switch (index) {
                        case 3: // Helmet
                            Integer cooldownTicks = armorStack.get(ModDataComponents.TURTLE_HELMET_COOLDOWN);
                            if (cooldownTicks == null || cooldownTicks <= 0) {
                                player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, EFFECT_DURATION, 0, false, false, true));
                            } else {
                                armorStack.set(ModDataComponents.TURTLE_HELMET_COOLDOWN, cooldownTicks - 1);
                            }
                            break;
                    }
                }
                index++;
            }
        }
    }

}
