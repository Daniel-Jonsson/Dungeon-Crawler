package com.dt180g.project.stats;

import com.dt180g.project.support.AppConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTraitAndAttribute {
    private final int baseValue = 20;
    private final int baseModifier = -5;

    @Test
    public void validateBaseClassAbstraction() {  // super class should be abstract
        assertThrows(InstantiationException.class, BaseStat.class::newInstance);
    }

    private void runAsserts(String statName, BaseStat stat, int extraModifier) {
        stat.adjustDynamicModifier(extraModifier);

        assertAll("Validate that stat values are correct",
                () -> assertEquals(statName, stat.getStatName()),
                () -> assertEquals(baseValue, stat.getBaseValue()),
                () -> assertEquals(baseModifier + extraModifier, stat.getTotalModifier()),
                () -> assertEquals(baseValue + (baseModifier + extraModifier), stat.getModifiedValue())
        );
    }

    /* ATTIBUTE TESTS */
    private void doAttributeTest(String attributeName, int extraModifier) {
        BaseStat attribute = new Attribute(attributeName, baseValue);
        //attribute.adjustStaticModifier(baseModifier);
        attribute.adjustDynamicModifier(baseModifier);
        runAsserts(attributeName, attribute, extraModifier);
    }

    @Test
    public void testAttributeStrength() {
        doAttributeTest(AppConfig.ATTRIBUTE_STRENGTH, 2);
    }

    @Test
    public void testAttributeDexterity() {
        doAttributeTest(AppConfig.ATTRIBUTE_DEXTERITY, 1);
    }

    @Test
    public void testAttributeIntelligence() {
        doAttributeTest(AppConfig.ATTRIBUTE_INTELLIGENCE, 3);
    }

    @Test
    public void testAttributeWillpower() {
        doAttributeTest(AppConfig.ATTRIBUTE_WILLPOWER, 4);
    }

    /* TRAIT TESTS */
    private void doTraitTest(String traitName, int extraModifier) {
        BaseStat trait = new Trait(traitName, baseValue);
        trait.adjustDynamicModifier(baseModifier);
        runAsserts(traitName, trait, extraModifier);
    }

    @Test
    public void testTraitVitality() {
        doTraitTest(AppConfig.TRAIT_VITALITY, 4);
    }

    @Test
    public void testTraitEnergy() { doTraitTest(AppConfig.TRAIT_ENERGY, 3); }

    @Test
    public void testTraitAttackRate() {
        doTraitTest(AppConfig.TRAIT_ATTACK_RATE, 2);
    }

    @Test
    public void testTraitDefenceRate() {
        doTraitTest(AppConfig.TRAIT_DEFENSE_RATE, 1);
    }
}

