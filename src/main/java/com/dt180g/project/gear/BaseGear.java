package com.dt180g.project.gear;


import com.dt180g.project.stats.BaseStat;
import com.dt180g.project.support.AppConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code BaseGear} class represents the base class for all gear items in the game and acts as the Base
 * Component to the {@link Armor} and {@link Weapon} classes.
 * It provides common functionality and properties for gear items and defines abstract methods to be implemented
 * by derivatives.
 * @author Daniel Jönsson
 * @see BaseStat
 *
 */
public abstract class BaseGear {
    /**Instance field variables*/
    private final String type;
    private final String gearName;
    private final List<Class<?>> classRestrictions;

    /**
     * Constructs a new {@code BaseGear} object with the provided gear details.
     *
     * @param type        The type of the gear (e.g. Shield).
     * @param gearName    The name of the gear (e.g. Large Shield/Medium Shield).
     * @param restriction A string representing the class restrictions for the gear (e.g. Cleric,Warrior).
     * @throws RuntimeException If the class is not found.
     */
    protected BaseGear(String type, String gearName, String restriction){
        this.type = type;
        this.gearName = gearName;
        String [] restrictions = restriction.split(",");
        List<Class<?>> classPlaceholder = new ArrayList<>();

        for (String className : restrictions){
            try {
                Class<?> cls = Class.forName("com.dt180g.project.characters.heroes." + className);
                classPlaceholder.add(cls);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(String.format("%sThere was a problem finding the class: %s%s",
                        AppConfig.ANSI_RED, className, AppConfig.ANSI_RESET));
            }
        }
        this.classRestrictions = classPlaceholder;
    }

    /**
     * Gets the type of the gear.
     *
     * @return The type of the gear.
     */
    public String getType(){
        return this.type;
    }

    /**
     * Gets the class restrictions for the gear.
     *
     * @return The list of class restrictions.
     */
    public List<Class<?>> getClassRestrictions(){
        return this.classRestrictions;
    }

    /**
     * Checks if the gear has a class restriction for the specified class.
     *
     * @param classRestriction The class to check against the gear's class restrictions.
     * @return {@code true} if the gear has a class restriction for the specified class, {@code false} otherwise.
     */
    public boolean checkClassRestriction(Class<?> classRestriction){
        return getClassRestrictions().stream()
                .anyMatch(c -> c.isAssignableFrom(classRestriction));
    }

    /**
     * Gets the stat of the gear.
     *
     * @return The stat of the gear (e.g. {@link com.dt180g.project.stats.Trait Trait}) for armor.
     */
    public abstract BaseStat getStat();

    /**
     * Returns a string representation of the gear name.
     *
     * @return The string representation of the gear name.
     */
    @Override
    public String toString(){
        return this.gearName;
    }
}
