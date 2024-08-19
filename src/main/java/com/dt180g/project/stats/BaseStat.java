package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;

/**
 * The {@code BaseStat} class is an abstract class representing a base stat in the game.
 * It provides common functionality and attributes for different types of stats.
 * Subclasses of {@code BaseStat} can be created to define specific types of stats in the game.
 * The BaseStat works as a base component for all stats in the game.
 *
 * <p>A stat represents a numerical value that defines a specific aspect or characteristic of a character.
 * It can include Strength, action points, hit points, energy level, and more.
 *
 * @author Daniel Jönsson
 * @see Attribute
 * @see CombatStat
 * @version 1.0
 */
public abstract class BaseStat{

    /**Instance field variables*/
    private final String statName;
    private final int baseValue;
    private int staticModifier;
    private int dynamicModifier;

    /**
     * Constructs a {@code BaseStat} object with the specified stat name and base value.
     *
     * @param statName  the name of the stat
     * @param baseValue the base value of the stat
     */
    protected BaseStat(String statName, int baseValue){
        this.statName = statName;
        this.baseValue = baseValue;
        this.dynamicModifier = 0;
        this.staticModifier = 0;
    }

    /**
     * Returns the name of the stat.
     *
     * @return the name of the stat
     */
    public String getStatName(){
        return statName;
    }

    /**
     * Returns the base value of the stat.
     *
     * @return the base value of the stat
     */
    public int getBaseValue(){
        return baseValue;
    }

    /**
     * Returns the modified value of the stat, taking into account both static and dynamic modifiers.
     * <p>Calls {@link #getBaseValue()} since {@link CombatStat} overrides the original baseValue.
     *
     * @return the modified value of the stat
     */
    public int getModifiedValue(){
        return getBaseValue() + getStaticModifier() + dynamicModifier;
    }

    /**
     * Returns the total modifier of the stat, which is the sum of the static and dynamic modifiers.
     *
     * @return the total modifier of the stat
     */
    public int getTotalModifier(){
        return dynamicModifier + getStaticModifier();
    }


    /**
     * Returns the static modifier of the stat.
     *
     * @return the static modifier of the stat
     */
    public int getStaticModifier(){
        return staticModifier;
    }

    /**
     * Adjusts the static modifier of the stat by the specified static adjustment value.
     *
     * @param staticAdjustment the value by which to adjust the static modifier
     */
    public void adjustStaticModifier(int staticAdjustment){
        staticModifier += staticAdjustment;
    }

    /**
     * Adjusts the dynamic modifier of the stat by the specified dynamic adjustment value.
     *
     * @param dynamicAdjustment the value by which to adjust the dynamic modifier
     */
    public void adjustDynamicModifier(int dynamicAdjustment){
        dynamicModifier += dynamicAdjustment;
    }

    /**
     * Resets the dynamic modifier of the stat to zero.
     */
    public void resetDynamicModifier(){
        dynamicModifier = 0;
    }

    /**
     * Returns a string representation of the stat, including its name, modified value, and total modifier.
     * The string representation is formatted with ANSI color codes for visual enhancement.
     *
     * @return a string representation of the stat
     */
    @Override
    public String toString(){
        return String.format("%s%s %s%d %s+%d%s",
                AppConfig.ANSI_GREEN,
                getStatName(),
                AppConfig.ANSI_BLUE,
                getModifiedValue(),
                AppConfig.ANSI_YELLOW,
                getTotalModifier(),
                AppConfig.ANSI_RESET);
    }
}
