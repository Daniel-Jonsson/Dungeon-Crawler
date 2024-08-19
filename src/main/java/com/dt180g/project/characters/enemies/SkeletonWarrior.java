package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code SkeletonWarrior} class represents a specific type of enemy character called Skeleton Warrior.
 * It extends the {@code BaseEnemy} class and defines the behavior and abilities specific to the Skeleton Warrior enemy.
 * The Skeleton Warrior enemy is equipped with certain abilities and weapons upon instantiation.
 */
public class SkeletonWarrior extends BaseEnemy{

    /**
     * Constructs a new Skeleton Warrior enemy character.
     * It sets the character name with a sequence number and initializes the character stats based on predefined values.
     * Additionally, it adds specific abilities and equips the enemy with weapons.
     *
     * @param sequenceNum The sequence number of the Skeleton Warrior (e.g. 2).
     */
    public SkeletonWarrior(int sequenceNum) {
        super(AppConfig.ENEMY_SKELETON_WARRIOR + " " + sequenceNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_WARRIOR);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new HeavyAttack(),
                new Whirlwind(),
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
