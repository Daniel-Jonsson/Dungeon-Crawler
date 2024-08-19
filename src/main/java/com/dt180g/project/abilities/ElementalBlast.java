package com.dt180g.project.abilities;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code ElementalBlast} class represents a magical ability called Elemental Blast.
 * It extends the {@link BaseAbility} class and implements specific behavior for this ability.
 * Elemental Blast is a damaging ability that targets multiple opponents.
 * It has a high action point cost and high energy cost.
 * The ability belongs to the magic category and does not heal.
 * The elemental type of the blast is specified during construction, being either Air, Fire or Ice.
 * The class provides methods to execute the ability, determine if it is a healing ability,
 * determine if it is a magic ability, and retrieve the number of targets it affects.
 * @author Daniel Jönsson
 * @see BaseAbility
 * @see AppConfig
 * @version 1.0
 */
public class ElementalBlast extends BaseAbility{

    /**Instance field variables*/
    private final String magicalPhrase;
    private final String element;

    /**
     * Constructs a new {@code ElementalBlast} object with the specified elemental type.
     *
     * @param element the elemental type of the blast (Air, Fire or Ice)
     */
    public ElementalBlast(String element){
        super(AppConfig.HIGHEST_AP_COST, AppConfig.HIGH_ENERGY_COST);
        this.element = element;
        this.magicalPhrase = AppConfig.MAGICAL_PHRASE_1;
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
        return String.format("%s: %s %s", magicalPhrase, element,
                AppConfig.ABILITY_ELEMENTAL_BLAST);
    }
}
