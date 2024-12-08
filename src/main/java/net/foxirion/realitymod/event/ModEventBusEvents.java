package net.foxirion.realitymod.event;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtle;
import net.foxirion.realitymod.entity.custom.Fennec;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RealityMod.MOD_ID,bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT.get(), 0.65F);
            ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT_SHELL.get(), 0.15F);
            ComposterBlock.COMPOSTABLES.put(ModItems.COCONUT_MILK.get(), 0.325F);
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.DESERT_TURTLE.get(), DesertTurtle.createAttributes().build());
        event.put(ModEntities.FENNEC.get(), Fennec.createAttributes().build());
    }

}
