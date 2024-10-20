package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.ModBoatEntity;
import net.foxirion.realitymod.item.custom.ModArmorMaterials;
import net.foxirion.realitymod.item.custom.ModBoatItem;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(RealityMod.MODID);

    // Boats
    public static final DeferredItem<Item> PALM_BOAT = ITEMS.register("palm_boat",
            () -> new ModBoatItem(false, ModBoatEntity.Type.PALM, new Item.Properties().stacksTo(1)));
    public static final DeferredItem<Item> PALM_CHEST_BOAT = ITEMS.register("palm_chest_boat",
            () -> new ModBoatItem(true, ModBoatEntity.Type.PALM, new Item.Properties().stacksTo(1)));

    // Coconut
    public static final DeferredItem<Item> COCONUT = ITEMS.register("coconut",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> COCONUT_MILK = ITEMS.register("coconut_milk",
            () -> new Item(new Item.Properties().food(ModFoods.COCONUT_MILK)));

    // Desert Turtle
    public static final DeferredItem<Item> DESERT_TURTLE_HELMET = ITEMS.register("desert_turtle_helmet",
            () -> new DesertTurtleHelmet(ModArmorMaterials.DESERT_TURTLE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(26))));
    public static final DeferredItem<Item> DESERT_TURTLE_SCUTE = ITEMS.register("desert_turtle_scute",
            () -> new Item(new Item.Properties()));

    // Oasis Clay Ball
    public static final DeferredItem<Item> OASIS_CLAY_BALL = ITEMS.register("oasis_clay_ball",
            () -> new Item(new Item.Properties()));

    // Signs
    public static final DeferredItem<Item> PALM_HANGING_SIGN = ITEMS.register("palm_hanging_sign",
            () -> new HangingSignItem(ModBlocks.PALM_HANGING_SIGN.get(), ModBlocks.PALM_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final DeferredItem<Item> PALM_SIGN = ITEMS.register("palm_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), ModBlocks.PALM_SIGN.get(), ModBlocks.PALM_WALL_SIGN.get()));

    // Spawn Eggs
    public static final DeferredItem<SpawnEggItem> DESERT_TURTLE_SPAWN_EGG = ITEMS.register("desert_turtle_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.DESERT_TURTLE, 0x8D4B38, 0x6A5220, new Item.Properties()));
}
