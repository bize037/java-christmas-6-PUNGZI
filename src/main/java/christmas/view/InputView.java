package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.common.constants.InputMessage;

public class InputView {
    public String inputConsole() {
        return Console.readLine();
    }

    public void inputVisitDateMessage() {
        System.out.println(InputMessage.INTRODUCE.getMessage());
        System.out.println(InputMessage.WHEN_VISIT_DATE.getMessage());
    }

    public void inputOrderMessage() {
        System.out.println(InputMessage.WHAT_ORDER.getMessage());
    }
}
