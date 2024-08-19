package com.dt180g.project;

import com.dt180g.project.characters.BaseCharacter;
import com.dt180g.project.characters.heroes.BaseHero;
import com.dt180g.project.support.ActivityLogger;
import com.dt180g.project.support.AppConfig;
import com.dt180g.project.support.IOHelper;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Entity responsible for running game simulation, filtering user interaction and issuing
 * requests which will reach the game engine for processing.
 * @author Erik Ström
 */
public class GameRunner {
    private final GameEngine engine = GameEngine.INSTANCE;  // reference to game engine
    private final PrintStream out = System.out;             // our standard output stream
    private int currentDungeonLevel = AppConfig.DUNGEON_START_LEVEL;
    private int currentRound = 0;       // counter for the amount of rounds in level
    private boolean endGame = false;    // condition to end game

    private interface CharacterType {  // for easy enumeration of character types
        int ALL = 0;
        int HEROES = 1;
        int ENEMIES = 2;
    }

    /** Default constructor. */
    public GameRunner() { }

    /**
     * Used internally by Game Runner to access remaining characters from Game Engine.
     * @param type type of characters which should be collected.
     * @return list of characters which has been collected from engine.
     */
    private List<? extends BaseCharacter> getCharacters(final int type) {
        return (type == CharacterType.ENEMIES) ? engine.getEnemies() : (type == CharacterType.HEROES)
                ? engine.getHeroes() : engine.getAllCharacters();
    }

    /**
     * Used internally by Game Runner to produce information about specific characters.
     * @param characters list of characters to view information about.
     */
    private void printCharacterInformation(final List<BaseHero> characters) {
        characters.forEach(System.out::println);
    }

    /**
     * Used internally by Game Runner to produce sub menu for hero details.
     * User may choose to view details for specific hero, or all of them.
     */
    private void heroDetailsMenu() {
        List<BaseHero> heroes = engine.getHeroes();
        // Use Stream API to create the menuOptions list
        List<String> menuOptions = new ArrayList<>(heroes.stream().map(BaseHero::getCharacterName).toList());
        menuOptions.add("All");

        int input;
        do {
            out.println("\n****** CHOOSE HERO TO VIEW ******");
            IOHelper.printOptionItems(menuOptions.listIterator());
            out.println("0. Back");
            input = IOHelper.getInput(0, menuOptions.size());

            if (input == menuOptions.size()) {
                printCharacterInformation(heroes);
            } else if (input > 0) {
                printCharacterInformation(Collections.singletonList(heroes.get(input - 1)));
            }
        } while (input != 0);
    }


    /**
     * Used internally by Game Runner to produce main user menu.
     * User may choose to view hero details, proceed to next dungeon level, or quit the program.
     * @return user input.
     */
    private int runUserMenu() {
        // The menu options for user navigation
        List<String> menuOptions = Arrays.asList("Run Dungeon Level " + currentDungeonLevel, "View Hero Details");

        int input;
        do {
            out.printf(System.lineSeparator() + "****** %d HEROES LEFT, CHOOSE YOUR NEXT MOVE ******%n",
                    engine.getAmountOfHeroes());

            // Output menu options
            IOHelper.printOptionItems(menuOptions.listIterator());
            out.println("0. Exit");

            input = IOHelper.getInput(0, menuOptions.size());  // Get user input

            // Use a switch statement for handling user input
            switch (input) {
                case 2 -> heroDetailsMenu();
                case 0 -> endGame = true;
            }
        } while (input == 2);

        return input;
    }


    /**
     * Used internally by Game Runner, for as long there are enemies remaining.
     */
    private void runRound() {
        ++currentRound;

        final int repeatAmount = 15;
        String filler = "*".repeat(repeatAmount);
        String logInfo = String.format("%n%s DUNGEON LEVEL %d | ROUND %d | %d HEROES | %d ENEMIES %s",
                filler, currentDungeonLevel, currentRound, engine.getAmountOfHeroes(),
                engine.getAmountOfEnemies(), filler);
        ActivityLogger.INSTANCE.logRoundInfo(logInfo);

        // Get all characters, both heroes and enemies
        List<? extends BaseCharacter> characters = getCharacters(CharacterType.ALL);

        // Sort based on action points in descending order. Highest AP rate will act first
        characters.sort(Comparator.comparingInt(BaseCharacter::getActionPoints).reversed());

        for (BaseCharacter character : characters) {
            if (currentRound > 1) {  // replenish some AP and Energy for all characters
                character.roundReset();
            }

            if (character.isDead()) {  // ignore late updates regarding list removals
                continue;
            }

            character.doTurn();

            if (!engine.heroesRemaining()) {
                endGame = true;  // game over
                return;
            } else if (!engine.enemiesRemaining()) {
                return;  // break turn
            }
        }
    }

    /**
     * Used internally by Game Runner to represent dungeon level.
     * Each new dungeon level will increase difficulty by adding more enemies,
     * except for the last level which will only contain final boss.
     * @param difficulty multiplier for creating additional enemies.
     */
    private void runDungeonLevel(final int difficulty) {
        // Produce the amount of enemies we need. Final level will only have a single boss
        int additionalEnemies = (currentDungeonLevel == 1) ? 0 : AppConfig.ENEMIES_MULTIPLIER;
        engine.produceEnemies(difficulty * additionalEnemies, currentDungeonLevel == 1);

        // Make sure remaining heroes are rested before entering dungeon level
        engine.resetHeroesStats();

        if (runUserMenu() == 0) {  // user wishes to quit program?
            return;
        }

        currentRound = 0;  // reset counter for the amount of rounds in level
        while (engine.enemiesRemaining() && !endGame) {
            runRound();  // do round
        }

        out.println();
    }

    /**
     * Main simulation for the game, running for as long as there are levels remaining in dungeon.
     * Will exit abruptly if user states to do so, or in the case all heroes are dead.
     */
    public void runGame() {
        IOHelper.printWelcomeHeader();

        int difficulty = 0;
        while (currentDungeonLevel > 0 && !endGame) {
            runDungeonLevel(++difficulty);
            --currentDungeonLevel;
        }

        out.println();

        if (!engine.enemiesRemaining()) {
            IOHelper.printGameCompletedHeader();
        } else if (!engine.heroesRemaining()) {
            IOHelper.printGameOverHeader();
        }
    }
}
