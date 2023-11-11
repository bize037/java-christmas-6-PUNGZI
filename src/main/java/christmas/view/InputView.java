package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.constants.InputMessage;
import christmas.common.validate.DateValidate;

public class InputView {
    public void visitDate() {
        System.out.println(InputMessage.INTRODUCE.getMessage());
        System.out.println(InputMessage.WHEN_VISIT_DATE.getMessage());
        inputVisitDate();
    }

    private void inputVisitDate() {
        try {
            String inputVisitDate = Console.readLine();
            visitDateValidate(inputVisitDate);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputVisitDate();
        }
    }

    private void visitDateValidate(String visitDate) {
        DateValidate.inBlank(visitDate);
        DateValidate.notNumber(visitDate);
        DateValidate.notRangeNumber(Integer.parseInt(visitDate));
    }
}
