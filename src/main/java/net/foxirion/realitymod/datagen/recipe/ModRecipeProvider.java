package net.foxirion.realitymod.datagen.recipe;

import net.foxirion.realitymod.datagen.ModBlockFamilies;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.BlockFamily;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;

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
    @Override
    protected void generateForEnabledBlockFamilies(RecipeOutput recipeOutput, FeatureFlagSet featureFlagSet) {
        ModBlockFamilies.getAllFamilies().filter(BlockFamily::shouldGenerateRecipe).forEach(blockFamily -> generateRecipes(recipeOutput, blockFamily, featureFlagSet));
    }

    public static void oreSmelting(RecipeOutput pRecipeOutput, ItemLike pIngredients, RecipeCategory pCategory, Item pResult, int pResultCount, float pExperience, int pCookingTime) {
        oreCooking(pRecipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, new ItemStack(pResult, pResultCount), pExperience, pCookingTime, "_from_smelting");
    }

    public static void oreBlasting(RecipeOutput pRecipeOutput, ItemLike pIngredients, RecipeCategory pCategory, Item pResult, int pResultCount, float pExperience, int pCookingTime) {
        oreCooking(pRecipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, new ItemStack(pResult, pResultCount), pExperience, pCookingTime, "_from_blasting");
    }

    public static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput pRecipeOutput, RecipeSerializer<T> pSerializer, AbstractCookingRecipe.Factory<T> pRecipeFactory, ItemLike itemlike, RecipeCategory pCategory, ItemStack pResultStack, float pExperience, int pCookingTime, String pSuffix) {
        SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResultStack, pExperience, pCookingTime, pSerializer, pRecipeFactory)
                .unlockedBy(getHasName(itemlike), has(itemlike))
                .save(pRecipeOutput, path + getItemName(pResultStack.getItem()) + pSuffix + "_" + getItemName(itemlike));
    }
}
