package com.dt180g.project.characters.heroes;

import com.dt180g.project.abilities.FocusedShot;
import com.dt180g.project.abilities.SprayOfArrows;
import com.dt180g.project.abilities.WeaponAttack;
import com.dt180g.project.support.AppConfig;

import java.util.Arrays;

/**
 * The {@code Ranger} class represents a Ranger hero character in the game.
 * It extends the {@link BaseHero} class and provides specific abilities and equipment for the Ranger class.
 *
 * <p>The Ranger class inherits the base functionality from the {@link BaseHero} class,
 * and additional abilities and equipment are added to specialize the Ranger character.
 *
 * @author Daniel Jönsson
 * @see BaseHero
 * @version 1.0
 */
public class Ranger extends BaseHero{

    /**
     * Constructs a {@code Ranger} object with the specified character name.
     *
     * @param rangerName the name of the Ranger character
     */
    public Ranger(String rangerName){
        super(String.format("%s The %s", rangerName, AppConfig.HERO_RANGER),
                AppConfig.ATTRIBUTE_VALUES_RANGER_HERO);

        // Add abilities
        super.addAbilities(Arrays.asList(
                new FocusedShot(),
                new SprayOfArrows(),
                new WeaponAttack()
        ));

        // Equip weapon/armor
        super.equipHero(this.getClass());
    }
}
