package christmas.common.constants;

public enum OutputMessage {
    DATE_PREVIEW("에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    ORDER_MENU_AND_TOTAL("<주문 메뉴>"),
    BEFORE_SALE_PAY("<할인 전 총주문 금액>"),
    PRESENT_MENU("<증정 메뉴>"),
    PRESENT_MENU_MORE_NEED_PAY(" 더 결제하면 " + Menu.DRINK_3.getMenu() + " 증정!"),
    BENEFIT_DETAILS("<혜택 내역>"),
    ALL_BENEFIT_PAY("<총혜택 금액>"),
    BENEFIT_RATE("<예상 혜택률>"),
    BENEFIT_RATE_MORE_NEED_PAY(" 더 결제하면 예상 혜택률은 "),
    AFTER_SALE_PAY("<할인 후 예상 결제 금액>"),
    EVENT_BEDGE("<12월 이벤트 배지>");

    private final String message;

    OutputMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
