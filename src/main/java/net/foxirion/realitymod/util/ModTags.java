package net.foxirion.realitymod.util;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.HashSet;
import java.util.Set;

public class ModTags {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = new HashSet<>();

    public static class Blocks {
        public static final TagKey<Block> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Block> FOSSILS = tag("fossils");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(RealityMod.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Item> FOSSILS = tag("fossils");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(RealityMod.MODID, name));
        }
    }

    public static class ChestLoot {
        public static final ResourceKey<LootTable> OASIS = register("chests/oasis_treasure");

        private static ResourceKey<LootTable> register(String name) {
            return register(ResourceKey.create(Registries.LOOT_TABLE, RealityMod.rl(name)));
        }

        private static ResourceKey<LootTable> register(ResourceKey<LootTable> pName) {
            if (LOCATIONS.add(pName)) {
                return pName;
            } else {
                throw new IllegalArgumentException(pName.location() + " is already a registered built-in loot table");
            }
        }
    }
}
