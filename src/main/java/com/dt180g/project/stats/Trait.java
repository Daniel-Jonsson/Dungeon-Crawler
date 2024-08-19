package com.dt180g.project.stats;

/**
 * The {@code Trait} class represents a trait stat in the game.
 * It extends the {@link BaseStat} class and provides methods to manage and retrieve information about the trait.
 * Subclasses of {@code Trait} can be created to define specific types of traits in the game.
 *
 * @see BaseStat
 */
public class Trait extends BaseStat{

    /**
     * Constructs a {@code Trait} object with the specified stat name and base value.
     *
     * @param statName   the name of the trait stat
     * @param baseValue  the base value of the trait stat
     */
    public Trait(String statName, int baseValue) {
        super(statName, baseValue);
    }

}
