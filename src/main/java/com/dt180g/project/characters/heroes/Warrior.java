package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.HeavyAttack;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.abilities.Whirlwind;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code Warrior} class represents a Warrior hero character in the game.
 * It extends the {@link BaseHero} class and provides specific abilities and equipment for the Warrior class.
 *
 * <p>The Warrior class inherits the base functionality from the {@link BaseHero} class,
 * and additional abilities and equipment are added to specialize the Warrior character.
 *
 * @author Daniel Jönsson
 * @see BaseHero
 * @version 1.0
 */
public class Warrior extends BaseHero{

    /**
     * Constructs a {@code Warrior} object with the specified character name.
     *
     * @param warriorName the name of the Warrior character
     */
    public Warrior(String warriorName){
        super(String.format("%s The %s", warriorName, AppConfig.HERO_WARRIOR),
                AppConfig.ATTRIBUTE_VALUES_WARRIOR_HERO);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new HeavyAttack(),
                new Whirlwind(),
                new WeaponAttack()
        ));

        // Equip armor/weapon
        super.equipHero(this.getClass());
    }

}
