package net.foxirion.realitymod.datagen;

import com.google.gson.JsonObject;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
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

                //Smelting

        // Oasis Clay & Ball
        customSmelting(pWriter, ModBlocks.OASIS_CLAY.get(), Items.TERRACOTTA, 2, 0.7f, 200, "oasis_clay_to_terracotta");
        customSmelting(pWriter, ModItems.OASIS_CLAY_BALL.get(), Items.BRICK, 2, 0.6f, 200, "oasis_clay_ball_to_brick");


            //Crafting

        //Coconut
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COCONUT_MILK.get())
                .requires(ModItems.COCONUT.get())
                .unlockedBy(getHasName(ModItems.COCONUT.get()), has(ModItems.COCONUT.get()))
                .unlockedBy(getHasName(ModItems.COCONUT_MILK.get()), has(ModItems.COCONUT_MILK.get()))
                .save(pWriter);

        //Desert Turtle Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DESERT_TURTLE_HELMET.get())
                .pattern("###")
                .pattern("# #")
                .define('#', ModItems.DESERT_TURTLE_SCUTE.get())
                .unlockedBy(getHasName(ModItems.DESERT_TURTLE_SCUTE.get()), has(ModItems.DESERT_TURTLE_SCUTE.get()))
                .save(pWriter);


        //Oasis Clay
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OASIS_CLAY.get())
                .pattern("##")
                .pattern("##")
                .define('#', ModItems.OASIS_CLAY_BALL.get())
                .unlockedBy(getHasName(ModItems.OASIS_CLAY_BALL.get()), has(ModItems.OASIS_CLAY_BALL.get()))
                .save(pWriter);

        //Palm Blocks and non-Blocks

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.PALM_CHEST_BOAT.get())
                .requires(ModItems.PALM_BOAT.get(), 1)
                .requires(Blocks.CHEST, 1)
                .unlockedBy(getHasName(ModItems.PALM_BOAT.get()), has(ModItems.PALM_BOAT.get()))
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_BUTTON.get())
                .requires(ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PALM_BOAT.get())
                .pattern("# #")
                .pattern("###")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_DOOR.get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_FENCE.get(), 3)
                .pattern("#|#")
                .pattern("#|#")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .define('|', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_FENCE_GATE.get())
                .pattern("|#|")
                .pattern("|#|")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .define('|', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PALM_HANGING_SIGN.get())
                .pattern("| |")
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.STRIPPED_PALM_LOG.get())
                .define('|', Items.CHAIN)
                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PRESSURE_PLATE.get())
                .pattern("##")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PALM_SIGN.get())
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .define('|', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_SLAB.get(), 6)
                .pattern("###")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_stairs"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_TRAPDOOR.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.PALM_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
                .save(pWriter);

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

    //Methods
    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, int pResultCount) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting", pResultCount);
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, int pResultCount) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void Smelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, int pResultCount) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_smelting", pResultCount);
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName, int pResultCount) {
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

    //Custom Smelting Method (made at 1AM)
    protected void customSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, ItemLike pInput, ItemLike pResult, int pResultCount, float pExperience, int pCookingTime, String pGroup) {
        pFinishedRecipeConsumer.accept(new CustomSmeltingRecipe(
                new ResourceLocation(RealityMod.MOD_ID, pGroup),
                Ingredient.of(pInput),
                pResult,
                pResultCount,
                pExperience,
                pCookingTime
        ));
    }

    private record CustomSmeltingRecipe(
            ResourceLocation id,
            Ingredient ingredient,
            ItemLike result,
            int resultCount,
            float experience,
            int cookingTime
    ) implements FinishedRecipe {

        @Override
        public void serializeRecipeData(JsonObject json) {
            json.addProperty("type", "minecraft:smelting");
            json.addProperty("category", "blocks");
            json.addProperty("cookingtime", this.cookingTime);
            json.addProperty("experience", this.experience);
            json.add("ingredient", this.ingredient.toJson());

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("item", this.result.toString());
            resultJson.addProperty("count", this.resultCount);
            json.add("result", resultJson);
        }

        @Override
        public ResourceLocation getId() {
            return this.id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return RecipeSerializer.SMELTING_RECIPE;
        }

        @Override
        public JsonObject serializeAdvancement() {
            return null;
        }

        @Override
        public ResourceLocation getAdvancementId() {
            return null;
        }
    }
}
