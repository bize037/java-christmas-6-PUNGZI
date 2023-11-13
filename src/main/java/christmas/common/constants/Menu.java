package christmas.common.constants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Menu {
    APPETIZER_1("양송이수프", 6_000),
    APPETIZER_2("타파스", 5_500),
    APPETIZER_3("시저샐러드", 8_000),

    MAIN_1("티본스테이크", 55_000),
    MAIN_2("바비큐립", 54_000),
    MAIN_3("해산물파스타", 35_000),
    MAIN_4("크리스마스파스타", 25_000),

    DESSERT_1("초코케이크", 15_000),
    DESSERT_2("아이스크림", 5_000),

    DRINK_1("제로콜라", 3_000),
    DRINK_2("레드와인", 60_000),
    DRINK_3("샴페인", 25_000);

    private final String menu;
    private final int price;

    Menu(String menu, int price) {
        this.menu = menu;
        this.price = price;
    }

    public String getMenu() {
        return menu;
    }

    public int getPrice() {
        return price;
    }

    public static List<Menu> getAllMenus() {
        return Arrays.asList(Menu.values());
    }

    public static List<Menu> getDesserts() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().startsWith("DESSERT"))
                .collect(Collectors.toList());
    }

    public static List<Menu> getMains() {
        return Arrays.stream(Menu.values())
                .filter(menu -> menu.name().startsWith("MAIN"))
                .collect(Collectors.toList());
    }
}
