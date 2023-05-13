package PlanningApp.Model;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.TreeMap;

    public class DayMap {
        private TreeMap<DayKey, Day> days;

        public DayMap() {
            days = new TreeMap<>();
        }

        public TreeMap<DayKey, Day> getDays() {
            return days;
        }

        public void fillYear(int year) {
            LocalDate startDate = LocalDate.of(year, 1, 1);
            LocalDate endDate = LocalDate.of(year, 12, 31);

            for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
                DayKey key = new DayKey(date);
                days.put(key, new Day(date.toString()));
            }
        }

        private static class DayKey implements Comparable<DayKey> {
            private LocalDate date;
            private String weekday;

            public DayKey(LocalDate date) {
                this.date = date;
                this.weekday = date.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());
            }
            @Override
            public int compareTo(DayKey o) {
                return date.compareTo(o.date);
            }
            @Override
            public String toString() {
                return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " " + weekday;
            }
        }
    }




