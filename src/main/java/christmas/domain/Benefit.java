package christmas.domain;

import christmas.common.constants.DayOfWeek;
import christmas.common.constants.Menu;
import christmas.common.utils.Utils;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;

public class Benefit {
    private final HashMap<String, Integer> menusAndTotalNumbers;
    private final int date;
    private final int beforeSaleTotalPay;
    private final String dayOfWeek;

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private int totalBenefitPrice = 0;

    public Benefit(int date, HashMap<String, Integer> menusAndTotalNumbers, int beforeSaleTotalPay) {
        this.date = date;
        this.menusAndTotalNumbers = menusAndTotalNumbers;
        this.beforeSaleTotalPay = beforeSaleTotalPay;
        this.dayOfWeek = dayOfWeek(date);
    }

    public int getTotalBenefitPrice(){
        return totalBenefitPrice;
    }

    public String christmasSale() {
        int christmasSale = checkChristmasSale();
        if (date > 25 || christmasSale == 0) {
            return "";
        }
        return "크리스마스 디데이 할인: " + Utils.minusDecformat(christmasSale) + "원" + LINE_SEPARATOR;
    }

    public String dayOfWeekSale() {
        if (dayOfWeek.equals(DayOfWeek.FRIDAY.getWeek()) || dayOfWeek.equals(DayOfWeek.SATURDAY.getWeek())) {
            return weekendSale();
        }
        return weekdaySale();
    }

    public String specialSale() {
        if (dayOfWeek.equals("일") || date == 25) {
            totalBenefitPrice += 1_000;
            return "특별 할인: " + Utils.minusDecformat(1_000) + "원" + LINE_SEPARATOR;
        }
        return "";
    }

    public String presentEvent() {
        if (beforeSaleTotalPay >= 120_000) {
            totalBenefitPrice += Menu.DRINK_3.getPrice();
            return "증정 이벤트: " + Utils.minusDecformat(Menu.DRINK_3.getPrice()) + "원" + LINE_SEPARATOR;
        }
        return "";
    }

    public String allBenefitPay() {
        return Utils.decFormat(totalBenefitPrice) + "원" + LINE_SEPARATOR;
    }

    public String afterSalePay() {
        int afterSalePay = beforeSaleTotalPay - totalBenefitPrice;
        return Utils.decFormat(afterSalePay) + "원" + LINE_SEPARATOR;
    }

    // 크리스마스 할인 관련 메서드
    private int checkChristmasSale() {
        if (date >= 1 && date <= 25) {
            totalBenefitPrice += discountByDate();
            return discountByDate();
        }
        return 0;
    }

    private int discountByDate() {
        return 1000 + (date - 1) * 100;
    }

    // 평일, 주말 할인 관련 메서드
    private String weekdaySale() {
        int price = checkWeekdaySale();
        if (price == 0) {
            return "";
        }
        totalBenefitPrice += price;
        return "평일 할인: " + Utils.minusDecformat(price) + "원" + LINE_SEPARATOR;
    }

    private String weekendSale() {
        int price = checkWeekendSale();
        if (price == 0) {
            return "";
        }
        totalBenefitPrice += price;
        return "주말 할인: " + Utils.minusDecformat(price) + "원" + LINE_SEPARATOR;
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
        for (Menu menuAndPrice : Menu.getDesserts()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += (2023 * order.getValue());
            }
        }
        return price;
    }

    private int addWeekendSale(HashMap.Entry<String, Integer> order, int price) {
        for (Menu menuAndPrice : Menu.getMains()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += 2023;
            }
        }
        return price;
    }

    // 요일 계산
    private String dayOfWeek(int date) {
        List<String> dayOfWeekNames = DayOfWeek.getAllDayOfWeek().stream().map(DayOfWeek::getWeek).toList();
        return dayOfWeekNames.get(date % 7 - 1);
    }
}
