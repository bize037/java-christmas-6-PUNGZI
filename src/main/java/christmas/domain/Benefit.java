package christmas.domain;

import christmas.common.constants.DayOfWeek;
import christmas.common.constants.MenuAndPrice;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class Benefit {
    private final HashMap<String, Integer> menusAndTotalNumbers;
    private final int date;
    private final int beforeSaleTotalPay;
    private final String dayOfWeek;

    private static final String LINE_SEPARATOR = System.lineSeparator();

    DecimalFormat decFormat = new DecimalFormat("###,###");
    private int totalBenefitPrice = 0;

    public Benefit(int date, HashMap<String, Integer> menusAndTotalNumbers, int beforeSaleTotalPay) {
        this.date = date;
        this.menusAndTotalNumbers = menusAndTotalNumbers;
        this.beforeSaleTotalPay = beforeSaleTotalPay;
        this.dayOfWeek = dayOfWeek(date);
    }

    // 크리스마스 할인
    public String christmasSale() {
        if (date > 25 || checkChristmasSale() == 0) {
            return "";
        }
        return "크리스마스 디데이 할인: -" + decFormat.format(checkChristmasSale()) + "원" + LINE_SEPARATOR;
    }

    // 평일, 주말 할인
    public String dayOfWeekSale() {
        if (dayOfWeek.equals(DayOfWeek.FRIDAY.getWeek()) || dayOfWeek.equals(DayOfWeek.SATURDAY.getWeek())) {
            return weekendSale();
        }
        return weekdaySale();
    }

    // 특별 할인
    public String specialSale() {
        if (dayOfWeek.equals("일") || date == 25) {
            totalBenefitPrice = 1000;
            return "특별 할인: -" + 1000 + "원" + LINE_SEPARATOR;
        }
        return "";
    }

    // 증정 할인
    public String presentEvent() {
        if (beforeSaleTotalPay >= 120_000) {
            return "증정 이벤트: -" + MenuAndPrice.DRINK_3.getPrice() + "원" + LINE_SEPARATOR;
        }
        return "";
    }

    // 크리스마스 할인 관련 메서드
    private int checkChristmasSale() {
        if (date >= 1 && date <= 25) {
            return 1000 + (date - 1) * 100;
        }
        return 0;
    }

    // 평일, 주말 할인 관련 메서드
    private String weekdaySale() {
        int price = checkWeekdaySale();
        if (price == 0) {
            return "";
        }
        totalBenefitPrice += price;
        return "평일 할인: -" + decFormat.format(price) + "원" + LINE_SEPARATOR;
    }

    private String weekendSale() {
        int price = checkWeekendSale();
        if (price == 0) {
            return "";
        }
        totalBenefitPrice += price;
        return "주말 할인: -" + decFormat.format(price) + "원" + LINE_SEPARATOR;
    }

    private int checkWeekendSale() {
        int price = 0;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            price = addWeekendSale(order, price);
        }
        return price;
    }

    private int checkWeekdaySale() {
        int price = 0;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            price = addWeekdaySale(order, price);
        }
        return price;
    }

    private int addWeekdaySale(HashMap.Entry<String, Integer> order, int price) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getDesserts()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += (2023 * order.getValue());
            }
        }
        return price;
    }

    private int addWeekendSale(HashMap.Entry<String, Integer> order, int price) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getMains()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += 2023;
            }
        }
        return price;
    }

    private String dayOfWeek(int date) {
        List<String> dayOfWeekNames = DayOfWeek.getAllDayOfWeek().stream().map(DayOfWeek::getWeek).toList();
        return dayOfWeekNames.get(date % 7 - 1);
    }
}
