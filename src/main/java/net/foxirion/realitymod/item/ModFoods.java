package net.foxirion.realitymod.item;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Items;

public class ModFoods extends Foods {
    public static final FoodProperties APPLE_PIE = new FoodProperties.Builder()
            .nutrition(8)
            .saturationModifier(0.3F)
            .build();

    public static final FoodProperties BOWL_OF_WATER = new FoodProperties.Builder()
            .nutrition(0)
            .saturationModifier(0F)
            .alwaysEdible()
            .usingConvertsTo(Items.BOWL)
            .build();

    public static final FoodProperties COCONUT_MILK = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.3F)
            .usingConvertsTo(ModItems.COCONUT_SHELL)
            .build();

}
