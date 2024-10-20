//        //Coconut
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ModItems.COCONUT_MILK.get())
//                .requires(ModItems.COCONUT.get())
//                .unlockedBy(getHasName(ModItems.COCONUT.get()), has(ModItems.COCONUT.get()))
//                .unlockedBy(getHasName(ModItems.COCONUT_MILK.get()), has(ModItems.COCONUT_MILK.get()))
//                .save(pWriter);
//
//        //Desert Turtle Helmet
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.DESERT_TURTLE_HELMET.get())
//                .pattern("###")
//                .pattern("# #")
//                .define('#', ModItems.DESERT_TURTLE_SCUTE.get())
//                .unlockedBy(getHasName(ModItems.DESERT_TURTLE_SCUTE.get()), has(ModItems.DESERT_TURTLE_SCUTE.get()))
//                .save(pWriter);
//
//
//        //Oasis Clay
//        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.OASIS_CLAY.get())
//                .pattern("##")
//                .pattern("##")
//                .define('#', ModItems.OASIS_CLAY_BALL.get())
//                .unlockedBy(getHasName(ModItems.OASIS_CLAY_BALL.get()), has(ModItems.OASIS_CLAY_BALL.get()))
//                .save(pWriter);
//
//        //Palm Blocks and non-Blocks
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModItems.PALM_CHEST_BOAT.get())
//                .requires(ModItems.PALM_BOAT.get(), 1)
//                .requires(Blocks.CHEST, 1)
//                .unlockedBy(getHasName(ModItems.PALM_BOAT.get()), has(ModItems.PALM_BOAT.get()))
//                .save(pWriter);
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
//                .requires(ModBlocks.PALM_LOG.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG.get()))
//                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_palm_log"));
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
//                .requires(ModBlocks.PALM_WOOD.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_WOOD.get()), has(ModBlocks.PALM_WOOD.get()))
//                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_palm_wood"));
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
//                .requires(ModBlocks.STRIPPED_PALM_LOG.get())
//                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
//                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_stripped_palm_log"));
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PLANKS.get(), 4)
//                .requires(ModBlocks.STRIPPED_PALM_WOOD.get())
//                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_WOOD.get()), has(ModBlocks.STRIPPED_PALM_WOOD.get()))
//                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_planks_from_stripped_palm_wood"));
//
//        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_BUTTON.get())
//                .requires(ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.PALM_BOAT.get())
//                .pattern("# #")
//                .pattern("###")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_DOOR.get(), 3)
//                .pattern("##")
//                .pattern("##")
//                .pattern("##")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_FENCE.get(), 3)
//                .pattern("#|#")
//                .pattern("#|#")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .define('|', Items.STICK)
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_FENCE_GATE.get())
//                .pattern("|#|")
//                .pattern("|#|")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .define('|', Items.STICK)
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PALM_HANGING_SIGN.get())
//                .pattern("| |")
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModBlocks.STRIPPED_PALM_LOG.get())
//                .define('|', Items.CHAIN)
//                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_PRESSURE_PLATE.get())
//                .pattern("##")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.PALM_SIGN.get())
//                .pattern("###")
//                .pattern("###")
//                .pattern(" | ")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .define('|', Items.STICK)
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_SLAB.get(), 6)
//                .pattern("###")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_STAIRS.get(), 4)
//                .pattern("#  ")
//                .pattern("## ")
//                .pattern("###")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter, new ResourceLocation(RealityMod.MOD_ID, "palm_stairs"));
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_TRAPDOOR.get(), 2)
//                .pattern("###")
//                .pattern("###")
//                .define('#', ModBlocks.PALM_PLANKS.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_PLANKS.get()), has(ModBlocks.PALM_PLANKS.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PALM_WOOD.get(), 3)
//                .pattern("##")
//                .pattern("##")
//                .define('#', ModBlocks.PALM_LOG.get())
//                .unlockedBy(getHasName(ModBlocks.PALM_LOG.get()), has(ModBlocks.PALM_LOG.get()))
//                .unlockedBy(getHasName(ModBlocks.PALM_WOOD.get()), has(ModBlocks.PALM_WOOD.get()))
//                .save(pWriter);
//
//        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.STRIPPED_PALM_WOOD.get(), 3)
//                .pattern("##")
//                .pattern("##")
//                .define('#', ModBlocks.STRIPPED_PALM_LOG.get())
//                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_LOG.get()), has(ModBlocks.STRIPPED_PALM_LOG.get()))
//                .unlockedBy(getHasName(ModBlocks.STRIPPED_PALM_WOOD.get()), has(ModBlocks.STRIPPED_PALM_WOOD.get()))
//                .save(pWriter);
//
//    }
