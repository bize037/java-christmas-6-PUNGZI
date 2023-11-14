package christmas.common.validate;

import christmas.common.constants.Menu;
import christmas.common.constants.Symbol;
import java.util.ArrayList;
import java.util.List;

public class MenuCatalogValidate {
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String DRINK = "DRINK";
    public static final String DIGITS_PATTERN = "\\d+";
    private static final int RANGE_MIN_NUMBER = 1;
    private static final int RANGE_MAX_NUMBER = 20;
    private static final int ADD_COUNT = 1;
    private static final int ZERO = 0;

    public static void inBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void menuBlank(String input) {  // "-,-" 입력시 예외처리
        if (input.startsWith(Symbol.HYPHEN.getStringSymbol())) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void notNumber(String input) {
        if (!input.matches(DIGITS_PATTERN)) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void notRangeNumber(int input) {
        if (input < RANGE_MIN_NUMBER || input > RANGE_MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void notInCharacter(String input) {
        int commaCount = commaCount(input);
        int hyphenCount = hyphenCount(input);
        if (commaCount + ADD_COUNT != hyphenCount) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static int commaCount(String input) {
        int count = ZERO;
        for (int i = ZERO; i < input.length(); i++) {
            if (input.charAt(i) == Symbol.COMMA.getCharSymbol()) {
                count++;
            }
        }
        return count;
    }

    private static int hyphenCount(String input) {
        int count = ZERO;
        for (int i = ZERO; i < input.length(); i++) {
            if (input.charAt(i) == Symbol.HYPHEN.getCharSymbol()) {
                count++;
            }
        }
        return count;
    }

    public static boolean isNotInMenuList(String input) {
        for (Menu menu : Menu.getAllMenus()) {
            if (input.equals(menu.getMenu())) {
                return true;
            }
        }
        throw new IllegalArgumentException(ERROR_MESSAGE);
    }

    public static void overlapMenu(List<String> menus) {
        if(menus.size() != menus.stream().distinct().count()){
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void overPriceSum(int input) {
        if(input > RANGE_MAX_NUMBER) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    public static void onlyDrink(List<String> menus) {
        menus.removeAll(onlyDrinks());
        if (menus.isEmpty()) {
            throw new IllegalArgumentException(ERROR_MESSAGE);
        }
    }

    private static List<String> onlyDrinks() {
        List<String> drinkMenus = new ArrayList<>();
        for (Menu menu : Menu.values()) {
            if (menu.name().startsWith(DRINK)) {
                drinkMenus.add(menu.getMenu());
            }
        }
        return drinkMenus;
    }
}
