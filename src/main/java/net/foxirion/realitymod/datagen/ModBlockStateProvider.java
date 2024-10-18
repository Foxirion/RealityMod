package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.custom.DesertTurtleEggBlock;
import net.foxirion.realitymod.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, RealityMod.MODID, exFileHelper);
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

        buttonBlock(((ButtonBlock) ModBlocks.PALM_BUTTON.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        createTurtleEgg(ModBlocks.DESERT_TURTLE_EGG.get());

        createFossilBlock(ModBlocks.FOSSIL.get(), "fossil", "fossil_front", "fossil_side");
        createFossilBlock(ModBlocks.DEEPSLATE_FOSSIL.get(), "deepslate_fossil", "deepslate_fossil_front", "deepslate_fossil_side");
        createFossilBlock(ModBlocks.FROZEN_FOSSIL.get(), "frozen_fossil", "frozen_fossil_front", "frozen_fossil_side");
        createFossilBlock(ModBlocks.NETHER_FOSSIL.get(), "nether_fossil", "nether_fossil_front", "nether_fossil_side");

        doorBlockWithRenderType(((DoorBlock) ModBlocks.PALM_DOOR.get()),
                modLoc("block/palm_door_bottom"),
                modLoc("block/palm_door_top"),
                "cutout");

        fenceBlock(((FenceBlock) ModBlocks.PALM_FENCE.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.PALM_FENCE_GATE.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        hangingSignBlock(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.PALM_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PALM_PRESSURE_PLATE.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        createSaplingBlock(ModBlocks.PALM_SAPLING.get(), ModBlocks.POTTED_PALM_SAPLING.get());

        slabBlock(((SlabBlock) ModBlocks.PALM_SLAB.get()), blockTexture(ModBlocks.PALM_PLANKS.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        signBlock(((StandingSignBlock) ModBlocks.PALM_SIGN.get()), ((WallSignBlock) ModBlocks.PALM_WALL_SIGN.get()),
                blockTexture(ModBlocks.PALM_PLANKS.get()));

        stairsBlock(((StairBlock) ModBlocks.PALM_STAIRS.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.PALM_TRAPDOOR.get()),
                modLoc("block/palm_trapdoor"),
                true,
                "cutout");

    }

    public void createTurtleEgg(Block turtleEgg) {
        String name = name(turtleEgg);
        if (turtleEgg instanceof TurtleEggBlock) {
            int minEggs = TurtleEggBlock.MIN_EGGS;
            int maxEggs = TurtleEggBlock.MAX_EGGS;
            int maxHatch = TurtleEggBlock.MAX_HATCH_LEVEL;

            for (int eggs = minEggs; eggs <= maxEggs; eggs++) {
                for (int hatch = 0; hatch <= maxHatch; hatch++) {
                    String modelName1 = switch (hatch) {
                        case 1 -> "slightly_cracked_" + name;
                        case 2 -> "very_cracked_" + name;
                        default -> name;
                    };

                    String modelName = switch (eggs) {
                        case 1 -> modelName1;
                        case 2 -> "two_" + modelName1 + "s";
                        case 3 -> "three_" + modelName1 + "s";
                        case 4 -> "four_" + modelName1 + "s";
                        default -> throw new IllegalStateException("Unexpected value: " + eggs);
                    };

                    String parentModel = switch (eggs) {
                        case 1 -> "template_turtle_egg";
                        case 2 -> "template_two_turtle_eggs";
                        case 3 -> "template_three_turtle_eggs";
                        case 4 -> "template_four_turtle_eggs";
                        default -> throw new IllegalStateException("Unexpected egg count: " + eggs);
                    };

                    String textureName = switch (hatch) {
                        case 1 -> name + "_slightly_cracked";
                        case 2 -> name + "_very_cracked";
                        default -> name;
                    };

                    models().getBuilder(modelName)
                            .parent(new ModelFile.UncheckedModelFile(mcLoc("block/" + parentModel)))
                            .texture("all", modLoc("block/" + textureName));

                    getVariantBuilder(turtleEgg).partialState()
                            .with(DesertTurtleEggBlock.EGGS, eggs)
                            .with(DesertTurtleEggBlock.HATCH, hatch)
                            .addModels(
                                    new ConfiguredModel(models().getExistingFile(modLoc("block/" + modelName)), 0, 0, false),
                                    new ConfiguredModel(models().getExistingFile(modLoc("block/" + modelName)), 0, 90, false),
                                    new ConfiguredModel(models().getExistingFile(modLoc("block/" + modelName)), 0, 180, false),
                                    new ConfiguredModel(models().getExistingFile(modLoc("block/" + modelName)), 0, 270, false)
                            );
                }
            }
        }
    }

    private void createFossilBlock(Block fossilBlock, String fossilName, String frontTexture, String sideTexture) {
        ResourceLocation name = RealityMod.rl(fossilName);

        ModelFile model = models().getBuilder(name.getPath())
                .parent(models().getExistingFile(mcLoc("block/orientable")))
                .texture("front", modLoc("block/" + frontTexture))
                .texture("side", modLoc("block/" + sideTexture))
                .texture("top", modLoc("block/" + sideTexture));

        getVariantBuilder(fossilBlock)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationY((int) dir.getOpposite().toYRot())
                            .build();
                });
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private void logBlock(ModFlammableRotatedPillarBlock block) {
        axisBlock(block,
                RealityMod.rl("block/" + name(block)),
                RealityMod.rl("block/" + name(block) + "_top"));
    }

    private void createSaplingBlock(Block block, Block pottedSaplingBlock) {
        simpleBlock(block, models().cross(name(block), blockTexture(block)).renderType(RenderType.CUTOUT.name));

        simpleBlock(pottedSaplingBlock, models().withExistingParent(name(pottedSaplingBlock), "block/flower_pot_cross")
                .texture("plant", blockTexture(block)).renderType(RenderType.CUTOUT.name));

        itemModels().getBuilder(name(block))
                .parent(itemModels().getExistingFile(mcLoc("item/generated")))
                .texture("layer0", "block/" + name(block));
    }

    private void blockWithItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(RealityMod.MODID + ":block/" + name(blockRegistryObject.get())));
    }

    // Other stuff
    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }
}