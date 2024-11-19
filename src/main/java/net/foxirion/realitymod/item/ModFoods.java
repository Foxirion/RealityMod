package net.foxirion.realitymod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;

public class ModFoods extends Foods {
    public static final FoodProperties COCONUT_MILK = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(0.15F)
            .usingConvertsTo(ModItems.COCONUT_SHELL)
            .build();

}
