package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code Whirlwind} class represents a powerful ability that targets a group of enemies.
 * It has a high action point cost and a high energy cost.
 * The ability is not considered a healing ability or a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class Whirlwind extends BaseAbility{

    /**
     * Constructs a {@code Whirlwind} object.
     * It sets the action point cost to the highest value and the energy cost to a high value.
     */
    public Whirlwind(){
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
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
        return AppConfig.ABILITY_GROUP_TARGET;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean execute(int baseAtk, boolean shouldTargetEnemy) {
        return super.performAbility(toString(), getAmountOfTargets(), baseAtk, shouldTargetEnemy);
    }

    /**
     * String representation of the ability, contains magical phrase(if magical) and element(if elemental).
     *
     * @return String representation of ability.
     */
    @Override
    public String toString(){
        return AppConfig.ABILITY_WHIRLWIND;
    }
}
