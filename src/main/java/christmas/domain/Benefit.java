package christmas.domain;

import christmas.common.constants.DayOfWeek;
import christmas.common.constants.Menu;
import christmas.common.utils.Utils;
import java.util.HashMap;
import java.util.List;

public class Benefit {
    private final HashMap<String, Integer> menusAndTotalNumbers;
    private final int date;
    private final int beforeSaleTotalPay;
    private final String dayOfWeek;

    private static final int DAY_OF_WEEK_SALE_PRICE = 2_023;
    private static final int SPECIAL_SALE_PRICE = 1_000;
    private static final int CHRISTMAS_SALE_MIN_TIME = 1;
    private static final int CHRISTMAS_SALE_MAX_TIME = 25;
    private static final int CHRISTMAS_SALE_START_PRICE = 1_000;
    private static final int CHRISTMAS_SALE_UNIT = 100;
    private static final int PRESENT_EVENT_CRITERIA = 120_000;
    private static final int ZERO_PRICE = 0;
    private static final int A_WEEK = 7;
    private static final int ONE_DAY = 1;

    private static final String CHRISTMAS_SALE = "크리스마스 디데이 할인: ";
    private static final String SPECIAL_SALE = "특별 할인: ";
    private static final String WEEKDAY_SALE = "평일 할인: ";
    private static final String WEEKEND_SALE = "주말 할인: ";
    private static final String PRESENT_EVENT = "증정 이벤트: ";
    private static final String NOTHING_STRING = "";

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

    public String printChristmasSale() {
        int christmasSale = checkChristmasSale();
        if (christmasSale == ZERO_PRICE) {
            return NOTHING_STRING;
        }
        return CHRISTMAS_SALE + Utils.minusDecformat(christmasSale) + "원" + LINE_SEPARATOR;
    }

    public String printDayOfWeekSale() {
        if (dayOfWeek.equals(DayOfWeek.FRIDAY.getWeek()) || dayOfWeek.equals(DayOfWeek.SATURDAY.getWeek())) {
            return weekendSale();
        }
        return weekdaySale();
    }

    public String printSpecialSale() {
        if (dayOfWeek.equals(DayOfWeek.SUNDAY.getWeek()) || date == 25) {
            totalBenefitPrice += SPECIAL_SALE_PRICE;
            return SPECIAL_SALE + Utils.minusDecformat(SPECIAL_SALE_PRICE) + "원" + LINE_SEPARATOR;
        }
        return NOTHING_STRING;
    }

    public String printPresentEvent() {
        if (beforeSaleTotalPay >= PRESENT_EVENT_CRITERIA) {
            totalBenefitPrice += Menu.DRINK_3.getPrice();
            return PRESENT_EVENT + Utils.minusDecformat(Menu.DRINK_3.getPrice()) + "원" + LINE_SEPARATOR;
        }
        return NOTHING_STRING;
    }

    public String printAllBenefitPay() {
        return Utils.decFormat(totalBenefitPrice) + "원" + LINE_SEPARATOR;
    }

    public String printAfterSalePay() {
        int afterSalePay = beforeSaleTotalPay - totalBenefitPrice;
        return Utils.decFormat(afterSalePay) + "원" + LINE_SEPARATOR;
    }

    // 크리스마스 할인 관련 메서드
    private int checkChristmasSale() {
        if (date >= CHRISTMAS_SALE_MIN_TIME && date <= CHRISTMAS_SALE_MAX_TIME) {
            totalBenefitPrice += discountByDate();
            return discountByDate();
        }
        return ZERO_PRICE;
    }

    private int discountByDate() {
        return CHRISTMAS_SALE_START_PRICE + (date - ONE_DAY) * CHRISTMAS_SALE_UNIT;
    }

    // 평일, 주말 할인 관련 메서드
    private String weekdaySale() {
        int price = checkWeekdaySale();
        if (price == ZERO_PRICE) {
            return NOTHING_STRING;
        }
        totalBenefitPrice += price;
        return WEEKDAY_SALE + Utils.minusDecformat(price) + "원" + LINE_SEPARATOR;
    }

    private String weekendSale() {
        int price = checkWeekendSale();
        if (price == ZERO_PRICE) {
            return NOTHING_STRING;
        }
        totalBenefitPrice += price;
        return WEEKEND_SALE + Utils.minusDecformat(price) + "원" + LINE_SEPARATOR;
    }

    private int checkWeekendSale() {
        int price = ZERO_PRICE;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            price = addWeekendSale(order, price);
        }
        return price;
    }

    private int checkWeekdaySale() {
        int price = ZERO_PRICE;
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            price = addWeekdaySale(order, price);
        }
        return price;
    }

    private int addWeekdaySale(HashMap.Entry<String, Integer> order, int price) {
        for (Menu menuAndPrice : Menu.getDesserts()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += (DAY_OF_WEEK_SALE_PRICE * order.getValue());
            }
        }
        return price;
    }

    private int addWeekendSale(HashMap.Entry<String, Integer> order, int price) {
        for (Menu menuAndPrice : Menu.getMains()) {
            if (menuAndPrice.getMenu().equals(order.getKey())) {
                price += DAY_OF_WEEK_SALE_PRICE;
            }
        }
        return price;
    }

    // 요일 계산
    private String dayOfWeek(int date) {
        List<String> dayOfWeekNames = DayOfWeek.getAllDayOfWeek().stream().map(DayOfWeek::getWeek).toList();
        return dayOfWeekNames.get(date % A_WEEK - ONE_DAY);
    }
}
