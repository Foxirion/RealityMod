package net.foxirion.realitymod.util;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> LEAVES = tag("leaves");
        public static final TagKey<Block> LOGS_THAT_BURN = tag("logs_that_burn");
        public static final TagKey<Block> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Block> PLANKS = tag("planks");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(RealityMod.MOD_ID, name));
        }

    }

    public static class Items {
        public static final TagKey<Item> LEAVES = tag("leaves");
        public static final TagKey<Item> LOGS_THAT_BURN = tag("logs_that_burn");
        public static final TagKey<Item> PALM_LOGS = tag("palm_logs");
        public static final TagKey<Item> PLANKS = tag("planks");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(RealityMod.MOD_ID, name));
        }
    }
}
