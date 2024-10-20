package net.foxirion.realitymod.block;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.custom.*;
import net.foxirion.realitymod.init.ModTreeGrower;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.util.ModWoodTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(RealityMod.MODID);


    //Desert Turtle Egg
    public static final DeferredBlock<Block> DESERT_TURTLE_EGG = registerBlock("desert_turtle_egg", () -> new DesertTurtleEggBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TURTLE_EGG)));

    //Fossils
    public static final DeferredBlock<Block> DEEPSLATE_FOSSIL = registerBlock("deepslate_fossil", () -> new ModFossilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DEEPSLATE).mapColor(MapColor.DEEPSLATE)));
    public static final DeferredBlock<Block> FOSSIL = registerBlock("fossil", () -> new ModFossilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(MapColor.STONE)));
    public static final DeferredBlock<Block> FROZEN_FOSSIL = registerBlock("frozen_fossil", () -> new ModFossilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PACKED_ICE).mapColor(MapColor.ICE)));
    public static final DeferredBlock<Block> NETHER_FOSSIL = registerBlock("nether_fossil", () -> new ModFossilBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.NETHER_GOLD_ORE).mapColor(MapColor.NETHER)));

    //Oasis Clay
    public static final DeferredBlock<Block> OASIS_CLAY = registerBlock("oasis_clay",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CLAY)
                    .mapColor(MapColor.SAND)));

    //Palm Button
    public static final DeferredBlock<Block> PALM_BUTTON = registerBlock("palm_button",
            () -> new ButtonBlock(BlockSetType.OAK, 20, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_BUTTON)));

    //Palm Door
    public static final DeferredBlock<Block> PALM_DOOR = registerBlock("palm_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_DOOR).noOcclusion()));

    //Palm Fence
    public static final DeferredBlock<Block> PALM_FENCE = registerBlock("palm_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE)));

    //Palm Fence Gate
    public static final DeferredBlock<Block> PALM_FENCE_GATE = registerBlock("palm_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    //Palm Leaves
    public static final DeferredBlock<Block> PALM_LEAVES = registerBlock("palm_leaves",
            () -> new ModLeaves(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    //Palm Log
    public static final DeferredBlock<Block> PALM_LOG = registerBlock("palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LOG)));

    //Palm Planks
    public static final DeferredBlock<Block> PALM_PLANKS = registerBlock("palm_planks",
            () -> new ModPlanks(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS)));

    //Palm Pressure Plate
    public static final DeferredBlock<Block> PALM_PRESSURE_PLATE = registerBlock("palm_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PRESSURE_PLATE)));


    //Palm Sapling
    public static final DeferredBlock<SaplingBlock> PALM_SAPLING = registerBlock("palm_sapling",
            () -> new PalmSaplingBlock(ModTreeGrower.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING)));
    public static final DeferredBlock<Block> POTTED_PALM_SAPLING = registerBlock("potted_palm_sapling", () -> flowerPot(PALM_SAPLING.get()));

    //Palm Slab
    public static final DeferredBlock<Block> PALM_SLAB = registerBlock("palm_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SLAB)));

    //Palm Signs(Normal, hanging & wall(for both of them))
    public static final DeferredBlock<Block> PALM_SIGN = BLOCKS.register("palm_sign",
            () -> new ModStandingSignBlock(ModWoodTypes.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SIGN)));
    public static final DeferredBlock<Block> PALM_WALL_SIGN = BLOCKS.register("palm_wall_sign",
            () -> new ModWallSignBlock(ModWoodTypes.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_SIGN)));
    public static final DeferredBlock<Block> PALM_HANGING_SIGN = BLOCKS.register("palm_hanging_sign",
            () -> new ModHangingSignBlock(ModWoodTypes.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_HANGING_SIGN)));
    public static final DeferredBlock<Block> PALM_WALL_HANGING_SIGN = BLOCKS.register("palm_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(ModWoodTypes.PALM, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WALL_HANGING_SIGN)));


    //Palm Stairs
    public static final DeferredBlock<Block> PALM_STAIRS = registerBlock("palm_stairs",
            () -> stair(ModBlocks.PALM_PLANKS.get()));

    //Palm Trapdoor
    public static final DeferredBlock<Block> PALM_TRAPDOOR = registerBlock("palm_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK,
                    BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_TRAPDOOR).noOcclusion()));

    //Palm Wood
    public static final DeferredBlock<Block> PALM_WOOD = registerBlock("palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_WOOD)));

    //Stripped Palm Log
    public static final DeferredBlock<Block> STRIPPED_PALM_LOG = registerBlock("stripped_palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_LOG)));

    //Stripped Palm Wood
    public static final DeferredBlock<Block> STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_OAK_WOOD)));

    // Methods
    private static Block stair(Block pBaseBlock) {
        return new StairBlock(pBaseBlock.defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(pBaseBlock));
    }

    private static Block flowerPot(Block pPotted) {
        return new FlowerPotBlock(pPotted, BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY));
    }

    // Registers
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItems(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> DeferredItem<Item> registerBlockItems(String name, DeferredBlock<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
