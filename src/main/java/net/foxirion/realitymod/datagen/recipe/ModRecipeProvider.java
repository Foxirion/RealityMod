package net.foxirion.realitymod.datagen.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

import static net.foxirion.realitymod.RealityMod.MODID;

public class ModRecipeProvider extends RecipeProvider {
    public final PackOutput output;
    public final CompletableFuture<HolderLookup.Provider> lookupProvider;
    public static String path = MODID + ":";

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
        this.output = output;
        this.lookupProvider = lookupProvider;
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        new ModCraftingRecipes(output, lookupProvider, recipeOutput).build();
        new ModFurnaceRecipes(output, lookupProvider, recipeOutput).build();
    }

    // Recipes
    protected static void simpleCountCookingRecipeBuilder

}
