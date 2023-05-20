package PlanningApp.Model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public interface TimeCalcs {


    /**
     * Adds a time period to a given time.
     *
     * @param time the time to add the period to, in the format "HH:mm".
     * @param period the time period to add, in the format "HH:mm".
     * @return the resulting time as a String in the format "HH:mm".
     */
    public default String add(String time, String period) {
        LocalTime localTime1 = LocalTime.parse(time);
        LocalTime localTime2 = LocalTime.parse(period);
        LocalTime result = localTime1.plusHours(localTime2.getHour()).plusMinutes(localTime2.getMinute());
        return result.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    /**
     * Subtracts a time period from a given time.
     *
     * @param time the time to subtract the period from, in the format "HH:mm".
     * @param period the time period to subtract, in the format "HH:mm".
     * @return the resulting time as a String in the format "HH:mm".
     */
    public default String subtract(String time, String period) {
        LocalTime localTime1 = LocalTime.parse(time);
        LocalTime localTime2 = LocalTime.parse(period);
        LocalTime result = localTime1.minusHours(localTime2.getHour()).minusMinutes(localTime2.getMinute());
        return result.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    public default String getNextDay(String day) {
        // Define the date formatter for input and output formats
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddEEEE");

        // Parse the input day into a LocalDate object
        LocalDate date = LocalDate.parse(day, formatter);

        // Get the next day by adding one day to the parsed date
        LocalDate nextDate = date.plusDays(1);

        // Format the next day using the formatter and return it as a string
        return nextDate.format(formatter);
    }

}
