package ca.qc.cegepheritage.g10a2;

import java.util.Scanner;
import java.io.*;

/**
 * Kiko's Hiking Trail Navigator
 * <p>
 * This is a text-based hiking adventure simulation that lets users issue commands (go, look, help, reset, quit)
 * to explore a virtual trail system. The player navigates through locations (Trailhead, Forest, Ridge, Waterfall, Summit)
 * by issuing direction-based commands (north, south, east, west).
 * <p>
 * Journey history, coloration, and simple palindrome/backtracking logic are incorporated for dynamic responses.
 * <p>
 * Usage:
 * <ul>
 *     <li>Type 'help' for available commands and tips.</li>
 *     <li>Move using 'go {direction}'. Directions allowed: north, south, east, west.</li>
 *     <li>Type 'look' to observe your surroundings.</li>
 *     <li>Type 'reset' to return to the trailhead.</li>
 *     <li>Type 'quit' to exit the program.</li>
 * </ul>
 *
 * @author Francis "Kiko" Tejido
 * @version 1.0
 */

public class Main {
    enum Action {
        GO,
        LOOK,
        HELP,
        QUIT,
        RESET,
        INVALID
    }

    enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST,
        INVALID
    }

    //Font and Background color + Font Format
    public static final String Font_Reset = "\u001B[0m";
    public static final String Font_Black = "\u001B[30m";
    public static final String Background_Green = "\u001B[42m";
    public static final String Font_Yellow = "\u001B[33m";
    public static final String Font_Red = "\u001B[31m";
    public static final String Font_Italic = "\u001B[3m";
    public static final String Font_Green = "\u001B[32m";

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String location = ("Trailhead");
        String journey = "";
        boolean running = true;

        System.out.println("\n" + Font_Black + Background_Green + "Welcome to Kiko's Hiking Trail Navigator!!" + Font_Reset + "\n");
        System.out.println("You are at the " + Font_Green + location + Font_Reset + ".");
        System.out.println("Type " + Font_Yellow + Font_Italic + "'help'" + Font_Reset + " to see available commands.\n");

        do {
            System.out.print("Enter command: ");
            String command = input.nextLine().trim().toLowerCase();

            //Find space using a For loop
            int spaceIndex = -1;
            int index;
            for (index = 0; index < command.length(); index++)
                if (command.charAt(index) == ' ') {
                    spaceIndex = index;
                    break;
                }

            //Splits the input into verb and direction
            String verb = (spaceIndex == -1) ? command : command.substring(0, spaceIndex);
            String secondWord = (spaceIndex == -1) ? "" : command.substring(spaceIndex).trim();

            //Switch statement with enums for the action
            Action action = Action.INVALID;
            switch (verb) {
                case "go":
                    action = Action.GO;
                    break;
                case "look":
                    action = Action.LOOK;
                    break;
                case "help":
                    action = Action.HELP;
                    break;
                case "quit":
                    action = Action.QUIT;
                    break;
                case "reset":
                    action = Action.RESET;
                    break;
                default:
                    System.out.println(Font_Red + "Invalid command '" + command + "'. Try " + Font_Yellow + "'help'." + Font_Reset + "\n");
                    //Continue to next iteration and prompt again.
                    continue;
            }

            //
            switch (action) {
                case HELP: //Help menu and tips
                    System.out.println("\n" + Font_Black + Background_Green + "Commands:" + Font_Reset);
                    System.out.printf("%-31s%s%n", Font_Yellow + "go <north|south|east|west>", Font_Reset + "- hike in a direction");
                    System.out.printf("%-48s%s%n", Font_Yellow + "look", Font_Reset + "- observe the surroundings");
                    System.out.printf("%-48s%s%n", Font_Yellow + "help", Font_Reset + "- show commands and tips");
                    System.out.printf("%-49s%s%n", Font_Yellow + "quit", Font_Reset + "- exit the navigator");
                    System.out.printf("%-48s%s%n", Font_Yellow + "reset", Font_Reset + "- restart from the beginning");
                    System.out.println("\n" + Font_Black + Background_Green + "Tips:" + Font_Reset);
                    System.out.println("Try hiking north a few times to reach the " + Font_Green + "ridge" + Font_Reset + ".");
                    System.out.println("Explore east then north to discover the " + Font_Green + "waterfall" + Font_Reset + ".");
                    System.out.println("A longer journey ending north might lead to the " + Font_Green + "summit" + Font_Reset + ".");
                    System.out.println("Retrace steps (palindrome journey like NEN) to return to the " + Font_Green + "trailhead" + Font_Reset + ".");
                    System.out.println("If you're feeling lost, try the " + Font_Yellow + "'reset'" + Font_Reset + " command to go back to the starting point.\n");
                    break;
                case LOOK: //Location-specific surroundings
                    System.out.print("You take a moment to look around. ");
                    switch (location) {
                        case "Trailhead":
                            System.out.print("At the " + Font_Green + "trailhead" + Font_Reset + ": look at the maps, sign the logbook, and fillup your water.\n\n");
                            break;
                        case "Forest":
                            System.out.print("In the " + Font_Green + "forest" + Font_Reset + ": dappled light, birdsong, and soft earth underfoot.\n\n");
                        case "Ridge":
                            System.out.print("On the " + Font_Green + "ridge" + Font_Reset + ": distant lakes sparkle, fog covers the distant mountains, eagles soar.\n\n");
                            break;
                        case "Waterfall":
                            System.out.print("At the " + Font_Green + "waterfall" + Font_Reset + ": cool mist, mossy rocks, echoes of falling water.\n\n");
                            break;
                        case "Summit":
                            System.out.print(Font_Green + "Summit " + Font_Reset + "sights: jagged peaks, cairns to follow, and 360 views!!\n\n");
                            break;
                    }
                    break;
                case QUIT: //Goodbye message, end loop
                    System.out.println(Font_Yellow +"\n********************************");
                    System.out.println(Font_Reset + Font_Green + "Thanks for hiking!! Safe travels." + Font_Reset);
                    System.out.println(Font_Yellow +"********************************" + Font_Reset);
                    running = false;
                    break;
                case RESET: // Restart from the beginning
                    System.out.println(Font_Green + "Instantly guiding you back to base camp." + Font_Reset + "\n");
                    journey = "";
                    location = "Trailhead";
                    break;
                case GO:
                    Direction direction = Direction.INVALID;
                    switch (secondWord) { //Correlate Direction enum based on the value of string secondWord
                        case "north":
                            direction = Direction.NORTH;
                            break;
                        case "south":
                            direction = Direction.SOUTH;
                            break;
                        case "east":
                            direction = Direction.EAST;
                            break;
                        case "west":
                            direction = Direction.WEST;
                            break;
                        default:
                            break;
                    }
                    if (direction == Direction.INVALID) {
                        System.out.println(Font_Red + "Please specify a valid direction: " + Font_Yellow + "north, south, east, or west." + Font_Reset + "\n");
                        break;
                    }//Append a single-letter character based on direction and update journey
                    switch (direction) {
                        case NORTH:
                            journey += "N";
                            break;
                        case SOUTH:
                            journey += "S";
                            break;
                        case EAST:
                            journey += "E";
                            break;
                        case WEST:
                            journey += "W";
                            break;
                        default:
                            break;
                    }//Check palindrome
                    boolean isPalindrome = true;
                    for (int indexPalindrome = 0; indexPalindrome < journey.length() / 2; indexPalindrome++) {
                        if (journey.charAt(indexPalindrome) != journey.charAt(journey.length() - 1 - indexPalindrome)) {
                            isPalindrome = false;
                            break;
                        }
                    }//Check if E occurs before N
                    int eastIndex = -1, northIndex = -1;
                    for (int twoIndexes = 0; twoIndexes < journey.length(); twoIndexes++) {
                        if (eastIndex == -1 && journey.charAt(twoIndexes) == 'E') {
                            eastIndex = twoIndexes;
                        }
                        if (journey.charAt(twoIndexes) == 'N') {
                            northIndex = twoIndexes;
                            break;
                        }
                    }// Location logic
                    //First valid go 'X' action without meeting any of the priorities
                    if (journey.length() == 1) {
                        location = "Forest";
                        System.out.println("You have left the " + Font_Green + "trailhead" + Font_Reset + ".\n");
                    } else {
                        //Priority 1: journey >=5 and last segment is 'N'
                        if (journey.length() >= 5 && journey.charAt(journey.length() - 1) == 'N') {
                            location = "Summit";
                            //Priority 2: isPalindrome and journey >=3
                        } else if (isPalindrome && journey.length() >= 3) {
                            location = "Trailhead";
                            //Priority 3: E before N
                        } else if (eastIndex != -1 && northIndex != -1 && eastIndex < northIndex) {
                            location = "Waterfall";
                            //Priority 4: journey contains 'NN'
                        } else if (journey.contains("NN")) {
                            location = "Ridge";
                            //Priority 5: None of the above rules apply
                        } else {
                            location = "Forest";
                        }
                    }//Counter for each direction taken
                    int northCount = 0, southCount = 0, eastCount = 0, westCount = 0;
                    for (int indexCount = 0; indexCount < journey.length(); indexCount++) {
                        switch (journey.charAt(indexCount)) {
                            case 'N':
                                northCount++;
                                break;
                            case 'S':
                                southCount++;
                                break;
                            case 'E':
                                eastCount++;
                                break;
                            case 'W':
                                westCount++;
                                break;
                        }
                    }// Print location, segments based on journey.length() and counter for each direction
                    System.out.println("You are at the " + Font_Green + location + Font_Reset + ". Journey = " + journey);
                    System.out.printf("Segments: %d | N:%d S:%d E:%d W:%d\n\n", journey.length(), northCount, southCount, eastCount, westCount);
            }

        }
        while (running);

    }
}