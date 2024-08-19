package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.*;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code SkeletonMage} class represents a specific type of enemy character called Skeleton Mage.
 * It extends the {@code BaseEnemy} class and defines the behavior and abilities specific to the Skeleton Mage enemy.
 * The Skeleton Mage enemy is equipped with certain abilities and weapons upon instantiation.
 */
public class SkeletonMage extends BaseEnemy{

    /**
     * Constructs a new Skeleton Mage enemy character.
     * It sets the character name with a sequence number and initializes the character stats based on predefined values.
     * Additionally, it adds specific abilities and equips the enemy with weapons.
     *
     * @param sequenceNum The sequence number of the Skeleton Mage (e.g. 2).
     */
    public SkeletonMage(int sequenceNum){
        super(AppConfig.ENEMY_SKELETON_MAGE + " " + sequenceNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_MAGE);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new ElementalBlast(AppConfig.ELEMENT_ICE),
                new ElementalBlast(AppConfig.ELEMENT_FIRE),
                new ElementalBlast(AppConfig.ELEMENT_AIR),
                new ElementalBolt(AppConfig.ELEMENT_ICE),
                new ElementalBolt(AppConfig.ELEMENT_FIRE),
                new ElementalBolt(AppConfig.ELEMENT_AIR),
                new WeaponAttack()
        ));

        // Equip weapons
        super.equipEnemy(Arrays.asList(
                AppConfig.WEAPON_STAFF,
                AppConfig.WEAPON_WAND
        ));
    }
}
