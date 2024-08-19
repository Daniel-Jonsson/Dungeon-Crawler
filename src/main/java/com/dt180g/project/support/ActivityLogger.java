package com.dt180g.project.support;

import java.util.Arrays;
import java.util.logging.*;

/**
 * The {@code ActivityLogger} class is responsible for logging activity information during the game.
 * It provides methods to log various types of information such as round information, turn information,
 * attack information, damage information, death information, and healing information.
 * The logger uses a simple console handler for logging and can be configured to introduce a sleep delay
 * between log entries for better readability.
 *
 * @author Daniel Jönsson
 * @version 1.0
 */
public class ActivityLogger {
    /**Instance field variables*/
    public static final ActivityLogger INSTANCE = new ActivityLogger();
    private final Logger logger;

    /**
     * Constructs a new {@code ActivityLogger} object.
     * This is a singleton class and should be accessed through the {@code INSTANCE} field varaible.
     * The logger is configured with a console handler that outputs log messages to the console.
     * A custom log formatter is used to format log records.
     */
    private ActivityLogger(){
        logger = Logger.getLogger("ActivityLogger");
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord record) {
                return record.getMessage() + "\n";
            }
        });
        logger.addHandler(consoleHandler);
    }

    /**
     * Delays the execution by the configured sleep delay duration.
     */
    private void delayExecution(){
        try {
            Thread.sleep(AppConfig.SLEEP_DELAY);
        } catch (InterruptedException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * Performs the logging of the provided log message.
     *
     * @param logMessage the log message to be logged
     */
    private void performLog(String logMessage){
        logger.log(Level.INFO, logMessage);
        if (AppConfig.USE_SLEEP_DELAY){ delayExecution(); }
    }

    /**
     * Logs the round information with a purple color for better user-experience.
     *
     * @param roundInfo the round information to be logged
     */
    public void logRoundInfo(String roundInfo){
        performLog(AppConfig.ANSI_PURPLE + roundInfo + AppConfig.ANSI_RESET);
    }

    /**
     * Logs the turn information with a blue color for better user-experience.
     *
     * @param turnInfo the turn information to be logged
     */
    public void logTurnInfo(String turnInfo){
        performLog(String.format("\n%s%s%s", AppConfig.ANSI_BLUE, turnInfo, AppConfig.ANSI_RESET));
    }

    /**
     * Logs the attack information with a green color for better user-experience.
     *
     * @param attackInfo the attack information to be logged
     */
    public void logAttack(String attackInfo){
        performLog(String.format("\t%s%s%s", AppConfig.ANSI_GREEN, attackInfo, AppConfig.ANSI_RESET));
    }

    /**
     * Logs the damage information with a yellow color for better user-experience.
     *
     * @param damageInfo the damage information to be logged
     */
    public void logDamage(String damageInfo){
        performLog(String.format("\t\t%s%s%s", AppConfig.ANSI_YELLOW, damageInfo, AppConfig.ANSI_RESET));
    }

    /**
     * Logs the death information with a red color for better user-experience.
     *
     * @param deathInfo the death information to be logged
     */
    public void logDeath(String deathInfo){
        performLog(String.format("\t\t%s%s%s", AppConfig.ANSI_RED, deathInfo, AppConfig.ANSI_RESET));
    }

    /**
     * Logs the healing information with a green color for better user-experience.
     *
     * @param healInfo the healing information to be logged
     */
    public void logHealing(String healInfo){
        performLog(String.format("\t\t%s%s%s", AppConfig.ANSI_GREEN, healInfo, AppConfig.ANSI_RESET));
    }

}
