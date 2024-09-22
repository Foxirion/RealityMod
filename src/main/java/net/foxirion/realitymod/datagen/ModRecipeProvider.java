package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static net.minecraft.world.item.Items.BRICK;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {

        oreSmelting(pWriter, List.of(ModItems.OASIS_CLAY_BALL.get()), RecipeCategory.MISC, BRICK, 0.6f, 200, "oasis_clay_ball_to_brick", 2);

        oreSmelting(pWriter, List.of(ModBlocks.OASIS_CLAY.get()), RecipeCategory.BUILDING_BLOCKS, Blocks.TERRACOTTA, 0.7f, 200, "oasis_clay_to_terracotta", 2);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COCONUT_MILK.get())
                .requires(ModItems.COCONUT.get())
                .unlockedBy(getHasName(ModItems.COCONUT.get()), has(ModItems.COCONUT.get()))
                .unlockedBy(getHasName(ModItems.COCONUT_MILK.get()), has(ModItems.COCONUT_MILK.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OASIS_CLAY.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.OASIS_CLAY_BALL.get())
                .unlockedBy(getHasName(ModItems.OASIS_CLAY_BALL.get()), has(ModItems.OASIS_CLAY_BALL.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
                .requires(ModBlocks.PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG.get()))
                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_palm_log"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
                .requires(ModBlocks.PALM_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.PALM_WOOD.get()), has(ModBlocks.PALM_WOOD.get()))
                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_palm_wood"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_stripped_palm_log"));

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
                .requires(ModBlocks.STRIPPED_PALM_WOOD.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_WOOD.get()), has(ModBlocks.STRIPPED_PALM_WOOD.get()))
                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_stripped_palm_wood"));
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_WOOD.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG.get()))
                .unlockedBy(getHasName(ModBlocks.PALM_WOOD.get()), has(ModBlocks.PALM_WOOD.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PALM_WOOD.get(), 3)
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.STRIPPED_PALM_LOG.get())
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_WOOD.get()), has(ModBlocks.STRIPPED_PALM_WOOD.get()))
                .save(pWriter);


    }
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup, int pResultCount) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, int pResultCount) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            String s1 = (pResult) + pRecipeName + "_" + getItemName(itemlike);
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, RealityMod.MOD_ID + ":"  + pRecipeName + "_" + getItemName(itemlike));
        }

    }

}
