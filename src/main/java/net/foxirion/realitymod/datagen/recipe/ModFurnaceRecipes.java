package net.foxirion.realitymod.datagen.recipe;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModFurnaceRecipes extends ModRecipeProvider {
    public final RecipeOutput recipeOutput;

    public ModFurnaceRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        SimpleCountCookingRecipeBuilder.smelting(Ingredient.of(ModItems.OASIS_CLAY_BALL), //ingredient
                RecipeCategory.BUILDING_BLOCKS, //recipe category
                Items.BRICK, //result item type
                0.2F, //xp
                200, //smelting ticks
                2 //result count
        )
                .unlockedBy("has_oasis_clay_ball", has(ModItems.OASIS_CLAY_BALL)) //unlocked in recipe book
                .save(recipeOutput, path + getSmeltingRecipeName(Items.BRICK));
    }

    public void build() {
        SimpleCountCookingRecipeBuilder.smelting(Ingredient.of(ModBlocks.OASIS_CLAY), //ingredient
                        RecipeCategory.BUILDING_BLOCKS, //recipe category
                        Blocks.TERRACOTTA, //result item type
                        0.2F, //xp
                        200, //smelting ticks
                        2 //result count
                )
                .unlockedBy("has_oasis_clay_ball", has(ModBlocks.OASIS_CLAY)) //unlocked in recipe book
                .save(recipeOutput, path + getSmeltingRecipeName(Blocks.TERRACOTTA));
    }
}
