package net.nikdo53.moresnifferflowers.entities.goals;

import net.nikdo53.moresnifferflowers.entities.BoblingEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

public class BoblingAttackPlayerGoal extends MeleeAttackGoal {
    public BoblingAttackPlayerGoal(BoblingEntity pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
    }

    @Override
    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        if (pDistToEnemySqr <= this.getAttackReachSqr(pEnemy) && this.getTicksUntilNextAttack() <= 0) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(pEnemy);
            if(this.mob instanceof BoblingEntity bobling && pEnemy instanceof ServerPlayer serverPlayer) {
                bobling.setRunning(true);
            }
        }
    }
}
