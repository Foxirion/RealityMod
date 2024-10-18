package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealityMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_BUILDING_BLOCKS = CREATIVE_MODE_TABS.register("rm_building_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PALM_PLANKS.get()))
                    .title(Component.translatable("creativetab.rm_building_blocks"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .withTabsAfter(ModCreativeModeTabs.RM_NATURAL_BLOCKS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.OASIS_CLAY);
                        pOutput.accept(ModBlocks.PALM_LOG);
                        pOutput.accept(ModBlocks.PALM_WOOD);
                        pOutput.accept(ModBlocks.STRIPPED_PALM_LOG);
                        pOutput.accept(ModBlocks.STRIPPED_PALM_WOOD);
                        pOutput.accept(ModBlocks.PALM_PLANKS);
                        pOutput.accept(ModBlocks.PALM_BUTTON);
                        pOutput.accept(ModBlocks.PALM_DOOR);
                        pOutput.accept(ModBlocks.PALM_TRAPDOOR);
                        pOutput.accept(ModBlocks.PALM_PRESSURE_PLATE);
                        pOutput.accept(ModBlocks.PALM_SLAB);
                        pOutput.accept(ModBlocks.PALM_STAIRS);
                        pOutput.accept(ModBlocks.PALM_FENCE);
                        pOutput.accept(ModBlocks.PALM_FENCE_GATE);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_NATURAL_BLOCKS = CREATIVE_MODE_TABS.register("rm_natural_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.PALM_LOG))
                    .title(Component.translatable("creativetab.rm_natural_blocks"))
                    .withTabsBefore(ModCreativeModeTabs.RM_BUILDING_BLOCKS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.OASIS_CLAY);
                        pOutput.accept(ModBlocks.PALM_LOG);
                        pOutput.accept(ModBlocks.PALM_LEAVES);
                        pOutput.accept(ModBlocks.PALM_SAPLING);
                        pOutput.accept(ModBlocks.FOSSIL);
                        pOutput.accept(ModBlocks.DEEPSLATE_FOSSIL);
                        pOutput.accept(ModBlocks.FROZEN_FOSSIL);
                        pOutput.accept(ModBlocks.NETHER_FOSSIL);
                        pOutput.accept(ModBlocks.DESERT_TURTLE_EGG);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_FUNCTIONAL_BLOCKS = CREATIVE_MODE_TABS.register("rm_functional_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PALM_SIGN.get()))
                    .title(Component.translatable("creativetab.rm_functional_blocks"))
                    .withTabsBefore(ModCreativeModeTabs.RM_NATURAL_BLOCKS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PALM_SIGN);
                        pOutput.accept(ModItems.PALM_HANGING_SIGN);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_TOOLS_AND_UTILITIES = CREATIVE_MODE_TABS.register("rm_tools_and_utilities",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PALM_BOAT.get()))
                    .title(Component.translatable("creativetab.rm_tools_and_utilities"))
                    .withTabsBefore(ModCreativeModeTabs.RM_FUNCTIONAL_BLOCKS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.PALM_BOAT);
                        pOutput.accept(ModItems.PALM_CHEST_BOAT);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_COMBAT = CREATIVE_MODE_TABS.register("rm_combat",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DESERT_TURTLE_HELMET.get()))
                    .title(Component.translatable("creativetab.rm_combat"))
                    .withTabsBefore(ModCreativeModeTabs.RM_TOOLS_AND_UTILITIES.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DESERT_TURTLE_HELMET);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_FOOD_AND_DRINKS = CREATIVE_MODE_TABS.register("rm_food_and_drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COCONUT.get()))
                    .title(Component.translatable("creativetab.rm_food_and_drinks"))
                    .withTabsBefore(ModCreativeModeTabs.RM_COMBAT.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.COCONUT);
                        pOutput.accept(ModItems.COCONUT_MILK);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_INGREDIENTS = CREATIVE_MODE_TABS.register("rm_ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DESERT_TURTLE_SCUTE.get()))
                    .title(Component.translatable("creativetab.rm_ingredients"))
                    .withTabsBefore(ModCreativeModeTabs.RM_FOOD_AND_DRINKS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DESERT_TURTLE_SCUTE);
                        pOutput.accept(ModItems.OASIS_CLAY_BALL);
                    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RM_SPAWN_EGGS = CREATIVE_MODE_TABS.register("rm_spawn_eggs",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DESERT_TURTLE_SPAWN_EGG.get()))
                    .title(Component.translatable("creativetab.rm_spawn_eggs"))
                    .withTabsBefore(ModCreativeModeTabs.RM_INGREDIENTS.getKey())
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DESERT_TURTLE_SPAWN_EGG);
                    }).build());
}
