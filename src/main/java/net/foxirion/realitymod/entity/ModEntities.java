package net.foxirion.realitymod.entity;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.foxirion.realitymod.entity.custom.ModBoatEntity;
import net.foxirion.realitymod.entity.custom.ModChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealityMod.MOD_ID);

    //Register Desert Turtle
    public static final RegistryObject<EntityType<DesertTurtleEntity>> DESERT_TURTLE =
            ENTITY_TYPES.register("desert_turtle", () -> EntityType.Builder.of(DesertTurtleEntity::new, MobCategory.CREATURE)
                    .sized(1.0F, 0.75F).build("desert_turtle"));

    //Register ModBoats
    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}