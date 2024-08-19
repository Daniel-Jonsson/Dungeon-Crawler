package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code WeaponAttack} class represents a basic weapon attack ability.
 * It is a physical attack ability that targets a single enemy.
 * The ability has the lowest action point cost and no energy cost.
 * The ability is not considered a healing ability or a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class WeaponAttack extends BaseAbility {

    /**
     * Constructs a {@code WeaponAttack} object.
     * It sets the action point cost to the lowest value and no energy cost.
     */
    public WeaponAttack(){
        super(AppConfig.LOWEST_AP_COST, 0);
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isMagic() {
        return false;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isHeal() {
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
        return AppConfig.ABILITY_WEAPON_ATTACK;
    }
}

