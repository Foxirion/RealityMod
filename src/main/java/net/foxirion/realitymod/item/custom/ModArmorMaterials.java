package net.foxirion.realitymod.item.custom;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS = DeferredRegister.create(Registries.ARMOR_MATERIAL, RealityMod.MODID);

    public static Holder<ArmorMaterial> DESERT_TURTLE = ARMOR_MATERIALS.register("desert_turtle", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 3);
                map.put(ArmorItem.Type.LEGGINGS, 6);
                map.put(ArmorItem.Type.CHESTPLATE, 8);
                map.put(ArmorItem.Type.HELMET, 3);
                map.put(ArmorItem.Type.BODY, 11);
            }),9, SoundEvents.ARMOR_EQUIP_TURTLE, () -> Ingredient.of(ModItems.DESERT_TURTLE_SCUTE),
            List.of(new ArmorMaterial.Layer(
                    RealityMod.rl("desert_turtle")
            )),0.0F,0.0F
    ));
}
