package net.foxirion.realitymod.entity.custom;

import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.function.IntFunction;

public class ModBoatEntity extends Boat {
    public ModBoatEntity(EntityType<? extends Boat> entityType, Level level) {
        super(entityType, level);
        this.blocksBuilding = true;
    }

    public ModBoatEntity(Level level, double x, double y, double z) {
        this(ModEntities.MOD_BOAT.get(), level);
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
            this.entityData.set(DATA_ID_TYPE, Type.byName(compound.getString("Type")).ordinal());
        }
    }

    @Override
    public Item getDropItem() {
        return switch (getModel()) {
            case PALM -> ModItems.PALM_BOAT.get();
        };
    }

    public void setType(Type type) {
        this.entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public Type getModel() {
        return Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public enum Type {
        PALM("palm", ModBlocks.PALM_PLANKS.get());

        private final String name;
        private final Block planks;

        Type(String name, Block planks) {
            this.name = name;
            this.planks = planks;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        public static Type byId(int id) {
            Type[] type = values();
            return type[id < 0 || id >= type.length ? 0 : id];
        }

        public static Type byName(String aName) {
            Type[] type = values();
            return Arrays.stream(type).filter(t -> t.getName().equals(aName)).findFirst().orElse(type[0]);
        }
    }
}
