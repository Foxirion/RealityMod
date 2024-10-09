package net.foxirion.realitymod;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.entity.ModBlockEntities;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.foxirion.realitymod.item.ModCreativeModeTabs;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.worldgen.tree.ModFoliagePlacers;
import net.foxirion.realitymod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.foxirion.realitymod.RealityMod.MOD_ID;

@Mod(MOD_ID)
public class RealityMod {
    public static final String MOD_ID = "realitymod";
    public static final Logger logger = LoggerFactory.getLogger(RealityMod.class);

    public RealityMod(FMLJavaModLoadingContext context) {
        IEventBus bus = context.getModEventBus();

        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
        ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);

    }

}
