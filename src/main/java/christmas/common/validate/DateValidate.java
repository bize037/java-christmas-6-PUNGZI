package christmas.common.validate;

public class DateValidate {
    private static final String ERROR_MESSAGE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String DIGITS_PATTERN = "\\d+";
    private static final int RANGE_MIN_NUMBER = 1;
    private static final int RANGE_MAX_NUMBER = 31;

    public static void inBlank(String input) {
        if (input.isBlank()) {
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
}
