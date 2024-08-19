package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code SprayOfArrows} class represents an ability called Spray of Arrows.
 * It is a physical attack ability that targets a group of enemies.
 * The ability has the highest action point cost and high energy cost.
 * The ability is not considered a healing ability or a magic ability.
 * <p>The class provides methods to execute the ability, determine if it is a healing ability,
 * determine if it is a magic ability, and retrieve the number of targets it affects.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class SprayOfArrows extends BaseAbility {

    /**
     * Constructs a {@code SprayOfArrows} object.
     * It sets the action point cost and energy cost to the highest values.
     */
    public SprayOfArrows(){
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
        return AppConfig.ABILITY_SPRAY_OF_ARROWS;
    }
}
