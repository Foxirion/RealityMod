package net.foxirion.realitymod.worldgen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.util.ModTags;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;

import java.util.Map;

public class ModStructures {
    public static ResourceKey<Structure> OASIS = createKey("oasis");

    public static void bootstrap(BootstapContext<Structure> pContext) {
        HolderGetter<Biome> holdergetter = pContext.lookup(Registries.BIOME);
        pContext.register(OASIS, new OasisStructure(new Structure.StructureSettings(
                holdergetter.getOrThrow(ModTags.BiomeTags.HAS_OASIS),
                Map.of(),
                GenerationStep.Decoration.SURFACE_STRUCTURES,
                TerrainAdjustment.BEARD_THIN
        )));
    }

    private static ResourceKey<Structure> createKey(String name) {
        return ResourceKey.create(Registries.STRUCTURE, RealityMod.rl(name));
    }
}