package net.foxirion.realitymod;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.block.entity.ModBlockEntities;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.init.ModDataComponents;
import net.foxirion.realitymod.item.ModCreativeModeTabs;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.item.custom.ModArmorMaterials;
import net.foxirion.realitymod.worldgen.tree.ModFoliagePlacers;
import net.foxirion.realitymod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.foxirion.realitymod.RealityMod.MODID;

@Mod(MODID)
public class RealityMod {
    public static final String MODID = "realitymod";
    public static final Logger logger = LoggerFactory.getLogger(RealityMod.class);

    public RealityMod(IEventBus bus) {
        ModArmorMaterials.ARMOR_MATERIALS.register(bus);
        ModCreativeModeTabs.CREATIVE_MODE_TABS.register(bus);
        ModItems.ITEMS.register(bus);
        ModBlocks.BLOCKS.register(bus);
        ModEntities.ENTITY_TYPES.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModTrunkPlacerTypes.TRUNK_PLACER.register(bus);
        ModFoliagePlacers.FOLIAGE_PLACERS.register(bus);
        ModDataComponents.COMPONENTS.register(bus);
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }
}
