package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.constants.InputMessage;
import christmas.common.constants.Symbol;
import christmas.common.utils.Utils;
import christmas.common.validate.DateValidate;
import christmas.common.validate.OrderValidate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InputView {
    private HashMap<String, Integer> menusAndTotalNumbers = new HashMap<String, Integer>();
    private List<String> menus = new ArrayList<>();
    private int prices = 0;
    private static final int FIRST_INDEX_NUMBER = 0;
    private static final int SECOND_INDEX_NUMBER = 1;

    public int inputVisitDate() {
        System.out.println(InputMessage.INTRODUCE.getMessage());
        System.out.println(InputMessage.WHEN_VISIT_DATE.getMessage());
        return tryInputVisitDate();
    }

    public HashMap<String, Integer> inputOrder() {
        System.out.println(InputMessage.WHAT_ORDER.getMessage());
        tryInputOrder();
        return menusAndTotalNumbers;
    }

    private int tryInputVisitDate() {
        try {
            String inputVisitDate = Console.readLine();
            validateDate(inputVisitDate);
            return Integer.parseInt(inputVisitDate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            tryInputVisitDate();
        }
        return 0;
    }

    private void tryInputOrder() {
        try {
            inputOrderMenusAndTotalNumbers(Console.readLine());
        } catch (IllegalArgumentException e) {
            menusAndTotalNumbers.clear();
            menus.clear();
            prices = 0;
            System.out.println(e.getMessage());
            tryInputOrder();
        }
    }

    private HashMap<String, Integer> inputOrderMenusAndTotalNumbers(String orderMenusAndTotalNumbers) {
        validateOrderMenusAndTotalNumbers(orderMenusAndTotalNumbers);
        List<String> splitOrderMenusAndTotalNumbers = Utils.splitInList(orderMenusAndTotalNumbers, Symbol.COMMA.getStringSymbol());
        splitOrderMenusAndTotalNumbers.forEach(orderMenuAndTotalNumber -> {
            putInMapOrderMenusAndTotalNumbers(orderMenuAndTotalNumber);
        });
        OrderValidate.overlapMenu(menus);
        OrderValidate.overPriceSum(prices);
        return menusAndTotalNumbers;
    }

    private void putInMapOrderMenusAndTotalNumbers(String orderMenuAndTotalNumber) {
        validateOrderMenuAndTotalNumber(orderMenuAndTotalNumber);
        List<String> splitOrderMenuAndTotalNumber = Utils.splitInList(orderMenuAndTotalNumber, Symbol.HYPHEN.getStringSymbol());
        validateOrderMenu(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        validateTotalNumber(splitOrderMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menus.add(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER));
        prices += Integer.parseInt(splitOrderMenuAndTotalNumber.get(SECOND_INDEX_NUMBER));
        menusAndTotalNumbers.put(splitOrderMenuAndTotalNumber.get(FIRST_INDEX_NUMBER), Integer.parseInt(splitOrderMenuAndTotalNumber.get(1)));
    }

    private void validateDate(String visitDate) {
        DateValidate.inBlank(visitDate);
        DateValidate.notNumber(visitDate);
        DateValidate.notRangeNumber(Integer.parseInt(visitDate));
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
