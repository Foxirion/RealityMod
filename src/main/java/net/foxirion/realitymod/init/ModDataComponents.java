package net.foxirion.realitymod.init;

import com.mojang.serialization.Codec;
import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModDataComponents {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, RealityMod.MODID);

    public static final Supplier<DataComponentType<Integer>> TURTLE_HELMET_COOLDOWN = COMPONENTS.registerComponentType(
            "cooldown",
            builder -> builder.persistent(Codec.INT).networkSynchronized(ByteBufCodecs.INT)
    );
}
