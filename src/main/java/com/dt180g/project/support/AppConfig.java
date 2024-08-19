package com.dt180g.project.support;

import java.util.Arrays;
import java.util.List;

/**
 * The AppConfig class provides a collection of constants and help methods which are
 * used throughout the game application. These constants include game-related values,
 * logging configurations, and color codes. The class is final and should not be
 * instantiated, since all constants are static.
 *
 * @author Erik Ström
 */
public final class AppConfig {
    /** Prohibit instantiation. */
    private AppConfig() { throw new IllegalStateException("Utility class"); }

    /** Identifier for hero. */
    public static final String CHARACTER_TYPE_HERO = "HERO";

    /** Identifier for enemy. */
    public static final String CHARACTER_TYPE_ENEMY = "ENEMY";

    /** Name of cleric class. */
    public static final String HERO_CLERIC = "Cleric";

    /** Name of ranger class. */
    public static final String HERO_RANGER = "Ranger";

    /** Name of warrior class. */
    public static final String HERO_WARRIOR = "Warrior";

    /** Name of wizard class. */
    public static final String HERO_WIZARD = "Wizard";

    /** Name of skeleton archer class. */
    public static final String ENEMY_SKELETON_ARCHER = "Skeleton Archer";

    /** Name of skeleton warrior class. */
    public static final String ENEMY_SKELETON_WARRIOR = "Skeleton Warrior";

    /** Name of skeleton mage class. */
    public static final String ENEMY_SKELETON_MAGE = "Skeleton Mage";

    /** Name of lich lord class class. */
    public static final String ENEMY_LICH_LORD = "Bertil The Lich Lord";

    /* -------------------------
    NAMES OF ATTRIBUTE TYPES.
    ------------------------- */
    /** Name of strength attribute. */
    public static final String ATTRIBUTE_STRENGTH = "Strength";

    /** Name of dexterity attribute. */
    public static final String ATTRIBUTE_DEXTERITY = "Dexterity";

    /** Name of intelligence attribute. */
    public static final String ATTRIBUTE_INTELLIGENCE = "Intelligence";

    /** Name of willpower attribute. */
    public static final String ATTRIBUTE_WILLPOWER = "Willpower";

    /* -------------------------
    NAMES OF TRAIT TYPES.
    ------------------------- */
    /** Name of vitality trait. */
    public static final String TRAIT_VITALITY = "Vitality";

    /** Name of energy trait. */
    public static final String TRAIT_ENERGY = "Energy";

    /** Name of attack rate trait. */
    public static final String TRAIT_ATTACK_RATE = "Attack Rate";

    /** Name of defense rate trait. */
    public static final String TRAIT_DEFENSE_RATE = "Defense Rate";

    /* -------------------------
    NAMES OF COMBAT STAT TYPES.
    ------------------------- */
    /** Name of action points stat. */
    public static final String COMBAT_STAT_ACTION_POINTS = "Action Points";

    /** Name of physical power stat. */
    public static final String COMBAT_STAT_PHYSICAL_POWER = "Physical Power";

    /** Name of magic power stat. */
    public static final String COMBAT_STAT_MAGIC_POWER = "Magical Power";

    /** Name of healing power stat. */
    public static final String COMBAT_STAT_HEALING_POWER = "Healing Power";

    /* -------------------------
    VALUES FOR ATTRIBUTES.
    ------------------------- */
    /** Starting stat values for hero warrior. */
    public static final List<Integer> ATTRIBUTE_VALUES_WARRIOR_HERO = Arrays.asList(8, 6, 2, 4);

    /** Starting stat values for hero ranger. */
    public static final List<Integer> ATTRIBUTE_VALUES_RANGER_HERO = Arrays.asList(6, 8, 4, 2);

    /** Starting stat values for hero wizard. */
    public static final List<Integer> ATTRIBUTE_VALUES_WIZARD_HERO = Arrays.asList(2, 4, 8, 6);

    /** Starting stat values for hero cleric. */
    public static final List<Integer> ATTRIBUTE_VALUES_CLERIC_HERO = Arrays.asList(4, 2, 6, 8);

    /** Starting stat values for enemy skeleton warrior. */
    public static final List<Integer> ATTRIBUTE_VALUES_SKELETON_WARRIOR = Arrays.asList(4, 3, 2, 1);

    /** Starting stat values for enemy skeleton archer. */
    public static final List<Integer> ATTRIBUTE_VALUES_SKELETON_ARCHER = Arrays.asList(3, 4, 2, 1);

    /** Starting stat values for enemy skeleton mage. */
    public static final List<Integer> ATTRIBUTE_VALUES_SKELETON_MAGE = Arrays.asList(2, 3, 4, 1);

    /** Starting stat values for enemy lich lord. */
    public static final List<Integer> ATTRIBUTE_VALUES_LICH_LORD = Arrays.asList(10, 10, 8, 8);

    /* -------------------------
    NUMERIC STAT BASE VALUES.
    ------------------------- */
    /** Vitality base value. Represents HP. */
    public static final int TRAIT_VITALITY_BASE_VALUE = 200;

    /** Energy base value. */
    public static final int TRAIT_ENERGY_BASE_VALUE = 100;

    /** Attack rate base value. */
    public static final int TRAIT_ATTACK_RATE_BASE_VALUE = 30;

    /** Defence rate base value. */
    public static final int TRAIT_DEFENCE_RATE_BASE_VALUE = 30;

    /** Used for calculating value of combat stats. */
    public static final double COMBAT_STAT_MULTIPLIER = .2;

    /** Used for calculating attribute values. */
    public static final int ATTRIBUTE_BASE_VALUE = 10;

    /* -------------------------
    NAMES OF WEAPON TYPES.
    ------------------------- */
    /** Name for axes. */
    public static final String WEAPON_AXE = "Axe";

    /** Name for bows. */
    public static final String WEAPON_BOW = "Bow";

    /** Name for crossbows. */
    public static final String WEAPON_CROSSBOW = "Crossbow";

    /** Name for orbs. */
    public static final String WEAPON_ORB = "Orb";

    /** Name for shields. */
    public static final String WEAPON_SHIELD = "Shield";

    /** Name for staves. */
    public static final String WEAPON_STAFF = "Staff";

    /** Name for swords. */
    public static final String WEAPON_SWORD = "Sword";

    /** Name for tomes. */
    public static final String WEAPON_TOME = "Tome";

    /** Name for wands. */
    public static final String WEAPON_WAND = "Wand";

    /* -------------------------
    NAMES OF ARMOR TYPES.
    ------------------------- */
    /** Name for chest pieces. */
    public static final String ARMOR_CHEST = "Chest";

    /** Name for feet pieces. */
    public static final String ARMOR_FEET = "Feet";

    /** Name for hand pieces. */
    public static final String ARMOR_HANDS = "Hands";

    /** Name for head pieces. */
    public static final String ARMOR_HEAD = "Head";

    /** Name for leg pieces. */
    public static final String ARMOR_LEGS = "Legs";

    /* -------------------------
    NAMES OF ELEMENT TYPES.
    ------------------------- */
    /** Name for fire element. */
    public static final String ELEMENT_FIRE = "Fire";

    /** Name for ice element. */
    public static final String ELEMENT_ICE = "Ice";

    /** Name for air element. */
    public static final String ELEMENT_AIR = "Air";

    /* -------------------------
    MAGICAL PHRASES.
    ------------------------- */
    /** Magical phrase. */
    public static final String MAGICAL_PHRASE_1 = "Abracadabra";

    /** Magical phrase. */
    public static final String MAGICAL_PHRASE_2 = "Hocus Pocus";

    /** Magical phrase. */
    public static final String MAGICAL_PHRASE_3 = "Sim Sala Bim";

    /** Magical phrase. */
    public static final String MAGICAL_PHRASE_4 = "Bibbidi-Bobbidi-Boo";

    /* -------------------------
    ABILITY NAMES.
    ------------------------- */
    /** Name of basic weapon ability. */
    public static final String ABILITY_WEAPON_ATTACK = "Basic Weapon Attack";

    /** Name of heavy weapon ability. */
    public static final String ABILITY_HEAVY_ATTACK = "Heavy Attack";

    /** Name of whirlwind ability. */
    public static final String ABILITY_WHIRLWIND = "Whirlwind";

    /** Name of focused shot ability. */
    public static final String ABILITY_FOCUSED_SHOT = "Focused Shot";

    /** Name of spray of arrows ability. */
    public static final String ABILITY_SPRAY_OF_ARROWS = "Spray of Arrows";

    /** Name of elemental bolt ability. */
    public static final String ABILITY_ELEMENTAL_BOLT = "Bolt";

    /** Name of elemental blast ability. */
    public static final String ABILITY_ELEMENTAL_BLAST = "Blast";

    /** Name of focused heal ability. */
    public static final String ABILITY_FOCUSED_HEAL = "Focused Heal";

    /** Name of group heal ability. */
    public static final String ABILITY_GROUP_HEAL = "Group Heal";

    /* -------------------------
    ABILITY AP COSTS.
    ------------------------- */
    /** Lower boundary of AP cost. */
    public static final int LOWEST_AP_COST = 2;

    /** Medium AP cost. */
    public static final int MEDIUM_AP_COST = 4;

    /** Upper boundary of AP cost. */
    public static final int HIGHEST_AP_COST = 6;

    /* -------------------------
    ABILITY ENERGY COSTS.
    ------------------------- */
    /** Lower boundary of AP cost. */
    public static final int LOW_ENERGY_COST = 20;

    /** Upper boundary of AP cost. */
    public static final int HIGH_ENERGY_COST = 40;

    /* -------------------------
    ABILITY TARGET AMOUNTS.
    ------------------------- */
    /** Single target will always only target one. */
    public static final int ABILITY_SINGLE_TARGET = 1;

    /** Group target amount. */
    public static final int ABILITY_GROUP_TARGET = 3;

    /** Multiply bonus damage for single target abilities. */
    public static final int SINGLE_TARGET_ABILITY_MULTIPLIER = 3;

    /** Limit for crit chance calculations (e.g. 3 will be 30%). */
    public static final int CRIT_CHANCE = 3;

    /** Multiplier for hero critical hits. */
    public static final int HERO_CRIT_MULTIPLIER = 3;

    /** Multiplier for enemy critical hits. */
    public static final int ENEMY_CRIT_MULTIPLIER = 2;

    /** Multiplier for Lich Lord health. */
    public static final int BOSS_HEALTH_MULTIPLIER = 9;

    /** The game will start at this level. */
    public static final int DUNGEON_START_LEVEL = 5;

    /** Each new dungeon level will add this amount of enemies. */
    public static final int ENEMIES_MULTIPLIER = 4;

    /** Amount of abilities to queue for each character's turn. */
    public static final int ACTIONS_PER_TURN = 7;

    /** Amount of Action Points replenished at start of turn. */
    public static final int ROUND_RESET_AP = 3;

    /** Amount of Energy replenished at start of turn. */
    public static final int ROUND_RESET_ENERGY = 10;

    /* -------------------------
    THREAD DELAY.
    ------------------------- */
    /** whether the activity logger should apply a sleep delay. */
    public static final boolean USE_SLEEP_DELAY = true;

    /** amount of ms to sleep between log prints. */
    public static final int SLEEP_DELAY = 200;

    /** used as upper bound for randomising weapon stat value. */
    public static final int WEAPON_ATTRIBUTE_VALUE_UPPER_BOUND = 10;

    /** used as upper bound for randomising armor stat value. */
    public static final int ARMOR_STAT_VALUE_UPPER_BOUND = 15;

    /* ---------------------------------------
    ANSI COLOR CODES. Used to colorize output.
    ------------------------------------------ */
    /** Reset color. */
    public static final String ANSI_RESET = Color.RESET.ansiCode;

    /** Black color. */
    public static final String ANSI_BLACK = Color.BLACK.ansiCode;

    /** Red color. */
    public static final String ANSI_RED = Color.RED.ansiCode;

    /** Green color. */
    public static final String ANSI_GREEN = Color.GREEN.ansiCode;

    /** Yellow color. */
    public static final String ANSI_YELLOW = Color.YELLOW.ansiCode;

    /** Blue color. */
    public static final String ANSI_BLUE = Color.BLUE.ansiCode;

    /** Purple color. */
    public static final String ANSI_PURPLE = Color.PURPLE.ansiCode;

    /** Cyan color. */
    public static final String ANSI_CYAN = Color.CYAN.ansiCode;

    /** White color. */
    public static final String ANSI_WHITE = Color.WHITE.ansiCode;

    /** Magenta color. */
    public static final String ANSI_MAGENTA = Color.MAGENTA.ansiCode;

    private enum Color {
        BLACK("\u001B[30m"), RED("\u001B[31m"), GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"), BLUE("\u001B[34m"), PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"), WHITE("\u001B[37m"), MAGENTA("\u001b[35m"),
        RESET("\u001B[0m");

        private final String ansiCode;

        /**
         * Constructs a new color with the given ANSI escape code.
         * @param ansiCode the ANSI escape code for the color
         */
        Color(final String ansiCode) {
            this.ansiCode = ansiCode;
        }

        /**
         * Returns the ANSI escape code for the color.
         * @return the ANSI escape code
         */
        @Override public String toString() {
            return ansiCode;
        }
    }
}
