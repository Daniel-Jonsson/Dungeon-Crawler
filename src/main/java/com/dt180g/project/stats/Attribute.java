package com.dt180g.project.stats;

/**
 * The {@code Attribute} class represents an attribute stat in the game.
 * It extends the {@link BaseStat} class and provides functionality specific to attributes.
 *
 * <p>An attribute stat represents a fundamental characteristic of a character,
 * such as strength, dexterity, intelligence, or vitality.
 *
 * @author Daniel Jönsson
 * @see BaseStat
 * @version 1.0
 */
public class Attribute extends BaseStat{

    /**
     * Constructs an {@code Attribute} object with the specified attribute name and base value.
     *
     * @param statName  the name of the attribute
     * @param baseValue the base value of the attribute
     */
    public Attribute(String statName, int baseValue) {
        super(statName, baseValue);
    }
}
