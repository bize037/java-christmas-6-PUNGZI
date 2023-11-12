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
}