package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, RealityMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        //Blocks Tags
        tag(BlockTags.CEILING_HANGING_SIGNS).add(ModBlocks.PALM_HANGING_SIGN.get());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.PALM_FENCE_GATE.get());

        tag(ModTags.Blocks.FOSSILS)
                .add(ModBlocks.FOSSIL.get(),
                        ModBlocks.DEEPSLATE_FOSSIL.get(),
                        ModBlocks.FROZEN_FOSSIL.get(),
                        ModBlocks.NETHER_FOSSIL.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.PALM_PLANKS.get());

        tag(ModTags.Blocks.PALM_LOGS)
                .add(ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        tag(BlockTags.LEAVES)
                .add(ModBlocks.PALM_LEAVES.get());

        tag(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.PALM_SIGN.get());

        tag(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.PALM_WALL_HANGING_SIGN.get());

        tag(BlockTags.WALL_SIGNS)
                .add(ModBlocks.PALM_WALL_SIGN.get());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.PALM_BUTTON.get());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.PALM_DOOR.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.PALM_FENCE.get());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.PALM_PRESSURE_PLATE.get());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.PALM_SLAB.get());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.PALM_STAIRS.get());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.PALM_TRAPDOOR.get());


        //Mineable Tags

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(
                        ModBlocks.PALM_BUTTON.get(),
                        ModBlocks.PALM_DOOR.get(),
                        ModBlocks.PALM_FENCE.get(),
                        ModBlocks.PALM_FENCE_GATE.get(),
                        ModBlocks.PALM_HANGING_SIGN.get(),
                        ModBlocks.PALM_LOG.get(),
                        ModBlocks.PALM_PLANKS.get(),
                        ModBlocks.PALM_PRESSURE_PLATE.get(),
                        ModBlocks.PALM_SIGN.get(),
                        ModBlocks.PALM_SLAB.get(),
                        ModBlocks.PALM_STAIRS.get(),
                        ModBlocks.PALM_TRAPDOOR.get(),
                        ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                        ModBlocks.PALM_WALL_SIGN.get(),
                        ModBlocks.PALM_WOOD.get(),
                        ModBlocks.STRIPPED_PALM_LOG.get(),
                        ModBlocks.STRIPPED_PALM_WOOD.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.PALM_LEAVES.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(ModTags.Blocks.FOSSILS);

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.OASIS_CLAY.get());

        tag(Tags.Blocks.NEEDS_NETHERITE_TOOL);

        tag(BlockTags.NEEDS_DIAMOND_TOOL);

        tag(BlockTags.NEEDS_IRON_TOOL);

        tag(Tags.Blocks.NEEDS_GOLD_TOOL);

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.FOSSIL.get(),
                        ModBlocks.DEEPSLATE_FOSSIL.get());

        tag(Tags.Blocks.NEEDS_WOOD_TOOL);
    }
}
