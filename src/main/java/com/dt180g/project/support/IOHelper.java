package com.dt180g.project.support;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Support class containing procedures for I/O operations.
 * @author Erik Ström
 */
public final class IOHelper {
    private static final PrintStream OUT = System.out;  // our standard output stream
    private static final Scanner IN = new Scanner(System.in);  // our standard input stream
    private static final String WELCOME_HEADER = loadHeader("header_welcome.txt", "DT180G DUNGEON");
    private static final String GAME_OVER_HEADER = loadHeader("header_game_over.txt", "GAME OVER");
    private static final String GAME_COMPLETED_HEADER = loadHeader("header_game_completed.txt", "GAME COMPLETED");

    private IOHelper() { }

    /**
     * Helper method used internally to load ANSI text headers from resource files.
     * @param fileName name of file containing ANSI text.
     * @param defaultText header value to use as default if ANSI text cannot be loaded.
     * @return header value.
     */
    private static String loadHeader(final String fileName, final String defaultText) {
        String text = defaultText;

        try (InputStream inputStream = IOHelper.class.
                getResourceAsStream("/" + fileName)) {
            assert inputStream != null;
            text = new String(inputStream.readAllBytes());
        } catch (IOException | AssertionError e) {
            e.printStackTrace();
        }
        return text;
    }

    /**
     * Helper method used to retrieve data from JSON files.
     * @param fileName name of JSON file containing target data.
     * @return list of mapped values from file.
     */
    public static List<Map<String, String>> readFromFile(final String fileName) {
        List<Map<String, String>> output;

        try {
            JSONObject jsonData = loadJsonFile(fileName);

            // Iterate through the keys in the jsonData JSONObject using the Stream API
            output = jsonData.keySet().stream()
                    .flatMap(key -> {
                        // Get the JSONArray associated with the current key
                        JSONArray jsonArray = jsonData.getJSONArray(key);

                        // Create an IntStream to iterate through the indices of the jsonArray
                        return IntStream.range(0, jsonArray.length())
                                .mapToObj(i -> {
                                    // Get the JSONObject at the current index
                                    JSONObject item = jsonArray.getJSONObject(i);

                                    // Create a map from item JSONObject by iterating through its keys
                                    Map<String, String> itemDetails = item.keySet().stream()
                                            .collect(Collectors.toMap(
                                                    itemKey -> itemKey,    // Key in the output map
                                                    item::getString // Value in the output map
                                            ));

                                    // Add the "type" key to the itemDetails map
                                    itemDetails.put("type", key);

                                    // Return the itemDetails map as a Stream element
                                    return itemDetails;
                                });
                    })
                    .collect(Collectors.toList()); // Collect the Stream elements into a List

        } catch (IOException e) {
            e.printStackTrace();
            output = new ArrayList<>();
        }

        return output;
    }


    private static JSONObject loadJsonFile(final String fileName) throws IOException {
        try (InputStream inputStream = IOHelper.class.getResourceAsStream("/" + fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }

            byte[] jsonData = inputStream.readAllBytes();
            return new JSONObject(new String(jsonData, StandardCharsets.UTF_8));
        }
    }

    /**
     * Support method to handle and validate numeric inputs.
     * @param min lower boundary of numeric limit.
     * @param max higher boundary of numeric limit.
     * @return the value provided by user.
     */
    public static int getInput(final int min, final int max) {
        int input = -1;
        while (true) {
            try {
                OUT.print("> ");
                input = Integer.parseInt(IN.next());
                if (input > max || input < min) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException | NumberFormatException ex) {
                String msg = String.format("Sorry, only integer values between %d and %d are allowed!", min, max);
                OUT.println(msg);
            }
        }

        return input;
    }

    /**
     * Support method used for outputting menu options on screen.
     * @param it the iterator to be used.
     */
    public static void printOptionItems(final ListIterator<?> it) {
        while (it.hasNext()) {  // we utilize the overloaded 'toString()' for printout
            OUT.println(it.nextIndex() + 1 + ". " + it.next());
        }
    }

    /**
     * Support method used to format rows and columns in table fashion for output.
     * @param data the table data to be processed.
     * @return the table data formatted in single string value.
     */
    public static String formatAsTable(final List<List<String>> data) {
        // Calculate maxLengths using the original loop-based approach
        int[] maxLengths = new int[data.get(0).size()];
        data.forEach(row -> {
            for (int i = 0; i < row.size(); i++) {
                maxLengths[i] = Math.max(maxLengths[i], row.get(i).length());
            }
        });

        // Build format String using Stream API
        String format = Arrays.stream(maxLengths)
                .mapToObj(len -> "%-" + (len + 2) + "s")
                .collect(Collectors.joining());

        // Format the rows using Stream API and join them with line separators
        return data.stream()
                .map(row -> String.format(format, row.toArray(Object[]::new)))
                .collect(Collectors.joining(System.lineSeparator()));
    }

    /** Prints welcome header, used by client during application start. */
    public static void printWelcomeHeader() {
        OUT.print(AppConfig.ANSI_BLUE + WELCOME_HEADER + AppConfig.ANSI_RESET);
    }

    /** Prints game over header, used by client to illustrate failed game. */
    public static void printGameOverHeader() {
        OUT.print(AppConfig.ANSI_RED + GAME_OVER_HEADER + AppConfig.ANSI_RESET);
    }

    /** Prints completion header, used by client to illustrate successful game. */
    public static void printGameCompletedHeader() {
        OUT.print(AppConfig.ANSI_GREEN + GAME_COMPLETED_HEADER + AppConfig.ANSI_RESET);
    }
}
