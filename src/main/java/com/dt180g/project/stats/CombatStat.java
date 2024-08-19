package com.dt180g.project.stats;


import com.dt180g.project.support.AppConfig;

/**
 * The {@code CombatStat} class represents a combat-related stat in the game.
 * It extends the {@link BaseStat} class and provides functionality specific to combat stats.
 * Combat stats rely on attribute and trait stats to calculate their base value.
 *
 * <p>Combat stats are numerical values that affect a character's combat performance,
 * such as physical power, magic power, action points, and healing power.
 *
 * @author Daniel Jönsson
 * @see BaseStat
 * @version 1.0
 */
public class CombatStat extends BaseStat{

    /**Instance field variables*/
    private final BaseStat attributeReliance;
    private final BaseStat traitReliance;


    /**
     * Constructs a {@code CombatStat} object with the specified stat name,
     * attribute reliance, and trait reliance.
     *
     * @param statName          the name of the combat stat
     * @param attributeReliance the attribute stat this combat stat relies on
     * @param traitReliance     the trait stat this combat stat relies on (always Attack Rate)
     */
    public CombatStat(String statName, BaseStat attributeReliance, BaseStat traitReliance) {
        super(statName, 0);
        this.attributeReliance = attributeReliance;
        this.traitReliance = traitReliance;
    }

    /**
     * Returns the new base value of the combat stat.
     * The base value is calculated based on the modified values of the attribute and trait stats
     * that the combat stat relies on, multiplied by a combat stat multiplier from {@link AppConfig}.
     *
     * @return the base value of the combat stat
     */
    @Override
    public int getBaseValue(){
        double attributevalue = attributeReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER;
        double traitValue = traitReliance.getModifiedValue() * AppConfig.COMBAT_STAT_MULTIPLIER;
        return (int) Math.round(attributevalue + traitValue);
    }
}
