package com.dt180g.project.characters.enemies;

import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code SkeletonArcher} class represents a specific type of enemy character called Skeleton Archer.
 * It extends the {@code BaseEnemy} class and defines the behavior and abilities specific to the Skeleton Archer enemy.
 * The Skeleton Archer enemy is equipped with certain abilities and weapons upon instantiation.
 */
public class SkeletonArcher extends BaseEnemy{

    /**
     * Constructs a new Skeleton Archer enemy character.
     * It sets the character name with a sequence number and initializes the character stats based on predefined values.
     * Additionally, it adds specific abilities and equips the enemy with weapons.
     *
     * @param sequenceNum The sequence number of the Skeleton Archer (e.g. 2).
     */
    public SkeletonArcher(int sequenceNum) {
        super(AppConfig.ENEMY_SKELETON_ARCHER + " " + sequenceNum, AppConfig.ATTRIBUTE_VALUES_SKELETON_ARCHER);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new FocusedShot(),
                new SprayOfArrows(),
                new WeaponAttack()
        ));

        // Equip weapons
        super.equipEnemy(Arrays.asList(
                AppConfig.WEAPON_BOW,
                AppConfig.WEAPON_CROSSBOW
        ));
    }
}
