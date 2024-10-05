package net.foxirion.realitymod.worldgen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

import java.util.List;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_FOSSIL_KEY = registerKey("overworld_fossil");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_FOSSIL_KEY = registerKey("nether_fossil");

    public static void boostrap(BootstapContext<ConfiguredFeature<?, ?>> context) {
        RuleTest stoneReplaceables = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceables = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplacables = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest iceReplaceables = new BlockMatchTest(Blocks.PACKED_ICE);

        List<OreConfiguration.TargetBlockState> overworldFossils = List.of(
                OreConfiguration.target(stoneReplaceables, ModBlocks.FOSSIL.get().defaultBlockState()),
                OreConfiguration.target(deepslateReplaceables, ModBlocks.DEEPSLATE_FOSSIL.get().defaultBlockState()),
                OreConfiguration.target(iceReplaceables, ModBlocks.FROZEN_FOSSIL.get().defaultBlockState()));

        register(context, OVERWORLD_FOSSIL_KEY, Feature.ORE, new OreConfiguration(overworldFossils, 4));
        register(context, NETHER_FOSSIL_KEY, Feature.ORE, new OreConfiguration(netherrackReplacables,
                ModBlocks.NETHER_FOSSIL.get().defaultBlockState(), 4));
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(RealityMod.MOD_ID, name));
    }
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context,
                                                                                          ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}