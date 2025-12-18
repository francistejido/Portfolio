package ca.qc.cegepheritage.g10a3;

import ca.qc.cegepheritage.g10a3.exceptions.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    enum Commands {
        LIST,
        RESERVE,
        MY,
        QUIT
    }

    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_GREEN = "\u001B[33m";
    public static final String FONT_RED = "\u001B[31m";
    public static final String FONT_PURPLE = "\u001B[35m";
    public static final String FONT_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println(FONT_PURPLE + "***************************************************");
        System.out.println("* Welcome to the Camping Reservation System! *");
        System.out.println("***************************************************" + FONT_RESET);
        System.out.println(FONT_CYAN + "Please enter a command:" + FONT_RESET);
        System.out.printf(FONT_PURPLE + "%-12s %s%n", "list", "- list campgrounds and sites");
        System.out.printf("%-8s %s%n", "reserve", "- make a reservation");
        System.out.printf("%-9s %s%n", "my", "- view reservations");
        System.out.printf("%-9s %s%n", "quit", "- exit the system" + FONT_RESET);
        System.out.printf(FONT_CYAN + "\n(Type a command and press Enter)\n" + FONT_RESET);

        while (running) {
            System.out.print("\nEnter command: ");
            String input = scanner.nextLine().trim().toUpperCase();

            try {
                Commands command = Commands.valueOf(input);

                switch (command) {
                    case LIST:
                        handleListCommand();
                        break;

                    case RESERVE:
                        handleReserveCommand(scanner);
                        break;

                    case MY:
                        handleMyCommand();
                        break;

                    case QUIT:
                        System.out.println(FONT_GREEN + "Thank you for using the Camping Reservation System. Goodbye!");
                        running = false;
                        break;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(FONT_RED + "Invalid command. Please enter: list, reserve, my, or quit" + FONT_RESET);
            } catch (InvalidCommandException e) {
                System.out.println(FONT_RED + e.getMessage() + FONT_RESET);
            } catch (DateRangeParseException e) {
                System.out.println(FONT_RED + e.getMessage() + FONT_RESET);
            } catch (InvalidCapacityException e) {
                System.out.println(FONT_RED + "Capacity mismatch: " + e.getMessage() + FONT_RESET);
            } catch (OverbookingException e) {
                System.out.println(FONT_RED + "Availability: " + e.getMessage() + FONT_RESET);
            } catch (MaxStayExceededException e) {
                System.out.println(FONT_RED + "Stay Duration: " + e.getMessage() + FONT_RESET);
            } catch (Exception e) {
                System.out.println(FONT_RED +  e.getMessage() + FONT_RESET);
            }
        }

        scanner.close();
    }

    private static void handleListCommand() {
        FileUtil.printAllCampgrounds();
        FileUtil.printAllCampsites();
    }


    private static void handleMyCommand() {
        FileUtil.printAllReservations();
    }

    private static void handleReserveCommand(Scanner scanner)
            throws InvalidCommandException, DateRangeParseException,
            InvalidCapacityException, OverbookingException, MaxStayExceededException {

        System.out.print("\nEnter campsite ID: ");
        String campsiteId = scanner.nextLine().trim();

        Campsite campsite = FileUtil.getCampsiteById(campsiteId);
        Campground campground = FileUtil.getCampgroundById(campsite.getCampgroundId());

        System.out.print("Enter start date (YYYY-MM-DD): ");
        String startDateStr = scanner.nextLine().trim();
        LocalDate startDate = ReservationService.parseDate(startDateStr);

        System.out.print("Enter end date (YYYY-MM-DD): ");
        String endDateStr = scanner.nextLine().trim();
        LocalDate endDate = ReservationService.parseDate(endDateStr);

        System.out.print("Enter party size: ");
        int partySize;
        try {
            partySize = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidCapacityException(FONT_RED + "Party size must be a valid number" + FONT_RESET);
        }

        Reservation reservation = ReservationService.validateAndCreateRequest(campground, campsite, startDate, endDate, partySize);
        int nights = ReservationService.calculateNights(startDate, endDate);
        double costPerNight = campground.getCostPerNight();
        double totalPrice = ReservationService.calculateTotalPrice(nights, costPerNight);

        System.out.println(FONT_PURPLE + "\nPreview:" + FONT_RESET);
        System.out.println(FONT_CYAN + "\t" + nights + " nights at $" + String.format("%.2f", costPerNight) + "/night = $" + String.format("%.2f", totalPrice));
        System.out.println("\tMax stay: " + campground.getMaxNumberOfNights() + " nights | Capacity: " + campsite.getCampsiteMaxPartySize() + " | Party Size: " + reservation.getPartySize() + FONT_RESET);
        System.out.print("\nConfirm reservation? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            FileUtil.appendReservation(reservation);
            System.out.println(FONT_PURPLE + "\nReservation confirmed:" + FONT_RESET);
            System.out.println(FONT_CYAN + reservation.display() + FONT_RESET);
        } else {
            System.out.println(FONT_RED + "\nReservation cancelled." + FONT_RESET);
        }
    }
}
