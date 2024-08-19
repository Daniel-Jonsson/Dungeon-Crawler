package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code GroupHeal} class represents an ability called Group Heal.
 * It is a healing ability that targets multiple allies.
 * The ability has the highest action point cost and high energy cost.
 * <p>When executed, the base attack value is modified based on whether the ability targets enemies.
 * The ability is considered both a healing ability and a magic ability.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class GroupHeal extends BaseAbility{

    /**Instance field variable*/
    private final String magicalPhrase;

    /**
     * Constructs a {@code GroupHeal} object.
     * It sets the action point cost and energy cost to the highest values.
     * It also initializes the magical phrase for the ability.
     */
    public GroupHeal(){
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_3;
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
        return String.format("%s: %s", magicalPhrase ,AppConfig.ABILITY_GROUP_HEAL);
    }
}
