package net.foxirion.realitymod.block;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.custom.*;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.util.ModWoodTypes;
import net.foxirion.realitymod.worldgen.tree.PalmTreeGrower;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RealityMod.MOD_ID);


    //Desert Turtle Egg
    public static final RegistryObject<Block> DESERT_TURTLE_EGG = registerBlock("desert_turtle_egg",
            () -> new DesertTurtleEggBlock(BlockBehaviour.Properties.copy(Blocks.TURTLE_EGG)));

    //Fossils
    public static final RegistryObject<Block> DEEPSLATE_FOSSIL = registerBlock("deepslate_fossil",
            () -> new ModFossilBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
                    .mapColor(MapColor.DEEPSLATE)));
    public static final RegistryObject<Block> FOSSIL = registerBlock("fossil",
            () -> new ModFossilBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .mapColor(MapColor.STONE)));
    public static final RegistryObject<Block> FROZEN_FOSSIL = registerBlock("frozen_fossil",
            () -> new ModFossilBlock(BlockBehaviour.Properties.copy(Blocks.PACKED_ICE)
                    .mapColor(MapColor.ICE)));
    public static final RegistryObject<Block> NETHER_FOSSIL = registerBlock("nether_fossil",
            () -> new ModFossilBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_GOLD_ORE)
                    .mapColor(MapColor.NETHER)));

    //Oasis Clay
    public static final RegistryObject<Block> OASIS_CLAY = registerBlock("oasis_clay",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.CLAY)
                    .mapColor(MapColor.SAND)));

    //Palm Button
    public static final RegistryObject<Block> PALM_BUTTON = registerBlock("palm_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.copy(Blocks.OAK_BUTTON),
                    BlockSetType.OAK, 10, true));

    //Palm Door
    public static final RegistryObject<Block> PALM_DOOR = registerBlock("palm_door",
            () -> new DoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_DOOR)
                    .noOcclusion(),
                    BlockSetType.OAK));

    //Palm Fence
    public static final RegistryObject<Block> PALM_FENCE = registerBlock("palm_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE)));

    //Palm Fence Gate
    public static final RegistryObject<Block> PALM_FENCE_GATE = registerBlock("palm_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.copy(Blocks.OAK_FENCE_GATE),
                    SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));

    //Palm Leaves
    public static final RegistryObject<Block> PALM_LEAVES = registerBlock("palm_leaves",
            () -> new ModLeaves(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)));

    //Palm Log
    public static final RegistryObject<Block> PALM_LOG = registerBlock("palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG)));

    //Palm Planks
    public static final RegistryObject<Block> PALM_PLANKS = registerBlock("palm_planks",
            () -> new ModPlanks(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)));

    //Palm Pressure Plate
    public static final RegistryObject<Block> PALM_PRESSURE_PLATE = registerBlock("palm_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.copy(Blocks.OAK_PRESSURE_PLATE),
                    BlockSetType.OAK));

    //Palm Sapling
    public static final RegistryObject<Block> PALM_SAPLING = registerBlock("palm_sapling",
            () -> new PalmSaplingBlock(new PalmTreeGrower(), BlockBehaviour.Properties.copy(Blocks.ACACIA_SAPLING)));

    //Palm Slab
    public static final RegistryObject<Block> PALM_SLAB = registerBlock("palm_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SLAB)));

    //Palm Signs(Normal, hanging & wall(for both of them))
    public static final RegistryObject<Block> PALM_SIGN = BLOCKS.register("palm_sign",
            () -> new ModStandingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_SIGN), ModWoodTypes.PALM));
    public static final RegistryObject<Block> PALM_WALL_SIGN = BLOCKS.register("palm_wall_sign",
            () -> new ModWallSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_SIGN), ModWoodTypes.PALM));
    public static final RegistryObject<Block> PALM_HANGING_SIGN = BLOCKS.register("palm_hanging_sign",
            () -> new ModHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_HANGING_SIGN), ModWoodTypes.PALM));
    public static final RegistryObject<Block> PALM_WALL_HANGING_SIGN = BLOCKS.register("palm_wall_hanging_sign",
            () -> new ModWallHangingSignBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WALL_HANGING_SIGN), ModWoodTypes.PALM));


    //Palm Stairs
    public static final RegistryObject<Block> PALM_STAIRS = registerBlock("palm_stairs",
            () -> new StairBlock(() -> ModBlocks.PALM_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.copy(Blocks.OAK_STAIRS)));

    //Palm Trapdoor
    public static final RegistryObject<Block> PALM_TRAPDOOR = registerBlock("palm_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.copy(Blocks.OAK_TRAPDOOR)
                    .noOcclusion(),
                    BlockSetType.OAK));

    //Palm Wood
    public static final RegistryObject<Block> PALM_WOOD = registerBlock("palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD)));

    //Stripped Palm Log
    public static final RegistryObject<Block> STRIPPED_PALM_LOG = registerBlock("stripped_palm_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG)));

    //Stripped Palm Wood
    public static final RegistryObject<Block> STRIPPED_PALM_WOOD = registerBlock("stripped_palm_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD)));



    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
