package ca.qc.cegepheritage.g10a3;

import ca.qc.cegepheritage.g10a3.exceptions.InvalidCommandException;

import java.io.*;
import java.time.LocalDate;


public class FileUtil {

    public static final String CAMPGROUNDS_FILE = "campgrounds.txt";
    public static final String CAMPSITES_FILE = "campsites.txt";
    public static final String RESERVATIONS_FILE = "reservations.txt";

    public static final String FONT_PURPLE = "\u001B[35m";
    public static final String FONT_CYAN = "\u001B[36m";
    public static final String FONT_RESET = "\u001B[0m";
    public static final String FONT_GREEN = "\u001B[33m";
    public static final String FONT_RED = "\u001B[31m";

    public static Campground getCampgroundById(String campgroundId) throws InvalidCommandException {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMPGROUNDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int firstComma = line.indexOf(',');
                if (firstComma == -1) continue;

                int secondComma = line.indexOf(',', firstComma + 1);
                if (secondComma == -1) continue;

                int thirdComma = line.indexOf(',', secondComma + 1);
                if (thirdComma == -1) continue;


                String campgroundIdString = line.substring(0, firstComma).trim();
                if (campgroundIdString.equals(campgroundId)) {
                    String campgroundName = line.substring(firstComma + 1, secondComma).trim();
                    double costPerNight = Double.parseDouble(line.substring(secondComma + 1, thirdComma).trim());
                    int maxNumberOfNights = Integer.parseInt(line.substring(thirdComma + 1).trim());
                    return new Campground(campgroundId, campgroundName, costPerNight, maxNumberOfNights);
                }
            }
            throw new InvalidCommandException(FONT_RED + "Campground ID not found: " + campgroundId + FONT_RESET);
        } catch (IOException e) {
            throw new InvalidCommandException(FONT_RED + "Error reading campgrounds file: " + e.getMessage() + FONT_RESET);
        }
    }

    public static Campsite getCampsiteById(String campsiteId) throws InvalidCommandException {
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMPSITES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int firstComma = line.indexOf(',');
                if (firstComma == -1) continue;

                int secondComma = line.indexOf(',', firstComma + 1);
                if (secondComma == -1) continue;

                int thirdComma = line.indexOf(',', secondComma + 1);
                if (thirdComma == -1) continue;

                String campsiteIdString = line.substring(0, firstComma).trim();
                if (campsiteIdString.equals(campsiteId)) {
                    String campgroundId = line.substring(firstComma + 1, secondComma).trim();
                    String campsiteName = line.substring(secondComma + 1, thirdComma).trim();
                    int campsiteMaxPartySize = Integer.parseInt(line.substring(thirdComma + 1).trim());
                    return new Campsite(campsiteId, campgroundId, campsiteName, campsiteMaxPartySize);
                }
            }
            throw new InvalidCommandException(FONT_RED + "Campsite ID not found: " + campsiteId + FONT_RESET);
        } catch (IOException e) {
            throw new InvalidCommandException(FONT_RED + "Error reading campsite file: " + e.getMessage() + FONT_RESET);
        }
    }

    public static void appendReservation(Reservation reservation) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(RESERVATIONS_FILE, true))) {

            String line = String.format("%s,%s,%s,%s,%s,%d,%.2f", reservation.getReservationId(), reservation.getCampgroundId(), reservation.getCampsiteId(), reservation.getStartDate(), reservation.getEndDate(), reservation.getPartySize(), reservation.getTotalPrice());
            writer.println(line);
        } catch (IOException e) {
            System.out.println(FONT_RED + "Error writing to reservations file: " + e.getMessage() + FONT_RESET);
        }
    }

    public static void printAllCampgrounds() {
        System.out.println(FONT_PURPLE + "\nCampgrounds:" + FONT_RESET);
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMPGROUNDS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int firstComma = line.indexOf(',');
                if (firstComma == -1) continue;

                int secondComma = line.indexOf(',', firstComma + 1);
                if (secondComma == -1) continue;

                int thirdComma = line.indexOf(',', secondComma + 1);
                if (thirdComma == -1) continue;

                String campgroundId = line.substring(0, firstComma).trim();
                String campgroundName = line.substring(firstComma + 1, secondComma).trim();
                double costPerNight = Double.parseDouble(line.substring(secondComma + 1, thirdComma).trim());
                int maxNumberOfNights = Integer.parseInt(line.substring(thirdComma + 1).trim());

                Campground campground = new Campground(campgroundId,campgroundName, costPerNight, maxNumberOfNights);
                System.out.println(FONT_CYAN + campground.display() + FONT_RESET);
            }
        } catch (IOException e) {
            System.out.println(FONT_RED + "Error reading campgrounds file: " + e.getMessage() + FONT_RESET);
        }
    }

    public static void printAllCampsites() {
        System.out.println(FONT_PURPLE + "\nCampsites:" + FONT_RESET);
        try (BufferedReader reader = new BufferedReader(new FileReader(CAMPSITES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int firstComma = line.indexOf(',');
                if (firstComma == -1) continue;

                int secondComma = line.indexOf(',', firstComma + 1);
                if (secondComma == -1) continue;

                int thirdComma = line.indexOf(',', secondComma + 1);
                if (thirdComma == -1) continue;

                String campsiteId = line.substring(0, firstComma).trim();
                String campgroundId = line.substring(firstComma + 1, secondComma).trim();
                String campsiteName = line.substring(secondComma + 1, thirdComma).trim();
                int campsiteMaxPartySize = Integer.parseInt(line.substring(thirdComma + 1).trim());

                Campsite campsite = new Campsite(campsiteId, campgroundId, campsiteName, campsiteMaxPartySize);
                System.out.println(FONT_CYAN + campsite.display() + FONT_RESET);
            }
        } catch (IOException e) {
            System.out.println(FONT_RED + "Error reading campsite file: " + e.getMessage() + FONT_RESET);
        }
    }

    public static void printAllReservations() {
        System.out.println(FONT_PURPLE + "\nReservations:" + FONT_RESET);
        try (BufferedReader reader = new BufferedReader(new FileReader(RESERVATIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int firstComma = line.indexOf(',');
                if (firstComma == -1) continue;

                int secondComma = line.indexOf(',', firstComma + 1);
                if (secondComma == -1) continue;

                int thirdComma = line.indexOf(',', secondComma + 1);
                if (thirdComma == -1) continue;

                int fourthComma = line.indexOf(',', thirdComma + 1);
                if (fourthComma == -1) continue;

                int fifthComma = line.indexOf(',', fourthComma + 1);
                if (fifthComma == -1) continue;

                int sixthComma = line.indexOf(',', fifthComma + 1);
                if (sixthComma == -1) continue;

                String reservationId = line.substring(0, firstComma).trim();
                String campgroundId = line.substring(firstComma + 1, secondComma).trim();
                String campsiteId = line.substring(secondComma + 1, thirdComma).trim();
                LocalDate startDate = ReservationService.parseDate(line.substring(thirdComma + 1, fourthComma));
                LocalDate endDate = ReservationService.parseDate(line.substring(fourthComma + 1, fifthComma));
                int partySize = Integer.parseInt(line.substring(fifthComma + 1, sixthComma).trim());
                double totalPrice = Double.parseDouble(line.substring(sixthComma + 1).trim());

                Reservation reservation = new Reservation(reservationId, campgroundId, campsiteId, startDate, endDate, partySize,totalPrice);
                System.out.println(FONT_CYAN + reservation.display() + FONT_RESET);
            }
        } catch (IOException e) {
            System.out.println(FONT_RED + "Error reading reservations file: " + e.getMessage() + FONT_RESET);
        }
    }

}
