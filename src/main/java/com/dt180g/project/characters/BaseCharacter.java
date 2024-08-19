package com.dt180g.project.characters;

import com.dt180g.project.abilities.BaseAbility;
import com.dt180g.project.characters.heroes.Cleric;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * The abstract base class for all characters in the game.
 * Provides common functionality and properties for characters. Acts as an invoker to execute attacks.
 */
public abstract class BaseCharacter {

    /**Instance field variables*/
    private final CharacterStats characterStats;
    private final CharacterEquipment equipment;

    private final List<BaseAbility> abilities;

    /**
     * Constructs a new instance of the BaseCharacter class with the given character stats.
     * and creates ability/equipment objects for later use.
     * @param characterStats the character stats for the character
     */
    protected BaseCharacter(CharacterStats characterStats){
        this.characterStats = characterStats;
        this.equipment = new CharacterEquipment();
        this.abilities = new ArrayList<>();
    }

    /**
     * Adds a list of abilities to the character.
     *
     * @param abilities the abilities to add
     */
    protected void addAbilities(List<BaseAbility> abilities){
        getAbilities().addAll(abilities);
    }

    /**
     * Returns the turn information for the character.
     *
     * @param characterType the type of the character (Enemy or Hero)
     * @return the turn information string
     */
    protected String getTurnInformation(String characterType){
        return String.format("[%s TURN] %s | %d AP | %d HP | %d Energy",
                characterType,
                getCharacterName(),
                getActionPoints(),
                getHitPoints(),
                getEnergyLevel());
    }

    /**
     * Executes the actions for the character.
     *
     * @param target indicates whether the attack should be aimed towards hero or enemy (true if enemy, false if hero).
     */
    protected void executeActions(boolean target){
        int magicDamage = getCharacterStats().getMagicPower() + getCharacterStats().getAttackRate();
        int healingAmount = getCharacterStats().getHealingPower() + getCharacterStats().getAttackRate();
        int physicalDamage =
                getCharacterStats().getPhysicalPower() + getCharacterStats().getAttackRate() + getEquipment().getTotalWeaponDamage();
        Deque<BaseAbility> abilityDeque = determineActions();
        for (BaseAbility ability : abilityDeque){
            if (ability.getEnergyCost() > getEnergyLevel() || ability.getActionPointCost()
                    > getActionPoints()) {
                continue;
            }
            if(ability.isHeal()){
                ability.execute(-healingAmount, !this.getClass().equals(Cleric.class));
            }
            else if(ability.isMagic()){
                boolean output = ability.execute(magicDamage, target);
                if(!output){
                    break;
                }
            } else{
                boolean output = ability.execute(physicalDamage, target);
                if (!output){
                    break;
                }
            }

            getCharacterStats().adjustEnergyLevel(-ability.getEnergyCost());
            getCharacterStats().adjustActionPoints(-ability.getActionPointCost());
        }
    }

    /**
     * Selects a random amount of abilities from the list of base abilities and returns them as a deque.
     * Uses {@link IntStream#range(int, int)} to create a similarity to a for loop.
     * @return A deque containing a random set of abilities.
     */
    private Deque<BaseAbility> determineActions(){
        return IntStream.range(0, AppConfig.ACTIONS_PER_TURN)
                .mapToObj(i -> getAbilities().get(Randomizer.INSTANCE.getRandomValue(getAbilities().size()-1)))
                .collect(Collectors.toCollection(ArrayDeque::new)); // Create new ArrayDeque with abilities.
    }

    /**
     * Registers the damage received by the character.
     *
     * @param dmg       the damage amount
     * @param isMagical indicates whether the attack is magical or not
     * @return a list containing the damage applied and the remaining hit points
     */
    public List<Integer> registerDamage(int dmg, boolean isMagical){
        int totalDefenceRate = getCharacterStats().getDefenceRate();

        if (!isMagical)
        {
            totalDefenceRate += getEquipment().getTotalArmorProtection();
        }

        int totalDmg = Math.max(dmg - totalDefenceRate, 0); // If dmg is negative use 0 as dmg.
        getCharacterStats().adjustHitPoints(-totalDmg);
        return Arrays.asList(dmg - totalDmg, totalDmg);
    }

    /**
     * Registers the healing received by the character.
     *
     * @param healAmount the amount of healing
     * @return the current hit points after healing
     */
    public int registerHealing(int healAmount){
        getCharacterStats().adjustHitPoints(healAmount);
        return getCharacterStats().getCurrentHitPoints();
    }

    /**
     * Replenish the character's energy level and action points at the start of a new round.
     * Amount to be replenished is dictated by {@link AppConfig#ROUND_RESET_AP} and
     * {@link AppConfig#ROUND_RESET_ENERGY}.
     */
    public void roundReset(){
        getCharacterStats().adjustEnergyLevel(AppConfig.ROUND_RESET_ENERGY);
        getCharacterStats().adjustActionPoints(AppConfig.ROUND_RESET_AP);
    }

    /**
     * Returns the name of the character.
     *
     * @return the character name
     */

    public abstract String getCharacterName();

    /**
     * Returns the character stats.
     *
     * @return the character stats
     */
    public CharacterStats getCharacterStats(){
        return characterStats;
    }

    /**
     * Returns the character's equipment.
     *
     * @return the character's equipment
     */
    public CharacterEquipment getEquipment(){
        return equipment;
    }

    /**
     * Returns the current action points of the character.
     *
     * @return the current action points
     */
    public int getActionPoints(){
        return getCharacterStats().getCurrentActionPoints();
    }

    /**
     * Returns the current hit points of the character.
     *
     * @return the current hit points
     */
    public int getHitPoints(){
        return getCharacterStats().getCurrentHitPoints();
    }

    /**
     * Returns the current energy level of the character.
     *
     * @return the current energy level
     */
    public int getEnergyLevel(){
        return getCharacterStats().getCurrentEnergyLevel();
    }

    /**
     * Returns the list of abilities of the character.
     *
     * @return the list of abilities
     */
    public List<BaseAbility> getAbilities(){
        return abilities;
    }

    /**
     * Checks if the character is dead.
     *
     * @return true if the character is dead, false otherwise
     */
    public boolean isDead(){
        return getHitPoints() <= 0;
    }

    /**
     * Performs the character's turn and logs the turn info. Used by derivatives.
     */
    public abstract void doTurn();

    /**
     * Returns a string representation of the characters stats/gear.
     * Calls {@link CharacterStats#toString()} and {@link CharacterEquipment#toString()}
     * @return a string representation of the character
     */
    @Override
    public String toString(){
        final int REPEAT_AMOUNT = Math.round(getCharacterName().length() * 2);
        String filler = "*".repeat(REPEAT_AMOUNT);
        String spacer = " ".repeat((REPEAT_AMOUNT - getCharacterName().length())/2);
        return String.format("%s%n%s%s%n%s%n%s%s",
                filler, spacer, getCharacterName().toUpperCase(),
                filler, getCharacterStats().toString(), getEquipment().toString());
    }

}
