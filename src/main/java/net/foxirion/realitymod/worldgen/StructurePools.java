package net.foxirion.realitymod.worldgen;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class StructurePools {

    public static ResourceKey<StructureTemplatePool> createKey(String name) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, RealityMod.rl(name));
    }

    public static ResourceKey<StructureTemplatePool> parseKey(String pKey) {
        return ResourceKey.create(Registries.TEMPLATE_POOL, ResourceLocation.withDefaultNamespace(pKey));
    }

    public static void register(BootstrapContext<StructureTemplatePool> pContext, String pName, StructureTemplatePool pPool) {
        pContext.register(createKey(pName), pPool);
    }

    public static void bootstrap(BootstrapContext<StructureTemplatePool> pContext) {
        OasisPools.bootstrap(pContext);
    }
}
