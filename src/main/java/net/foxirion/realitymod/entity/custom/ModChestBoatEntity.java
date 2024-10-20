package net.foxirion.realitymod.entity.custom;

import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class ModChestBoatEntity extends ChestBoat {
    public ModChestBoatEntity(EntityType<? extends ChestBoat> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public ModChestBoatEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOD_CHEST_BOAT.get(), level);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compound) {
        compound.putString("Type", this.getVariant().getSerializedName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compound) {
        if (compound.contains("Type", 8)) {
            this.entityData.set(DATA_ID_TYPE, ModBoatEntity.Type.byName(compound.getString("Type")).ordinal());
        }
    }

    @Override
    public Item getDropItem() {
        return switch (getModel()) {
            case PALM -> ModItems.PALM_CHEST_BOAT.get();
        };
    }

    public void setType(ModBoatEntity.Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public ModBoatEntity.Type getModel() {
        return ModBoatEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }
}