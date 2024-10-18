package net.foxirion.realitymod.init;

import net.foxirion.realitymod.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrower {
    public static final TreeGrower PALM = new TreeGrower(
            "palm",
            Optional.empty(),
            Optional.of(ModConfiguredFeatures.PALM_KEY),
            Optional.empty()
    );
}
