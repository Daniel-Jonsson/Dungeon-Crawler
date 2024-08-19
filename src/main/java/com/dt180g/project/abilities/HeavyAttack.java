package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code HeavyAttack} class represents an ability called Heavy Attack.
 * It is a physical attack ability that targets a single enemy.
 * The ability has a medium action point cost and low energy cost.
 * <p>When executed, the base attack value is multiplied by a single target ability multiplier.
 * The ability is not considered a healing ability or a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class HeavyAttack extends BaseAbility{

    /**
     * Constructs a {@code HeavyAttack} object.
     * It sets the action point cost and energy cost to medium values.
     */
    public HeavyAttack(){
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isHeal() {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isMagic() {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public int getAmountOfTargets() {
        return AppConfig.ABILITY_SINGLE_TARGET;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean execute(int baseAtk, boolean shouldTargetEnemy) {
        baseAtk *= AppConfig.SINGLE_TARGET_ABILITY_MULTIPLIER;
        return super.performAbility(toString(), getAmountOfTargets(), baseAtk, shouldTargetEnemy);
    }

    /**
     * String representation of the ability, contains magical phrase(if magical) and element(if elemental).
     *
     * @return String representation of ability.
     */
    @Override
    public String toString() {
        return AppConfig.ABILITY_HEAVY_ATTACK;
    }
}
