package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RealityMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.OASIS_CLAY);
        blockWithItem(ModBlocks.PALM_LEAVES);
        blockWithItem(ModBlocks.PALM_PLANKS);

        logBlock((ModFlammableRotatedPillarBlock) ModBlocks.PALM_LOG.get());
        axisBlock((ModFlammableRotatedPillarBlock) ModBlocks.PALM_WOOD.get(), blockTexture(ModBlocks.PALM_LOG.get()), blockTexture(ModBlocks.PALM_LOG.get()));
        logBlock((ModFlammableRotatedPillarBlock) ModBlocks.STRIPPED_PALM_LOG.get());
        axisBlock((ModFlammableRotatedPillarBlock) ModBlocks.STRIPPED_PALM_WOOD.get(), blockTexture(ModBlocks.STRIPPED_PALM_LOG.get()), blockTexture(ModBlocks.STRIPPED_PALM_LOG.get()));

        blockItem(ModBlocks.PALM_LOG);
        blockItem(ModBlocks.PALM_WOOD);
        blockItem(ModBlocks.STRIPPED_PALM_LOG);
        blockItem(ModBlocks.STRIPPED_PALM_WOOD);

        buttonBlock(((ButtonBlock) ModBlocks.PALM_BUTTON.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        doorBlockWithRenderType(((DoorBlock) ModBlocks.PALM_DOOR.get()),
                modLoc("block/palm_door_bottom"),
                modLoc("block/palm_door_top"),
                "cutout");

        fenceBlock(((FenceBlock) ModBlocks.PALM_FENCE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.PALM_FENCE_GATE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PALM_PRESSURE_PLATE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        slabBlock(((SlabBlock) ModBlocks.PALM_SLAB.get()),blockTexture(ModBlocks.PALM_PLANKS.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        stairsBlock(((StairBlock)ModBlocks.PALM_STAIRS.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.PALM_TRAPDOOR.get()),
                modLoc("block/palm_trapdoor"),
                true,
                "cutout");

    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private void logBlock(ModFlammableRotatedPillarBlock block) {
        axisBlock(block,
                new ResourceLocation(RealityMod.MOD_ID, "block/" + getName(block)),
                new ResourceLocation(RealityMod.MOD_ID, "block/" + getName(block) + "_top"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(RealityMod.MOD_ID + ":block/" + getName(blockRegistryObject.get())));
    }

    private String getName(Block block) {
        return block.getDescriptionId().replaceFirst("block\\.realitymod\\.", "");
    }
}