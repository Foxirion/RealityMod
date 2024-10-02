package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
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

    //Coconut
    public static final RegistryObject<Item> COCONUT_MILK = ITEMS.register("coconut_milk",
            () -> new Item(new Item.Properties().food(ModFoods.COCONUT_MILK)));

    //Desert Turtle Scute
    public static final RegistryObject<Item> DESERT_TURTLE_SCUTE = ITEMS.register("desert_turtle_scute",
            () -> new Item(new Item.Properties()));

    //Desert Turtle Spawn Egg
    public static final RegistryObject<Item> DESERT_TURTLE_SPAWN_EGG = ITEMS.register("desert_turtle_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.DESERT_TURTLE, 0x8D4B38, 0x6A5220,
                    new Item.Properties()));

    //Oasis Clay Ball
    public static final RegistryObject<Item> OASIS_CLAY_BALL = ITEMS.register("oasis_clay_ball",
            () -> new Item(new Item.Properties()));

    //Palm Signs (Normal and hanging)
    public static final RegistryObject<Item> PALM_SIGN = ITEMS.register("palm_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16),
                    ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get()));
    public static final RegistryObject<Item> PALM_HANGING_SIGN = ITEMS.register("palm_hanging_sign",
            () -> new HangingSignItem(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(),
                    new Item.Properties().stacksTo(16)));




    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
