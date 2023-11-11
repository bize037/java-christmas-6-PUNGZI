package christmas.controller;

import christmas.domain.MenuAndTotalNumber;
import christmas.domain.VisitDate;
import christmas.view.InputView;

public class EventController {
    InputView inputView = new InputView();

    public void startEvent() {
        inputView.inputVisitDateMessage();
        inputVisitDate();
        inputView.inputOrderMessage();
        inputOrder();
    }

    private void inputVisitDate() {
        try {
            new VisitDate(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputVisitDate();
        }
    }

    private void inputOrder() {
        try {
            new MenuAndTotalNumber(inputView.inputConsole());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            inputOrder();
        }
    }
}
