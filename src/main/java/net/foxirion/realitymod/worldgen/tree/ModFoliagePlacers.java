package net.foxirion.realitymod.worldgen.tree;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.worldgen.tree.custom.PalmFoliagePlacer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS =
            DeferredRegister.create(Registries.FOLIAGE_PLACER_TYPE, RealityMod.MOD_ID);

    public static final RegistryObject<FoliagePlacerType<PalmFoliagePlacer>> PALM_FOLIAGE_PLACER =
            FOLIAGE_PLACERS.register("palm_foliage_placer", () -> new FoliagePlacerType<>(PalmFoliagePlacer.CODEC));

    public static void register(IEventBus bus) {
        FOLIAGE_PLACERS.register(bus);
    }
}
