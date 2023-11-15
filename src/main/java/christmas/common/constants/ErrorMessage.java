package christmas.common.constants;

public enum ErrorMessage {
    INVALID_DATE("유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    INVALID_ORDER("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    private final String message;
    private static final String FRONT_WORD = "[ERROR] ";

    ErrorMessage(String message) {
        this.message = FRONT_WORD + message;
    }

    public String getMessage() {
        return message;
    }
}
