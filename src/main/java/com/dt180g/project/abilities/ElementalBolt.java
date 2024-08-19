package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code ElementalBolt} class represents an ability called Elemental Bolt.
 * It is a type of attack ability that deals damage to a single target.
 * The ability is associated with a specific element and has a medium action point cost and a low energy cost.
 * <P>When executed, the base attack value is multiplied by a single target ability multiplier obtained via {@link AppConfig}.
 * The ability is considered a magic attack and cannot heal.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class ElementalBolt extends BaseAbility{

    /**Instance field variables*/
    private final String magicalPhrase;
    private final String element;

    /**
     * Constructs an {@code ElementalBolt} object with the specified element.
     *
     * @param element the element associated with the ability
     */
    public ElementalBolt(String element){
        super(AppConfig.MEDIUM_AP_COST, AppConfig.LOW_ENERGY_COST);
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_2;
        this.element = element;
    }

    /**{@inheritDoc}*/
    @Override
    public boolean isHeal() {
        return false;
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
        return String.format("%s: %s %s", magicalPhrase, element,
                AppConfig.ABILITY_ELEMENTAL_BOLT);
    }
}
