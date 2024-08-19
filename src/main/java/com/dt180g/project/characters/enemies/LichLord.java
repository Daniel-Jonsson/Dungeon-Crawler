package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code LichLord} class represents a specific type of enemy character called Lich Lord.
 * It extends the BaseEnemy class and defines the behavior and abilities specific to the Lich Lord enemy.
 * The Lich Lord enemy is equipped with certain abilities and weapons upon instantiation.
 * The Lich Lord acts as the enemy boss in the game.
 *
 * @author Daniel Jönsson
 * @see AppConfig
 * @see com.dt180g.project.characters.BaseCharacter
 * @see BaseEnemy
 * @version 1.0
 */
public class LichLord extends BaseEnemy{

    /**
     * Constructs a new Lich Lord enemy character.
     * It sets the character name and initializes the character stats based on predefined values.
     * Additionally, it adds specific abilities and equips the enemy with weapons.
     */
    public LichLord(){
        super(AppConfig.ENEMY_LICH_LORD, AppConfig.ATTRIBUTE_VALUES_LICH_LORD);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new HeavyAttack(),
                new Whirlwind(),
                new FocusedHeal(),
                new ElementalBolt(AppConfig.ELEMENT_FIRE),
                new ElementalBlast(AppConfig.ELEMENT_FIRE),
                new WeaponAttack()
        ));

        // Equip weapons
        super.equipEnemy(Arrays.asList(
                AppConfig.WEAPON_AXE,
                AppConfig.WEAPON_SWORD,
                AppConfig.WEAPON_SHIELD
        ));
    }
}
