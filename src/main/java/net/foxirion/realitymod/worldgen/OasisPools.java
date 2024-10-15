package net.foxirion.realitymod.worldgen;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.Pools;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

public class OasisPools {
    public static final ResourceKey<StructureTemplatePool> OASIS = StructurePools.createKey("oasis/oasis_lake");

    public static void bootstrap(BootstapContext<StructureTemplatePool> pContext) {
        HolderGetter<StructureTemplatePool> holdergetter = pContext.lookup(Registries.TEMPLATE_POOL);
        Holder<StructureTemplatePool> holder = holdergetter.getOrThrow(Pools.EMPTY);

        pContext.register(OASIS, new StructureTemplatePool(holder, ImmutableList.of(
                Pair.of(StructurePoolElement.legacy("realitymod:oasis/oasis_lake"), 1)),
                StructureTemplatePool.Projection.RIGID)
        );

        StructurePools.register(pContext, "oasis/oasis_plants", new StructureTemplatePool(holder, ImmutableList.of(
                Pair.of(StructurePoolElement.legacy("realitymod:oasis/oasis_plants"), 1)),
                StructureTemplatePool.Projection.TERRAIN_MATCHING)
        );
    }
}
