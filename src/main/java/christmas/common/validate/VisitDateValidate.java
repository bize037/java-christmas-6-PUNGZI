package christmas.common.validate;

import christmas.common.constants.ErrorMessage;

public class VisitDateValidate {
    public static final String DIGITS_PATTERN = "\\d+";
    private static final int RANGE_MIN_NUMBER = 1;
    private static final int RANGE_MAX_NUMBER = 31;

    public static void inBlank(String input) {
        if (input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static void notNumber(String input) {
        if (!input.matches(DIGITS_PATTERN)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static void notRangeNumber(int input) {
        if (input < RANGE_MIN_NUMBER || input > RANGE_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
