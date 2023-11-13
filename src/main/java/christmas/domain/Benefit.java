package christmas.domain;

import christmas.common.constants.DayOfWeek;
import christmas.common.constants.MenuAndPrice;
import java.util.HashMap;
import java.util.List;

public class Benefit {
    private final HashMap<String, Integer> menusAndTotalNumbers;
    private final int date;
    private final int beforeSaleTotalPay;

    public Benefit(int date, HashMap<String, Integer> menusAndTotalNumbers, int beforeSaleTotalPay) {
        dayOfWeekSale(dayOfWeek(date));

        this.date = date;
        this.menusAndTotalNumbers = menusAndTotalNumbers;
        this.beforeSaleTotalPay = beforeSaleTotalPay;
    }

    private String dayOfWeek(int date) {
        List<String> dayOfWeekNames = DayOfWeek.getAllDayOfWeek().stream().map(DayOfWeek::getWeek).toList();
        return dayOfWeekNames.get(date % 7 - 1);
    }

    public String dayOfWeekSale(String dayOfWeek) {
        if (dayOfWeek.equals(DayOfWeek.FRIDAY.getWeek()) || dayOfWeek.equals(DayOfWeek.SATURDAY.getWeek())) {
            return "주말 할인: -" + weekendSale();
        }
        return "평일 할인: -" + weekdaySale();
    }

    public int weekdaySale() {
        int price = 0;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            addWeekdaySale(order, price);
        }
        return price;
    }

    private void addWeekdaySale(HashMap.Entry<String, Integer> order, int price) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getDesserts()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += 2023;
            }
        }
    }

    private int weekendSale() {
        int price = 0;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            addWeekendSale(order, price);
        }
        return price;
    }

    private void addWeekendSale(HashMap.Entry<String, Integer> order, int price) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getMains()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += 2023;
            }
        }
    }

    private int checkChristmasSale() {
        if (date >= 1 && date <= 25) {
            return 1000 + (date - 1) * 100;
        }
        return 0;
    }
}
