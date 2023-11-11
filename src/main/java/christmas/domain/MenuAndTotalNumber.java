package christmas.domain;

import christmas.common.constants.Symbol;
import christmas.common.utils.Utils;
import christmas.common.validate.MenuAndTotalNumberValidate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuAndTotalNumber {
    private final HashMap<String, Integer> MenusAndTotalNumbers;

    private HashMap<String, Integer> temporaryMenusAndTotalNumbers = new HashMap<String, Integer>();;
    private List<String> menus = new ArrayList<>();
    private int prices = 0;
    private static final int FIRST_INDEX_NUMBER = 0;
    private static final int SECOND_INDEX_NUMBER = 1;

    public MenuAndTotalNumber(String menusAndTotalNumbers) {
        initVariables();
        inputMenusAndTotalNumbers(menusAndTotalNumbers);
        this.MenusAndTotalNumbers = temporaryMenusAndTotalNumbers;
    }

    private void initVariables() {
        temporaryMenusAndTotalNumbers.clear();
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
        return temporaryMenusAndTotalNumbers;
    }

    private void putInMapMenusAndTotalNumbers(String menuAndTotalNumber) {
        validateMenuAndTotalNumber(menuAndTotalNumber);
        List<String> splitMenuAndTotalNumber = Utils.splitInList(menuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
        validateMenu(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        validateTotalNumber(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menus.add(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        prices += Integer.parseInt(splitMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        temporaryMenusAndTotalNumbers.put(splitMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitMenuAndTotalNumber.get(1)));
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
