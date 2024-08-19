package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code FocusedHeal} class represents an ability called Focused Heal.
 * It is a healing ability that targets a single ally.
 * The ability has a medium action point cost and a low energy cost.
 * <p>When executed, the base attack value is multiplied by a single target ability multiplier obtained via
 * {@link AppConfig}.
 * The ability is considered a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class FocusedHeal extends BaseAbility{

    /**Instance field variable*/
    private final String magicalPhrase;

    /**
     * Constructs a {@code FocusedHeal} object.
     * It sets the action point cost and energy cost to medium and low respectively.
     * It also initializes the magical phrase associated with the ability.
     */
    public FocusedHeal(){
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_4;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isHeal() {
        return true;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isMagic() {
        return true;
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
    public String toString(){
        return String.format("%s: %s", magicalPhrase,
                AppConfig.ABILITY_FOCUSED_HEAL);
    }
}
