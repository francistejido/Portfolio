package ca.qc.cegepheritage.g10a3;

import ca.qc.cegepheritage.g10a3.exceptions.DateRangeParseException;
import ca.qc.cegepheritage.g10a3.exceptions.InvalidCapacityException;
import ca.qc.cegepheritage.g10a3.exceptions.MaxStayExceededException;
import ca.qc.cegepheritage.g10a3.exceptions.OverbookingException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class ReservationService {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate parseDate(String dateString) throws DateRangeParseException {
        if (dateString == null || dateString.trim().isEmpty()) {
            throw new DateRangeParseException("Date cannot be empty or null.");
        }

        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new DateRangeParseException("Invalid date format. Please use 'YYYY-MM-DD' format.");
        }
    }

    public static void validateDateRange(LocalDate startDate, LocalDate endDate) throws DateRangeParseException {
        if (startDate == null || endDate == null) {
            throw new DateRangeParseException("Start date and end date cannot be null");
        }
        if (startDate.equals(endDate)) {
            throw new DateRangeParseException("Start date and end date cannot be the same.");
        }
        if (startDate.isAfter(endDate)) {
            throw new DateRangeParseException("Start date must be before end date");
        }
        if (startDate.isBefore(LocalDate.now())) {
            throw new DateRangeParseException("Start date cannot be in the past.");
        }
    }

    public static void validatePartySize(int partySize, Campsite campsite) throws InvalidCapacityException {
        if (partySize < 1) {
            throw new InvalidCapacityException("Party size must be at least 1");
        }
        if (!campsite.isValidPartySize(partySize)) {
            throw new InvalidCapacityException(String.format("Party size (%d) exceeds campsite capacity (%d).",
                    partySize, campsite.getCampsiteMaxPartySize()));
        }
    }

    public static int calculateNights(LocalDate startDate, LocalDate endDate) {
        return (int) ChronoUnit.DAYS.between(startDate, endDate);
    }

    public static double calculateTotalPrice(int numberOfNights, double costPerNight) {
        return numberOfNights * costPerNight;
    }

    public static boolean isSiteAvailable(String campsiteId, LocalDate startDate, LocalDate endDate) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FileUtil.RESERVATIONS_FILE));

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

                String campsiteIdString = line.substring(secondComma + 1, thirdComma).trim();
                if (campsiteIdString.equals(campsiteId)) {
                    String startDateString = line.substring(thirdComma + 1, fourthComma).trim();
                    LocalDate existingStartDate = ReservationService.parseDate(startDateString);

                    String endDateString = line.substring(fourthComma + 1, fifthComma).trim();
                    LocalDate existingEndDate = ReservationService.parseDate(endDateString);
                    if (startDate.isBefore(existingEndDate) && existingStartDate.isBefore(endDate)) {
                        reader.close();
                        return false;
                    }
                }
            }
            reader.close();
            return true;
        } catch(IOException e) {
            System.out.println("Error reading reservations file: " + e.getMessage());
            return false;
        } catch(DateTimeParseException e) {
            System.out.println("Error validating date from reservations file: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error checking availability: " + e.getMessage());
            return false;
        }
    }

    public static Reservation validateAndCreateRequest(Campground campground, Campsite campsite, LocalDate startDate, LocalDate endDate, int partySize) throws DateRangeParseException, InvalidCapacityException, OverbookingException, MaxStayExceededException {
        validateDateRange(startDate, endDate);
        validatePartySize(partySize, campsite);
        int numberOfNights = calculateNights(startDate, endDate);

        if (!campground.isValidNumberOfNights(numberOfNights)) {
            throw new MaxStayExceededException(String.format("Number of night (%d) exceeds maximum stay (%d) for this campground", numberOfNights, campground.getMaxNumberOfNights()));
        }

        if (!isSiteAvailable(campsite.getCampsiteId(), startDate, endDate)) {
            throw new OverbookingException("This campsite is not available for the selected dates");
        }

        double totalPrice = calculateTotalPrice(numberOfNights, campground.getCostPerNight());
        String reservationId = "RES" + System.currentTimeMillis();
        return new Reservation(reservationId, campground.getCampgroundId(), campsite.getCampsiteId(), startDate, endDate, partySize, totalPrice);
    }
}
