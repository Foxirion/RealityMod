package net.foxirion.realitymod.util;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ModWoodTypes {
    public static final WoodType PALM = WoodType.register(new WoodType(RealityMod.MODID + ":palm", BlockSetType.OAK));
}