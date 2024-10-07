package net.foxirion.realitymod.entity.goal;

import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.PanicGoal;

public class DesertTurtlePanicGoal extends PanicGoal {
    public DesertTurtlePanicGoal(DesertTurtleEntity pTurtle, double pSpeedModifier) {
        super(pTurtle, pSpeedModifier);
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (!this.shouldPanic()) {
            return false;
        } else {
            BlockPos blockpos = this.lookForWater(this.mob.level(), this.mob, 7);
            if (blockpos != null) {
                this.posX = (double)blockpos.getX();
                this.posY = (double)blockpos.getY();
                this.posZ = (double)blockpos.getZ();
                return true;
            } else {
                return this.findRandomPosition();
            }
        }
    }
}