package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The {@code StatsManager} class is responsible for managing the stats in the game.
 * It provides methods to retrieve random attribute names, trait names, and combat stat names.
 *
 * <p>The {@code StatsManager} follows the singleton design pattern to ensure only one instance
 * of the class exists throughout the game.
 */
public class StatsManager {
    /**Instance field variables*/
    public static final StatsManager INSTANCE = new StatsManager();
    private final List<String> attributeNames;
    private final List<String> traitNames;
    private final List<String> combatStatNames;

    /**
     * Private constructor to prevent external instantiation of the {@code StatsManager} class.
     * <p>Initializes the attributeNames, traitNames, and combatStatNames lists with predefined values obtained through
     * {@link AppConfig}.
     */
    private StatsManager (){
        this.attributeNames = new ArrayList<>(Arrays.asList(
                AppConfig.ATTRIBUTE_STRENGTH,
                AppConfig.ATTRIBUTE_DEXTERITY,
                AppConfig.ATTRIBUTE_INTELLIGENCE,
                AppConfig.ATTRIBUTE_WILLPOWER
        ));

        this.traitNames = new ArrayList<>(Arrays.asList(
                AppConfig.TRAIT_VITALITY,
                AppConfig.TRAIT_ENERGY,
                AppConfig.TRAIT_ATTACK_RATE,
                AppConfig.TRAIT_DEFENSE_RATE
        ));

        this.combatStatNames = new ArrayList<>(Arrays.asList(
                AppConfig.COMBAT_STAT_PHYSICAL_POWER,
                AppConfig.COMBAT_STAT_ACTION_POINTS,
                AppConfig.COMBAT_STAT_MAGIC_POWER,
                AppConfig.COMBAT_STAT_HEALING_POWER
        ));

    }

    /**
     * Returns a randomly selected attribute name from the available attribute names.
     *
     * @return a random attribute name
     */
    public String getRandomAttributeName(){
        return getAttributeNames().get(Randomizer.INSTANCE.getRandomValue(getAttributeNames().size() - 1));
    }

    /**
     * Returns a randomly selected trait name from the available trait names.
     *
     * @return a random trait name
     */
    public String getRandomTraitName(){
        return getTraitNames().get(Randomizer.INSTANCE.getRandomValue(getTraitNames().size() - 1));
    }

    /**
     * Returns a list of all attribute names.
     *
     * @return a list of attribute names
     */
    public List<String> getAttributeNames(){
        return attributeNames;
    }

    /**
     * Returns a list of all trait names.
     *
     * @return a list of trait names
     */
    public List<String> getTraitNames(){
        return traitNames;
    }

    /**
     * Returns a list of all combat stat names.
     *
     * @return a list of combat stat names
     */
    public List<String> getCombatStatNames(){
        return combatStatNames;
    }
}
