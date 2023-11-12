package christmas.domain;

import christmas.common.constants.MenuAndPrice;
import christmas.common.constants.Symbol;
import christmas.common.utils.Utils;
import christmas.common.validate.MenuAndTotalNumberValidate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuAndTotalNumber {
    private final HashMap<String, Integer> menusAndTotalNumbers;
    private int menusTotalPay;

    private HashMap<String, Integer> tempMenusAndTotalNumbers = new HashMap<String, Integer>();;
    private List<String> menus = new ArrayList<>();
    private int prices = 0;

    private static final int FIRST_INDEX_NUMBER = 0;
    private static final int SECOND_INDEX_NUMBER = 1;
    private static final int PRESENT_MENU_BASE_PAY = 120_000;
    private static final int PRESENT_MENU_COUNT = 1;
    private static final String NOTHING = "없음";
    private static final String SPACE = " ";
    private static final String COUNT_UNIT = "개";


    public MenuAndTotalNumber(String MenusAndTotalNumbersTemp) {
        initVariables();
        inputMenusAndTotalNumbers(MenusAndTotalNumbersTemp);
        this.menusAndTotalNumbers = tempMenusAndTotalNumbers;
    }

    public void outputOrderMenuAndTotalNumber() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            System.out.println(order.getKey() + SPACE + order.getValue() + COUNT_UNIT);
        }
        System.out.println();
    }

    public int beforeSaleTotalPay() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            addBeforeSaleTotalPay(order.getKey(), order.getValue());
        }
        return menusTotalPay;
    }

    private void addBeforeSaleTotalPay(String menu, int menuConut) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getAllMenus()) {
            if (menu.equals(menuAndPrice.getMenu())) {
                int menuTotalPay = menuAndPrice.getPrice() * menuConut;
                menusTotalPay += menuTotalPay;
            }
        }
    }

    public String presentMenu() {
        if (menusTotalPay >= PRESENT_MENU_BASE_PAY) {
            return MenuAndPrice.DRINK_3.getMenu() + SPACE + PRESENT_MENU_COUNT + COUNT_UNIT;
        }
        return NOTHING;
    }

    private void initVariables() {
        tempMenusAndTotalNumbers.clear();
        menus.clear();
        prices = 0;
    }

    private HashMap<String, Integer> inputMenusAndTotalNumbers(String menusAndTotalNumbers) {
        validateMenusAndTotalNumbers(menusAndTotalNumbers);
        List<String> splitMenusAndTotalNumbers = Utils.splitInList(menusAndTotalNumbers, Symbol.COMMA.getStringSymbol());
        splitMenusAndTotalNumbers.forEach(MenuAndTotalNumber -> {
            putInMapMenusAndTotalNumbers(MenuAndTotalNumber);
        });
        validateMenus(menus);
        MenuAndTotalNumberValidate.overPriceSum(prices);
        return tempMenusAndTotalNumbers;
    }

    private void putInMapMenusAndTotalNumbers(String menuAndTotalNumber) { // 자르기
        validateMenuAndTotalNumber(menuAndTotalNumber);
        List<String> splitMenuAndTotalNumber = Utils.splitInList(menuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
        validateMenu(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        validateTotalNumber(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menus.add(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        prices += Integer.parseInt(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        tempMenusAndTotalNumbers.put(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitMenuAndTotalNumber.get(1)));
    }

    // 예외 처리 메서드
    private void validateMenusAndTotalNumbers(String menusAndTotalNumbers) {
        MenuAndTotalNumberValidate.inBlank(menusAndTotalNumbers);
        MenuAndTotalNumberValidate.notInCharacter(menusAndTotalNumbers);
    }

    private void validateMenus(List<String> menu) {
        MenuAndTotalNumberValidate.overlapMenu(menu);
        MenuAndTotalNumberValidate.onlyDrink(menu);
    }

    private void validateMenuAndTotalNumber(String menuAndTotalNumber) {
        MenuAndTotalNumberValidate.inBlank(menuAndTotalNumber);
        MenuAndTotalNumberValidate.menuBlank(menuAndTotalNumber);
    }

    private void validateMenu(String menu) {
        MenuAndTotalNumberValidate.inBlank(menu);
        MenuAndTotalNumberValidate.isNotInMenuList(menu);
    }

    private void validateTotalNumber(String totalNumber) {
        MenuAndTotalNumberValidate.inBlank(totalNumber);
        MenuAndTotalNumberValidate.notNumber(totalNumber);
        MenuAndTotalNumberValidate.notRangeNumber(Integer.parseInt(totalNumber));
    }

}
