package com.dt180g.project.abilities;

import com.dt180g.project.GameEngine;
import com.dt180g.project.support.AppConfig;

/**
 * The base component class for all abilities in the game.
 * Abilities represent actions that characters can perform during combat, such as attacking or healing.
 * Each ability has an action point cost and an energy cost associated with it.
 * Abilities can be performed by invoking the characterAttack method in the {@link GameEngine} class.
 * @author Daniel Jönsson
 * @see AppConfig
 * @see GameEngine
 * @version 1.0
 */
public abstract class BaseAbility {
    private final int actionPointCost;
    private final int energyCost;

    /**
     * Constructs a new BaseAbility with the specified action point cost and energy cost.
     *
     * @param actionPointCost the action point cost required to perform the ability
     * @param energyCost the energy cost required to perform the ability
     */
    protected BaseAbility(int actionPointCost, int energyCost){
        this.actionPointCost = actionPointCost;
        this.energyCost = energyCost;
    }

    /**
     * Performs the ability by invoking the characterAttack method in the GameEngine.
     *
     * @param ability the name of the ability to be performed
     * @param targets the number of targets affected by the ability
     * @param damageOrHeal the amount of damage or healing caused by the ability
     * @param shouldTargetEnemy determines if the ability should target enemies or allies
     * @return true if the ability was successfully performed, false otherwise
     */
    protected boolean performAbility(String ability, int targets, int damageOrHeal, boolean shouldTargetEnemy){
        String formatAbility = String.format("%s (-%d AP, -%d %s)", ability, actionPointCost, energyCost, AppConfig.TRAIT_ENERGY);
        return GameEngine.INSTANCE.characterAttack(
                new AbilityInfo(formatAbility, targets, damageOrHeal, shouldTargetEnemy, isMagic(), isHeal()));
    }

    /**
     * Returns the action point cost required to perform the ability.
     *
     * @return the action point cost
     */
    public int getActionPointCost() {
        return actionPointCost;
    }

    /**
     * Returns the energy cost required to perform the ability.
     *
     * @return the energy cost
     */
    public int getEnergyCost() {
        return energyCost;
    }

    /**
     * Checks if the ability is a magic ability.
     *
     * @return true if the ability is a magic ability, false otherwise
     */
    public abstract boolean isMagic();

    /**
     * Checks if the ability is a healing ability.
     *
     * @return true if the ability is a healing ability, false otherwise
     */
    public abstract boolean isHeal();

    /**
     * Returns the amount of targets affected by the ability.
     *
     * @return the amount of targets
     */
    public abstract int getAmountOfTargets();

    /**
     * Executes the ability by performing the necessary actions.
     *
     * @param baseAtk the base attack value of the character using the ability
     * @param shouldTargetEnemy determines if the ability should target enemies or allies
     * @return true if the ability was successfully executed, false otherwise
     */
    public abstract boolean execute(int baseAtk, boolean shouldTargetEnemy);
}
