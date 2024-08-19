package com.dt180g.project.gear;

import com.dt180g.project.support.IOHelper;
import com.dt180g.project.support.Randomizer;

import java.util.*;

/**
 * The {@code GearManager} class manages the collection of weapons and armor pieces available in the game.
 * It provides methods for retrieving random weapons and armor pieces based on various criteria.
 * The gear information is retrieved from their respective json file with the help of {@link IOHelper} class.
 * @author Daniel Jönsson
 * @see IOHelper
 * @see Randomizer
 * @version 1.0
 */
public class GearManager {
    /**Instance field variables*/
    public static final GearManager INSTANCE = new GearManager();
    private final Map<String, List<Weapon>> weapons = new HashMap<>();
    private final Map<String, List<Armor>> armorPieces = new HashMap<>();

    /**
     * Private constructor to enforce singleton pattern.
     * Reads weapon and armor data from files and populates the corresponding maps.
     */
    private GearManager(){
        List<Map<String, String>> weaponInfo = IOHelper.readFromFile("gear_weapons.json");
        List<Map<String, String>> armorInfo = IOHelper.readFromFile("gear_armor.json");

        for (Map<String, String> weaponDetails : weaponInfo){
            Weapon weapon = new Weapon(weaponDetails);
            String type = weaponDetails.get("type");
            if(this.weapons.containsKey(type)){
                this.weapons.get(type).add(weapon);
            } else {
                List<Weapon> weaponList = new ArrayList<>();
                weaponList.add(weapon);
                this.weapons.put(type, weaponList);
            }

        }
        for (Map<String, String> armorDetails : armorInfo){
            Armor armor = new Armor(armorDetails);
            String type = armorDetails.get("type");

            if(this.armorPieces.containsKey(type)){
                this.armorPieces.get(type).add(armor);
            } else {
                List<Armor> armorList = new ArrayList<>();
                armorList.add(armor);
                this.armorPieces.put(type, armorList);
            }
        }
    }

    /**
     * Returns a mapping of armor types to lists of armor pieces.
     *
     * @return A mapping of armor types to lists of armor pieces.
     */
    public Map<String, List<Armor>> getAllMappedArmorPieces() {
        return this.armorPieces;
    }

    /**
     * Returns a mapping of weapon types to lists of weapons.
     *
     * @return A mapping of weapon types to lists of weapons.
     */
    public Map<String, List<Weapon>> getAllMappedWeapons(){
        return this.weapons;
    }

    /**
     * Returns a list of weapons of the specified type.
     *
     * @param weaponType The type of weapons to be retrieved.
     * @return A list of weapons of the specified type.
     */
    public List<Weapon> getWeaponsOfType(String weaponType){
        return getAllMappedWeapons().get(weaponType);
    }

    /**
     * Returns a random weapon that satisfies the given class restriction.
     * Uses Stream API to effectively retrieve correct weapons.
     *
     * @param restrictionClass The class restriction for the weapon.
     * @return A random weapon that satisfies the class restriction.
     */
    public Weapon getRandomWeapon(Class<?> restrictionClass){
        List<Weapon> weaponOfType = getAllMappedWeapons().values().stream()
                .flatMap(Collection::stream)    // Flattens nested List to stream of <Weapons>
                .filter(weapon -> weapon.checkClassRestriction(restrictionClass))   // Filters weapons
                .toList();
        int randomIndex = Randomizer.INSTANCE.getRandomValue(weaponOfType.size() - 1);
        return weaponOfType.get(randomIndex);
    }

    /**
     * Returns a random weapon of the specified type.
     *
     * @param weaponType       The type of weapon to retrieve.
     * @return A random weapon of the specified type.
     */
    public Weapon getRandomWeapon(List<String> weaponType){
        List<Weapon> filteredWeapons = weaponType.stream()
                .map(this::getWeaponsOfType)    // Creates a nested List of weapons of correct type.
                .flatMap(Collection::stream)    // Flattens nested List to single stream of <Weapon>
                .toList();
        int randomIndex = Randomizer.INSTANCE.getRandomValue(filteredWeapons.size() - 1);
        return filteredWeapons.get(randomIndex);
    }

    /**
     * Returns a random one-handed weapon that satisfies the given class restriction.
     *
     * @param restrictionClass The class restriction for the weapon.
     * @return A random one-handed weapon that satisfies the class restriction.
     */
    public Weapon getRandomOneHandedWeapon(Class<?> restrictionClass){
        List<Weapon> validWeapons = getAllMappedWeapons().values().stream()
                .flatMap(Collection::stream)    // Flattens List<List<Weapon>> to a single stream of <Weapon>.
                .filter(weapon -> weapon.checkClassRestriction(restrictionClass) && !weapon.isTwoHanded())
                .toList();
        int randomIndex = Randomizer.INSTANCE.getRandomValue(validWeapons.size() -1);
        return validWeapons.get(randomIndex);
    }

    /**
     * Returns a random one-handed weapon of the specified type.
     *
     * @param weaponType       The type of weapon to retrieve.
     * @return A random one-handed weapon of the specified type.
     */
    public Weapon getRandomOneHandedWeapon(List<String> weaponType){
        List<Weapon> filteredWeapons = weaponType.stream()
                .map(this::getWeaponsOfType)    // Creates List<List<Weapon>>.
                .flatMap(Collection::stream)    // Flattens List to single stream of <Weapon>.
                .filter(weapon -> !weapon.isTwoHanded())    // Filter stream to obtain weapons that is not two-handed.
                .toList();  // Create new List with the desired weapons.

        int randomIndex = Randomizer.INSTANCE.getRandomValue(filteredWeapons.size() -1);
        return filteredWeapons.get(randomIndex);
    }

    /**
     * Returns a list of all armor pieces that satisfy the given class restriction.
     *
     * @param restrictionClass The class restriction for the armor pieces.
     * @return A list of all armor pieces that satisfy the class restriction.
     */
    public List<Armor> getAllArmorForRestriction(Class<?> restrictionClass){
        return getAllMappedArmorPieces().values().stream()
                .flatMap(Collection::stream)    //Flatten list of armor to a single stream.
                .filter(armorPiece -> armorPiece.checkClassRestriction(restrictionClass))
                .toList();
    }

    /**
     * Returns a random armor piece of the specified type that satisfies the given class restriction.
     *
     * @param armorType        The type of armor piece to retrieve.
     * @param restrictionClass The class restriction for the armor piece.
     * @return A random armor piece of the specified type that satisfies the class restriction.
     */
    public Armor getRandomArmorOfType(String armorType, Class<?> restrictionClass){
        List<Armor> matchingArmor = getAllArmorForRestriction(restrictionClass).stream()
                .filter(armor -> armor.getType().equalsIgnoreCase(armorType))   // Filter armor that equals armor type
                .toList();
        int randomIndex = Randomizer.INSTANCE.getRandomValue(matchingArmor.size() - 1);
        return matchingArmor.get(randomIndex);
    }
}
