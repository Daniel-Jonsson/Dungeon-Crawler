package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedHeal;
import com.dt180g.project.abilities.GroupHeal;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code Cleric} class represents a Cleric hero character in the game.
 * It extends the {@link BaseHero} class and provides specific abilities and equipment for the Cleric class.
 *
 * <p>The Cleric class inherits the base functionality from the {@link BaseHero} class,
 * and additional abilities and equipment are added to specialize the Cleric character.
 *
 * @author Daniel Jönsson
 * @see BaseHero
 * @version 1.0
 */
public class Cleric extends BaseHero{

    /**
     * Constructs a {@code Cleric} object with the specified character name.
     *
     * @param characterName the name of the Cleric character
     */
    public Cleric(String characterName){
        super(String.format("%s The %s", characterName, AppConfig.HERO_CLERIC)
                , AppConfig.ATTRIBUTE_VALUES_CLERIC_HERO);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new FocusedHeal(),
                new GroupHeal(),
                new WeaponAttack()
        ));

        // Equip armor/weapon
        super.equipHero(this.getClass());
    }


}
