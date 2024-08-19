package com.dt180g.project.characters;

import com.dt180g.project.support.AppConfig;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCharacterStats {
    private final int baseStrength = 8;
    private final int baseDexterity = 6;
    private final int baseIntelligence = 2;
    private final int baseWillpower = 4;
    private final CharacterStats characterStats;

    public TestCharacterStats() {
        characterStats = new CharacterStats(
                Arrays.asList(baseStrength, baseDexterity, baseIntelligence, baseWillpower));
    }

    @Test
    public void testAttributes() {
        int attributeBaseValue = AppConfig.ATTRIBUTE_BASE_VALUE;
        assertAll("Validate that all Attributes are correct",
                () -> assertEquals(baseStrength * attributeBaseValue, characterStats.getStatValue(AppConfig.ATTRIBUTE_STRENGTH)),
                () -> assertEquals(baseDexterity * attributeBaseValue, characterStats.getStatValue(AppConfig.ATTRIBUTE_DEXTERITY)),
                () -> assertEquals(baseIntelligence * attributeBaseValue, characterStats.getStatValue(AppConfig.ATTRIBUTE_INTELLIGENCE)),
                () -> assertEquals(baseWillpower * attributeBaseValue, characterStats.getStatValue(AppConfig.ATTRIBUTE_WILLPOWER))
        );
    }

    @Test
    public void testStats() {
        assertAll("Validate that all Stats are correct",
                () -> assertEquals(AppConfig.TRAIT_VITALITY_BASE_VALUE, characterStats.getStatValue(AppConfig.TRAIT_VITALITY)),
                () -> assertEquals(AppConfig.TRAIT_ENERGY_BASE_VALUE, characterStats.getStatValue(AppConfig.TRAIT_ENERGY)),
                () -> assertEquals(AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE, characterStats.getStatValue(AppConfig.TRAIT_ATTACK_RATE)),
                () -> assertEquals(AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE, characterStats.getStatValue(AppConfig.TRAIT_DEFENSE_RATE))
        );
    }

    /* TESTS FOR COMBAT STATS */

    private int calcCombatStatExpectedValue(String attributeReliance) {
        double attackRateValue = characterStats.getStatValue(AppConfig.TRAIT_ATTACK_RATE) * AppConfig.COMBAT_STAT_MULTIPLIER;
        double attributeValue = characterStats.getStatValue(attributeReliance) * AppConfig.COMBAT_STAT_MULTIPLIER;
        return (int) Math.round(attributeValue + attackRateValue);
    }

    private void runCombatStatAssertion(String attributeReliance, String combatStat) {
        int expValue = calcCombatStatExpectedValue(attributeReliance);
        assertEquals(expValue, characterStats.getStatValue(combatStat));
    }

    private void runCombatStatAssertion(String attributeReliance, int value) {
        int expValue = calcCombatStatExpectedValue(attributeReliance);
        assertEquals(expValue, value);
    }

    @Test
    public void testCombatStats() {
        assertAll("Validate that all Combat Stats are correct",
                () -> runCombatStatAssertion(AppConfig.ATTRIBUTE_STRENGTH, AppConfig.COMBAT_STAT_PHYSICAL_POWER),
                () -> runCombatStatAssertion(AppConfig.ATTRIBUTE_DEXTERITY, AppConfig.COMBAT_STAT_ACTION_POINTS),
                () -> runCombatStatAssertion(AppConfig.ATTRIBUTE_INTELLIGENCE, AppConfig.COMBAT_STAT_MAGIC_POWER),
                () -> runCombatStatAssertion(AppConfig.ATTRIBUTE_WILLPOWER, AppConfig.COMBAT_STAT_HEALING_POWER)
        );
    }

    /* TEST OPEN INTERFACE FOR ATTACK RATE, DEFENCE RATE, PHYSICAL POWER, MAGIC POWER & HEALING POWER */

    @Test
    public void testPhysicalPower() {
        runCombatStatAssertion(AppConfig.ATTRIBUTE_STRENGTH, characterStats.getPhysicalPower());
    }

    @Test
    public void testMagicPower() {
        runCombatStatAssertion(AppConfig.ATTRIBUTE_INTELLIGENCE, characterStats.getMagicPower());
    }

    @Test
    public void testHealingPower() {
        runCombatStatAssertion(AppConfig.ATTRIBUTE_WILLPOWER, characterStats.getHealingPower());
    }

    @Test
    public void testDefenceRate() {
        assertEquals(AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE, characterStats.getDefenceRate());
    }

    @Test
    public void testAttackRate() {
        assertEquals(AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE, characterStats.getAttackRate());
    }

    /* TEST OPEN INTERFACE FOR HP, AP & ENERGY LEVELS */

    @Test
    public void testHitPoints() {
        int totalHP = characterStats.getTotalHitPoints();
        int reduction = 10;
        assertAll("Validate that Hit Points are calculated correct",
                () -> assertEquals(totalHP, characterStats.getCurrentHitPoints()),
                () -> assertEquals(totalHP, characterStats.getTotalHitPoints()),
                () -> characterStats.adjustHitPoints(-reduction),
                () -> assertEquals(totalHP - reduction, characterStats.getCurrentHitPoints()),
                () -> assertEquals(totalHP, characterStats.getTotalHitPoints()),
                () -> characterStats.resetHitPoints(),
                () -> assertEquals(totalHP, characterStats.getCurrentHitPoints()),
                () -> assertEquals(totalHP, characterStats.getTotalHitPoints())
        );
    }

    @Test
    public void testActionPoints() {
        int totalAP = characterStats.getTotalActionPoints();
        int reduction = 10;
        assertAll("Validate that Action Points are calculated correct",
                () -> assertEquals(totalAP, characterStats.getCurrentActionPoints()),
                () -> assertEquals(totalAP, characterStats.getTotalActionPoints()),
                () -> characterStats.adjustActionPoints(-reduction),
                () -> assertEquals(totalAP - reduction, characterStats.getCurrentActionPoints()),
                () -> assertEquals(totalAP, characterStats.getTotalActionPoints()),
                () -> characterStats.resetActionPoints(),
                () -> assertEquals(totalAP, characterStats.getCurrentActionPoints()),
                () -> assertEquals(totalAP, characterStats.getTotalActionPoints())
        );
    }

    @Test
    public void testEnergyLevel() {
        int totalEnergy = characterStats.getTotalEnergyLevel();
        int reduction = 10;
        assertAll("Validate that Action Points are calculated correct",
                () -> assertEquals(totalEnergy, characterStats.getCurrentEnergyLevel()),
                () -> assertEquals(totalEnergy, characterStats.getTotalEnergyLevel()),
                () -> characterStats.adjustEnergyLevel(-reduction),
                () -> assertEquals(totalEnergy - reduction, characterStats.getCurrentEnergyLevel()),
                () -> assertEquals(totalEnergy, characterStats.getTotalEnergyLevel()),
                () -> characterStats.resetEnergyLevel(),
                () -> assertEquals(totalEnergy, characterStats.getCurrentEnergyLevel()),
                () -> assertEquals(totalEnergy, characterStats.getTotalEnergyLevel())
        );
    }
}
