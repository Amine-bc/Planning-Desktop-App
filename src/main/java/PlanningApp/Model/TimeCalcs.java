package PlanningApp.Model;

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

}
