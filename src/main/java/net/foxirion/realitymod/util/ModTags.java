package net.foxirion.realitymod.util;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Block> FOSSILS = tag("fossils");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(RealityMod.MOD_ID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Item> FOSSILS = tag("fossils");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RealityMod.MOD_ID, name));
        }
    }
}
