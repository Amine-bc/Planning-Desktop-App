package PlanningApp.Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TreeMap;

    public class DayMap {
        private TreeMap<String, Day> days;

        public DayMap() {
            days = new TreeMap<>();
        }
        public TreeMap<String, Day> getDays() {
            return days;
        }
        public void fillYear(int year) {
            LocalDate startDate = LocalDate.of(year, 1, 1);
            LocalDate endDate = LocalDate.of(year, 12, 31);

            for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
                String key = date+date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
                days.put(key.toString(), new Day(date.toString()));
            }
        }


    }




