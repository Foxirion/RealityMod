package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, RealityMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
            //Fossils
        this.tag(ModTags.Items.FOSSILS)
                .add(ModBlocks.FOSSIL.get().asItem(),
                        ModBlocks.DEEPSLATE_FOSSIL.get().asItem(),
                        ModBlocks.FROZEN_FOSSIL.get().asItem(),
                        ModBlocks.NETHER_FOSSIL.get().asItem());

            //Leaves
        this.tag(ItemTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get().asItem());

            //Logs that burn
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get().asItem(),
                        ModBlocks.PALM_WOOD.get().asItem(),
                        ModBlocks.STRIPPED_PALM_LOG.get().asItem(),
                        ModBlocks.STRIPPED_PALM_WOOD.get().asItem());

            //Palm Logs
        this.tag(ModTags.Items.PALM_LOGS)
                .add(ModBlocks.PALM_LOG.get().asItem(),
                        ModBlocks.PALM_WOOD.get().asItem().asItem(),
                        ModBlocks.STRIPPED_PALM_LOG.get().asItem(),
                        ModBlocks.STRIPPED_PALM_WOOD.get().asItem());

            //Planks
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get().asItem());

            //Signs
        this.tag(ItemTags.HANGING_SIGNS)
                .add(ModItems.PALM_HANGING_SIGN.get());
        this.tag(ItemTags.SIGNS)
                .add(ModItems.PALM_SIGN.get());

            //Trimmable Armor
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.DESERT_TURTLE_HELMET.get());

    }
}
