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

        //Blocks Tags

        this.tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.PALM_FENCE_GATE.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get());

        this.tag(ModTags.Blocks.PALM_LOGS)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        this.tag(BlockTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get());

        this.tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.PALM_BUTTON.get());

        this.tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.PALM_DOOR.get());

        this.tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.PALM_FENCE.get());

        this.tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.PALM_PRESSURE_PLATE.get());

        this.tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.PALM_SLAB.get());

        this.tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.PALM_STAIRS.get());

        this.tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.PALM_TRAPDOOR.get());



        //Mineable Tags

        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.PALM_BUTTON.get(),
                        ModBlocks.PALM_DOOR.get(),
                        ModBlocks.PALM_FENCE.get(),
                        ModBlocks.PALM_FENCE_GATE.get(),
                        ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_PLANKS.get(),
                        ModBlocks.PALM_PRESSURE_PLATE.get(),
                        ModBlocks.PALM_SLAB.get(),
                        ModBlocks.PALM_STAIRS.get(),
                        ModBlocks.PALM_TRAPDOOR.get(),
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
