package christmas.view;

import christmas.common.constants.OutputMessage;
import java.text.DecimalFormat;

public class OutputView {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    DecimalFormat decFormat = new DecimalFormat("###,###");

    public void outputDatePreviewMessage(String date) {
        System.out.println(date + OutputMessage.DATE_PREVIEW.getMessage() + LINE_SEPARATOR);
    }

    public void orderMenuAndTotalNumberMessage() {
        System.out.println(OutputMessage.ORDER_MENU_AND_TOTAL.getMessage());
    }

    public void outputBeforeSaleAllPay(int beforeSaleAllPay) {
        System.out.println(OutputMessage.BEFORE_SALE_PAY.getMessage());
        System.out.println(decFormat.format(beforeSaleAllPay) + "원" + LINE_SEPARATOR);
    }

    public void outputPresentMenu(String presentMenu) {
        System.out.println(OutputMessage.PRESENT_MENU.getMessage());
        System.out.println(presentMenu + LINE_SEPARATOR);
    }

    public void benefitDetailsMessage(int salePrice) {
        System.out.println(OutputMessage.BENEFIT_DETAILS.getMessage());
        noSalePrice(salePrice);
    }

    private void noSalePrice(int salePrice) {
        if (salePrice == 0) {
            System.out.println("없음");
        }
    }

    public void allBenefitPayMessage() {
        System.out.println(OutputMessage.ALL_BENEFIT_PAY.getMessage());
    }

    public void afterSalePayMessage() {
        System.out.println(OutputMessage.AFTER_SALE_PAY.getMessage());
    }

    public void eventBadgeMessage() {
        System.out.println(OutputMessage.EVENT_BEDGE.getMessage());
    }
}
