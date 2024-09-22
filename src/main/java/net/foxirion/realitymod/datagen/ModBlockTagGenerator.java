package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RealityMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(ModTags.Blocks.PALM_LOGS)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_PLANKS.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.PALM_LEAVES.get());

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE);

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.OASIS_CLAY.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL);

        this.tag(BlockTags.NEEDS_IRON_TOOL);

        this.tag(Tags.Blocks.NEEDS_GOLD_TOOL);

        this.tag(BlockTags.NEEDS_STONE_TOOL);

        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL);
    }
}
