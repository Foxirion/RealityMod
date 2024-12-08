//package net.foxirion.realitymod.entity.goal;
//
//import net.minecraft.world.entity.EquipmentSlot;
//import net.minecraft.world.entity.ai.goal.Goal;
//import net.minecraft.world.entity.animal.Fox;
//import net.minecraft.world.entity.item.ItemEntity;
//import net.minecraft.world.item.ItemStack;
//
//import java.util.EnumSet;
//import java.util.List;
//
//public class FennecSearachForItemGoal extends Goal {
//    public FennecSearachForItemGoal() {
//        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
//    }
//
//    public boolean canUse() {
//        if (!Fox.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty()) {
//            return false;
//        } else if (Fox.this.getTarget() == null && Fox.this.getLastHurtByMob() == null) {
//            if (!Fox.this.canMove()) {
//                return false;
//            } else if (Fox.this.getRandom().nextInt(reducedTickDelay(10)) != 0) {
//                return false;
//            } else {
//                List<ItemEntity> list = Fox.this.level().getEntitiesOfClass(ItemEntity.class, Fox.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Fox.ALLOWED_ITEMS);
//                return !list.isEmpty() && Fox.this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty();
//            }
//        } else {
//            return false;
//        }
//    }
//
//    public void tick() {
//        List<ItemEntity> list = Fox.this.level().getEntitiesOfClass(ItemEntity.class, Fox.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Fox.ALLOWED_ITEMS);
//        ItemStack itemstack = Fox.this.getItemBySlot(EquipmentSlot.MAINHAND);
//        if (itemstack.isEmpty() && !list.isEmpty()) {
//            Fox.this.getNavigation().moveTo(list.get(0), (double)1.2F);
//        }
//
//    }
//
//    public void start() {
//        List<ItemEntity> list = Fox.this.level().getEntitiesOfClass(ItemEntity.class, Fox.this.getBoundingBox().inflate(8.0D, 8.0D, 8.0D), Fox.ALLOWED_ITEMS);
//        if (!list.isEmpty()) {
//            Fox.this.getNavigation().moveTo(list.get(0), (double)1.2F);
//        }
//
//    }
//}
