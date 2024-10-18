package net.foxirion.realitymod.datagen.loot;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.function.BiConsumer;

public record ModChestLoot(HolderLookup.Provider registries) implements LootTableSubProvider {

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        output.accept(ModTags.ChestLoot.OASIS, this.oasisSiteLootTable());
    }

    public LootTable.Builder oasisSiteLootTable() {
        return LootTable.lootTable()
                // Pool 1
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(4))
                        .add(LootItem.lootTableItem(Items.PAPER)
                                .setWeight(7).setQuality(2))
                        .add(LootItem.lootTableItem(Items.MAP)
                                .setWeight(1).setQuality(1))
                )
                // Pool 2
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(3))
                        .add(LootItem.lootTableItem(ModItems.COCONUT.get())
                                .setWeight(3).setQuality(2))
                        .add(LootItem.lootTableItem(ModBlocks.PALM_SAPLING.get())
                                .setWeight(1).setQuality(1))
                )
                // Pool 3
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(7))
                        .add(LootItem.lootTableItem(Items.IRON_INGOT)
                                .setWeight(2).setQuality(2))
                        .add(LootItem.lootTableItem(Items.GOLD_INGOT)
                                .setWeight(2).setQuality(2))
                        .add(LootItem.lootTableItem(Items.EMERALD)
                                .setWeight(1).setQuality(2))
                        .add(LootItem.lootTableItem(Items.DIAMOND)
                                .setWeight(1).setQuality(1))
                )

                // Pool 4
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(3))
                        .add(LootItem.lootTableItem(ModItems.OASIS_CLAY_BALL.get())
                                .setWeight(3).setQuality(2))
                        .add(LootItem.lootTableItem(ModBlocks.OASIS_CLAY.get())
                                .setWeight(1).setQuality(1))
                )

                // Pool 5
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(5))
                        .add(LootItem.lootTableItem(Items.ROTTEN_FLESH)
                                .setWeight(1).setQuality(3))
                        .add(LootItem.lootTableItem(Items.BONE)
                                .setWeight(1).setQuality(2))
                );

    }
}
