package christmas.domain;

import christmas.common.constants.Symbol;
import christmas.common.utils.Utils;
import christmas.common.validate.OrderValidate;
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

    public MenuAndTotalNumber(String orderMenusAndTotalNumbers) {
        temporaryMenusAndTotalNumbers.clear();
        menus.clear();
        prices = 0;
        inputOrderMenusAndTotalNumbers(orderMenusAndTotalNumbers);
        this.MenusAndTotalNumbers = temporaryMenusAndTotalNumbers;
    }

    private HashMap<String, Integer> inputOrderMenusAndTotalNumbers(String orderMenusAndTotalNumbers) {
        validateOrderMenusAndTotalNumbers(orderMenusAndTotalNumbers);
        List<String> splitOrderMenusAndTotalNumbers = Utils.splitInList(orderMenusAndTotalNumbers, Symbol.COMMA.getStringSymbol());
        splitOrderMenusAndTotalNumbers.forEach(orderMenuAndTotalNumber -> {
            putInMapOrderMenusAndTotalNumbers(orderMenuAndTotalNumber);
        });
        OrderValidate.overlapMenu(menus);
        OrderValidate.overPriceSum(prices);
        return temporaryMenusAndTotalNumbers;
    }

    private void putInMapOrderMenusAndTotalNumbers(String orderMenuAndTotalNumber) {
        validateOrderMenuAndTotalNumber(orderMenuAndTotalNumber);
        List<String> splitOrderMenuAndTotalNumber = Utils.splitInList(orderMenuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
        validateOrderMenu(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        validateTotalNumber(splitOrderMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menus.add(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        prices += Integer.parseInt(splitOrderMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        temporaryMenusAndTotalNumbers.put(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitOrderMenuAndTotalNumber.get(1)));
    }

    private void validateOrderMenusAndTotalNumbers(String orderMenusAndTotalNumbers) {
        OrderValidate.inBlank(orderMenusAndTotalNumbers);
        OrderValidate.notInCharacter(orderMenusAndTotalNumbers);
    }

    private void validateOrderMenuAndTotalNumber(String orderMenuAndTotalNumber) {
        OrderValidate.inBlank(orderMenuAndTotalNumber);
        OrderValidate.menuBlank(orderMenuAndTotalNumber);
    }

    private void validateOrderMenu(String orderMenu) {
        OrderValidate.inBlank(orderMenu);
        OrderValidate.isNotInMenuList(orderMenu);
    }

    private void validateTotalNumber(String totalNumber) {
        OrderValidate.inBlank(totalNumber);
        OrderValidate.notNumber(totalNumber);
        OrderValidate.notRangeNumber(Integer.parseInt(totalNumber));
    }

}
