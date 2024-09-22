package net.foxirion.realitymod.datagen.loot;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.PALM_PLANKS.get());
        this.dropSelf(ModBlocks.PALM_LOG.get());
        this.dropSelf(ModBlocks.PALM_SAPLING.get());
        this.dropSelf(ModBlocks.PALM_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_PALM_WOOD.get());

        this.add(ModBlocks.OASIS_CLAY.get(),
                (block) -> createOasisClayDrops(ModBlocks.OASIS_CLAY.get(), ModItems.OASIS_CLAY_BALL.get()));

        this.add(ModBlocks.PALM_LEAVES.get(),
                (block) -> createPalmLeavesDrops(ModBlocks.PALM_LEAVES.get(), ModBlocks.PALM_SAPLING.get(), 0.05F, 0.0625F, 0.083333336F, 0.1F));

    }

    protected LootTable.Builder createPalmLeavesDrops(Block pPalmLeavesBlock, Block pSaplingBlock, float... pChances) {
        return this.createLeavesDrops(pPalmLeavesBlock, pSaplingBlock, pChances)
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .when(HAS_SILK_TOUCH.invert())
                        .when(HAS_SHEARS.invert())
                        .add(((LootPoolSingletonContainer.Builder)this.applyExplosionCondition(pPalmLeavesBlock,
                                LootItem.lootTableItem(ModItems.COCONUT.get())))
                                .when(BonusLevelTableCondition.bonusLevelFlatChance(Enchantments.BLOCK_FORTUNE,
                                        0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))));

    }
    protected LootTable.Builder createOasisClayDrops(Block block, Item ball) {
        return LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(block)
                                .when(HAS_SILK_TOUCH)
                                .otherwise(LootItem.lootTableItem(ball)
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0F))))));
    }


    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}