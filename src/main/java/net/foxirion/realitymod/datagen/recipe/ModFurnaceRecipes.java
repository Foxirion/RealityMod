package net.foxirion.realitymod.datagen.recipe;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModFurnaceRecipes extends ModRecipeProvider {
    public final RecipeOutput recipeOutput;

    public ModFurnaceRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        oreSmelting(recipeOutput, ModItems.OASIS_CLAY_BALL, RecipeCategory.BUILDING_BLOCKS, Items.BRICK, 2, 0.2F, 200);
        oreSmelting(recipeOutput, ModBlocks.OASIS_CLAY, RecipeCategory.BUILDING_BLOCKS, Items.TERRACOTTA, 2, 0.2F, 200);
    }
}
