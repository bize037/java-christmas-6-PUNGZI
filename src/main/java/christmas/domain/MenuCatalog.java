package christmas.domain;

import christmas.common.constants.Menu;
import christmas.common.constants.Symbol;
import christmas.common.utils.Utils;
import christmas.common.validate.MenuAndTotalNumberValidate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuCatalog {
    private final HashMap<String, Integer> menusAndTotalNumbers;

    private HashMap<String, Integer> tempMenusAndTotalNumbers = new HashMap<String, Integer>();
    private List<String> menus = new ArrayList<>();
    private int menusTotalPay;
    private int price = 0;

    private static final int FIRST_INDEX_NUMBER = 0;
    private static final int SECOND_INDEX_NUMBER = 1;
    private static final int PRESENT_MENU_BASE_PAY = 120_000;
    private static final int PRESENT_MENU_COUNT = 1;
    private static final String NOTHING = "없음";
    private static final String SPACE = " ";
    private static final String COUNT_UNIT = "개";

    public MenuCatalog(String MenusAndTotalNumbersTemp) {
        initVariables();
        inputMenusAndTotalNumbers(MenusAndTotalNumbersTemp);
        this.menusAndTotalNumbers = tempMenusAndTotalNumbers;
    }

    public HashMap<String, Integer> getMenusAndTotalNumbers() {
        return menusAndTotalNumbers;
    }

    public void outputOrderMenuAndTotalNumber() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            System.out.println(order.getKey() + SPACE + order.getValue() + COUNT_UNIT);
        }
        System.out.println();
    }

    public int getMenusTotalPay() {
        return menusTotalPay;
    }

    public int beforeSaleTotalPay() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            addBeforeSaleTotalPay(order.getKey(), order.getValue());
        }
        return menusTotalPay;
    }

    private void addBeforeSaleTotalPay(String menu, int menuConut) {
        for (Menu menuAndPrice : Menu.getAllMenus()) {
            if (menu.equals(menuAndPrice.getMenu())) {
                int menuTotalPay = menuAndPrice.getPrice() * menuConut;
                menusTotalPay += menuTotalPay;
            }
        }
    }

    public String presentMenu() {
        if (menusTotalPay >= PRESENT_MENU_BASE_PAY) {
            return Menu.DRINK_3.getMenu() + SPACE + PRESENT_MENU_COUNT + COUNT_UNIT;
        }
        return NOTHING;
    }

    private void initVariables() {
        tempMenusAndTotalNumbers.clear();
        menus.clear();
        price = 0;
    }

    private HashMap<String, Integer> inputMenusAndTotalNumbers(String menusAndTotalNumbers) {
        List<String> splitMenusAndTotalNumbers = putInListMenusAndTotalNumbers(menusAndTotalNumbers);
        splitMenusAndTotalNumbers.forEach(this::putInMapMenusAndTotalNumbers);
        validateMenusAndPrices(menus);
        return tempMenusAndTotalNumbers;
    }

    private List<String> putInListMenusAndTotalNumbers(String menusAndTotalNumbers) {
        validateMenusAndTotalNumbers(menusAndTotalNumbers);
        return Utils.splitInList(menusAndTotalNumbers, Symbol.COMMA.getStringSymbol());
    }

    private void putInMapMenusAndTotalNumbers(String menuAndTotalNumber) {
        List<String> splitMenuAndTotalNumber = putInListMenuAndTotalNumber(menuAndTotalNumber);
        addAndValidateSplitMenuAndTotalNumber(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        tempMenusAndTotalNumbers.put(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitMenuAndTotalNumber.get(1)));
    }

    private List<String> putInListMenuAndTotalNumber(String menuAndTotalNumber) {
        validateMenuAndTotalNumber(menuAndTotalNumber);
        return Utils.splitInList(menuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
    }

    private void addAndValidateSplitMenuAndTotalNumber(String menu, String totalNumber) {
        validateSplitMenuAndTotalNumber(menu, totalNumber);
        menus.add(menu);
        price += Integer.parseInt(totalNumber);
    }

    // 예외 처리 메서드
    private void validateMenusAndPrices(List<String> menu) {
        MenuAndTotalNumberValidate.overlapMenu(menu);
        MenuAndTotalNumberValidate.onlyDrink(menu);
        MenuAndTotalNumberValidate.overPriceSum(price);
    }

    private void validateMenusAndTotalNumbers(String menusAndTotalNumbers) {
        MenuAndTotalNumberValidate.inBlank(menusAndTotalNumbers);
        MenuAndTotalNumberValidate.notInCharacter(menusAndTotalNumbers);
    }

    private void validateSplitMenuAndTotalNumber(String menu, String totalNumber) {
        validateMenu(menu);
        validateTotalNumber(totalNumber);
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
