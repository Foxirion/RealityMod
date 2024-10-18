package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ModLangProvider extends LanguageProvider {

    public ModLangProvider(PackOutput output) {
        super(output, RealityMod.MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Block translations
        add(ModBlocks.DEEPSLATE_FOSSIL.get(), "Deepslate Fossil");
        add(ModBlocks.DESERT_TURTLE_EGG.get(), "Desert Turtle Egg");
        add(ModBlocks.FOSSIL.get(), "Fossil");
        add(ModBlocks.FROZEN_FOSSIL.get(), "Frozen Fossil");
        add(ModBlocks.NETHER_FOSSIL.get(), "Nether Fossil");
        add(ModBlocks.OASIS_CLAY.get(), "Oasis Clay");
        add(ModBlocks.PALM_BUTTON.get(), "Palm Button");
        add(ModBlocks.PALM_DOOR.get(), "Palm Door");
        add(ModBlocks.PALM_FENCE.get(), "Palm Fence");
        add(ModBlocks.PALM_FENCE_GATE.get(), "Palm Fence Gate");
        add(ModBlocks.PALM_HANGING_SIGN.get(), "Palm Hanging Sign");
        add(ModBlocks.PALM_LEAVES.get(), "Palm Leaves");
        add(ModBlocks.PALM_LOG.get(), "Palm Log");
        add(ModBlocks.PALM_PLANKS.get(), "Palm Planks");
        add(ModBlocks.PALM_PRESSURE_PLATE.get(), "Palm Pressure Plate");
        add(ModBlocks.PALM_SAPLING.get(), "Palm Sapling");
        add(ModBlocks.PALM_SLAB.get(), "Palm Slab");
        add(ModBlocks.PALM_SIGN.get(), "Palm Sign");
        add(ModBlocks.PALM_STAIRS.get(), "Palm Stairs");
        add(ModBlocks.PALM_TRAPDOOR.get(), "Palm Trapdoor");
        add(ModBlocks.PALM_WOOD.get(), "Palm Wood");
        add(ModBlocks.STRIPPED_PALM_LOG.get(), "Stripped Palm Log");
        add(ModBlocks.STRIPPED_PALM_WOOD.get(), "Stripped Palm Wood");
        add(Blocks.TURTLE_EGG, "Sea Turtle Egg");

        // Item translations
        add(ModItems.COCONUT.get(), "Coconut");
        add(ModItems.COCONUT_MILK.get(), "Coconut Milk");
        add(ModItems.DESERT_TURTLE_HELMET.get(), "Desert Turtle Shell");
        add(ModItems.DESERT_TURTLE_SCUTE.get(), "Desert Turtle Scute");
        add(ModItems.DESERT_TURTLE_SPAWN_EGG.get(), "Desert Turtle Spawn Egg");
        add(ModItems.OASIS_CLAY_BALL.get(), "Oasis Clay Ball");
        add(ModItems.PALM_BOAT.get(), "Palm Boat");
        add(ModItems.PALM_CHEST_BOAT.get(), "Palm Boat with Chest");
        add(Items.SCUTE, "Sea Turtle Scute");
        add(Items.TURTLE_HELMET, "Sea Turtle Shell");
        add(Items.TURTLE_SPAWN_EGG, "Sea Turtle Spawn Egg");

        // Entity translations
        add(ModEntities.MOD_CHEST_BOAT.get(), "Boat with Chest");

        // Creative Tabs
        add("creativetab.rm_building_blocks", "RealityMod Building Blocks");
        add("creativetab.rm_natural_blocks", "RealityMod Natural Blocks");
        add("creativetab.rm_functional_blocks", "RealityMod Functional Blocks");
        add("creativetab.rm_tools_and_utilities", "RealityMod Tools & Utilities");
        add("creativetab.rm_combat", "RealityMod Combat");
        add("creativetab.rm_food_and_drinks", "RealityMod Food & Drinks");
        add("creativetab.rm_ingredients", "RealityMod Ingredients");
        add("creativetab.rm_spawn_eggs", "RealityMod Spawn Eggs");
    }
}