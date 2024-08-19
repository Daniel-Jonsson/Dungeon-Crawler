package com.dt180g.project.gear;

import com.dt180g.project.stats.Attribute;
import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.stats.StatsManager;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.Map;

/**
 * The {@code Weapon} class represents a weapon gear item in the game. It extends the {@link BaseGear} class
 * and provides methods to retrieve information about the weapon.
 * @author Daniel Jönsson
 * @see BaseGear
 * @see Attribute
 * @see BaseStat
 * @see StatsManager
 * @see AppConfig
 * @see Randomizer
 * @version 1.0
 */
public class Weapon extends BaseGear{

    /**Instance field variables*/
    private final int damage;
    private final String wield;
    private final Attribute attribute;

    /**
     * Constructs a new {@code Weapon} object with the provided weapon details.
     *
     * @param weaponDetail A map containing the weapon details, including type, name, restriction, damage, and wield.
     */
    public Weapon(Map<String, String> weaponDetail){
        super(weaponDetail.get("type"), weaponDetail.get("name"), weaponDetail.get("restriction"));
        this.damage = Integer.parseInt(weaponDetail.get("damage"));
        this.wield = weaponDetail.get("wield");
        this.attribute = new Attribute(StatsManager.INSTANCE.getRandomAttributeName(),
                Randomizer.INSTANCE.getRandomValue(1, AppConfig.WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND));
    }

    /**
     * Gets the damage value of the weapon.
     *
     * @return The damage value of the weapon.
     */
    public int getDamage(){
        return this.damage;
    }

    /**
     * Gets the wield type of the weapon.
     *
     * @return The wield type of the weapon.
     */
    public String getWield(){
        return this.wield;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BaseStat getStat(){
        return this.attribute;
    }

    /**
     * Checks if the weapon is two-handed.
     *
     * @return {@code true} if the weapon is two-handed, {@code false} otherwise.
     */
    public boolean isTwoHanded(){
        return getWield().contains("Two Handed");
    }

    /**
     * {@inheritDoc}
     * adds stat name with of between.
     */
    @Override
    public String toString(){
        return super.toString() + " of " + getStat().getStatName();
    }
}
