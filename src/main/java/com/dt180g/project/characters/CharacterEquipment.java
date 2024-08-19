package com.dt180g.project.characters;

import com.dt180g.project.gear.Armor;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;


/**
 * The {@code CharacterEquipment} class represents the equipment of a {@link BaseCharacter}, including weapons and armor pieces.
 * It provides methods to manage and retrieve information about the character's equipment.
 * This class make use of default constructor.
 * @author Daniel Jönsson
 * @see Weapon
 * @see Armor
 * @see AppConfig
 * @see IOHelper
 * @version 1.0
 */
public class CharacterEquipment {

    /** Instance field variables. */
    private final List<Weapon> weapons = new ArrayList<>();
    private final Map<String, Armor> armorPieces = new HashMap<>();

    /**
     * Gets the list of weapons.
     * @return  List of weapons.
     */
    public List<Weapon> getWeapons(){
        return this.weapons;
    }


    /**
     * Gets the list of armor pieces.
     * @return  List of armor pieces.
     */
    public List<Armor> getArmorPieces(){
        return armorPieces.values().stream().toList();
    }

    /**
     * Calculate the sum of each weapons' damage.
     * Uses Stream API methods {@link java.util.stream.Stream#mapToInt(ToIntFunction)}
     * and {@link IntStream#sum()} to sum each weapon damage.
     * @return  Sum of weapons damage
     */
    public int getTotalWeaponDamage(){
        return getWeapons().stream()
                .mapToInt(Weapon::getDamage)
                .sum();
    }

    /**
     * Calculate the sum of each armors' protection.
     * Uses Stream API methods {@link java.util.stream.Stream#mapToInt(ToIntFunction)}
     * and {@link IntStream#sum()} to sum each armor protection.
     * @return  The sum of each armor protection.
     */
    public int getTotalArmorProtection(){
        return getArmorPieces().stream()
                .mapToInt(Armor::getProtection)
                .sum();
    }

    /**
     * Checks the amount of empty weapon slots.
     * Gets list of weapon from {@link #getWeapons()}
     * and uses Stream API to determine if each armor is two-handed.
     * @return  0 if list contains two-handed weapon, else 2 - list size.
     */
    public int amountOfEmptyWeaponSlots(){
        boolean hasTwoHandedWeapon = getWeapons().stream()
                .anyMatch(Weapon::isTwoHanded);
        return hasTwoHandedWeapon ? 0 : 2 - getWeapons().size();
    }

    /**
     * Checks the amount of empty armor slots.
     * @return  the amount of empty slots.
     */
    public int amountOfEmptyArmorSlots(){
        return 5 - getArmorPieces().size();
    }

    /**
     * Adds weapons to the instance field variable {@link #weapons}.
     * @param weapon    The {@code Weapon} object.
     * @return          true if the Weapon object could be equipped, else false.
     */
    public boolean addWeapon(Weapon weapon){
        if (amountOfEmptyWeaponSlots() == 2 && weapon.isTwoHanded() || amountOfEmptyWeaponSlots() != 0 && !weapon.isTwoHanded()){
            getWeapons().add(weapon);
            return true;
        }
        return false;
    }

    /**
     * Adds armor pieces to the instance field variable {@link #armorPieces}.
     * @param type  The type of the armor (e.g. Chest).
     * @param armor The {@code Armor} object.
     * @return      true if armor could be equipped, else false.
     */
    public boolean addArmorPiece(String type, Armor armor){
        if (amountOfEmptyArmorSlots() != 0 && !armorPieces.containsKey(type)){
            armorPieces.put(type, armor);
            return true;
        }
        return false;
    }

    /**
     * Adds weapon information to the character's equipment list.
     *
     * @param characterEquipmentInfo    The list to store the character's equipment information.
     */
    private void addWeaponInfo(List<List<String>> characterEquipmentInfo) {
        characterEquipmentInfo.addAll(getWeapons().stream()
                .map(weapon -> addEquipmentInfo(weapon.getType().toUpperCase(), weapon.getWield(), "Damage", weapon.getDamage(),
                        weapon.toString(), weapon.getStat().getBaseValue()))
                .toList());
    }

    /**
     * Adds armor information to the character's equipment list.
     *
     * @param characterEquipmentInfo    The list to store the character's equipment information.
     */
    private void addArmorInfo(List<List<String>> characterEquipmentInfo) {
        characterEquipmentInfo.addAll(getArmorPieces().stream()
                .map(armor -> addEquipmentInfo(armor.getType().toUpperCase(), armor.getMaterial(), "Protection",
                        armor.getProtection(), armor.toString(), armor.getStat().getBaseValue()))
                .toList());
    }

    /**
     * Adds equipment information to the sublist.
     *
     * @param type           The type of the equipment.
     * @param wieldMaterial  The material or wield of the equipment.
     * @param protectionDamage       The name of the equipment's protection/damage.
     * @param statValue      The value of the equipment's stat.
     * @param equipmentString The string representation of the equipment.
     * @param modifierValue  The value of the equipment's modifier.
     */
    private List<String> addEquipmentInfo(String type, String wieldMaterial,
                                  String protectionDamage, int statValue, String equipmentString, int modifierValue) {
        List<String> subList = new ArrayList<>();
        subList.add(String.format("[%s]", type));
        subList.add("|");
        subList.add(String.format("%s%s%s", AppConfig.ANSI_PURPLE, wieldMaterial, AppConfig.ANSI_RESET));
        subList.add("|");
        subList.add(String.format("%s%s%s", AppConfig.ANSI_RED, protectionDamage, AppConfig.ANSI_RESET));
        subList.add(String.format("%s+%d%s", AppConfig.ANSI_GREEN, statValue, AppConfig.ANSI_RESET));
        subList.add("|");
        subList.add(String.format("%s%s%s", AppConfig.ANSI_CYAN, equipmentString, AppConfig.ANSI_RESET));
        subList.add(String.format("%s+%d%s", AppConfig.ANSI_YELLOW, modifierValue, AppConfig.ANSI_RESET));
        return subList;
    }

    /**
     * Returns a string representation of the character's equipment formatted as table.
     * Uses the {@link IOHelper#formatAsTable(List)} method to format a 2d list of strings.
     * @return The string representation of the character's equipment as table.
     */
    @Override
    public String toString() {
        List<List<String>> characterEquipmentInfo = new ArrayList<>();
        addWeaponInfo(characterEquipmentInfo);
        addArmorInfo(characterEquipmentInfo);
        return String.format("%sEQUIPMENT%n%s%s", AppConfig.ANSI_BLUE, AppConfig.ANSI_RESET,
                IOHelper.formatAsTable(characterEquipmentInfo));
    }
}
