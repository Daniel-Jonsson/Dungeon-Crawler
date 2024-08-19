package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code FocusedShot} class represents an ability called Focused Shot.
 * It is an offensive ability that targets a single enemy.
 * The ability has a medium action point cost and a low energy cost.
 * <p>When executed, the base attack value is multiplied by a single target ability multiplier obtained via {@link AppConfig}.
 * The ability is not considered a healing ability or a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class FocusedShot extends BaseAbility{

    /**
     * Constructs a {@code FocusedShot} object.
     * It sets the action point cost and energy cost to medium and low respectively.
     */
    public FocusedShot(){
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
        return AppConfig.ABILITY_FOCUSED_SHOT;
    }
}
