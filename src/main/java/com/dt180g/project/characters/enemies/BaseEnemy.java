package com.dt180g.project.characters.enemies;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.CharacterEquipment;
import com.dt180g.project.characters.CharacterStats;
import com.dt180g.project.gear.GearManager;
import com.dt180g.project.gear.Weapon;
import com.dt180g.project.support.ActivityLogger;
import com.dt180g.project.support.AppConfig;

import java.util.List;

/**
 * An abstract class representing a base enemy character.
 * Extends the BaseCharacter class and provides methods for equipping weapons, adjusting stats,
 * and performing enemy-specific actions. The {@code BaseEnemy} class acts as the base component to all enemies.
 */
public abstract class BaseEnemy extends BaseCharacter {

    /**Instance field variable*/
    private final String characterName;

    /**
     * Constructs a new instance of the BaseEnemy class with the given character name and stat values.
     *
     * @param characterName the name of the enemy character
     * @param stats         the list of stat values
     */
    protected BaseEnemy(String characterName, List<Integer> stats) {
        super(new CharacterStats(stats));
        this.characterName = characterName;
    }

    /**
     * Equips weapons and adjusts stats for the enemy character.
     *
     * @param acceptableTypes the list of acceptable weapon types
     */
    protected void equipEnemy(List<String> acceptableTypes){
        CharacterEquipment characterEquipment = super.getEquipment();
        CharacterStats characterStats = super.getCharacterStats();

        equipWeapons(characterEquipment, acceptableTypes);

        adjustStats(characterStats, characterEquipment);
    }

    /**
     * Equips weapons for the enemy character.
     *
     * @param characterEquipment the character's equipment object.
     * @param acceptableTypes    the list of acceptable weapon types
     */
    private void equipWeapons(CharacterEquipment characterEquipment, List<String> acceptableTypes) {
        while (characterEquipment.amountOfEmptyWeaponSlots() != 0) {
            Weapon weapon = GearManager.INSTANCE.getRandomWeapon(acceptableTypes);
            characterEquipment.addWeapon(weapon);
        }
    }

    /**
     * Adjusts the character's stats based on equipped weapons.
     *
     * @param characterStats     the character's stats
     * @param characterEquipment the character's equipment
     */
    private void adjustStats(CharacterStats characterStats, CharacterEquipment characterEquipment) {

        for (Weapon weapon : characterEquipment.getWeapons()) {
            characterStats.adjustStatStaticModifier(weapon.getStat().getStatName(), weapon.getStat().getBaseValue());
        }
        if (this.getClass().equals(LichLord.class)){
            characterStats.adjustStatStaticModifier(AppConfig.TRAIT_VITALITY,
                    characterStats.getStatValue(AppConfig.TRAIT_VITALITY) * AppConfig.BOSS_HEALTH_MULTIPLIER);
        }
    }


    /**{@inheritDoc}*/
    public String getCharacterName(){
        return this.characterName;
    }

    /**{@inheritDoc}*/
    @Override
    public void doTurn() {
        ActivityLogger.INSTANCE.logTurnInfo(super.getTurnInformation(AppConfig.CHARACTER_TYPE_ENEMY));
        super.executeActions(false);
    }
}
