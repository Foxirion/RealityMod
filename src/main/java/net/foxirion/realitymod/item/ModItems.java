package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.ModBoatEntity;
import net.foxirion.realitymod.item.custom.ModBoatItem;
import net.minecraft.world.item.HangingSignItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RealityMod.MOD_ID);

    // Coconut
    public static final RegistryObject<Item> COCONUT = ITEMS.register("coconut", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COCONUT_MILK = ITEMS.register("coconut_milk", () -> new Item(new Item.Properties().food(ModFoods.COCONUT_MILK)));

    // Desert Turtle
    public static final RegistryObject<Item> DESERT_TURTLE_SCUTE = ITEMS.register("desert_turtle_scute", () -> new Item(new Item.Properties()));

    // Spawn Egg
    public static final RegistryObject<Item> DESERT_TURTLE_SPAWN_EGG = ITEMS.register("desert_turtle_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DESERT_TURTLE, 0x8D4B38, 0x6A5220, new Item.Properties()));

    // Oasis Clay Ball
    public static final RegistryObject<Item> OASIS_CLAY_BALL = ITEMS.register("oasis_clay_ball", () -> new Item(new Item.Properties()));

    // Boats
    public static final RegistryObject<Item> PALM_BOAT = ITEMS.register("palm_boat", () -> new ModBoatItem(false, ModBoatEntity.Type.PALM, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> PALM_CHEST_BOAT = ITEMS.register("palm_chest_boat", () -> new ModBoatItem(true, ModBoatEntity.Type.PALM, new Item.Properties().stacksTo(1)));

    // Signs
    public static final RegistryObject<Item> PALM_SIGN = ITEMS.register("palm_sign", () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get()));
    public static final RegistryObject<Item> PALM_HANGING_SIGN = ITEMS.register("palm_hanging_sign", () -> new HangingSignItem(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
}
