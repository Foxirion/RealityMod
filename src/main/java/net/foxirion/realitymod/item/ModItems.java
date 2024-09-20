package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, RealityMod.MOD_ID);

    //Coconut
    public static final RegistryObject<Item> COCONUT = ITEMS.register("coconut",
            () -> new Item(new Item.Properties()));

    //Desert Turtle Scute
    public static final RegistryObject<Item> DESERT_TURTLE_SCUTE = ITEMS.register("desert_turtle_scute",
            () -> new Item(new Item.Properties()));

    //Oasis Clay Ball
    public static final RegistryObject<Item> OASIS_CLAY_BALL = ITEMS.register("oasis_clay_ball",
            () -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
