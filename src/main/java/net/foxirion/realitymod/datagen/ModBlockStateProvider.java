package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.custom.DesertTurtleEggBlock;
import net.foxirion.realitymod.block.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
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

        createDesertTurtleEggBlockStateAndModel(ModBlocks.DESERT_TURTLE_EGG.get(), "realitymod");

        createFossilBlock(ModBlocks.FOSSIL.get(), "fossil", "fossil_front", "fossil_side");
        createFossilBlock(ModBlocks.DEEPSLATE_FOSSIL.get(), "deepslate_fossil", "deepslate_fossil_front", "deepslate_fossil_side");
        createFossilBlock(ModBlocks.FROZEN_FOSSIL.get(), "frozen_fossil", "frozen_fossil_front", "frozen_fossil_side");
        createFossilBlock(ModBlocks.NETHER_FOSSIL.get(), "nether_fossil", "nether_fossil_front", "nether_fossil_side");

        doorBlockWithRenderType(((DoorBlock) ModBlocks.PALM_DOOR.get()),
                modLoc("block/palm_door_bottom"),
                modLoc("block/palm_door_top"),
                "cutout");

        fenceBlock(((FenceBlock) ModBlocks.PALM_FENCE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        fenceGateBlock(((FenceGateBlock) ModBlocks.PALM_FENCE_GATE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        hangingSignBlock(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(), blockTexture(ModBlocks.PALM_PLANKS.get()));

        pressurePlateBlock(((PressurePlateBlock) ModBlocks.PALM_PRESSURE_PLATE.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        saplingBlock(ModBlocks.PALM_SAPLING);

        slabBlock(((SlabBlock) ModBlocks.PALM_SLAB.get()),blockTexture(ModBlocks.PALM_PLANKS.get()), blockTexture(ModBlocks.PALM_PLANKS.get()));

        signBlock(((StandingSignBlock) ModBlocks.PALM_SIGN.get()), ((WallSignBlock) ModBlocks.PALM_WALL_SIGN.get()),
                blockTexture(ModBlocks.PALM_PLANKS.get()));

        stairsBlock(((StairBlock)ModBlocks.PALM_STAIRS.get()),blockTexture(ModBlocks.PALM_PLANKS.get()));

        trapdoorBlockWithRenderType(((TrapDoorBlock) ModBlocks.PALM_TRAPDOOR.get()),
                modLoc("block/palm_trapdoor"),
                true,
                "cutout");

    }

    public void createDesertTurtleEggBlockStateAndModel(Block desertTurtleEgg, String modId) {
        var variantBuilder = getVariantBuilder(desertTurtleEgg);

        for (int eggs = 1; eggs <= 4; eggs++) {
            for (int hatch = 0; hatch <= 2; hatch++) {
                // Determine the appropriate texture based on hatch level
                String textureName = switch (hatch) {
                    case 1 -> "desert_turtle_egg_slightly_cracked";
                    case 2 -> "desert_turtle_egg_very_cracked";
                    default -> "desert_turtle_egg";
                };

                // Construct the model name based on eggs and texture type
                String modelName = eggs + "_" + textureName;

                // Determine the parent model based on egg count
                String parentModel = switch (eggs) {
                    case 1 -> "template_turtle_egg";
                    case 2 -> "template_two_turtle_eggs";
                    case 3 -> "template_three_turtle_eggs";
                    case 4 -> "template_four_turtle_eggs";
                    default -> throw new IllegalStateException("Unexpected egg count: " + eggs);
                };

                // Generate the model file with specified parent and texture under "all"
                models().getBuilder(modelName)
                        .parent(new ModelFile.UncheckedModelFile(mcLoc("block/" + parentModel)))
                        .texture("all", modLoc("block/" + textureName));

                // Add four rotations for the blockstate to handle specified eggs/hatch combination
                variantBuilder.partialState()
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


    private void createFossilBlock(Block fossilBlock, String fossilName, String frontTexture, String sideTexture) {
        ResourceLocation name = new ResourceLocation(RealityMod.MOD_ID, fossilName);

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

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
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