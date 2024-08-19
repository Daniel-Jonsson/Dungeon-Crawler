package com.dt180g.project.characters.heroes;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;
import com.dt180g.project.support.AppConfig;

import java.util.*;

/**
 * The {@code BaseHero} class is an abstract class representing a base hero character in the game.
 * It extends the {@link BaseCharacter} class and provides additional functionality specific to heroes.
 * Subclasses of {@code BaseHero} can be created to define specific types of heroes in the game.
 * The idea of this class is to give the hero relevant armors/equipment/stats by the use of
 * {@link  CharacterStats}, {@link CharacterEquipment} and their relevant methods/functions.
 * @see BaseCharacter
 * @see CharacterStats
 * @see CharacterEquipment
 * @author Daniel Jönsson
 */
public abstract class BaseHero extends BaseCharacter {

    /**Instance field variables*/
    private final String characterName;

    /**
     * Constructs a {@code BaseHero} object with the specified character name and initial stats.
     *
     * @param characterName the name of the hero
     * @param stats         a list of initial stats for the hero
     */
    protected BaseHero(String characterName, List<Integer> stats){
        super(new CharacterStats(stats));
        this.characterName = characterName;
    }


    /**
     * Equips the hero with armor and weapons based on the specified hero class.
     * Uses the {@link CharacterEquipment} and {@link CharacterStats} classes
     * to retrieve relevant information.
     * @param heroClass the class of the hero
     */
    protected void equipHero(Class<?> heroClass){
        CharacterEquipment characterEquipment = super.getEquipment();
        CharacterStats characterStats = super.getCharacterStats();

        equipArmor(characterEquipment, heroClass);
        equipWeapons(characterEquipment, heroClass);

        adjustStats(characterStats, characterEquipment);

    }

    /**
     * Equips the hero with relevant armor pieces based on the specified hero class.
     *
     * @param characterEquipment the character's equipment
     * @param heroClass          the class of the hero
     */
    private void equipArmor(CharacterEquipment characterEquipment, Class<?> heroClass) {
        List<String> types = Arrays.asList(
                AppConfig.ARMOR_HEAD,
                AppConfig.ARMOR_CHEST,
                AppConfig.ARMOR_HANDS,
                AppConfig.ARMOR_LEGS,
                AppConfig.ARMOR_FEET
        );
        types.forEach(type -> characterEquipment.addArmorPiece(type,
                GearManager.INSTANCE.getRandomArmorOfType(type, heroClass)));
    }

    /**
     * Equips the hero with relevant weapons based on the specified hero class.
     *
     * @param characterEquipment the character's equipment
     * @param heroClass          the class of the hero
     */
    private void equipWeapons(CharacterEquipment characterEquipment, Class<?> heroClass) {
        while (characterEquipment.amountOfEmptyWeaponSlots() != 0) {
            Weapon weapon = GearManager.INSTANCE.getRandomWeapon(heroClass);
            characterEquipment.addWeapon(weapon);
        }
    }

    /**
     * Adjusts the hero's stats based on the equipped armor and weapons.
     *
     * @param characterStats      the character's stats
     * @param characterEquipment  the character's equipment
     */
    private void adjustStats(CharacterStats characterStats, CharacterEquipment characterEquipment) {
        characterEquipment.getWeapons()
                .forEach(weapon -> characterStats.adjustStatStaticModifier(weapon.getStat().getStatName(),
                        weapon.getStat().getBaseValue()));
        characterEquipment.getArmorPieces()
                .forEach(armor -> characterStats.adjustStatStaticModifier(armor.getStat().getStatName(),
                        armor.getStat().getBaseValue()));

    }

    /**
     * Resets the hero's stats, such as action points, hit points, and energy level.
     */
    public void resetHeroStats(){
        super.getCharacterStats().resetActionPoints();
        super.getCharacterStats().resetHitPoints();
        super.getCharacterStats().resetEnergyLevel();
    }

    /**{@inheritDoc}*/
    @Override
    public String getCharacterName(){
        return characterName;
    }

    /**{@inheritDoc}*/
    @Override
    public void doTurn() {
        ActivityLogger.INSTANCE.logTurnInfo(super.getTurnInformation(AppConfig.CHARACTER_TYPE_HERO));
        super.executeActions(true);
    }
}
