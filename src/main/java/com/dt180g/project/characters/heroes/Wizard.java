package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.ElementalBlast;
import com.dt180g.project.abilities.ElementalBolt;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code Wizard} class represents a Wizard hero character in the game.
 * It extends the {@link BaseHero} class and provides specific abilities and equipment for the Wizard class.
 *
 * <p>The Wizard class inherits the base functionality from the {@link BaseHero} class,
 * and additional abilities and equipment are added to specialize the Wizard character.
 *
 * @author Daniel Jönsson
 * @see BaseHero
 * @version 1.0
 */
public class Wizard extends BaseHero{

    /**
     * Constructs a {@code Wizard} object with the specified character name.
     *
     * @param wizardName the name of the Wizard character
     */
    public Wizard(String wizardName){
        super(String.format("%s The %s", wizardName, AppConfig.HERO_WIZARD),
                AppConfig.ATTRIBUTE_VALUES_WIZARD_HERO);

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

        // Equip armor/weapon
        super.equipHero(this.getClass());
    }
}
