package net.foxirion.realitymod.datagen.loot;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables(HolderLookup.Provider pProvider) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pProvider);
    }

    @Override
    protected void generate() {
        //dropSelf
        dropSelf(ModBlocks.PALM_BUTTON.get());
        dropSelf(ModBlocks.PALM_FENCE.get());
        dropSelf(ModBlocks.PALM_FENCE_GATE.get());
        dropSelf(ModBlocks.PALM_PLANKS.get());
        dropSelf(ModBlocks.PALM_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PALM_LOG.get());
        dropSelf(ModBlocks.PALM_STAIRS.get());
        dropSelf(ModBlocks.PALM_SAPLING.get());
        dropPottedContents(ModBlocks.POTTED_PALM_SAPLING.get());
        dropSelf(ModBlocks.PALM_TRAPDOOR.get());
        dropSelf(ModBlocks.PALM_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
        dropSelf(ModBlocks.STRIPPED_PALM_WOOD.get());
        dropSelf(ModBlocks.PALM_SIGN.get());
        dropSelf(ModBlocks.PALM_WALL_SIGN.get());
        dropSelf(ModBlocks.PALM_HANGING_SIGN.get());
        dropSelf(ModBlocks.PALM_WALL_HANGING_SIGN.get());

        //Complex drops

        //Fossils
        createFossilDrops(ModBlocks.FOSSIL.get(), Items.BONE_MEAL, Items.BONE, Items.BONE_BLOCK, Items.SKELETON_SKULL);
        createFossilDrops(ModBlocks.DEEPSLATE_FOSSIL.get(), Items.BONE_MEAL, Items.BONE, Items.BONE_BLOCK, Items.SKELETON_SKULL);
        createFossilDrops(ModBlocks.FROZEN_FOSSIL.get(), Items.BONE_MEAL, Items.BONE, Items.BONE_BLOCK, Items.SKELETON_SKULL);
        createFossilDrops(ModBlocks.NETHER_FOSSIL.get(), Items.BONE, Items.COAL, Items.BONE_BLOCK, Items.WITHER_SKELETON_SKULL);

        dropWhenSilkTouch(ModBlocks.DESERT_TURTLE_EGG.get());

        add(ModBlocks.OASIS_CLAY.get(), block -> this.createSingleItemTableWithSilkTouch(block, ModItems.OASIS_CLAY_BALL.get(), ConstantValue.exactly(4.0F)));
        add(ModBlocks.PALM_DOOR.get(), this::createDoorTable);
        add(ModBlocks.PALM_LEAVES.get(), block -> createOakLeavesDrops(block, ModBlocks.PALM_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        add(ModBlocks.PALM_SLAB.get(), this::createSlabItemTable);
    }

    //Methods
    private void createFossilDrops(Block block, Item commonDrop, Item uncommonDrop, Item rareDrop, Item epicDrop) {
        add(block, LootTable.lootTable()
                .withPool(createSilkTouchPool(block))
                .withPool(createCommonDropPool(commonDrop))
                .withPool(createUncommonDropPool(uncommonDrop))
                .withPool(createRareDropPool(rareDrop))
                .withPool(createEpicDropPool(epicDrop)));
    }

    private LootPool.Builder createSilkTouchPool(Block block) {
        return LootPool.lootPool()
                .when(this.hasSilkTouch()).setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(block));
    }

    private LootPool.Builder createCommonDropPool(ItemLike commonDrop) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return LootPool.lootPool().add(LootItem.lootTableItem(commonDrop)
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))
                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 5))
                        .when(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(
                                List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.FORTUNE), MinMaxBounds.Ints.exactly(3)))))))
                )
        );
    }

    private LootPool.Builder createUncommonDropPool(Item uncommonDrop) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(uncommonDrop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 2)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(LootItemRandomChanceCondition.randomChance(0.75f)) // Replace with standard random chance
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                .when(LootItemRandomChanceCondition.randomChance(0.45f))));
    }

    private LootPool.Builder createRareDropPool(Item rareDrop) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(rareDrop)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(0, 1)))
                        .apply(ApplyBonusCount.addUniformBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                        .when(LootItemRandomChanceCondition.randomChance(0.20f)) // Replace with standard random chance
                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(2))
                                .when(MatchTool.toolMatches(ItemPredicate.Builder.item().withSubPredicate(ItemSubPredicates.ENCHANTMENTS, ItemEnchantmentsPredicate.enchantments(
                                        List.of(new EnchantmentPredicate(registrylookup.getOrThrow(Enchantments.FORTUNE), MinMaxBounds.Ints.exactly(3)))))
                                ).and(LootItemRandomChanceCondition.randomChance(0.15f)))));
    }

    private LootPool.Builder createEpicDropPool(Item epicDrop) {
        return LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(epicDrop).when(LootItemRandomChanceCondition.randomChance(0.004f)));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> Optional.of(BuiltInRegistries.BLOCK.getKey(block))
                        .filter(key -> key.getNamespace().equals(RealityMod.MODID))
                        .isPresent())
                .collect(Collectors.toSet());
    }
}