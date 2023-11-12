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
    private int menusAllPay;

    private HashMap<String, Integer> tempMenusAndTotalNumbers = new HashMap<String, Integer>();;
    private List<String> menus = new ArrayList<>();
    private int prices = 0;

    private static final int FIRST_INDEX_NUMBER = 0;
    private static final int SECOND_INDEX_NUMBER = 1;
    private static final int PRESENT_MENU_BASE_PAY = 120_000;
    private static final String PRESENT_MENU_MESSAGE = " 증정";
    private static final String NOTHING = "없음";

    public MenuAndTotalNumber(String MenusAndTotalNumbersTemp) {
        initVariables();
        inputMenusAndTotalNumbers(MenusAndTotalNumbersTemp);
        this.menusAndTotalNumbers = tempMenusAndTotalNumbers;
    }

    public void outputOrderMenuAndTotalNumber() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            System.out.println(order.getKey() + " " + order.getValue() + "개");
        }
        System.out.println();
    }

    public int beforeSaleAllPay() {
        for (HashMap.Entry<String, Integer> order : menusAndTotalNumbers.entrySet()) {
            addBeforeSaleAllPay(order.getKey());
        }
        return menusAllPay;
    }

    private void addBeforeSaleAllPay(String menu) {
        for (MenuAndPrice menuAndPrice : MenuAndPrice.getAllMenus()) {
            if (menu.equals(menuAndPrice.getMenu())) {
                menusAllPay += menuAndPrice.getPrice();
            }
        }
    }

    public String presentMenu() {
        if (menusAllPay >= PRESENT_MENU_BASE_PAY) {
            return MenuAndPrice.DRINK_3.getMenu() + PRESENT_MENU_MESSAGE;
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
        MenuAndTotalNumberValidate.overlapMenu(menus);
        MenuAndTotalNumberValidate.overPriceSum(prices);
        return tempMenusAndTotalNumbers;
    }

    private void putInMapMenusAndTotalNumbers(String menuAndTotalNumber) {
        validateMenuAndTotalNumber(menuAndTotalNumber);
        List<String> splitMenuAndTotalNumber = Utils.splitInList(menuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
        validateMenu(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        validateTotalNumber(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menus.add(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        prices += Integer.parseInt(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        tempMenusAndTotalNumbers.put(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitMenuAndTotalNumber.get(1)));
    }

    private void validateMenusAndTotalNumbers(String menusAndTotalNumbers) {
        MenuAndTotalNumberValidate.inBlank(menusAndTotalNumbers);
        MenuAndTotalNumberValidate.notInCharacter(menusAndTotalNumbers);
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
