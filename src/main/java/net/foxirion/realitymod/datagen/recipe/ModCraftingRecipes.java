package net.foxirion.realitymod.datagen.recipe;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModCraftingRecipes extends ModRecipeProvider {
    public final RecipeOutput recipeOutput;

    public ModCraftingRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, RecipeOutput recipeOutput) {
        super(output, lookupProvider);
        this.recipeOutput = recipeOutput;
    }

    public void build() {
        generateForEnabledBlockFamilies(recipeOutput, FeatureFlagSet.of(FeatureFlags.VANILLA));
        planksFromLog(recipeOutput, ModBlocks.PALM_PLANKS, ModTags.Items.PALM_LOGS, 4);
        woodFromLogs(recipeOutput, ModBlocks.PALM_WOOD, ModBlocks.PALM_LOG);
        woodFromLogs(recipeOutput, ModBlocks.STRIPPED_PALM_WOOD, ModBlocks.STRIPPED_PALM_LOG);
        woodenBoat(recipeOutput, ModItems.PALM_BOAT, ModBlocks.PALM_PLANKS);
        chestBoat(recipeOutput, ModItems.PALM_CHEST_BOAT, ModBlocks.PALM_PLANKS);
        hangingSign(recipeOutput, ModItems.PALM_HANGING_SIGN, ModBlocks.STRIPPED_PALM_LOG);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COCONUT_MILK)
                .requires(ModItems.COCONUT)
                .unlockedBy(getHasName(ModItems.COCONUT), has(ModItems.COCONUT))
                .unlockedBy(getHasName(ModItems.COCONUT_MILK), has(ModItems.COCONUT_MILK))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DESERT_TURTLE_HELMET)
                .define('#', ModItems.DESERT_TURTLE_SCUTE)
                .pattern("###")
                .pattern("# #")
                .unlockedBy(getHasName(ModItems.DESERT_TURTLE_SCUTE), has(ModItems.DESERT_TURTLE_SCUTE))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OASIS_CLAY)
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.OASIS_CLAY_BALL)
                .unlockedBy(getHasName(ModItems.OASIS_CLAY_BALL), has(ModItems.OASIS_CLAY_BALL))
                .save(recipeOutput);

    }
}
