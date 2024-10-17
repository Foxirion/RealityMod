package net.foxirion.realitymod.datagen.loot;

import com.google.common.collect.Sets;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.worldgen.ModStructures;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;

public class ModChestLoot implements LootTableSubProvider {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
    public static final ResourceLocation OASIS = register("chests/oasis");

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> consumer) {
        consumer.accept(OASIS, this.oasisSiteLootTable());
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

    private static ResourceLocation register(String pId) {
        return register(new ResourceLocation(RealityMod.MOD_ID, pId));
    }

    private static ResourceLocation register(ResourceLocation pId) {
        if (LOCATIONS.add(pId)) {
            return pId;
        } else {
            throw new IllegalArgumentException(pId + " is already a registered built-in loot table");
        }
    }
}
