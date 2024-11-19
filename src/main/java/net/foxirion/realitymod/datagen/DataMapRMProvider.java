package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class DataMapRMProvider extends DataMapProvider {
    public DataMapRMProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public void gather() {
        builder(NeoForgeDataMaps.COMPOSTABLES)
                .add(ModItems.COCONUT, new Compostable(0.65F), false)
                .add(ModItems.COCONUT_SHELL, new Compostable(0.25F), false)
                .add(ModItems.COCONUT_MILK, new Compostable(0.4F), false);
    }
}
