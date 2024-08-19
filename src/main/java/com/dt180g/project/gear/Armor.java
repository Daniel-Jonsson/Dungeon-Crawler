package com.dt180g.project.gear;

import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.stats.Trait;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

/**
 * The {@code Armor} class represents an armor gear item in the game. It extends the {@link BaseGear} class
 * and provides methods to retrieve information about the armor.
 * @author Daniel Jönsson
 * @see BaseGear
 * @see BaseStat
 * @see StatsManager
 * @see Trait
 * @see AppConfig
 * @see Randomizer
 * @version 1.0
 */
public class Armor extends BaseGear{

    /**Instance field variables*/
    private final int protection;

    private final String material;

    private final Trait trait;

    /**
     * Constructs a new {@code Armor} object with the provided armor details.
     *
     * @param armorDetail A map containing the armor details, including type, name, restriction, material, and protection.
     */
    public Armor(Map<String, String> armorDetail) {
        super(armorDetail.get("type"), armorDetail.get("name"), armorDetail.get("restriction"));
        this.material = armorDetail.get("material");
        this.protection = Integer.parseInt(armorDetail.get("protection"));
        this.trait = new Trait(StatsManager.INSTANCE.getRandomTraitName(),
                Randomizer.INSTANCE.getRandomValue(1, AppConfig.ARMOR_STAT_VALUE_UPPER_BOUND - 1));
    }

    /**
     * Gets the protection value of the armor.
     *
     * @return The protection value of the armor.
     */
    public int getProtection(){
        return this.protection;
    }

    /**
     * Gets the material of the armor.
     *
     * @return The material of the armor.
     */
    public String getMaterial(){
        return this.material;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseStat getStat(){
        return this.trait;
    }

    /**
     * {@inheritDoc}
     * adds stat name with "of" between.
     */
    @Override
    public String toString(){
        return super.toString() + " of " + getStat().getStatName();
    }

}
