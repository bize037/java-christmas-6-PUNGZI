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
        this.date = date;
        this.menusAndTotalNumbers = menusAndTotalNumbers;
        this.beforeSaleTotalPay = beforeSaleTotalPay;
    }

    private int checkChristmasSale() {
        if (date >= 1 && date <= 25) {
            return 1000 + (date - 1) * 100;
        }
        return 0;
    }
}
