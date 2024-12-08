package net.foxirion.realitymod.entity;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.entity.custom.DesertTurtle;
import net.foxirion.realitymod.entity.custom.Fennec;
import net.foxirion.realitymod.entity.custom.ModBoatEntity;
import net.foxirion.realitymod.entity.custom.ModChestBoatEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RealityMod.MOD_ID);

    //Register Desert Turtle
    public static final RegistryObject<EntityType<DesertTurtle>> DESERT_TURTLE =
            ENTITY_TYPES.register("desert_turtle", () -> EntityType.Builder.of(DesertTurtle::new, MobCategory.CREATURE)
                    .sized(1.0F, 0.75F).build("desert_turtle"));

    //Register Fennec
    public static final RegistryObject<EntityType<Fennec>> FENNEC =
            ENTITY_TYPES.register("fennec", () -> EntityType.Builder.of(Fennec::new, MobCategory.CREATURE)
                    .sized(0.5F, 0.5F).build("fennec"));

    //Register ModBoats
    public static final RegistryObject<EntityType<ModBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<ModBoatEntity>of(ModBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));

    public static final RegistryObject<EntityType<ModChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<ModChestBoatEntity>of(ModChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));
}