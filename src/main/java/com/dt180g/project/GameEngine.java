package com.dt180g.project;

import com.dt180g.project.abilities.AbilityInfo;
import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.enemies.BaseEnemy;
import com.dt180g.project.characters.enemies.LichLord;
import com.dt180g.project.characters.enemies.SkeletonArcher;
import com.dt180g.project.characters.enemies.SkeletonMage;
import com.dt180g.project.characters.enemies.SkeletonWarrior;
import com.dt180g.project.characters.heroes.BaseHero;
import com.dt180g.project.characters.heroes.Cleric;
import com.dt180g.project.characters.heroes.Ranger;
import com.dt180g.project.characters.heroes.Warrior;
import com.dt180g.project.characters.heroes.Wizard;
import com.dt180g.project.support.ActivityLogger;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.Randomizer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Entity responsible for game state and character activities.
 * @author Erik Ström
 */
public final class GameEngine {
    /** Object instance. */
    public static final GameEngine INSTANCE = new GameEngine();
    private final List<BaseHero> heroes;
    private final List<BaseEnemy> enemies = new LinkedList<>();
    private final List<String> enemyTypes;

    /**
     * Constructor initialising needed members.
     */
    private GameEngine() {
        heroes = Stream.of(
                        new Warrior("Leila"), new Ranger("Allan"),
                        new Wizard("Elvira"), new Cleric("Kevin"))
                .collect(Collectors.toList());

        enemyTypes = Stream.of(
                        AppConfig.ENEMY_SKELETON_WARRIOR, AppConfig.ENEMY_SKELETON_ARCHER, AppConfig.ENEMY_SKELETON_MAGE)
                .collect(Collectors.toList());
    }



    /**
     * Get index of targets for character ability.
     * @param amountOfTargets the number of targets needed
     * @param listSize size of container holding target characters
     * @return container of integer positions
     */
    private List<Integer> getTargetIndexPos(final int amountOfTargets, final int listSize) {
        // Create an IntStream with a range of [0, amountOfTargets)
        return IntStream.range(0, amountOfTargets)
                // Map each integer in the range to a random value using the provided Randomizer
                .map(i -> Randomizer.INSTANCE.getRandomValue(listSize - 1))
                // Convert the IntStream to a Stream<Integer> (boxing the primitive ints to Integer objects)
                .boxed()
                // Collect the stream elements into a List<Integer>
                .collect(Collectors.toList());
    }

    /**
     * Accessor to get hero characters.
     * @return list of remaining hero characters.
     */
    public List<BaseHero> getHeroes() { return heroes; }

    /**
     * Accessor to get enemy characters.
     * @return list of remaining enemy characters.
     */
    public List<BaseEnemy> getEnemies() { return enemies; }

    /**
     * Accessor to get amount of available enemy characters.
     * @return amount of remaining enemies.
     */
    public int getAmountOfEnemies() { return enemies.size(); }

    /**
     * Accessor to get amount of available hero characters.
     * @return amount of remaining heroes.
     */
    public int getAmountOfHeroes() { return heroes.size(); }

    /**
     * Used to reset stats of remaining heroes. Represents resting between dungeon levels.
     */
    public void resetHeroesStats() { heroes.forEach(BaseHero::resetHeroStats); }

    /**
     * Accessor to get all available characters, both heroes and enemies.
     * @return list of characters, as combination of remaining heroes and enemies.
     */
    public List<BaseCharacter> getAllCharacters() {
        return Stream.concat(heroes.stream(), enemies.stream())
                .collect(Collectors.toList());
    }

    /**
     * Descriptor method, used to determine if there are heroes left.
     * @return whether there are heroes remaining.
     */
    public boolean heroesRemaining() { return !heroes.isEmpty(); }

    /**
     * Descriptor method, used to determine if there are enemies left.
     * @return whether there are enemies remaining.
     */
    public boolean enemiesRemaining() { return !enemies.isEmpty(); }

    /**
     * Fill list of enemies with stated amount.
     * @param amountOfEnemies the amount of basic enemies to produce.
     * @param finalBoss whether final boss should be produced.
     */
    public void produceEnemies(final int amountOfEnemies, final boolean finalBoss) {
        enemies.clear(); // be sure we start with an empty list

        // If it's the final boss, add the LichLord and return
        if (finalBoss) {
            enemies.add(new LichLord());
            return;
        }

        // Map to store the count of each enemy type
        Map<String, Integer> amountOfTypes = new HashMap<>();

        // Generate 'amountOfEnemies' enemies
        IntStream.range(0, amountOfEnemies)
                // Generate a random index based on the size of the enemyTypes list
                .map(i -> Randomizer.INSTANCE.getRandomValue(enemyTypes.size() - 1))
                // Get the corresponding enemy type string from the enemyTypes list
                .mapToObj(enemyTypes::get)
                // For each randomly generated enemy type, create and add the enemy instance
                .forEach(randStr -> {
                    // Retrieve the current enemy count from the map, defaulting to 0 if not present
                    int enemyCounter = amountOfTypes.getOrDefault(randStr, 0) + 1;
                    // Update the enemy count in the map
                    amountOfTypes.put(randStr, enemyCounter);

                    // Create the enemy instance based on the enemy type and pass the updated count as a sequence number
                    BaseEnemy enemy = switch (randStr) {
                        case AppConfig.ENEMY_SKELETON_ARCHER -> new SkeletonArcher(enemyCounter);
                        case AppConfig.ENEMY_SKELETON_MAGE -> new SkeletonMage(enemyCounter);
                        default -> new SkeletonWarrior(enemyCounter);
                    };
                    // Add the created enemy to the enemies list
                    enemies.add(enemy);
                });
    }

    /* ATTACK PROCEDURES */

    /**
     * Used internally by Game Engine to perform character attacks.
     * @param targetList list containing character types which the attack targets.
     * @param abilityInfo information about the ability to base the attack.
     * @param critMultiplier multiplier for attack value (critical hit).
     */
    private void performCharacterAttack(final List<? extends BaseCharacter> targetList, final AbilityInfo abilityInfo,
                                        final int critMultiplier) {

        List<Integer> targetIndexes = getTargetIndexPos(abilityInfo.getAmountOfTargets(), targetList.size());

        targetIndexes.stream()
                .map(targetList::get)
                .distinct()
                .forEach(character -> {
                    final int critUpperBound = 9;
                    boolean addCrit = Randomizer.INSTANCE.getRandomValue(1, critUpperBound) <= AppConfig.CRIT_CHANCE;
                    int damage = addCrit ? abilityInfo.getDamage() * critMultiplier : abilityInfo.getDamage();

                    String baseInfo = String.format("%s%s", addCrit ? "[CRITICAL HIT] " : "", character.getCharacterName());

                    if (abilityInfo.isHeal()) {
                        int healedHp = character.registerHealing(-damage);
                        ActivityLogger.INSTANCE.logHealing(String.format("%s receives %+d points of healing and has %d HP left",
                                baseInfo, -damage, healedHp));
                    } else {
                        List<Integer> damageInfo = character.registerDamage(damage, abilityInfo.isMagic());
                        String logInfo = String.format("%s deflects %d points of damage taking %d as direct hit",
                                baseInfo, damageInfo.get(0), damageInfo.get(1));

                        if (character.getHitPoints() < 1) {
                            ActivityLogger.INSTANCE.logDeath(String.format("%s causing death (%d HP)",
                                    logInfo, character.getHitPoints()));
                        } else {
                            ActivityLogger.INSTANCE.logDamage(String.format("%s and has %d HP left",
                                    logInfo, character.getHitPoints()));
                        }
                    }
                });

        // Clean up dead characters from target list
        targetList.removeIf(BaseCharacter::isDead);
    }


    /**
     * Used internally by Game Engine for hero specific attack.
     * @param abilityInfo information about the ability to base the attack.
     * @return whether the attack has been performed successfully.
     */
    private boolean heroAttack(final AbilityInfo abilityInfo) {
        if (enemies.isEmpty()) {
            ActivityLogger.INSTANCE.logAttack("All enemies are dead...");
            return false;
        }

        int amountOfTargets = abilityInfo.getAmountOfTargets();
        String targets = (amountOfTargets == 1) ? "enemy" : "enemies";
        ActivityLogger.INSTANCE.logAttack(String.format("%s targeting %d %s",
                abilityInfo.getInformation(), amountOfTargets, targets));
        performCharacterAttack(enemies, abilityInfo, AppConfig.HERO_CRIT_MULTIPLIER);
        return true;
    }

    /**
     * Used internally by Game Engine for enemy specific attack.
     * @param abilityInfo information about the ability to base the attack.
     * @return whether the attack has been performed successfully.
     */
    private boolean enemyAttack(final AbilityInfo abilityInfo) {
        if (heroes.isEmpty()) {
            ActivityLogger.INSTANCE.logAttack("All heroes are dead...");
            return false;
        }

        int amountOfTargets = abilityInfo.getAmountOfTargets();
        String targets = (amountOfTargets == 1) ? "hero" : "heroes";
        ActivityLogger.INSTANCE.logAttack(String.format("%s targeting %d %s",
                abilityInfo.getInformation(), amountOfTargets, targets));
        performCharacterAttack(heroes, abilityInfo, AppConfig.ENEMY_CRIT_MULTIPLIER);
        return true;
    }

    /**
     * Used by client to request that the engine performs an attack.
     * @param abilityInfo information about the ability to base the attack.
     * @return whether the attack has been performed successfully.
     */
    public boolean characterAttack(final AbilityInfo abilityInfo) {
        return (abilityInfo.getTargetEnemies()) ? heroAttack(abilityInfo) : enemyAttack(abilityInfo);
    }
}
