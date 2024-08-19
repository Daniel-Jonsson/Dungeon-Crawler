package com.dt180g.project.characters;

import com.dt180g.project.stats.*;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.util.*;
import java.util.stream.IntStream;

/**
 * The {@code CharacterStats} is representing the statistics of a specific character.
 * Provides methods to access and modify various character stats.
 */
public class CharacterStats{

    /**Instance field variable*/
    private final Map<String, BaseStat> stats;

    /**
     * Constructs a new instance of the CharacterStats class with the given stat values.
     *
     * @param stats the Map of stat values
     */
    public CharacterStats(List<Integer> stats){
        this.stats = new LinkedHashMap<>();
        List<String> attributeNames = StatsManager.INSTANCE.getAttributeNames();
        List<String> traitNames = StatsManager.INSTANCE.getTraitNames();
        List<String> combatStatNames = StatsManager.INSTANCE.getCombatStatNames();
        List<Integer> traitValues = Arrays.asList(
                AppConfig.TRAIT_VITALITY_BASE_VALUE,
                AppConfig.TRAIT_ENERGY_BASE_VALUE,
                AppConfig.TRAIT_ATTACK_RATE_BASE_VALUE,
                AppConfig.TRAIT_DEFENCE_RATE_BASE_VALUE
        );
        List<Trait> traitList = IntStream.range(0, traitNames.size())
                .mapToObj(i -> new Trait(traitNames.get(i), traitValues.get(i)))
                .toList();
        for (int i = 0; i < attributeNames.size(); i++){
            Attribute attribute1 = new Attribute(attributeNames.get(i), stats.get(i) * AppConfig.ATTRIBUTE_BASE_VALUE);
            CombatStat combatStat = new CombatStat(combatStatNames.get(i), attribute1, traitList.get(2));
            this.stats.put(attribute1.getStatName(), attribute1);
            this.stats.put(traitNames.get(i), traitList.get(i));
            this.stats.put(combatStat.getStatName(), combatStat);
        }
    }

    /**
     * Returns the base stat object for the given stat name.
     *
     * @param stat the name of the stat
     * @return the base stat object
     */
    public BaseStat getStat(String stat){
        return stats.get(stat);
    }

    /**
     * Returns the modified value of the given stat.
     *
     * @param stat the name of the stat
     * @return the modified value of the stat
     */
    public int getStatValue(String stat){
        return stats.get(stat).getModifiedValue();
    }

    /**
     * Returns the total action points of the character.
     *
     * @return the total action points with bonuses applied
     */
    public int getTotalActionPoints(){
        return getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getBaseValue() +
                getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).getStaticModifier();
    }

    /**
     * Returns the current action points of the character.
     *
     * @return the current action points
     */
    public int getCurrentActionPoints(){
        return getStatValue(AppConfig.COMBAT_STAT_ACTION_POINTS);
    }

    /**
     * Returns the total hit points of the character.
     *
     * @return the total hit points with bonuses applied
     */
    public int getTotalHitPoints(){
        return getStat(AppConfig.TRAIT_VITALITY).getBaseValue() +
                getStat(AppConfig.TRAIT_VITALITY).getStaticModifier();
    }

    /**
     * Returns the current hit points of the character.
     *
     * @return the current hit points
     */
    public int getCurrentHitPoints(){
        return getStatValue(AppConfig.TRAIT_VITALITY);
    }

    /**
     * Returns the total energy level of the character.
     *
     * @return the total energy level with bonuses applied.
     */
    public int getTotalEnergyLevel(){
        return getStat(AppConfig.TRAIT_ENERGY).getBaseValue() +
                getStat(AppConfig.TRAIT_ENERGY).getStaticModifier();
    }

    /**
     * Returns the current energy level of the character.
     *
     * @return the current energy level
     */
    public int getCurrentEnergyLevel(){
        return getStatValue(AppConfig.TRAIT_ENERGY);
    }

    /**
     * Returns the defense rate of the character.
     *
     * @return the defense rate
     */
    public int getDefenceRate(){
        return getStatValue(AppConfig.TRAIT_DEFENSE_RATE);
    }

    /**
     * Returns the attack rate of the character.
     *
     * @return the attack rate
     */
    public int getAttackRate(){
        return getStatValue(AppConfig.TRAIT_ATTACK_RATE);
    }

    /**
     * Returns the physical power of the character.
     *
     * @return the physical power
     */
    public int getPhysicalPower(){
        return getStatValue(AppConfig.COMBAT_STAT_PHYSICAL_POWER);
    }

    /**
     * Returns the magic power of the character.
     *
     * @return the magic power
     */
    public int getMagicPower(){
        return getStatValue(AppConfig.COMBAT_STAT_MAGIC_POWER);
    }

    /**
     * Returns the healing power of the character.
     *
     * @return the healing power
     */
    public int getHealingPower(){
        return getStatValue(AppConfig.COMBAT_STAT_HEALING_POWER);
    }

    /**
     * Adjusts the action points of the character by the specified amount.
     *
     * @param amount the amount to adjust the action points by
     */
    public void adjustActionPoints(int amount){
        adjustStatDynamicModifier(AppConfig.COMBAT_STAT_ACTION_POINTS, amount);
    }

    /**
     * Adjusts the hit points of the character by the specified amount.
     *
     * @param amount the amount to adjust the hit points by
     */
    public void adjustHitPoints(int amount){
        adjustStatDynamicModifier(AppConfig.TRAIT_VITALITY, amount);
    }

    /**
     * Adjusts the energy level of the character by the specified amount.
     *
     * @param amount the amount to adjust the energy level by
     */
    public void adjustEnergyLevel(int amount){
        adjustStatDynamicModifier(AppConfig.TRAIT_ENERGY, amount);
    }

    /**
     * Adjusts the static modifier of the specified stat by the specified amount.
     *
     * @param statName the name of the stat
     * @param amount   the amount to adjust the static modifier by
     */
    public void adjustStatStaticModifier(String statName, int amount){
        getStat(statName).adjustStaticModifier(amount);
    }

    /**
     * Adjusts the dynamic modifier of the specified stat by the specified amount.
     *
     * @param statName the name of the stat
     * @param amount   the amount to adjust the dynamic modifier by
     */
    public void adjustStatDynamicModifier(String statName, int amount){
        getStat(statName).adjustDynamicModifier(amount);
    }

    /**
     * Resets the dynamic modifier of the action points to its default value (0).
     */
    public void resetActionPoints(){
        getStat(AppConfig.COMBAT_STAT_ACTION_POINTS).resetDynamicModifier();
    }

    /**
     * Resets the dynamic modifier of the hit points to its default value (0).
     */
    public void resetHitPoints(){
        getStat(AppConfig.TRAIT_VITALITY).resetDynamicModifier();
    }

    /**
     * Resets the dynamic modifier of the energy level to its default value (0).
     */
    public void resetEnergyLevel(){
        getStat(AppConfig.TRAIT_ENERGY).resetDynamicModifier();
    }

    /**
     * Returns a string representation of the character stats.
     * Uses {@link IOHelper#formatAsTable(List)} to format the information in a table-form.
     * Uses {@link AppConfig} to colorize the output.
     *
     * @return a string representation of the character stats in table-form.
     */
    @Override
    public String toString(){
        List<List<String>> characterStatInfo = new ArrayList<>();
        for (BaseStat stat : stats.values()){
            String[] parts = stat.toString().split(" ");
            // Used if stat name is two words (E.g. Action Points)
            if (Arrays.stream(parts).count() > 3){
                String[] combined = {parts[0] + " " + parts[1], parts[2], parts[3]};
                characterStatInfo.add(List.of(combined));
            }
            else{
                characterStatInfo.add(List.of(parts));
            }
        }
        List<List<String>> resultList = new ArrayList<>();
        // Used to format the columns in correct order.
        for (int i = 0; i < characterStatInfo.size(); i+= 3){
            List<String> subList = new ArrayList<>(characterStatInfo.get(i));
            subList.add("|");
            subList.addAll(characterStatInfo.get(i + 1));
            subList.add("|");
            subList.addAll(characterStatInfo.get(i + 2));
            resultList.add(subList);
        }
        return String.format("%sSTATISTICS%n%s%s%n%n", AppConfig.ANSI_BLUE, AppConfig.ANSI_RESET,
                IOHelper.formatAsTable(resultList));
    }
}
