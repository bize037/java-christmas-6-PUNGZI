package christmas.common.constants;

import java.util.Arrays;
import java.util.List;

public enum DayOfWeek {
    FRIDAY("금"),
    SATURDAY("토"),
    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목");

    private final String week;

    DayOfWeek(String week) {
        this.week = week;
    }

    public String getWeek() {
        return week;
    }

    public static List<DayOfWeek> getAllDayOfWeek() {
        return Arrays.asList(DayOfWeek.values());
    }
}
